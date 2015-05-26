package istia.st.springmvc;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

	@RequestMapping("/")
	public String heure(Model model) {
		// format de l'heure
		SimpleDateFormat formater = new SimpleDateFormat("HH:MM:ss");
		// l'heure du moment
		String heure = formater.format(new Date());
		// on met l'heure dans le modèle de la vue
		model.addAttribute("heure", heure);
		// on fait afficher la vue [exemple-02.html]
		return "exemple-02";
	}

	// ----------------------- rendre un flux vide [Content-Length=0]
	@RequestMapping(value = "/doNothing")
	@ResponseBody
	public void doNothing() {
	}
}