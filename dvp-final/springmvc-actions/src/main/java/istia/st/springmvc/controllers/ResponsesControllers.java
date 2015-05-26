package istia.st.springmvc.controllers;

import istia.st.springmvc.models.Personne;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponsesControllers {
	// ----------------------- hello world ------------------------
	@RequestMapping(value = "/a01", method = RequestMethod.GET)
	public String a01() {
		return "Greetings from Spring Boot!";
	}

	// ----------------------- caractères accentués - UTF8 ------------------------
	@RequestMapping(value = "/a02", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String a02() {
		return "caractères accentués : éèàôûî";
	}

	// ----------------------- text/xml ------------------------
	@RequestMapping(value = "/a03", method = RequestMethod.GET, produces = "text/xml;charset=UTF-8")
	public String a03() {
		String greeting = "<greetings><greeting>Greetings from Spring Boot!</greeting></greetings>";
		return greeting;
	}

	// ----------------------- produire du JSON - 1 ------------------------
	@RequestMapping(value = "/a04", method = RequestMethod.GET)
	public Map<String, Object> a04() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("1", "un");
		map.put("2", new int[] { 4, 5 });
		return map;
	}

	// ----------------------- produire du JSON - 2 ------------------------
	@RequestMapping(value = "/a05", method = RequestMethod.GET)
	public Personne a05() {
		return new Personne(1, "carole", 45);
	}

	// ----------------------- rendre un flux vide ------------------------
	@RequestMapping(value = "/a06")
	public void a06() {
	}

	// ----------------------- Content-Type : text/html ------------------------
	@RequestMapping(value = "/a07", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String a07() {
		String greeting = "<h1>Greetings from Spring Boot!</h1>";
		return greeting;
	}

	// ----------------------- Content-Type : résultat HTML en text/plain ------------------------
	@RequestMapping(value = "/a08", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String a08() {
		String greeting = "<h1>Greetings from Spring Boot!</h1>";
		return greeting;
	}

	// ----------------------- Content-Type : résultat HTML en text/xml ------------------------
	@RequestMapping(value = "/a09", method = RequestMethod.GET, produces = "text/xml;charset=UTF-8")
	public String a09() {
		String greeting = "<h1>Greetings from Spring Boot!</h1>";
		return greeting;
	}

	// ----------------------- génération complète de la réponse ------------------------
	@RequestMapping(value = "/a13")
	public void a13(HttpServletResponse response) throws IOException {
		response.setStatus(666);
		response.addHeader("header1", "qq chose");
		response.addHeader("Content-Type", "text/html;charset=UTF-8");
		String greeting = "<h1>Greetings from Spring Boot!</h1>";
		response.getWriter().write(greeting);
	}

}
