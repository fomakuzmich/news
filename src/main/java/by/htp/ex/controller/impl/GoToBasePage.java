package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;
import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static by.htp.ex.util.constant.Parameters.*;
import static by.htp.ex.util.constant.Atributes.*;

public class GoToBasePage implements Command {

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String lastReuestUrl = request.getContextPath() + "/controller?command=go_to_base_page";
		request.getSession(true).setAttribute(URL, lastReuestUrl);

		List<News> latestNews;
		try {
			latestNews = newsService.latestList(5);
			request.setAttribute(NEWS_ATTR, latestNews);
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);

		} catch (ServiceException e) {
			response.sendRedirect("controller?command=go_to_error_page");
		}

	}

}
