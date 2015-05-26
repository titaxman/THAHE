package istia.st.springmvc.validators;

import istia.st.springmvc.models.Form01;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class Form01Validator implements Validator {

	// l'intervalle de validation
	private double min;
	private double max;

	// constructeur
	public Form01Validator(double min, double max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean supports(Class<?> classe) {
		return Form01.class.equals(classe);
	}

	@Override
	public void validate(Object form, Errors errors) {
		// objet validé
		Form01 form01 = (Form01) form;
		// la valeur de [double1]
		Double double1 = form01.getDouble1();
		if (double1 == null) {
			return;
		}
		// la valeur de [double2]
		Double double2 = form01.getDouble2();
		if (double2 == null) {
			return;
		}
		// [double1+double2]
		double somme = double1 + double2;
		// validation
		if (somme < min || somme > max) {
			errors.rejectValue("double2", "form01.double2", new Double[] { min, max }, null);
		}
	}

}
