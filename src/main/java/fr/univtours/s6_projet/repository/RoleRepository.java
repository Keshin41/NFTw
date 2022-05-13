package fr.univtours.s6_projet.repository;

import fr.univtours.s6_projet.entity.PanierCategory;
import fr.univtours.s6_projet.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);

}
