package istia.st.springmvc.models;

import org.springframework.stereotype.Component;

@Component
public class Listes {

	private String[] deplacements = new String[] { "0", "1", "2", "3", "4" };
	private String[] libellesDeplacements = new String[] { "vélo", "marche", "train", "avion", "autre" };
	private String[] libellesBijoux = new String[] { "émeraude", "rubis", "diamant", "opaline" };

	// getters et setters
	public String[] getLibellesDeplacements() {
		return libellesDeplacements;
	}

	public void setLibellesDeplacements(String[] libellesDeplacements) {
		this.libellesDeplacements = libellesDeplacements;
	}

	public String[] getDeplacements() {
		return deplacements;
	}

	public void setDeplacements(String[] deplacements) {
		this.deplacements = deplacements;
	}

	public String[] getLibellesBijoux() {
		return libellesBijoux;
	}

	public void setLibellesBijoux(String[] libellesBijoux) {
		this.libellesBijoux = libellesBijoux;
	}

}
