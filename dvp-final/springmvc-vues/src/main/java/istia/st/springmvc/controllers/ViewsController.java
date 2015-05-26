package istia.st.springmvc.controllers;

import istia.st.springmvc.models.Personne;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewsController {

	// les bases de Thymeleaf - 1
	@RequestMapping(value = "/v01", method = RequestMethod.GET)
	public String v01() {
		return "v01";
	}

	// les bases de Thymeleaf - 2
	@RequestMapping(value = "/v02", method = RequestMethod.GET)
	public String v02() {
		System.out.println("action v02");
		return "vue-02";
	}

	// internationalisation des vues
	@RequestMapping(value = "/v03", method = RequestMethod.GET)
	public String v03() {
		return "vue-03";
	}

	// création du modèle M d'une vue V
	@RequestMapping(value = "/v04", method = RequestMethod.GET)
	public String v04(Model model) {
		model.addAttribute("personne", new Personne(7, "martin", 17));
		System.out.println(String.format("Modèle=%s", model));
		return "vue-04";
	}
}
