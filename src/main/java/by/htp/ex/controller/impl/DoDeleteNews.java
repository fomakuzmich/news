package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoDeleteNews implements Command {
	
	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession()==null) {
			request.getSession(true).setAttribute(USER_ATTR, NOT_ACTIVE_VALUE);
			request.setAttribute(AUTHENTICATION_ERROR_ATTR, SESSION_TIME_OUT_MESSAGE);
			request.getRequestDispatcher("/WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
			return;
		}

		String[] idNewses= null;
		idNewses = request.getParameterValues(NEWS_ID);
		
		
		
		try {
			newsService.delete(idNewses);
			response.sendRedirect("controller?command=go_to_news_list");
		}catch (ServiceException e) {
			response.sendRedirect("controller?command=go_to_error_page");
		}
		
	}

}
