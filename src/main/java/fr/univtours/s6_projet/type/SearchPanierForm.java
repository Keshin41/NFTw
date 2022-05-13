package fr.univtours.s6_projet.type;

import fr.univtours.s6_projet.entity.Commercant;
import fr.univtours.s6_projet.entity.PanierCategory;

import java.util.ArrayList;
import java.util.List;

public class SearchPanierForm {

	private List<Long> categories_id = new ArrayList<>();

	private Long commercant_id;

	private String departement;

	private String ville;


	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public List<Long> getCategories_id() {
		return categories_id;
	}

	public void setCategories_id(List<Long> categories_id) {
		this.categories_id = categories_id;
	}

	public Long getCommercant_id() {
		return commercant_id;
	}

	public void setCommercant_id(Long commercant_id) {
		this.commercant_id = commercant_id;
	}
}
