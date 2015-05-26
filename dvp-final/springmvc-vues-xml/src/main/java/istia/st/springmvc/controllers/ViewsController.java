package istia.st.springmvc.controllers;

import istia.st.springmvc.models.Form19;
import istia.st.springmvc.models.Form21;
import istia.st.springmvc.models.Listes;
import istia.st.springmvc.models.Personne;
import istia.st.springmvc.models.SecuredPerson;
import istia.st.springmvc.models.StringSecuredPerson;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@SessionAttributes({ "jean", "paul" })
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

	// création du modèle M d'une vue V - 1
	@RequestMapping(value = "/v04", method = RequestMethod.GET)
	public String v04(Model model) {
		model.addAttribute("personne", new Personne(7, "martin", 17));
		System.out.println(String.format("Modèle=%s", model));
		return "vue-04";
	}

	// création du modèle M d'une vue V - 2
	@RequestMapping(value = "/v05", method = RequestMethod.GET)
	public String v05(Model model) {
		model.addAttribute("personne", new Personne(7, "martin", 17));
		return "vue-05";
	}

	// création du modèle M d'une vue V - 3
	@RequestMapping(value = "/v06", method = RequestMethod.GET)
	public String v06(Model model) {
		model.addAttribute("personne", new Personne(7, "martin", 17));
		return "vue-06";
	}

	// création du modèle M d'une vue V - 4
	@RequestMapping(value = "/v07", method = RequestMethod.GET)
	public String v07(Model model) {
		model.addAttribute("liste", new Personne[] { new Personne(7, "martin", 17), new Personne(8, "lucie", 32),
				new Personne(9, "paul", 7) });
		return "vue-07";
	}

	// --------------- Binding et ModelAttribute ----------------------------------

	// si le paramètre est un objet, il est instancié et éventuellement modifié par les paramètres de la requête
	// il fera automatiquement partie du modèle de la vue avec la clé [key]
	// pour @ModelAttribute("xx") paramètre, key sera égal à xx
	// pour @ModelAttribute paramètre, key sera égal au nom de la classe du paramètre commençant avec une minuscule
	// si @ModelAttribute est absent, alors tout se passe comme s'il était présent sans clé
	// on notera que cette présence automatique dans le modèle n'est pas effectuée si le paramètre n'est pas un objet

	@RequestMapping(value = "/v08", method = RequestMethod.GET)
	public String v08(@ModelAttribute("someone") Personne p, Model model) {
		System.out.println(String.format("Modèle=%s", model));
		return "vue-08";
	}

	@RequestMapping(value = "/v09", method = RequestMethod.GET)
	public String v09(Personne p, Model model) {
		System.out.println(String.format("Modèle=%s", model));
		return "vue-09";
	}

	@ModelAttribute("uneAutrePersonne")
	private Personne getPersonne() {
		return new Personne(24, "pauline", 55);
	}

	@RequestMapping(value = "/v10", method = RequestMethod.GET)
	public String v10(Model model) {
		System.out.println(String.format("Modèle=%s", model));
		return "vue-10";
	}

	@ModelAttribute("jean")
	private Personne getJean() {
		return new Personne(33, "jean", 10);
	}

	@RequestMapping(value = "/v11", method = RequestMethod.GET)
	public String v11(Model model, HttpSession session) {
		System.out.println(String.format("Modèle=%s, Session[jean]=%s", model, session.getAttribute("jean")));
		return "vue-11";
	}

	@RequestMapping(value = "/v12a", method = RequestMethod.GET)
	@ResponseBody
	public void v12a(HttpSession session) {
		session.setAttribute("paul", new Personne(51, "paul", 33));
	}

	// cas où la clé de [@ModelAttribute] est également une clé de [@SessionAttributes]
	// dans ce cas, le paramètre correspondant est initialisé avec la valeur de la session
	@RequestMapping(value = "/v12b", method = RequestMethod.GET)
	public String v12b(Model model, @ModelAttribute("paul") Personne p) {
		System.out.println(String.format("Modèle=%s", model));
		return "vue-12";
	}

	// génère un formulaire pour saisir une personne
	@RequestMapping(value = "/v13", method = RequestMethod.GET)
	public String v13() {
		return "vue-13";
	}

	// traite les valeurs du formulaire
	@RequestMapping(value = "/v14", method = RequestMethod.POST)
	public String v14(Personne p) {
		return "vue-14";
	}

	// ---------------------- affichage d'un formulaire
	@RequestMapping(value = "/v15", method = RequestMethod.GET)
	public String v15(SecuredPerson p) {
		return "vue-15";
	}

	// -------------------- validation d'un modèle------------------
	@RequestMapping(value = "/v16", method = RequestMethod.POST)
	public String v16(@Valid SecuredPerson p, BindingResult result) {
		// erreurs ?
		if (result.hasErrors()) {
			return "vue-15";
		} else {
			return "vue-16";
		}
	}

	// ---------------------- affichage d'un formulaire
	@RequestMapping(value = "/v17", method = RequestMethod.GET)
	public String v17(StringSecuredPerson p) {
		return "vue-17";
	}

	// -------------------- validation d'un modèle------------------
	@RequestMapping(value = "/v18", method = RequestMethod.POST)
	public String v18(@Valid StringSecuredPerson p, BindingResult result) {
		// erreurs ?
		if (result.hasErrors()) {
			return "vue-17";
		} else {
			return "vue-18";
		}
	}

	// ------------------ affichage d'un formulaire
	@RequestMapping(value = "/v19", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
	public String v19(Form19 formulaire) {
		return "vue-19";
	}

	// ----------------- validation du modèle du formulaire
	@RequestMapping(value = "/v20", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	public String v20(@Valid Form19 formulaire, BindingResult result, RedirectAttributes redirectAttributes,
			HttpSession session) {
		if (result.hasErrors()) {
			return "vue-19";
		} else {
			// redirection vers [vue-19]
			redirectAttributes.addFlashAttribute("form19", formulaire);
			return "redirect:/v19.html";
		}
	}

	// ------------------ formulaire avec boutons radio
	@Autowired
	private Listes listes;

	@RequestMapping(value = "/v21", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
	public String v21(@ModelAttribute("form") Form21 formulaire, Model model) {
		model.addAttribute("listes", listes);
		return "vue-21";
	}

	@RequestMapping(value = "/v22", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	public String v22(@ModelAttribute("form") Form21 formulaire, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("form", formulaire);
		return "redirect:/v21.html";
	}

	// ------------------ formulaire avec cases à cocher
	@RequestMapping(value = "/v23", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
	public String av20(@ModelAttribute("form") Form21 formulaire, Model model) {
		model.addAttribute("listes", listes);
		return "vue-23";
	}

	// mappeur Jackson / jSON
	private ObjectMapper mapper = new ObjectMapper();

	@RequestMapping(value = "/v24", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	public String v24(@ModelAttribute("form") Form21 formulaire, RedirectAttributes redirectAttributes)
			throws JsonProcessingException {
		redirectAttributes.addFlashAttribute("form", formulaire);
		formulaire.setStrCouleurs(mapper.writeValueAsString(formulaire.getCouleurs()));
		formulaire.setStrBijoux(mapper.writeValueAsString(formulaire.getBijoux()));
		return "redirect:/v23.html";
	}
  // ------------------ formulaire avec listes
  @RequestMapping(value = "/v25", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
  public String v25(@ModelAttribute("form") Form21 formulaire, Model model) {
		model.addAttribute("listes", listes);
		return "vue-25";
  }

  @RequestMapping(value = "/v26", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
  public String v26(@ModelAttribute("form") Form21 formulaire, RedirectAttributes redirectAttributes) throws JsonProcessingException {
    redirectAttributes.addFlashAttribute("form", formulaire);
    formulaire.setStrBijoux2(mapper.writeValueAsString(formulaire.getBijoux2()));
    return "redirect:/v25.html";
  }

  // ------------------ messages paramétrés
  @RequestMapping(value = "/v27", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
  public String v27(Model model) {
		model.addAttribute("param1","paramètre un");
		model.addAttribute("param2","paramètre deux");
		model.addAttribute("param3","paramètre trois");
		model.addAttribute("param4","messages.param4");		
		return "vue-27";
  }

}
