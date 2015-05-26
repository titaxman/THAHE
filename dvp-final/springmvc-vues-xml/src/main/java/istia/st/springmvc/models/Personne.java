package istia.st.springmvc.models;

public class Personne {

	// identifiant
	private Integer id;
	// nom
	private String nom;
	// âge
	private int age;

	// constructeurs
	public Personne() {

	}

	public Personne(String nom, int age) {
		this.nom = nom;
		this.age = age;
	}

	public Personne(Integer id, String nom, int age) {
		this(nom, age);
		this.id = id;
	}

	@Override
	public String toString() {
		return String.format("[id=%s, nom=%s,  age=%d]", id, nom, age);
	}

	// getters et setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
