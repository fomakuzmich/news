package by.htp.ex.util;

import java.util.Enumeration;

import jakarta.servlet.http.HttpServletRequest;

public class Security {

	public static boolean parametersAreEmpty(HttpServletRequest request) {

		Enumeration<String> parameterNames = request.getParameterNames();

		while (parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			String parameterValue = request.getParameter(parameterName);
			if (parameterValue.isEmpty()) {
				return true;
			}
		}
		return false;
	}

}
