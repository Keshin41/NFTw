package fr.univtours.s6_projet.repository;

import fr.univtours.s6_projet.entity.CommercantCategory;
import fr.univtours.s6_projet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CommercantCategoryRepository extends JpaRepository<CommercantCategory, Long> {

}
