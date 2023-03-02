package by.htp.ex.service.impl;

import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;

public class NewsServiceImpl implements INewsService {

	private final INewsDAO newsDAO = DaoProvider.getInstance().getNewsDAO();

	@Override
	public void save(News news) throws ServiceException {

		try {
			newsDAO.addNews(news);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(String[] idNewses) throws ServiceException {

		try {
			newsDAO.deleteNewses(idNewses);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void update(News news) throws ServiceException {

		try {
			newsDAO.updateNews(news);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public List<News> latestList(int count) throws ServiceException {

		try {
			return newsDAO.getLatestsList(count);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<News> list() throws ServiceException {
		try {
			return newsDAO.getList();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public News findById(int id) throws ServiceException {
		try {
			return newsDAO.fetchById(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
}