package fr.univtours.s6_projet.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Panier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Commercant commercant;

	public void setCommercant(Commercant commercant) {
		this.commercant = commercant;
	}

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private PanierCategory category;

	@OneToOne(mappedBy = "panier")
	private PanierOrder order;

	private String description;

	private double prix;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date createdAt;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false)
	private Date validUntil;

	public Panier(Commercant commercant, PanierCategory category, String description, Date validUntil) {
		this();
		this.commercant = commercant;
		this.category = category;
		this.description = description;
		this.validUntil = validUntil;
	}

	public Panier() {
		this.createdAt = new Date();
	}

	public PanierCategory getCategory() {
		return category;
	}

	public void setCategory(PanierCategory category) {
		this.category = category;
	}

	public Commercant getCommercant() {
		return commercant;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}

	public PanierOrder getOrder() {
		return order;
	}

	public void setOrder(PanierOrder order) {
		this.order = order;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
}
