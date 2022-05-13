package fr.univtours.s6_projet.type;

import fr.univtours.s6_projet.entity.CommercantCategory;

import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

public class SearchCommercantForm {

	private String keyword;

	private List<CommercantCategory> categories = new ArrayList<>();

	private String adresse;

	private String departement;

	private String ville;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<CommercantCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<CommercantCategory> categories) {
		this.categories = categories;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

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
}
