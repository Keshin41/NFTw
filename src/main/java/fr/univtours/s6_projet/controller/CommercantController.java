package fr.univtours.s6_projet.controller;

import fr.univtours.s6_projet.entity.Commercant;
import fr.univtours.s6_projet.entity.CommercantCategory;
import fr.univtours.s6_projet.entity.Panier;
import fr.univtours.s6_projet.repository.CommercantCategoryRepository;
import fr.univtours.s6_projet.repository.CommercantRepository;
import fr.univtours.s6_projet.type.SearchCommercantForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CommercantController {

	@Autowired
	private CommercantRepository commercantRepository;

	@Autowired
	private CommercantCategoryRepository commercantCategoryRepository;

	@GetMapping("/commercants")
	public String index(Model model) {
		model.addAttribute("searchCommercantForm", new SearchCommercantForm());
		model.addAttribute("commercants", commercantRepository.findAll());
		model.addAttribute("categoryList", commercantCategoryRepository.findAll());
		return "commercant/index";
	}

	@PostMapping("/commercants")
	public String search(@Valid SearchCommercantForm searchCommercantForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) return "redirect:/";

		String keyword = searchCommercantForm.getKeyword();
		List<CommercantCategory> categories = searchCommercantForm.getCategories();
		String departement = searchCommercantForm.getDepartement();
		String ville = searchCommercantForm.getVille();
		String adresse = searchCommercantForm.getAdresse();

		List<Commercant> commercants;

		if (categories.isEmpty()) {
			commercants = commercantRepository.findByNameContainingIgnoreCaseAndDepartementIgnoreCaseAndVilleContainingIgnoreCaseAndAddresseContainingIgnoreCase(keyword, departement, ville, adresse);
		} else {
			commercants = commercantRepository.findByNameContainingIgnoreCaseAndDepartementIgnoreCaseAndVilleContainingIgnoreCaseAndAddresseContainingIgnoreCaseAndCategoryIn(keyword, departement, ville, adresse, categories);
		}

		model.addAttribute("commercants", commercants);
		model.addAttribute("categoryList", commercantCategoryRepository.findAll());

		return "commercant/index";
	}

	@GetMapping("/commercants/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		Optional<Commercant> optionalCommercant = commercantRepository.findById(id);
		if (optionalCommercant.isEmpty()) return "redirect:/commercants";
		model.addAttribute("commercant", optionalCommercant.get());
		return "commercant/show";
	}
}
