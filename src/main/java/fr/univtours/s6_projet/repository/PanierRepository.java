package fr.univtours.s6_projet.repository;

import fr.univtours.s6_projet.entity.Commercant;
import fr.univtours.s6_projet.entity.Panier;
import fr.univtours.s6_projet.entity.PanierCategory;
import fr.univtours.s6_projet.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@RepositoryRestResource
public interface PanierRepository extends JpaRepository<Panier, Long> {

	List<Panier> findByValidUntilGreaterThanEqualAndOrderIsNull(Date validUntil);

	List<Panier> findByCommercant_VilleContainingIgnoreCaseAndCommercant_DepartementContainingAndValidUntilGreaterThanEqualAndOrderIsNull(String commercant_ville, String commercant_departement, Date validUntil);

	List<Panier> findByCommercant_VilleContainingIgnoreCaseAndCommercant_DepartementContainingAndValidUntilGreaterThanEqualAndCategoryInAndCommercantAndOrderIsNull(String commercant_ville, String commercant_departement, Date validUntil, Collection<PanierCategory> category, Commercant commercant);

	List<Panier> findByCommercant_VilleContainingIgnoreCaseAndCommercant_DepartementContainingIgnoreCaseAndValidUntilGreaterThanEqualAndCategoryInAndOrderIsNull(String commercant_ville, String commercant_departement, Date validUntil, Collection<PanierCategory> category);

	List<Panier> findByCommercant_VilleContainingIgnoreCaseAndCommercant_DepartementContainingIgnoreCaseAndValidUntilGreaterThanEqualAndCommercantAndOrderIsNull(String commercant_ville, String commercant_departement, Date validUntil, Commercant commercant);


	@Query("select count(p) from Panier p where (p.order.status = ?1) or (p.order is null and p.validUntil < ?2)")
	Long countByWasted(Status order_status, Date validUntil);

	@Query("select count(p) from Panier p where p.order is not null and p.order.status = ?1")
	Long countByDevilered(Status order_status);

	Long countByOrder_Status(Status order_status);

	Long countByOrderIsNullAndValidUntilBefore(Date validUntil);

	@Query("select count(p) from Panier p where p.order is not null and p.order.status = ?1 and p.order.createdAt >= ?2")
	Long countByDeliveredSince(Status order_status, Date since);
}
