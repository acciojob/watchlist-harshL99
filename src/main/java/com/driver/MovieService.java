package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

              boolean movieIsThere=false,directorIsThere=false;
        for(Movie movie : movieRepository.listOfMovies){
            if(movie.getName().equals(nameOfMovie)){
                movieIsThere=true;
                break;
            }

        }
        if(movieIsThere){
            for(Director director : movieRepository.listOfDirectors){
                if(director.getName().equals(nameOfDirector)){
                    directorIsThere=true;
                    break;
                }
            }
        }
        if(movieIsThere && directorIsThere)
         movieRepository.addMovieDirectorPair(nameOfMovie,nameOfDirector);
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

    public void deleteDirectorandMovies(String nameOfDirector){
//        movieRepository.listOfDirectors.remove(nameOfDirector);

        //1.Take out director movies in list from map
        //2.Delete the movies from movie list
        //3.Delete the director from director list
        List<String> movieList=new ArrayList<>();

        for(String movie : movieRepository.addMovieDirector.keySet()){
            if(movieRepository.addMovieDirector.get(movie).equals(nameOfDirector))
                movieList.add(movie);
        }
        for(String movie : movieList){
            if(movieRepository.listOfMovies.contains(movie)) {
                movieRepository.listOfMovies.remove(movie);
                movieRepository.addMovieDirector.remove(movie);
            }
        }

        for(Director director : movieRepository.listOfDirectors){
            if(director.getName().equals(nameOfDirector)) {
                movieRepository.listOfDirectors.remove(director);
                break;
            }
        }


    }

    public void deleteAllDirectors(){
        //Traverse through map and get all movies in a list and remove that from movie list...
        Set<String> moviesList=new HashSet<>();
        for(String movie : movieRepository.addMovieDirector.keySet()){
            moviesList.add(movie);
        }
        for(String movie : moviesList){
            for(Movie m : movieRepository.listOfMovies){
                if(m.getName().equals(movie))
                    movieRepository.listOfMovies.remove(m);
            }
        }
    }
}
