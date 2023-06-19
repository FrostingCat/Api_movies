package com.example.api_autho.rest;

import com.example.api_autho.dto.OrderTicketDto;
import com.example.api_autho.model.MovieList;
import com.example.api_autho.model.ShowtimeList;
import com.example.api_autho.model.Ticket;
import com.example.api_autho.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        if (movies.isEmpty()) {
            return new ResponseEntity<>("no movies", HttpStatus.BAD_REQUEST);
        }

        Map<Object, Object> response = new HashMap<>();
        response.put("movies", movies);

        return ResponseEntity.ok(response);
    }

    @GetMapping("showtimes")
    public ResponseEntity getShowtimes() {
        List<ShowtimeList> showtimes = orderService.findShowtimes();

        if (showtimes.isEmpty()) {
            return new ResponseEntity<>("no showtimes", HttpStatus.BAD_REQUEST);
        }

        Map<Object, Object> response = new HashMap<>();
        response.put("showtimes", showtimes);

        return ResponseEntity.ok(response);
    }

    @PostMapping("tickets")
    public ResponseEntity getTickets(@RequestBody OrderTicketDto orderTicket) {
        if (orderService.checkTickets(orderTicket.getId(), orderTicket.getAmount())) {
            return new ResponseEntity<>("not enough tickets", HttpStatus.BAD_REQUEST);
        }

        orderService.orderTickets(orderTicket.getId(), orderTicket.getAmount());
        return ResponseEntity.ok("tickets are ordered");
    }
}
