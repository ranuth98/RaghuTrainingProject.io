package com.cognizant.moviecruiser.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.moviecruiser.model.Movies;
import com.cognizant.moviecruiser.util.DateUtil;

public class MoviesDaoSqlImpl implements MoviesDao {

	public static final String ADMIN_MOVIE_LIST = "SELECT * FROM movie_list";
	public static final String CUSTOMER_MOVIE_LIST = "SELECT * FROM movie_list where mo_date_of_launch>=(select curdate()) and mo_active='1'";
	public static final String UPDATE_MOVIE_LIST = "update movie_list set mo_title=?, mo_gross=?, mo_active=?, mo_date_of_launch=?, mo_genre=?, mo_has_teaser=? where mo_id=?";
	public static final String GET_MOVIE = "select * from movie_list where mo_id=?";

	@Override
	public List<Movies> getMoviesListAdmin() {
		ArrayList<Movies> moviesList = new ArrayList<>();
		Connection connection = ConnectionHandler.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
	try {
			preparedStatement = connection.prepareStatement(ADMIN_MOVIE_LIST);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Movies movies = new Movies();
				movies.setId(resultSet.getLong("mo_id"));
				movies.setTitle(resultSet.getString("mo_title"));
				movies.setGross(resultSet.getLong("mo_gross"));
				movies.setActive(resultSet.getString("mo_active").equals("1"));
				movies.setDateOfLaunch(resultSet.getDate("mo_date_of_launch"));
				movies.setGenre(resultSet.getString("mo_genre"));
				movies.setHasTeaser(resultSet.getString("mo_has_teaser").equals("1"));

				moviesList.add(movies);
			}
		} catch (SQLException e) {
			System.out.println("Error");
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return moviesList;
	}

	@Override
	public List<Movies> getMoviesListCustomer() {
		ArrayList<Movies> moviesList = new ArrayList<>();
		Connection connection = ConnectionHandler.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(CUSTOMER_MOVIE_LIST);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Movies movies = new Movies();
				movies.setId(resultSet.getLong("mo_id"));
				movies.setTitle(resultSet.getString("mo_title"));
				movies.setGross(resultSet.getLong("mo_gross"));
				movies.setActive(resultSet.getString("mo_active").equals("1"));
				movies.setDateOfLaunch(resultSet.getDate("mo_date_of_launch"));
				movies.setGenre(resultSet.getString("mo_genre"));
				movies.setHasTeaser(resultSet.getString("mo_has_teaser").equals("1"));

				moviesList.add(movies);
			}
		} catch (SQLException e) {
			System.out.println("Error");
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return moviesList;
	}

	@Override
	public void modifyMovies(Movies movies) {
		Connection connection = ConnectionHandler.getConnection();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(UPDATE_MOVIE_LIST);

			statement.setString(1, movies.getTitle());
			statement.setLong(2, movies.getGross());
			statement.setString(3, movies.getActive() ? "1" : "0");
			statement.setDate(4, DateUtil.convertToSqlDate(movies.getDateOfLaunch()));
			statement.setString(5, movies.getGenre());
			statement.setString(6, movies.getHasTeaser() ? "1" : "0");
			statement.setLong(7, movies.getId());

			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Update not Done");
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public Movies getMovies(Long moviesId) {
		Connection connection = ConnectionHandler.getConnection();
		PreparedStatement preparedStatement = null;
		Movies movies = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(GET_MOVIE);
			preparedStatement.setLong(1, moviesId);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				movies = new Movies();
				movies.setId(resultSet.getLong("mo_id"));
				movies.setTitle(resultSet.getString("mo_title"));
				movies.setGross(resultSet.getLong("mo_gross"));
				movies.setActive(resultSet.getString("mo_active").equals("1"));
				movies.setDateOfLaunch(resultSet.getDate("mo_date_of_launch"));
				movies.setGenre(resultSet.getString("mo_genre"));
				movies.setHasTeaser(resultSet.getString("mo_has_teaser").equals("1"));

			}
		} catch (SQLException e) {
			System.out.println("Error");
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return movies;
	}

}
