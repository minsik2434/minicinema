package com.cinema.mini.insert;

import com.cinema.mini.domain.Genre;
import com.cinema.mini.repository.GenreRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@SpringBootTest
public class InsertGenreTest {

    @Autowired
    GenreRepository genreRepository;
    @Test
    @Transactional
    void insertGenre() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/genre/movie/list?language=ko"))
                .header("accept", "application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNWQxMDM3Y2MwYzZiZTk4ODVhZTRiMTQxMTVjN2U0MCIsInN1YiI6IjY1NWIxOTdhZjY3ODdhMDEwMDhiZGMxNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.dmjFpapNQf7gjcTImbzG1GwaF3lvgJtKENrzuddC1as")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        String jsonString = response.body();
        System.out.println(jsonString);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonString);
        JsonNode resultsNode = rootNode.get("genres");
        System.out.println(resultsNode);

        if(resultsNode.isArray()){
            for (JsonNode jsonNode : resultsNode) {
                int id = jsonNode.get("id").asInt();
                String genreName = jsonNode.get("name").asText();
                System.out.println("id = " + id + "name = " +genreName);
                Genre genre = Genre.builder().genreId(Integer.toString(id)).genreName(genreName).build();
                genreRepository.save(genre);
            }
        }
    }
}
