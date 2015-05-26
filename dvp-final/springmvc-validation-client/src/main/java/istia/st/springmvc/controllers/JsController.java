package istia.st.springmvc.controllers;

import istia.st.springmvc.models.Form01;
import istia.st.springmvc.validators.Custom3;
import istia.st.springmvc.validators.Form01Validator;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class JsController {

	@RequestMapping(value = "/js01", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
	public String js01(Form01 formulaire, Locale locale, Model model) {
		setModel(formulaire, model, locale, null);
		return "vue-01";
	}

	@Autowired
	private MessageSource messages;

	@RequestMapping(value = "/js02", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	public String js02(@Valid Form01 formulaire, BindingResult result, RedirectAttributes redirectAttributes,
			Locale locale, Model model) {
		Form01Validator validator = new Form01Validator(10, 13);
		validator.validate(formulaire, result);
		if (result.hasErrors()) {
			StringBuffer buffer = new StringBuffer();
			for (ObjectError error : result.getAllErrors()) {
				buffer.append(String.format("[name=%s,code=%s,message=%s]", error.getObjectName(), error.getCode(),
						error.getDefaultMessage()));
			}
			setModel(formulaire, model, locale, buffer.toString());
			return "vue-01";
		} else {
			redirectAttributes.addFlashAttribute("form01", formulaire);
			return "redirect:/js01.html";
		}
	}

	// préparation du modèle de la vue vue-01
	private void setModel(Form01 formulaire, Model model, Locale locale, String message) {
		// on ne gère que les locales fr-FR, en-US
		String language = locale.getLanguage();
		String country = null;
		if (language.equals("fr")) {
			country = "FR";
			formulaire.setLang("fr_FR");
		}
		if (language.equals("en")) {
			country = "US";
			formulaire.setLang("en_US");
		}
		model.addAttribute("locale", String.format("%s-%s", language, country));
		// les attributs de la règle [custom3]
		model.addAttribute("custom3",
				new Custom3("double1", 10, 13, messages.getMessage("form01.double3", new Object[] { 10, 13 }, locale)));
		// le message éventuel
		if (message != null) {
			model.addAttribute("message", message);
		}
	}
}
