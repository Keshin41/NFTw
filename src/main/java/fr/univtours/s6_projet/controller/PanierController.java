package fr.univtours.s6_projet.controller;

import fr.univtours.s6_projet.entity.Commercant;
import fr.univtours.s6_projet.entity.Panier;
import fr.univtours.s6_projet.entity.PanierCategory;
import fr.univtours.s6_projet.repository.CommercantRepository;
import fr.univtours.s6_projet.repository.PanierCategoryRepository;
import fr.univtours.s6_projet.repository.PanierRepository;
import fr.univtours.s6_projet.type.SearchPanierForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class PanierController {

	@Autowired
	private PanierRepository panierRepository;

	@Autowired
	private PanierCategoryRepository panierCategoryRepository;

	@Autowired
	private CommercantRepository commercantRepository;

	@GetMapping("/paniers")
	public String index(SearchPanierForm searchPanierForm, Model model) {
		model.addAttribute("paniers", panierRepository.findByValidUntilGreaterThanEqualAndOrderIsNull(new Date()));
		model.addAttribute("commercantList", commercantRepository.findAll());
		model.addAttribute("categoryList", panierCategoryRepository.findAll());
		return "panier/index";
	}

	@PostMapping("/paniers")
	public String search(@Valid SearchPanierForm searchPanierForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) return "redirect:/";

		List<Long> categories_id = searchPanierForm.getCategories_id();
		List<PanierCategory> categories = panierCategoryRepository.findByIdIn(categories_id);
		Long commercant_id = searchPanierForm.getCommercant_id();
		Optional<Commercant> optionalCommercant = commercantRepository.findById(commercant_id);
		String ville = searchPanierForm.getVille();
		String departement = searchPanierForm.getDepartement();

		List<Panier> paniers;

		if (!(categories.isEmpty() || optionalCommercant.isEmpty())) {
			paniers = panierRepository.findByCommercant_VilleContainingIgnoreCaseAndCommercant_DepartementContainingAndValidUntilGreaterThanEqualAndCategoryInAndCommercantAndOrderIsNull(ville, departement, new Date(), categories, optionalCommercant.get());
		}
		else {
			if (!categories.isEmpty()) {
				paniers = panierRepository.findByCommercant_VilleContainingIgnoreCaseAndCommercant_DepartementContainingIgnoreCaseAndValidUntilGreaterThanEqualAndCategoryInAndOrderIsNull(ville, departement, new Date(), categories);
			}
			else if (optionalCommercant.isPresent()) {
				paniers = panierRepository.findByCommercant_VilleContainingIgnoreCaseAndCommercant_DepartementContainingIgnoreCaseAndValidUntilGreaterThanEqualAndCommercantAndOrderIsNull(ville, departement, new Date(), optionalCommercant.get());
			}
			else {
				paniers = panierRepository.findByCommercant_VilleContainingIgnoreCaseAndCommercant_DepartementContainingAndValidUntilGreaterThanEqualAndOrderIsNull(ville, departement, new Date());
			}
		}
		model.addAttribute("paniers", paniers);
		model.addAttribute("commercantList", commercantRepository.findAll());
		model.addAttribute("categoryList", panierCategoryRepository.findAll());

		return "panier/index";
	}
}
