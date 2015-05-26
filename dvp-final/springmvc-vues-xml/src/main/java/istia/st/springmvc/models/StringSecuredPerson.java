package istia.st.springmvc.models;

import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class StringSecuredPerson {

	@Range(min = 1)
	@Digits(fraction = 0, integer = 4)
	private String id;

	@Length(min = 4, max = 10)
	private String nom;

	@Range(min = 8, max = 14)
	@Digits(fraction = 0, integer = 2)
	private String age;

	// constructeurs
	public StringSecuredPerson() {

	}

	public StringSecuredPerson(String id, String nom, String age) {
		this.id = id;
		this.nom = nom;
		this.age = age;
	}

	// getters et setters

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

}
