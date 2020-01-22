package io.javabrains.ratingsdataservice.resources;

import io.javabrains.ratingsdataservice.models.Rating;
import io.javabrains.ratingsdataservice.models.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratings-data")
public class RatingResource {

    @GetMapping("/{movieId}")
    public Rating getRating(@PathVariable String movieId) {
        return Rating.builder()
                .movieId(movieId)
                .rating(4)
                .build();
    }

    @GetMapping("/users/{userId}")
    public UserRating getRatings(@PathVariable String userId) {
        return UserRating.builder()
                .userRating(Arrays.asList(
                        Rating.builder()
                                .movieId("1234")
                                .rating(4)
                                .build(),
                        Rating.builder()
                                .movieId("5678")
                                .rating(3)
                                .build()))
                .build();
    }
}
