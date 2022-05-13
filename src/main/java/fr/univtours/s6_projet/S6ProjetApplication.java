package fr.univtours.s6_projet;

import fr.univtours.s6_projet.entity.*;
import fr.univtours.s6_projet.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class S6ProjetApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(S6ProjetApplication.class, args);
	}

}
