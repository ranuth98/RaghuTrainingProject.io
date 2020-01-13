package com.cognizant.moviecruiser.dao;

import java.text.SimpleDateFormat;
import java.util.List;

import com.cognizant.moviecruiser.model.Movies;
import com.cognizant.moviecruiser.util.DateUtil;

public class MoviesDaoSqlImplTest {
	public static void display(List<Movies> moviesList) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		System.out.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "ID", "TITLE", "BOXOFFICE", "ACTIVE",
				"DATE_OF_LAUNCH", "GENRE", "HASTEASER");
		String active, hasteaser;

		for (Movies movies : moviesList) {
			if (movies.getActive() == true) {
				active = "Yes";
			} else {
				active = "No";
			}
			if (movies.getHasTeaser() == true) {
				hasteaser = "Yes";
			} else {
				hasteaser = "No";
			}
			System.out.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", movies.getId(), movies.getTitle(),
					movies.getGross(), active, sdf.format(movies.getDateOfLaunch()), movies.getGenre(), hasteaser);

		}

	}

	public static void testGetMoviesListAdmin() {
		List<Movies> moviesListAdmin = new MoviesDaoSqlImpl().getMoviesListAdmin();
		System.out.println("ADMIN MOVIE LIST");
		display(moviesListAdmin);

	}

	public static void testGetMoviesListCustomer() {
		List<Movies> moviesListCustomer = new MoviesDaoSqlImpl().getMoviesListCustomer();
		System.out.println("CUSTOMER MOVIE LIST");
		display(moviesListCustomer);

	}

	public static void testModifyMovies() {
		Movies modifymovie = new Movies(1L, "CillunuOruKadhal", 123456789, true,
				new DateUtil().convertToDate("22/12/2019"), "Love", false);
		MoviesDaoSqlImpl moviesDaoSqlImpl = new MoviesDaoSqlImpl();
		moviesDaoSqlImpl.modifyMovies(modifymovie);

	}

	public static void main(String[] args)  {
//		Connection x = ConnectionHandler.getConnection();
//		System.out.println(x);
		testGetMoviesListAdmin();
		testGetMoviesListCustomer();
		testModifyMovies();
		testGetMoviesListAdmin();
	}

}
