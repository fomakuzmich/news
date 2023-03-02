package by.htp.ex.controller.impl;

import static by.htp.ex.util.constant.Parameters.*;
import static by.htp.ex.util.constant.Atributes.*;
import static by.htp.ex.util.constant.Pages.*;
import static by.htp.ex.util.constant.Patterns.*;
import java.io.IOException;

import by.htp.ex.controller.Command;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.util.Security;
import jakarta.servlet.http.HttpSession;

public class DoSIgnIn implements Command {

	private final IUserService service = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (Security.parametersAreEmpty(request)) {
			request.setAttribute(AUTHENTICATION_ERROR_ATTR, EMPTY_FIELDS_MESSAGE);
			request.getRequestDispatcher(BASE_LAYOUT_JSP).forward(request, response);
			return;
		}

		String email = request.getParameter(JSP_EMAIL_PARAM);
		String password = request.getParameter(JSP_PASSWORD_PARAM);
		HttpSession session = request.getSession(true);

		try {

			NewUserInfo user = service.signIn(email, password);

			if (user != null) {
				session.setAttribute(USER_ATTR, ACTIVE_VALUE);
				session.setAttribute(ROLE_ATTR, user.getRole());
				session.setAttribute("userId", user.getId());
				response.sendRedirect("controller?command=go_to_news_list");
			} else {
				session.setAttribute(USER_ATTR, NOT_ACTIVE_VALUE);
				request.setAttribute(AUTHENTICATION_ERROR_ATTR, WRONG_FIELDS_MESSAGE);
				request.getRequestDispatcher("/WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
			}

		} catch (ServiceException e) {
			response.sendRedirect("controller?command=go_to_error_page");
		}
	}
}
//*

//	private final IUserService service = ServiceProvider.getInstance().getUserService();

//	private static final String JSP_LOGIN_PARAM = "login";
//	private static final String JSP_PASSWORD_PARAM = "password";

//	@Override
//	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String login;
//		String password;
//
//		login = request.getParameter(JSP_LOGIN_PARAM);
//		password = request.getParameter(JSP_PASSWORD_PARAM);
//
//		 small validation
//
//		try {
//
//			String role = service.signIn(login, password);
//
//			if (!role.equals("guest")) {
//				request.getSession(true).setAttribute("user", "active");
//				request.getSession(true).setAttribute("role", role);
//				response.sendRedirect("controller?command=go_to_news_list");
//			} else {
//				request.getSession(true).setAttribute("user", "not active");
//				request.setAttribute("AuthenticationError", "wrong login or password");
//				request.getRequestDispatcher("/WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
//			}
//			
//		} catch (ServiceException e) {
//			 logging e
//			 go-to error page

//		}

// response.getWriter().print("do logination");

//	}

//}
