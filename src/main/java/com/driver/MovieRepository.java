package com.driver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Repository
public class MovieRepository {

    List<Movie> listOfMovies=new ArrayList<>();
    List<Director> listOfDirectors=new ArrayList<>();
    Map<String,String> addMovieDirector=new HashMap<>();

    public void addMovie(Movie movie){
        listOfMovies.add(movie);
    }

    public void addDirector(Director director){
        listOfDirectors.add(director);
    }

    public void addMovieDirectorPair(String nameOfMovie,String nameOfDirector){
        addMovieDirector.put(nameOfMovie,nameOfDirector);
    }
}
