package fr.univtours.s6_projet.repository;

import fr.univtours.s6_projet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import java.util.Date;
import java.util.Optional;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.email = ?1")
	Optional<User> findOneByEmail(String email);

	@Query("select count(u) from User u where u.createdAt >= ?1")
	Long countSince(Date since);
}
