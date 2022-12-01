package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public void addMovie(Movie movie){

        if(!movieRepository.listOfMovies.contains(movie))
        movieRepository.addMovie(movie);
    }

    public void addDirector(Director director){

        if(!movieRepository.listOfDirectors.contains(director))
        movieRepository.addDirector(director);
    }

    public void addMovieDirectorPair(String nameOfMovie,String nameOfDirector){
        if(nameOfMovie=="" || nameOfDirector=="") return;
        else {
            if (movieRepository.listOfMovies.contains(nameOfMovie) &&
                    movieRepository.listOfDirectors.contains(nameOfDirector))

                movieRepository.addMovieDirectorPair(nameOfMovie, nameOfDirector);

        }
    }

    public Movie findMovie(String nameOfMovie){
        for(Movie movie : movieRepository.listOfMovies){
            if(movie.getName().equals(nameOfMovie))
                return movie;
        }
        return null;
    }

    public Director findDirector(String nameOfDirector){
        for(Director director : movieRepository.listOfDirectors){
            if(director.getName().equals(nameOfDirector))
                return director;
        }
        return null;
    }

    public void findMoviesByDirector(String nameOfDirector,List<String> nameOfMoviesByDirector){
        for(String movie : movieRepository.addMovieDirector.keySet()){
            if(movieRepository.addMovieDirector.get(movie).equals(nameOfDirector))
                nameOfMoviesByDirector.add(movie);
        }
    }

    public void findAllMovies(List<Movie> allMovies){
        for(Movie movie : movieRepository.listOfMovies)
            allMovies.add(movie);
    }

    public void deleteDirectorandSongs(String nameOfDirector){
//        movieRepository.listOfDirectors.remove(nameOfDirector);
        for(Director director : movieRepository.listOfDirectors){
            if(director.getName().equals(nameOfDirector))
                movieRepository.listOfDirectors.remove(director);
        }
        for(String nameOfMovie : movieRepository.addMovieDirector.keySet()){
            if(movieRepository.addMovieDirector.get(nameOfMovie).equals(nameOfDirector))
                movieRepository.addMovieDirector.remove(nameOfMovie);
        }

    }

    public void deleteAllDirectors(){
        movieRepository.listOfDirectors.clear();
        movieRepository.addMovieDirector.clear();
    }
}
