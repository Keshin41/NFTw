package fr.univtours.s6_projet.controller;

import fr.univtours.s6_projet.entity.PanierOrder;
import fr.univtours.s6_projet.entity.Status;
import fr.univtours.s6_projet.entity.User;
import fr.univtours.s6_projet.repository.PanierOrderRepository;
import fr.univtours.s6_projet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class AccountController {

	@Autowired
	private PanierOrderRepository panierOrderRepository;
	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/account/orders")
	public String showOrders(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		for (PanierOrder order : user.getOrders()) {
			if (order.getStatus() != Status.EXPIRED && order.getStatus() != Status.DELIVERED) {
				if (order.getPanier().getValidUntil().before(new Date())) {
					order.setStatus(Status.EXPIRED);
					panierOrderRepository.saveAndFlush(order);
				}
			}
		}

		model.addAttribute("orders", user.getOrders());
		return "account/orders";
	}

	@RequestMapping("/account/orders/confirm/{id}")
	public String confirmOrder(@PathVariable("id") Long id) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<PanierOrder> panierOrders = user.getOrders();
		Optional<PanierOrder> orderOptional = panierOrders.stream().filter(panierOrder -> Objects.equals(panierOrder.getId(), id)).findFirst();
		if (orderOptional.isPresent() && orderOptional.get().getStatus() == Status.PRECONFIRMED) {
			PanierOrder order = orderOptional.get();
			order.setStatus(Status.CONFIRMED);
			panierOrderRepository.saveAndFlush(order);
		}
		return "redirect:/account/orders";
	}

	@RequestMapping("/account/orders/delivered/{id}")
	public String deliveredOrder(@PathVariable("id") Long id) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<PanierOrder> panierOrders = user.getOrders();
		Optional<PanierOrder> orderOptional = panierOrders.stream().filter(panierOrder -> Objects.equals(panierOrder.getId(), id)).findFirst();
		if (orderOptional.isPresent() && orderOptional.get().getStatus() == Status.CONFIRMED) {
			PanierOrder order = orderOptional.get();
			order.setStatus(Status.DELIVERED);
			panierOrderRepository.saveAndFlush(order);
		}
		return "redirect:/account/orders";
	}

	@RequestMapping("/account/orders/delete/{id}")
	public String deleteOrder(@PathVariable("id") Long id) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<PanierOrder> panierOrders = user.getOrders();
		Optional<PanierOrder> order = panierOrders.stream().filter(panierOrder -> Objects.equals(panierOrder.getId(), id)).findFirst();
		if (order.isPresent()) {
			panierOrders.remove(order.get());
			panierOrderRepository.delete(order.get());
			userRepository.saveAndFlush(user);
		}
		return "redirect:/account/orders";
	}
}
