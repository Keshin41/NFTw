package fr.univtours.s6_projet.controller.admin;

import fr.univtours.s6_projet.entity.Panier;
import fr.univtours.s6_projet.entity.PanierCategory;
import fr.univtours.s6_projet.repository.PanierCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PanierCategoryAdminController {

	@Autowired
	PanierCategoryRepository panierCategoryRepository;


	@GetMapping("/admin/panier/category")
	public String index(Model model) {
		model.addAttribute("listPanierCategories", panierCategoryRepository.findAll());
		return "/admin/panier_category/index";
	}

	@GetMapping("/admin/panier/category/new")
	public String showNewForm(Model model) {
		model.addAttribute("panierCategory", new PanierCategory());
		return "/admin/panier_category/form";
	}

	@PostMapping("/admin/panier/category/save")
	public String saveCategory(PanierCategory category) {
		panierCategoryRepository.saveAndFlush(category);
		return "redirect:/admin/panier/category";
	}

	@GetMapping("/admin/panier/category/edit/{id}")
	public String showEditForm(@PathVariable("id") Long id, Model model) {
		PanierCategory category = panierCategoryRepository.findById(id).get();
		model.addAttribute("panierCategory", category);
		return "/admin/panier_category/form";
	}

	@GetMapping("/admin/panier/category/delete/{id}")
	public String deleteCategory(@PathVariable("id") Long id, Model model) {
		panierCategoryRepository.deleteById(id);
		return "redirect:/admin/panier/category";
	}
}
