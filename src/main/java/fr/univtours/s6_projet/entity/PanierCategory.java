package fr.univtours.s6_projet.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PanierCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(targetEntity = Panier.class, mappedBy = "category")
	private List<Panier> paniers = new ArrayList<>();

	@Column(nullable = false)
	private String name;

	public PanierCategory() {}

	public PanierCategory(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Panier> getPaniers() {
		return paniers;
	}

	public void setPaniers(List<Panier> paniers) {
		this.paniers = paniers;
	}
}
