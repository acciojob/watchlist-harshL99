package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieService service;

    @PostMapping("/movies/add-movie")
    public ResponseEntity addMovie(@RequestBody(required = true) Movie movie){

        service.addMovie(movie);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/movies/add-director")
    public ResponseEntity addDirector(@RequestBody(required = true) Director director){

        service.addDirector(director);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam String nameOfMovie,@RequestParam String nameOfDirector){

        service.addMovieDirectorPair(nameOfMovie,nameOfDirector);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String nameOfMovie){

        Movie movie=service.findMovie(nameOfMovie);
        return new ResponseEntity(movie,HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String nameOfDirector){

        Director director=service.findDirector(nameOfDirector);
        return new ResponseEntity(director,HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("director") String nameOfDirector){

        List<String> nameOfMoviesByDirector=new ArrayList<>();
        service.findMoviesByDirector(nameOfDirector,nameOfMoviesByDirector);
        return new ResponseEntity(nameOfMoviesByDirector,HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-all-movies")
    public ResponseEntity<List<Movie>> findAllMovies(){

        List<Movie> allMovies=new ArrayList<>();
        service.findAllMovies(allMovies);
        return new ResponseEntity(allMovies,HttpStatus.FOUND);
    }

    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam String nameOfDirector){

        service.deleteDirectorandMovies(nameOfDirector);
        return new ResponseEntity(HttpStatus.GONE);
    }

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){

        service.deleteAllDirectors();
        return new ResponseEntity(HttpStatus.GONE);
    }
}
