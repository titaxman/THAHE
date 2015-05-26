package istia.st.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Layout {
	@RequestMapping(value = "/page1")
	public String page1() {
		return "page1";
	}

	@RequestMapping(value = "/page2", method=RequestMethod.POST)
	public String page2() {
		return "page2";
	}
}
