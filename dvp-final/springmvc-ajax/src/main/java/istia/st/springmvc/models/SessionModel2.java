package istia.st.springmvc.models;

import java.io.Serializable;

public class SessionModel2 implements Serializable {

	private static final long serialVersionUID = 1L;
	// deux compteurs
	private int cpt1 = 0;
	private int cpt3 = 0;

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
}
