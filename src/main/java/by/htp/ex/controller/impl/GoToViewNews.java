package by.htp.ex.controller.impl;

import static by.htp.ex.util.constant.Parameters.*;
import static by.htp.ex.util.constant.Atributes.*;
import java.io.IOException;
import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToViewNews implements Command {

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String lastReuestUrl = request.getContextPath() + "/controller?command=go_to_view_news";
		request.getSession(true).setAttribute(URL, lastReuestUrl);

		News news;

		String id;

		id = request.getParameter(ID);

		try {
			news = newsService.findById(Integer.parseInt(id));
			request.setAttribute(NEWS_ATTR, news);
			request.setAttribute(PRESENTATION_ATTR, VIEW_NEWS_VALUE);
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);

		} catch (ServiceException e) {
			response.sendRedirect("controller?command=go_to_error_page");
		}

	}
}
