package fr.univtours.s6_projet.repository;

import fr.univtours.s6_projet.entity.PanierCategory;
import fr.univtours.s6_projet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.List;

@RepositoryRestResource
public interface PanierCategoryRepository extends JpaRepository<PanierCategory, Long> {

	List<PanierCategory> findByIdIn(Collection<Long> id);
}
