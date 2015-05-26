package istia.st.springmvc.models;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionModel1 implements Serializable {

	private static final long serialVersionUID = 1L;
	// deux compteurs
	private int cpt1 = 0;
	private int cpt3 = 0;
	// les trois zones
	private String zone1 = "xx";
	private String zone3 = "zz";
	private String saisies;
	private boolean zone1Active = true;
	private boolean zone3Active = true;

	// getters et setters
	public int getCpt1() {
		return cpt1;
	}

	public void setCpt1(int cpt1) {
		this.cpt1 = cpt1;
	}

	public int getCpt3() {
		return cpt3;
	}

	public void setCpt3(int cpt3) {
		this.cpt3 = cpt3;
	}

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

	public String getSaisies() {
		return saisies;
	}

	public void setSaisies(String saisies) {
		this.saisies = saisies;
	}

}
