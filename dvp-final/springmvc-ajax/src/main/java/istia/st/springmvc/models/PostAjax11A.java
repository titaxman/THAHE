package istia.st.springmvc.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class PostAjax11A {

	// data
	@Size(min = 4, max = 6)
	@NotNull
	private String value1;
	@Range(min = 10, max = 14)
	@NotNull
	private Integer value2;

	// getters et setters
	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public Integer getValue2() {
		return value2;
	}

	public void setValue2(Integer value2) {
		this.value2 = value2;
	}

}
