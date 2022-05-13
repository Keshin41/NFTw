package fr.univtours.s6_projet.controller.admin;

import fr.univtours.s6_projet.entity.Panier;
import fr.univtours.s6_projet.entity.PanierOrder;
import fr.univtours.s6_projet.entity.Status;
import fr.univtours.s6_projet.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
public class OrderAdminController {

	@Autowired
	private PanierOrderRepository panierOrderRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PanierRepository panierRepository;

	@RequestMapping("/admin/order")
	public String index(Model model) {

		List<PanierOrder> orders = panierOrderRepository.findAll();

		for (PanierOrder order : orders) {
			if (order.getStatus() != Status.EXPIRED && order.getStatus() != Status.DELIVERED) {
				if (order.getPanier().getValidUntil().before(new Date())) {
					order.setStatus(Status.EXPIRED);
					panierOrderRepository.saveAndFlush(order);
				}
			}
		}

		model.addAttribute("listOrders", orders);
		return "/admin/order/index";
	}

	@GetMapping("/admin/order/new")
	public String showNewForm(Model model) {
		model.addAttribute("order", new PanierOrder());
		model.addAttribute("users", userRepository.findAll());
		model.addAttribute("paniers", panierRepository.findAll());
		return "/admin/order/form";
	}

	@PostMapping("/admin/order/save")
	public String saveOrder(PanierOrder order) {
		panierOrderRepository.saveAndFlush(order);
		return "redirect:/admin/order";
	}

	@GetMapping("/admin/order/edit/{id}")
	public String showEditForm(@PathVariable("id") Long id, Model model) {
		PanierOrder order = panierOrderRepository.findById(id).get();
		model.addAttribute("order", order);
		model.addAttribute("users", userRepository.findAll());
		model.addAttribute("paniers", panierRepository.findAll());
		return "/admin/order/form";
	}

	@GetMapping("/admin/order/delete/{id}")
	public String deleteOrder(@PathVariable("id") Long id, Model model) {
		panierOrderRepository.deleteById(id);
		return "redirect:/admin/order";
	}
}
