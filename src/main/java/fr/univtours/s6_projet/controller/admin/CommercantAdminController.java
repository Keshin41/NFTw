package fr.univtours.s6_projet.controller.admin;

import fr.univtours.s6_projet.entity.Commercant;
import fr.univtours.s6_projet.repository.CommercantCategoryRepository;
import fr.univtours.s6_projet.repository.CommercantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommercantAdminController {

	@Autowired
	private CommercantCategoryRepository commercantCategoryRepository;

	@Autowired
	private CommercantRepository commercantRepository;

	@RequestMapping("/admin/commercant")
	public String index(Model model) {
		model.addAttribute("listCommercants", commercantRepository.findAll());
		return "/admin/commercant/index";
	}

	@GetMapping("/admin/commercant/new")
	public String showNewForm(Model model) {
		model.addAttribute("commercant", new Commercant());
		model.addAttribute("categories", commercantCategoryRepository.findAll());
		return "/admin/commercant/form";
	}

	@PostMapping("/admin/commercant/save")
	public String saveCommercant(Commercant commercant) {
		commercantRepository.saveAndFlush(commercant);
		return "redirect:/admin/commercant";
	}

	@GetMapping("/admin/commercant/edit/{id}")
	public String showEditForm(@PathVariable("id") Long id, Model model) {
		Commercant commercant = commercantRepository.findById(id).get();
		model.addAttribute("commercant", commercant);
		model.addAttribute("categories", commercantCategoryRepository.findAll());
		return "/admin/commercant/form";
	}

	@GetMapping("/admin/commercant/delete/{id}")
	public String deleteCommercant(@PathVariable("id") Long id, Model model) {
		commercantRepository.deleteById(id);
		return "redirect:/admin/commercant";
	}
}
