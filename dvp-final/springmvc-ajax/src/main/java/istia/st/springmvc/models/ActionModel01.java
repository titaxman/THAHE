package istia.st.springmvc.models;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class ActionModel01 {

	// données postées
	@NotNull
	@DecimalMin(value = "0.0")
	private Double a;

	@NotNull
	@DecimalMin(value = "0.0")
	private Double b;

	// getters et setters
	public Double getA() {
		return a;
	}

	public void setA(Double a) {
		this.a = a;
	}

	public Double getB() {
		return b;
	}

	public void setB(Double b) {
		this.b = b;
	}
}
