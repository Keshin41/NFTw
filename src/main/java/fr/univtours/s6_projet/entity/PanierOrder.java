package fr.univtours.s6_projet.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PanierOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(nullable = false)
	private User user;

	@OneToOne
	private Panier panier;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Status status;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date createdAt;

	public PanierOrder(User user, Panier panier) {
		this();
		this.user= user;
		this.panier = panier;
	}

	public PanierOrder() {
		this.createdAt = new Date();
		this.status = Status.PRECONFIRMED;
	}

	public Panier getPanier() {
		return panier;
	}

	public User getUser() {
		return user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
