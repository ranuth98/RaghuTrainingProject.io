package com.cognizant.moviecruiser.dao;

import com.cognizant.moviecruiser.model.Favorites;

public class FavoritesDaoSqlImplTest {
	public static void testAddFavoritesMovies() {
        FavoritesDao favoritesDao = new FavoritesDaoSqlImpl();
        favoritesDao.addFavoriteMovies(1L, 1);
        System.out.println("Movie added to favorites successfully");
    }

    public static void testRemoveFavoritesMovies() {
        FavoritesDao favoritesDao = new FavoritesDaoSqlImpl();
        favoritesDao.removeFavoriteMovies(1L, 1);
        System.out.println("Movie removed from favorites successfully");

    }

    public static void testGetAllFavoritesMoviess() throws FavoritesEmptyException {
        FavoritesDaoSqlImpl favoritesDaoSqlImpl = new FavoritesDaoSqlImpl();
        Favorites favorites = favoritesDaoSqlImpl.getAllFavoriteMovies(1L);
        System.out.println(favorites.getMoviesList());
        System.out.println("total"+favorites.getTotal());
        System.out.println("Done");

    }

    public static void main(String[] args) throws FavoritesEmptyException {
//      testAddFavoritesMovies();
    testRemoveFavoritesMovies();
       // testGetAllFavoritesMoviess();
    }


}
