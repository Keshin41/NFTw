package fr.univtours.s6_projet.controller;

import fr.univtours.s6_projet.entity.Panier;
import fr.univtours.s6_projet.entity.PanierOrder;
import fr.univtours.s6_projet.entity.User;
import fr.univtours.s6_projet.repository.PanierOrderRepository;
import fr.univtours.s6_projet.repository.PanierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class OrderController {

	@Autowired
	private PanierRepository panierRepository;

	@Autowired
	private PanierOrderRepository panierOrderRepository;



	@GetMapping("/order/{id}")
	public String orderPanier(@PathVariable(value = "id") Long id) {

		Optional<Panier> optionalPanier = panierRepository.findById(id);
		if (optionalPanier.isEmpty()) return "redirect:/paniers";
		Panier panier = optionalPanier.get();

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		PanierOrder order = new PanierOrder(user, panier);
		user.getOrders().add(order);
		panierOrderRepository.saveAndFlush(order);
		return "redirect:/account/orders";
	}
}
