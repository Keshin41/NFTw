package fr.univtours.s6_projet.repository;

import fr.univtours.s6_projet.entity.Commercant;
import fr.univtours.s6_projet.entity.CommercantCategory;
import fr.univtours.s6_projet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface CommercantRepository extends JpaRepository<Commercant, Long> {


	List<Commercant> findByNameContainingIgnoreCaseAndDepartementIgnoreCaseAndVilleContainingIgnoreCaseAndAddresseContainingIgnoreCase(String name, String departement, String ville, String addresse);

	List<Commercant> findByNameContainingIgnoreCaseAndDepartementIgnoreCaseAndVilleContainingIgnoreCaseAndAddresseContainingIgnoreCaseAndCategoryIn(String name, String departement, String ville, String addresse, Collection<CommercantCategory> category);
}
