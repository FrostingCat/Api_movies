package com.example.api_autho.service;

import com.example.api_autho.model.*;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface OrderService {
    List<MovieList> findMovies();
    List<ShowtimeList> findShowtimes();
    void orderTickets(Long id, int quantity);
    boolean checkTickets(Long name, int quantity);
}
