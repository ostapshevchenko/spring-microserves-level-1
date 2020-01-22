package io.javabrains.resources;

import com.netflix.discovery.DiscoveryClient;
import io.javabrains.models.CatalogItem;
import io.javabrains.models.Movie;
import io.javabrains.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    private final RestTemplate restTemplate;
    private final WebClient.Builder webClientBuilder;

    public MovieCatalogResource(RestTemplate restTemplate,
                                WebClient.Builder webClientBuilder) {
        this.restTemplate = restTemplate;
        this.webClientBuilder = webClientBuilder;
    }

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        UserRating userRatings = restTemplate.getForObject("http://ratings-data-service/ratings-data/users/" + userId, UserRating.class);

        return userRatings.getUserRating().stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+ rating.getMovieId(), Movie.class);

            return CatalogItem.builder()
                    .name(movie.getName())
                    .desc("hardcoded desc")
                    .rating(rating.getRating())
                    .build();

        }).collect(Collectors.toList());
    }


    //            Movie movie = webClientBuilder.build()
//                    .get()
//                    .uri("http://localhost:8081/movies/" + rating.getMovieId())
//                    .retrieve()
//                    .bodyToMono(Movie.class)
//                    .block();
}
