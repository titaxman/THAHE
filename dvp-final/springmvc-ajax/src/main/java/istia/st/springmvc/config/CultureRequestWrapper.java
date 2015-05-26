package istia.st.springmvc.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CultureRequestWrapper extends HttpServletRequestWrapper {

	public CultureRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String[] getParameterValues(String name) {
		// valeurs postées a et b
		if (name != null && (name.equals("a") || name.equals("b"))) {
			String[] values = super.getParameterValues(name);
			String[] newValues = values.clone();
			newValues[0] = newValues[0].replace(",", ".");
			return newValues;
		}
		// autres cas
		return super.getParameterValues(name);
	}

}
