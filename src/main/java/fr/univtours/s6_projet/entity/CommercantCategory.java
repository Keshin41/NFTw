package fr.univtours.s6_projet.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CommercantCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(targetEntity = Commercant.class, mappedBy = "category")
	private List<Commercant> commercants = new ArrayList<>();

	@Column(nullable = false)
	private String name;

	public CommercantCategory() {}

	public CommercantCategory(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Commercant> getCommercants() {
		return commercants;
	}

	public void setCommercants(List<Commercant> commercants) {
		this.commercants = commercants;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
