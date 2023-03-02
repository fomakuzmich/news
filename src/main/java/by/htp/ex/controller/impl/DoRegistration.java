package by.htp.ex.controller.impl;

import static by.htp.ex.util.constant.Parameters.*;
import static by.htp.ex.util.constant.Atributes.*;
import static by.htp.ex.util.constant.Pages.*;
import java.io.IOException;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.controller.Command;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.util.Security;
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
