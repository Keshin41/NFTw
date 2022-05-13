package fr.univtours.s6_projet.controller.admin;

import fr.univtours.s6_projet.entity.Panier;
import fr.univtours.s6_projet.entity.PanierOrder;
import fr.univtours.s6_projet.entity.Status;
import fr.univtours.s6_projet.repository.CommercantRepository;
import fr.univtours.s6_projet.repository.PanierCategoryRepository;
import fr.univtours.s6_projet.repository.PanierOrderRepository;
import fr.univtours.s6_projet.repository.PanierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import javax.xml.ws.Service;
import java.util.Date;
import java.util.List;

@Controller
public class PanierAdminController {

	@Autowired
	private PanierRepository panierRepository;

	@Autowired
	private PanierOrderRepository panierOrderRepository;

	@Autowired
	private PanierCategoryRepository panierCategoryRepository;

	@Autowired
	private CommercantRepository commercantRepository;

	@RequestMapping("/admin/panier")
	public String index(Model model) {

		List<Panier> paniers = panierRepository.findAll();

		for (Panier panier : paniers) {
			PanierOrder order = panier.getOrder();
			if (order != null) {
				if (order.getStatus() != Status.EXPIRED && order.getStatus() != Status.DELIVERED) {
					if (order.getPanier().getValidUntil().before(new Date())) {
						order.setStatus(Status.EXPIRED);
						panierOrderRepository.saveAndFlush(order);
					}
				}
			}
		}

		model.addAttribute("listPaniers", paniers);
		return "/admin/panier/index";
	}

	@GetMapping("/admin/panier/new")
	public String showNewForm(Model model) {
		model.addAttribute("panier", new Panier());
		model.addAttribute("categories", panierCategoryRepository.findAll());
		model.addAttribute("commercants", commercantRepository.findAll());
		return "/admin/panier/form";
	}

	@PostMapping("/admin/panier/save")
	public String savePanier(Panier panier) {
		panierRepository.saveAndFlush(panier);
		return "redirect:/admin/panier";
	}

	@GetMapping("/admin/panier/edit/{id}")
	public String showEditForm(@PathVariable("id") Long id, Model model) {
		Panier panier = panierRepository.findById(id).get();
		model.addAttribute("panier", panier);
		model.addAttribute("categories", panierCategoryRepository.findAll());
		model.addAttribute("commercants", commercantRepository.findAll());
		return "/admin/panier/form";
	}

	@GetMapping("/admin/panier/delete/{id}")
	public String deletePanier(@PathVariable("id") Long id, Model model) {
		panierRepository.deleteById(id);
		return "redirect:/admin/panier";
	}
}
