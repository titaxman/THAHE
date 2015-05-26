package istia.st.springmvc.models;

public class JsonResult10 {

	// data
	private String content;
	private String zone1;
	private String zone3;
	private String erreur;
	private String saisies;
	private boolean zone1Active;
	private boolean zone3Active;

	public JsonResult10() {
	}

	// getters et setters

	public String getZone1() {
		return zone1;
	}

	public void setZone1(String zone1) {
		this.zone1 = zone1;
	}

	public String getZone3() {
		return zone3;
	}

	public void setZone3(String zone3) {
		this.zone3 = zone3;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSaisies() {
		return saisies;
	}

	public void setSaisies(String saisies) {
		this.saisies = saisies;
	}

	public String getErreur() {
		return erreur;
	}

	public void setErreur(String erreur) {
		this.erreur = erreur;
	}

	public boolean isZone1Active() {
		return zone1Active;
	}

	public void setZone1Active(boolean zone1Active) {
		this.zone1Active = zone1Active;
	}

	public boolean isZone3Active() {
		return zone3Active;
	}

	public void setZone3Active(boolean zone3Active) {
		this.zone3Active = zone3Active;
	}


}
