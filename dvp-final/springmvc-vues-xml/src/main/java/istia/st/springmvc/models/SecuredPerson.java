package istia.st.springmvc.models;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class SecuredPerson {

	@Range(min = 1)
	private int id;

	@Length(min = 4, max = 10)
	private String nom;

	@Range(min = 8, max = 14)
	private int age;

	// constructeurs
	public SecuredPerson() {

	}

	public SecuredPerson(int id, String nom, int age) {
		this.id = id;
		this.nom = nom;
		this.age = age;
	}

	// getters et setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
