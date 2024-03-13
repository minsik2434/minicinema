package com.cinema.mini.insert;


import com.cinema.mini.domain.Genre;
import com.cinema.mini.domain.Movie;
import com.cinema.mini.domain.MovieGenre;
import com.cinema.mini.repository.GenreRepository;
import com.cinema.mini.repository.MovieRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class InsertMovieGenreTest {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    GenreRepository genreRepository;
    @Test
    @Transactional
    @Commit
    void insertMovieGenre() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=ko&page=7&sort_by=popularity.desc"))
                .header("accept", "application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNWQxMDM3Y2MwYzZiZTk4ODVhZTRiMTQxMTVjN2U0MCIsInN1YiI6IjY1NWIxOTdhZjY3ODdhMDEwMDhiZGMxNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.dmjFpapNQf7gjcTImbzG1GwaF3lvgJtKENrzuddC1as")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        String jsonString = response.body();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonString);
        JsonNode resultsNode = rootNode.get("results");

        if(resultsNode.isArray()){
            for (JsonNode jsonNode : resultsNode) {
                JsonNode genreArray = jsonNode.get("genre_ids");
                String title = jsonNode.get("title").asText();
                String movie_id = Integer.toString(jsonNode.get("id").asInt());
                Optional<Movie> movie = movieRepository.findById(movie_id);
                if(movie.isPresent()){
                    Movie movieE = movie.get();
                    for (JsonNode genre : genreArray) {
                        String genre_id = Integer.toString(genre.asInt());
                        Genre genreE = genreRepository.findById(genre_id).orElseThrow();
                        log.info("genreE.name={}",genreE.getGenreName());
                        MovieGenre movieGenre = MovieGenre.builder().movie(movieE).genre(genreE).build();
                        movieE.getMovieGenres().add(movieGenre);
                    }
                    movieRepository.save(movieE);
                }
                log.info("movie_id={}, title={}, genreArray={}",movie_id, title, genreArray);
            }
        }
    }
}
