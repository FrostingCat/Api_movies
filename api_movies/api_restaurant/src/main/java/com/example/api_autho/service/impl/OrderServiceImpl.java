package com.example.api_autho.service.impl;

import com.example.api_autho.model.*;
import com.example.api_autho.repository.MovieRepository;
import com.example.api_autho.repository.ShowtimeRepository;
import com.example.api_autho.repository.TicketRepository;
import com.example.api_autho.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public void orderTickets(Long id, int quantity) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/movies", "root", "Db954216837")) {
            String sql = "UPDATE tickets SET amount = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            List<Ticket> ticketList = ticketRepository.findAll();
            for (Ticket ticket : ticketList) {
                if (Objects.equals(ticket.getId(), id)) {
                    statement.setString(1, String.valueOf(ticket.getAmount() - quantity));
                    statement.setString(2, String.valueOf(id));
                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkTickets(Long id, int quantity) {
        List<Ticket> res = new ArrayList<>();
        List<Ticket> ticketList = ticketRepository.findAll();
        for (Ticket ticket : ticketList) {
            return Objects.equals(ticket.getId(), id) && ticket.getAmount() < quantity;
        }
        return false;
    }
}
