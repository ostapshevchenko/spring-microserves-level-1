package io.javabrains.resources;

import io.javabrains.models.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @GetMapping("{movieId}")
    public Movie getMovies(@PathVariable String movieId) {
        return Movie.builder()
                .movieId(movieId)
                .name("TestName")
                .build();
    }

}
