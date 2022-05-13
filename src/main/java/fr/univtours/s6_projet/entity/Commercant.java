package fr.univtours.s6_projet.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
public class Commercant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private CommercantCategory category;

	@OneToMany(mappedBy = "commercant", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Panier> paniers = new ArrayList<>();

	@Column(nullable = false)
	private String name;

	private String description;

	@Column(nullable = false)
	private String departement;

	@Column(nullable = false)
	private String ville;

	@Column(nullable = false)
	private String addresse;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date createdAt;

	public Commercant() {
		this.createdAt = new Date();
	}

	public Commercant(String name, CommercantCategory category, String departement, String ville, String addresse) {
		this();
		this.name = name;
		this.category = category;
		this.departement = departement;
		this.ville = ville;
		this.addresse = addresse;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getAddresse() {
		return addresse;
	}

	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public List<Panier> getPaniers() {
		return paniers;
	}

	public void setPaniers(List<Panier> paniers) {
		this.paniers = paniers;
	}

	public CommercantCategory getCategory() {
		return category;
	}

	public void setCategory(CommercantCategory category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return name;
	}
}
