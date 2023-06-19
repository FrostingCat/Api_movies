package com.example.api_autho.rest;

import com.example.api_autho.model.MovieList;
import com.example.api_autho.model.ShowtimeList;
import com.example.api_autho.model.Ticket;
import com.example.api_autho.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/")
public class CinemaRestController {
    private final OrderService orderService;

    @GetMapping("movies")
    public ResponseEntity getMovies() {
        List<MovieList> movies = orderService.findMovies();

        Map<Object, Object> response = new HashMap<>();
        response.put("movies", movies);

        return ResponseEntity.ok(response);
    }

    @GetMapping("showtimes")
    public ResponseEntity getShowtimes() {
        List<ShowtimeList> showtimes = orderService.findShowtimes();

        Map<Object, Object> response = new HashMap<>();
        response.put("showtimes", showtimes);

        return ResponseEntity.ok(response);
    }

    @PostMapping("tickets")
    public ResponseEntity getTickets() {
        List<Ticket> tickets = orderService.findTickets();

        Map<Object, Object> response = new HashMap<>();
        response.put("tickets", tickets);

        return ResponseEntity.ok(response);
    }
}
