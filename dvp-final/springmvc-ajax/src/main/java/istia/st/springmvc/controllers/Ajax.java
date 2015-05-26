package istia.st.springmvc.controllers;

import istia.st.springmvc.models.ActionModel01;
import istia.st.springmvc.models.JsonResult10;
import istia.st.springmvc.models.JsonResult13;
import istia.st.springmvc.models.JsonResults;
import istia.st.springmvc.models.PostAjax11A;
import istia.st.springmvc.models.PostAjax14;
import istia.st.springmvc.models.Resultats;
import istia.st.springmvc.models.SessionModel1;
import istia.st.springmvc.models.SessionModel2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring4.SpringTemplateEngine;

@Controller
public class Ajax {

	@RequestMapping(value = "/ajax-01", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
	public String ajax01(Locale locale, Model modèle, HttpSession session, String tempo) {
		// tempo valide ?
		if (tempo != null) {
			boolean valide = false;
			int valueTempo = 0;
			try {
				valueTempo = Integer.parseInt(tempo);
				valide = valueTempo >= 0;
			} catch (NumberFormatException e) {

			}
			if (valide) {
				session.setAttribute("tempo", new Integer(valueTempo));
			}
		}
		// on prépare le modèle de la vue [vue-01]
		modèle.addAttribute("actionModel01", new ActionModel01());
		Resultats résultats = new Resultats();
		modèle.addAttribute("resultats", résultats);
		// locale
		setLocale(locale, modèle, résultats);
		// heure
		résultats.setHeureGet(new SimpleDateFormat("hh:mm:ss").format(new Date()));
		// vue
		return "vue-01";
	}

	private void setLocale(Locale locale, Model modèle, Resultats résultats) {
		// on ne gère que les locales fr-FR, en-US
		String language = locale.getLanguage();
		String country = null;
		switch (language) {
		case "fr":
			country = "FR";
			break;
		default:
			language = "en";
			country = "US";
			break;
		}
		// culture
		résultats.setCulture(String.format("%s-%s", language, country));
	}

	@RequestMapping(value = "/ajax-02", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	public String ajax02(@Valid ActionModel01 formulaire, BindingResult result, Locale locale, Model modèle,
			HttpSession session, HttpServletRequest request) throws InterruptedException {
		// requête Ajax ?
		boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
		// tempo ?
		Integer tempo = (Integer) session.getAttribute("tempo");
		if (tempo != null && tempo > 0) {
			Thread.sleep(tempo);
		}
		// on prépare le modèle de la prochaine vue
		Resultats résultats = new Resultats();
		modèle.addAttribute("resultats", résultats);
		// on fixe la locale
		setLocale(locale, modèle, résultats);
		// heure
		String heure = new SimpleDateFormat("hh:mm:ss").format(new Date());
		résultats.setHeurePost(heure);
		résultats.setHeureGet(heure);
		// requête valide ?
		if (!isAjax && result.hasErrors()) {
			return "vue-01";
		}
		// on génère une erreur une fois sur deux
		int val = new Random().nextInt(2);
		if (val == 0) {
			// on renvoie un message d'erreur
			résultats.setErreur("erreur.aleatoire");
			if (isAjax) {
				return "vue-03";
			} else {
				résultats.setVue("vue-03");
				return "vue-01";
			}
		}
		// on récupère les valeurs postées
		double a = formulaire.getA();
		double b = formulaire.getB();
		// on construit le modèle
		résultats.setAplusb(String.valueOf(a + b));
		résultats.setAmoinsb(String.valueOf(a - b));
		résultats.setAmultiplieparb(String.valueOf(a * b));
		try {
			résultats.setAdiviseparb(String.valueOf(a / b));
		} catch (RuntimeException e) {
			résultats.setAdiviseparb("NaN");
		}
		// on affiche la vue
		if (isAjax) {
			return "vue-02";
		} else {
			résultats.setVue("vue-02");
			return "vue-01";
		}
	}

	@RequestMapping(value = "/ajax-04", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
	public String ajax04(Locale locale, Model modèle, HttpSession session, String tempo) {
		// tempo valide ?
		if (tempo != null) {
			boolean valide = false;
			int valueTempo = 0;
			try {
				valueTempo = Integer.parseInt(tempo);
				valide = valueTempo >= 0;
			} catch (NumberFormatException e) {

			}
			if (valide) {
				session.setAttribute("tempo", new Integer(valueTempo));
			}
		}
		// on prépare le modèle de la vue [vue-01]
		modèle.addAttribute("actionModel01", new ActionModel01());
		Resultats résultats = new Resultats();
		modèle.addAttribute("resultats", résultats);
		// locale
		setLocale(locale, modèle, résultats);
		// heure
		résultats.setHeureGet(new SimpleDateFormat("hh:mm:ss").format(new Date()));
		// vue
		return "vue-04";
	}

	@RequestMapping(value = "/ajax-05", method = RequestMethod.POST)
	@ResponseBody()
	// traite le POST de la vue [vue-04]
	public JsonResults ajax05(@Valid ActionModel01 formulaire, BindingResult result, Locale locale,
			HttpServletRequest request, HttpSession session) throws InterruptedException {
		if (result.hasErrors()) {
			// cas anormal - on ne rend rien
			return null;
		}
		// tempo ?
		Integer tempo = (Integer) session.getAttribute("tempo");
		if (tempo != null && tempo > 0) {
			Thread.sleep(tempo);
		}
		// le contexte de l'application Spring
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		// on prépare le modèle de la prochaine vue
		JsonResults résultats = new JsonResults();
		// entête
		résultats.setTitre(ctx.getMessage("resultats.titre", null, locale));
		résultats.setLabelHeureCalcul(ctx.getMessage("labelHeureCalcul", null, locale));
		résultats.setHeureCalcul(new SimpleDateFormat("hh:mm:ss").format(new Date()));
		// on génère une erreur une fois sur deux
		int val = new Random().nextInt(2);
		if (val == 0) {
			// on renvoie un message d'erreur
			résultats.setMsgErreur(ctx.getMessage("resultats.erreur",
					new Object[] { ctx.getMessage("erreur.aleatoire", null, locale) }, locale));
			return résultats;
		}
		// on récupère les valeurs postées
		double a = formulaire.getA();
		double b = formulaire.getB();
		// on construit le modèle
		résultats.setAplusb(String.valueOf(a + b));
		résultats.setAmoinsb(String.valueOf(a - b));
		résultats.setAfoisb(String.valueOf(a * b));
		try {
			résultats.setAdivb(String.valueOf(a / b));
		} catch (RuntimeException e) {
			résultats.setAdivb("NaN");
		}
		// on rend le résultat
		return résultats;
	}

	@RequestMapping(value = "/ajax-06", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
	public String ajax06() {
		return "vue-06";
	}

	@RequestMapping(value = "/ajax-07", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	public String ajax07(int num) {
		// num : numéro de page
		switch (num) {
		case 1:
			return "vue-07";
		case 2:
			return "vue-08";
		default:
			return "vue-07";
		}
	}

	// la session
	@Autowired
	private SessionModel1 session;
	// le moteur Thymeleaf
	@Autowired
	private SpringTemplateEngine engine;

	@RequestMapping(value = "/ajax-09", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
	public String ajax09() {
		return "vue-09";
	}

	@RequestMapping(value = "/ajax-10", method = RequestMethod.POST)
	@ResponseBody()
	public JsonResult10 ajax10(HttpServletRequest request, HttpServletResponse response) {
		// contexte Thymeleaf
		WebContext thymeleafContext = new WebContext(request, response, request.getServletContext());
		// réponse
		JsonResult10 result = new JsonResult10();
		// session
		session.setZone1(null);
		session.setZone3(null);
		session.setZone1Active(false);
		session.setZone3Active(false);
		// on rend une réponse aléatoire
		int cas = new Random().nextInt(3);
		switch (cas) {
		case 0:
			// zone 1 active
			setZone1(thymeleafContext, result);
			return result;
		case 1:
			// zone 3 active
			setZone3(thymeleafContext, result);
			return result;
		case 2:
			// zones 1 et 3 actives
			setZone1(thymeleafContext, result);
			setZone3(thymeleafContext, result);
			return result;
		}
		return null;
	}

	private void setZone1(WebContext thymeleafContext, JsonResult10 result) {
		// zone 1 active
		// flux HTML
		int cpt1 = session.getCpt1() + 1;
		thymeleafContext.setVariable("cpt1", cpt1);
		thymeleafContext.setLocale(new Locale("fr", "FR"));
		String zone1 = engine.process("vue-09-zone1", thymeleafContext);
		result.setZone1(zone1);
		result.setZone1Active(true);
		// session
		session.setCpt1(cpt1);
		session.setZone1(zone1);
		session.setZone1Active(true);
	}

	private void setZone3(WebContext thymeleafContext, JsonResult10 result) {
		// zone 3 active
		// flux HTML
		int cpt3 = session.getCpt3() + 1;
		thymeleafContext.setVariable("cpt3", cpt3);
		thymeleafContext.setLocale(new Locale("en", "US"));
		String zone3 = engine.process("vue-09-zone3", thymeleafContext);
		result.setZone3(zone3);
		result.setZone3Active(true);
		// session
		session.setCpt3(cpt3);
		session.setZone3(zone3);
		session.setZone3Active(true);
	}

	@RequestMapping(value = "/ajax-11A", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JsonResult10 ajax11A(@RequestBody @Valid PostAjax11A post, BindingResult bindingResult, Locale locale,
			HttpServletRequest request, HttpServletResponse response) {
		// contexte Thymeleaf
		WebContext thymeleafContext = new WebContext(request, response, request.getServletContext());
		// réponse
		JsonResult10 result = new JsonResult10();
		// post valide ?
		if (bindingResult.hasErrors()) {
			// on renvoie la page 1 avec une erreur
			result.setZone1Active(session.isZone1Active());
			result.setZone3Active(session.isZone3Active());
			result.setErreur(getErreursForModel(bindingResult));
			return result;
		}
		// on mémorise la zone de saisie
		thymeleafContext.setVariable("value1", post.getValue1());
		thymeleafContext.setVariable("value2", post.getValue2());
		session.setSaisies(engine.process("vue-09-saisies", thymeleafContext));
		// on envoie la page 2
		result.setContent(engine.process("vue-09-page2", thymeleafContext));
		return result;
	}

	@RequestMapping(value = "/ajax-11B", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult10 ajax11B(HttpServletRequest request, HttpServletResponse response) {
		// contexte Thymeleaf
		WebContext thymeleafContext = new WebContext(request, response, request.getServletContext());
		// réponse
		JsonResult10 result = new JsonResult10();
		// on la rend la page 1 dans son état originel
		result.setContent(engine.process("vue-09-page1", thymeleafContext));
		result.setSaisies(session.getSaisies());
		result.setZone1(session.getZone1());
		result.setZone3(session.getZone3());
		result.setZone1Active(session.isZone1Active());
		result.setZone3Active(session.isZone3Active());
		return result;
	}

	@RequestMapping(value = "/ajax-12", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
	public String ajax12() {
		return "vue-12";
	}

	@RequestMapping(value = "/ajax-13", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	@ResponseBody()
	public JsonResult13 ajax13(@RequestBody SessionModel2 session2, HttpServletRequest request,
			HttpServletResponse response) {
		// contexte Thymeleaf
		WebContext thymeleafContext = new WebContext(request, response, request.getServletContext());
		// réponse
		JsonResult13 result = new JsonResult13();
		result.setSession(session2);
		// on rend une réponse aléatoire
		int cas = new Random().nextInt(3);
		switch (cas) {
		case 0:
			// zone 1 active
			setZone1B(thymeleafContext, result);
			return result;
		case 1:
			// zone 3 active
			setZone3B(thymeleafContext, result);
			return result;
		case 2:
			// zones 1 et 3 actives
			setZone1B(thymeleafContext, result);
			setZone3B(thymeleafContext, result);
			return result;
		}
		return null;
	}

	private void setZone1B(WebContext thymeleafContext, JsonResult13 result) {
		// on récupère la session
		SessionModel2 session = result.getSession();
		// zone 1 active
		// flux HTML
		int cpt1 = session.getCpt1() + 1;
		thymeleafContext.setVariable("cpt1", cpt1);
		thymeleafContext.setLocale(new Locale("fr", "FR"));
		String zone1 = engine.process("vue-09-zone1", thymeleafContext);
		result.setZone1(zone1);
		// session
		session.setCpt1(cpt1);
	}

	private void setZone3B(WebContext thymeleafContext, JsonResult13 result) {
		// on récupère la session
		SessionModel2 session = result.getSession();
		// zone 3 active
		// flux HTML
		int cpt3 = session.getCpt3() + 1;
		thymeleafContext.setVariable("cpt3", cpt3);
		thymeleafContext.setLocale(new Locale("en", "US"));
		String zone3 = engine.process("vue-09-zone3", thymeleafContext);
		result.setZone3(zone3);
		// session
		session.setCpt3(cpt3);
	}

	@RequestMapping(value = "/ajax-14", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult13 ajax14(@RequestBody @Valid PostAjax14 post, BindingResult bindingResult, Locale locale,
			HttpServletRequest request, HttpServletResponse response) {
		// contexte Thymeleaf
		WebContext thymeleafContext = new WebContext(request, response, request.getServletContext());
		// réponse
		JsonResult13 result = new JsonResult13();
		// post valide ?
		if (bindingResult.hasErrors()) {
			// on renvoie une erreur
			result.setErreur(getErreursForModel(bindingResult));
			return result;
		}
		// on envoie la page 2
		result.setValue1(post.getValue1());
		result.setValue2(post.getValue2());
		// page requise ?
		if (post.isPageRequired()) {
			result.setPage2(engine.process("vue-12-page2", thymeleafContext));
		}
		return result;
	}

	@RequestMapping(value = "/ajax-16", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
	public String ajax16() {
		return "vue-16";
	}

	// -------------------------------fonctions diverses
	// liste des messages d'erreur liés à un modèle invalide
	private String getErreursForModel(BindingResult result) {
		StringBuffer buffer = new StringBuffer();
		for (FieldError error : result.getFieldErrors()) {
			StringBuffer bufferCodes = new StringBuffer("(");
			for (String code : error.getCodes()) {
				bufferCodes.append(String.format("%s ", code));
			}
			bufferCodes.append(")");
			buffer.append(String.format("[%s:%s:%s:%s]", error.getField(), error.getRejectedValue(), bufferCodes,
					error.getDefaultMessage()));
		}
		return buffer.toString();
	}

}
