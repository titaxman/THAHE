package istia.st.springmvc.validators;

public class Custom3 {

	// champs
	private String otherFieldName;
	private double min;
	private double max;
	private String message;

	// constructeur
	public Custom3(String otherFieldName, double min, double max, String message) {
		this.otherFieldName = otherFieldName;
		this.min = min;
		this.max = max;
		this.message = message;
	}

	// getters et setters
	public String getOtherFieldName() {
		return otherFieldName;
	}

	public void setOtherFieldName(String otherFieldName) {
		this.otherFieldName = otherFieldName;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
