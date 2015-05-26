package istia.st.springmvc.models;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

@Component
public class ApplicationModel {

	// compteur
	private AtomicLong compteur = new AtomicLong(0);

	// getters et setters
	public AtomicLong getCompteur() {
		return compteur;
	}

	public void setCompteur(AtomicLong compteur) {
		this.compteur = compteur;
	}

}
