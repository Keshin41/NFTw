package fr.univtours.s6_projet.type;

import fr.univtours.s6_projet.entity.Commercant;
import fr.univtours.s6_projet.entity.Panier;
import fr.univtours.s6_projet.entity.PanierCategory;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class PanierForm {

	@NotNull
	private Commercant commercant;

	@NotNull
	private PanierCategory category;

	private String description;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date validUntil;

	public PanierCategory getCategory() {
		return category;
	}

	public void setCategory(PanierCategory category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Commercant getCommercant() {
		return commercant;
	}

	public void setCommercant(Commercant commercant) {
		this.commercant = commercant;
	}

	public Date getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}
}
