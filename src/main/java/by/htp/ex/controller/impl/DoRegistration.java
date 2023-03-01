package by.htp.ex.controller.impl;

import java.io.IOException;
import java.security.Security;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.controller.Command;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoRegistration implements Command {

	private final IUserService service = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (Security.parametersAreEmpty(request)) {
			request.setAttribute(REGISTRATION_ERROR_ATTR, EMPTY_FIELDS_MESSAGE);
			request.getRequestDispatcher(REGISTRATION_JSP).forward(request, response);
			return;
		}

		NewUserInfo info = new NewUserInfo();

		info.setFirstName(request.getParameter(JSP_FIRSTNAME_PARAM));
		info.setSurname(request.getParameter(JSP_SURNAME_PARAM));
		info.setLogin(request.getParameter(JSP_LOGIN_PARAM));
		info.setPassword(request.getParameter(JSP_PASSWORD_PARAM));
		info.setEmail(request.getParameter(JSP_EMAIL_PARAM));

		try {
			service.registration(info);
			response.sendRedirect("controller?command=go_to_after_registration_page");

		} catch (ServiceException e) {
			if (e.getMessage().equals(VALIDATION_EXCEPTION_MESSAGE)) {
				request.setAttribute(REGISTRATION_ERROR_ATTR, INVALID_FIELDS_MESSAGE);
				request.getRequestDispatcher(REGISTRATION_JSP).forward(request, response);
			} else {
				response.sendRedirect("controller?command=go_to_error_page");
			}
		}

	}

}
