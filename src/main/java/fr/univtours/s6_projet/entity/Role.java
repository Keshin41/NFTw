package fr.univtours.s6_projet.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Role {

	@Transient
	public static final String USER = "USER";
	@Transient
	public static final String ADMIN = "ADMIN";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(targetEntity = User.class, mappedBy = "role")
	private List<User> users = new ArrayList<>();

	@Column(nullable = false)
	private String name;

	public Role() {};

	public Role(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
