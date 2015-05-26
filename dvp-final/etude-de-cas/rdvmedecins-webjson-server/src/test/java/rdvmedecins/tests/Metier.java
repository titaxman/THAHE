package rdvmedecins.tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import rdvmedecins.domain.AgendaMedecinJour;
import rdvmedecins.entities.Client;
import rdvmedecins.entities.Creneau;
import rdvmedecins.entities.Medecin;
import rdvmedecins.entities.Rv;
import rdvmedecins.metier.IMetier;
import rdvmedecins.web.config.AppConfig;

@SpringApplicationConfiguration(classes = AppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class Metier {

	@Autowired
	private IMetier métier;

	@Test
	public void test1() throws ParseException {
		// affichage clients
		List<Client> clients = métier.getAllClients();
		display("Liste des clients :", clients);
		// affichage médecins
		List<Medecin> medecins = métier.getAllMedecins();
		display("Liste des médecins :", medecins);
		// affichage créneaux d'un médecin
		Medecin médecin = medecins.get(0);
		List<Creneau> creneaux = métier.getAllCreneaux(médecin.getId());
		display(String.format("Liste des créneaux du médecin %s", médecin), creneaux);
		// liste des Rv d'un médecin, un jour donné
		Date jour = new SimpleDateFormat("yyyy-MM-dd").parse("2012-06-24");
		display(String.format("Liste des rv du médecin %s, le [%s]", médecin, jour), métier.getRvMedecinJour(médecin.getId(), jour));
		// ajouter un RV
		Rv rv = null;
		Creneau créneau = creneaux.get(2);
		Client client = clients.get(0);
		System.out.println(String.format("Ajout d'un Rv le [%s] dans le créneau %s pour le client %s", jour, créneau, client));
		rv = métier.ajouterRv(jour, créneau, client);
		System.out.println("Rv ajouté");
		display(String.format("Liste des Rv du médecin %s, le [%s]", médecin, jour), métier.getRvMedecinJour(médecin.getId(), jour));
		// ajouter un RV dans le même créneau du même jour
		// doit provoquer une exception
		System.out.println(String.format("Ajout d'un Rv le [%s] dans le créneau %s pour le client %s", jour, créneau, client));
		Boolean erreur = false;
		try {
			rv = métier.ajouterRv(jour, créneau, client);
			System.out.println("Rv ajouté");
		} catch (Exception ex) {
			Throwable th = ex;
			while (th != null) {
				System.out.println(ex.getMessage());
				th = th.getCause();
			}
			// on note l'erreur
			erreur = true;
		}
		// on vérifie qu'il y a eu une erreur
		Assert.assertTrue(erreur);
		// liste des RV
		display(String.format("Liste des Rv du médecin %s, le [%s]", médecin, jour), métier.getRvMedecinJour(médecin.getId(), jour));
		// affichage agenda
		AgendaMedecinJour agenda = métier.getAgendaMedecinJour(médecin.getId(), jour);
		System.out.println(agenda);
		// supprimer un RV
		System.out.println("Suppression du Rv ajouté");
		métier.supprimerRv(rv.getId());
		System.out.println("Rv supprimé");
		display(String.format("Liste des Rv du médecin %s, le [%s]", médecin, jour), métier.getRvMedecinJour(médecin.getId(), jour));
		// affichage agenda
		agenda = métier.getAgendaMedecinJour(médecin.getId(), jour);
		System.out.println(agenda);
	}

	// méthode utilitaire - affiche les éléments d'une collection
	private void display(String message, Iterable<?> elements) {
		System.out.println(message);
		for (Object element : elements) {
			System.out.println(element);
		}
	}

}
