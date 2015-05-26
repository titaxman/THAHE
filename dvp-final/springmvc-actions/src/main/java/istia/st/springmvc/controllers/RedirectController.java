package istia.st.springmvc.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RedirectController {

	// ------------ pont vers une action tierce -----------------------
	@RequestMapping(value = "/a10", method = RequestMethod.GET)
	public String a10() {
		return "a01";
	}

	// ------------ redirection 302 vers une action tierce -----------------------
	@RequestMapping(value = "/a11", method = RequestMethod.GET)
	public String a11() {
		return "redirect:/a01";
	}
	
	// ------------ redirection permanente 301 vers une action tierce -----------------------
	@RequestMapping(value = "/a12", method = RequestMethod.GET)
	public void a12(HttpServletResponse response) {
		response.setStatus(301);
		response.addHeader("Location", "/a01");
	}
	
}
