package fr.univtours.s6_projet.controller.admin;

import fr.univtours.s6_projet.entity.CommercantCategory;
import fr.univtours.s6_projet.entity.Panier;
import fr.univtours.s6_projet.entity.PanierCategory;
import fr.univtours.s6_projet.repository.CommercantCategoryRepository;
import fr.univtours.s6_projet.repository.PanierCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommercantCategoryAdminController {

	@Autowired
	CommercantCategoryRepository commercantCategoryRepository;


	@GetMapping("/admin/commercant/category")
	public String index(Model model) {
		model.addAttribute("listCommercantCategories", commercantCategoryRepository.findAll());
		return "/admin/commercant_category/index";
	}

	@GetMapping("/admin/commercant/category/new")
	public String showNewForm(Model model) {
		model.addAttribute("commercantCategory", new PanierCategory());
		return "/admin/commercant_category/form";
	}

	@PostMapping("/admin/commercant/category/save")
	public String saveCategory(CommercantCategory category) {
		commercantCategoryRepository.saveAndFlush(category);
		return "redirect:/admin/commercant/category";
	}

	@GetMapping("/admin/commercant/category/edit/{id}")
	public String showEditForm(@PathVariable("id") Long id, Model model) {
		CommercantCategory category = commercantCategoryRepository.findById(id).get();
		model.addAttribute("commercantCategory", category);
		return "/admin/commercant_category/form";
	}

	@GetMapping("/admin/commercant/category/delete/{id}")
	public String deleteCategory(@PathVariable("id") Long id, Model model) {
		commercantCategoryRepository.deleteById(id);
		return "redirect:/admin/commercant/category";
	}
}
