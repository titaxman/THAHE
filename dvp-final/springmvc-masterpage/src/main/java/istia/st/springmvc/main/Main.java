package istia.st.springmvc.main;

import istia.st.springmvc.config.Config;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

public class Main {
	public static void main(String[] args) {
		// on lance l'application
		ApplicationContext context = SpringApplication.run(Config.class, args);
		// on affiche la liste des beans trouvés par Spring
		System.out.println("Liste des beans Spring");
		String[] beanNames = context.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
	}
}
