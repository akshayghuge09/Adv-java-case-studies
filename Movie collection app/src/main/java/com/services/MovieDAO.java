package com.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.model.Movie;

public class MovieDAO {
	// connection method
	public static Connection getConnection() {
		Connection connection = null;

		String dbURL = "jdbc:mysql://localhost:3306/";
		String dbName = "imdb";
		String userName = "root";
		String passwaord = "akshay@123";
		String dbDriverClassName = "com.mysql.cj.jdbc.Driver";

		try {
			Class.forName(dbDriverClassName);
			connection = DriverManager.getConnection(dbURL + dbName, userName, passwaord);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	// save
	public static int save(Movie movie) {
		int status = 0;
		try {

			Connection connection = MovieDAO.getConnection();
			String insertQuery = "insert into movie(movie_id,movie_name,actors,actresses,director,rating) values(?,?,?,?,?,?)";
			PreparedStatement prst = connection.prepareStatement(insertQuery);
			prst.setInt(1, movie.getMovieId());
			prst.setString(2, movie.getMovieName());
			prst.setString(3, movie.getActors());
			prst.setString(4, movie.getActresses());
			prst.setString(5, movie.getDirector());
			prst.setInt(6, movie.getRating());

			status = prst.executeUpdate();
			if (status > 0) {
				System.out.println("Record save succssfully");
			} else {
				System.out.println("record  save process failed");
			}

			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return status;
	}

	// get
	public static Movie getmovie(int movieId) {
		Movie movie = new Movie();

		try {

			Connection connection = MovieDAO.getConnection();
			String insertQuery = "select * from movie where movie_id=?";
			PreparedStatement prst = connection.prepareStatement(insertQuery);
			prst.setInt(1, movieId);

			ResultSet resultSet = prst.executeQuery();

			if (resultSet.next()) {
				movie.setMovieId(resultSet.getInt(1));
				movie.setMovieName(resultSet.getString(2));
				movie.setActors(resultSet.getNString(3));
				movie.setActresses(resultSet.getString(4));
				movie.setDirector(resultSet.getString(5));
				movie.setRating(resultSet.getInt(6));
			}

			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return movie;

	}

	// update
	public static int update(Movie movie) {
		int status = 0;
		try {

			Connection connection = MovieDAO.getConnection();
			String insertQuery = "update movie set movie_name=?,actors=?,actresses=?,director=?,rating=? where movie_id=?";
			PreparedStatement prst = connection.prepareStatement(insertQuery);

			prst.setString(1, movie.getMovieName());
			prst.setString(2, movie.getActors());
			prst.setString(3, movie.getActresses());
			prst.setString(4, movie.getDirector());
			prst.setInt(5, movie.getRating());
			prst.setInt(6, movie.getMovieId());

			status = prst.executeUpdate();
			if (status > 0) {
				System.out.println("Record update succssfully");
			} else {
				System.out.println("record  update process failed");
			}

			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return status;
	}

	// delete
	public static int delete(int movieId) {
		int status = 0;
		try {
			Connection connection = MovieDAO.getConnection();
			String insertQuery = "delete from movie where movie_id=?";
			PreparedStatement prst = connection.prepareStatement(insertQuery);
			prst.setInt(1, movieId);

			status = prst.executeUpdate();
			if (status > 0) {
				System.out.println("Record deleted succssfully");
			} else {
				System.out.println("record  delete process failed");
			}

			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return status;

	}

	public static List<Movie> allMovie() {
		List<Movie> movies = new ArrayList<Movie>();

		try {

			Connection connection = MovieDAO.getConnection();
			String insertQuery = "select * from movie";
			PreparedStatement prst = connection.prepareStatement(insertQuery);

			ResultSet resultSet = prst.executeQuery();

			while (resultSet.next()) {
				Movie movie = new Movie();
				movie.setMovieId(resultSet.getInt(1));
				movie.setMovieName(resultSet.getString(2));
				movie.setActors(resultSet.getNString(3));
				movie.setActresses(resultSet.getString(4));
				movie.setDirector(resultSet.getString(5));
				movie.setRating(resultSet.getInt(6));
				movies.add(movie);
			}

			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return movies;
	}
}
