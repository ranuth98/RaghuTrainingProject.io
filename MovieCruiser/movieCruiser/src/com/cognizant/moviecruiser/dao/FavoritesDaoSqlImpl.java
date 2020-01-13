package com.cognizant.moviecruiser.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cognizant.moviecruiser.model.Favorites;
import com.cognizant.moviecruiser.model.Movies;

public class FavoritesDaoSqlImpl implements FavoritesDao {

	public static final String ADD_TO_FAVORITES = "INSERT INTO `favorites` (`fv_us_id`, `fv_mo_id`) VALUES (?,?)";
	public static final String GET_ALL_FAVORITE_MOVIES = "SELECT mo_id,mo_title,mo_gross,mo_active,mo_date_of_launch,mo_genre,mo_has_teaser FROM movie_list inner join favorites on fv_mo_id=mo_id where fv_us_id=?";
	public static final String GET_NO_OF_FAVORITES = "SELECT count(mo_id) as No_of_Favorites FROM movie_list inner join favorites on fv_mo_id=mo_id where fv_us_id=?";
	public static final String DELETE_FAVORITE = "delete from favorites where fv_us_id=? and fv_mo_id=? limit 1";

	@Override
	public void addFavoriteMovies(long userId, long moviesId) {
		Connection connection = ConnectionHandler.getConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(ADD_TO_FAVORITES);
			preparedStatement.setLong(1, userId);
			preparedStatement.setLong(2, moviesId);
			int noOfRows = preparedStatement.executeUpdate();
			if (noOfRows == 0) {
				System.out.println("Insert Not Done");
			}
		} catch (SQLException e1) {
			System.out.println("Error");
		} finally

		{
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {

			}
		}
	}

	@Override
	public Favorites getAllFavoriteMovies(long userId) throws FavoritesEmptyException {
		Connection connection = ConnectionHandler.getConnection();
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatementNOF = null;
		ArrayList<Movies> moviesList = new ArrayList<>();
		ResultSet resultSet = null;
		ResultSet resultSetNof = null;
		Favorites favorites = new Favorites();

		try {
			preparedStatement = connection.prepareStatement(GET_ALL_FAVORITE_MOVIES);

			preparedStatement.setLong(1, userId);
			resultSet = preparedStatement.executeQuery();
			preparedStatementNOF = connection.prepareStatement(GET_NO_OF_FAVORITES);
			preparedStatementNOF.setLong(1, userId);
			resultSetNof = preparedStatementNOF.executeQuery();
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
			while (resultSetNof.next()) {
				favorites.setTotal(resultSetNof.getInt("No_of_Favorites"));
			}
			if (moviesList.size() == 0) {
				throw new FavoritesEmptyException();
			}
			favorites.setMoviesList(moviesList);
		} catch (SQLException e) {
			System.out.println("Error");
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (resultSetNof != null) {
					resultSetNof.close();
				}
				if (preparedStatementNOF != null) {
					preparedStatementNOF.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return favorites;

	}

	@Override
	public void removeFavoriteMovies(long userId, long moviesId) {
		Connection connection = ConnectionHandler.getConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(DELETE_FAVORITE);
			preparedStatement.setLong(1, userId);
			preparedStatement.setLong(2, moviesId);
			int noOfRows = preparedStatement.executeUpdate();
			if (noOfRows == 0) {
				System.out.println("Insert Not Done");
			}
		} catch (SQLException e1) {
			System.out.println("Error");
		} finally

		{
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {

			}
		}

	}

}
