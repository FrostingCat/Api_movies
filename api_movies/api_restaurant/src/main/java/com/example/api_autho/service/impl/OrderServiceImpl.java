package com.example.api_autho.service.impl;

import com.example.api_autho.model.*;
import com.example.api_autho.repository.MovieRepository;
import com.example.api_autho.repository.ShowtimeRepository;
import com.example.api_autho.repository.TicketRepository;
import com.example.api_autho.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final ShowtimeRepository showtimeRepository;

    private final MovieRepository movieRepository;

    private final TicketRepository ticketRepository;

    @Override
    public List<MovieList> findMovies() {
        List<MovieList> res = new ArrayList<>();
        List<Movie> movies = movieRepository.findAll();
        for (Movie movie : movies) {
            log.info("IN movie: {}", movie);
            MovieList movieList = new MovieList();
            movieList.name = movie.getName();
            movieList.genre = movie.getGenre();
            movieList.timing = movie.getTiming();
            movieList.rating = movie.getRating();
            res.add(movieList);
        }
        return res;
    }

    @Override
    public List<ShowtimeList> findShowtimes() {
        List<ShowtimeList> res = new ArrayList<>();
        List<Showtime> showtimes = showtimeRepository.findAll();
        for (Showtime showtime : showtimes) {
            log.info("IN showtime: {}", showtime);
            ShowtimeList showtimeList = new ShowtimeList();
            showtimeList.name = showtime.getName();
            showtimeList.date = showtime.getDate();
            res.add(showtimeList);
        }
        return res;
    }

    @Override
    public List<Ticket> findTickets() {
        return ticketRepository.findAll();
    }
}
