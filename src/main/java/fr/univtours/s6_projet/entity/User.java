package fr.univtours.s6_projet.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
public class User implements UserDetails {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String firstname;
	@Column(nullable = false)
	private String lastname;
	@Column(nullable = false)
	private String password;

	@Column(nullable = false, unique = true)
	private String email;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date createdAt;

	@OneToMany(targetEntity = PanierOrder.class, mappedBy = "user")
	private List<PanierOrder> panierOrders = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

	public Role getRole() {
		return role;
	}

	public User() {this.createdAt = new Date();}

	public User(Role role, String firstname, String lastname, String email, String password) {
		this();
		this.role = role;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role.getName()));
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public List<PanierOrder> getOrders() {
		return panierOrders;
	}

	public void setOrders(List<PanierOrder> panierOrders) {
		this.panierOrders = panierOrders;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return getFirstname() + " " + getLastname();
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
