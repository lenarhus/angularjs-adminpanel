package com.telegram_bot.controllers;

import com.telegram_bot.entity.Movies;
import com.telegram_bot.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Greg on 10/12/16.
 */
@RestController
@RequestMapping("/movies")
public class MoviesController {

    @Autowired
    private MoviesRepository movieRepository;

    public MoviesController(MoviesRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Movies> findAllMovies(){
        return movieRepository.findAll();
    }
}
