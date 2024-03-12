package com.cinema.mini.insert;

import com.cinema.mini.domain.Movie;
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
import java.time.LocalDate;

@Slf4j
@SpringBootTest
public class InsertMovieInfoTest {


    @Autowired
    MovieRepository movieRepository;

    @Test
    @Transactional
    @Commit
    void test() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=ko&page=5&sort_by=popularity.desc"))
                .header("accept", "application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNWQxMDM3Y2MwYzZiZTk4ODVhZTRiMTQxMTVjN2U0MCIsInN1YiI6IjY1NWIxOTdhZjY3ODdhMDEwMDhiZGMxNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.dmjFpapNQf7gjcTImbzG1GwaF3lvgJtKENrzuddC1as")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        String jsonString = response.body();
        System.out.println(jsonString);

        ObjectMapper objectMapper = new ObjectMapper();

        // JSON 파싱
        JsonNode rootNode = objectMapper.readTree(jsonString);

        // "results" 키의 값을 가져오기
        JsonNode resultsNode = rootNode.get("results");
        if (resultsNode.isArray()) {
            for (JsonNode resultNode : resultsNode) {
                // 각 결과 항목에 대한 작업 수행
                int id = resultNode.get("id").asInt();
                String title = resultNode.get("title").asText();
                boolean rating = resultNode.get("adult").asBoolean();
                String postPath = resultNode.get("poster_path").asText();
                LocalDate releaseDate = LocalDate.parse(resultNode.get("release_date").asText());
                String overview = resultNode.get("overview").asText();
                double grade = resultNode.get("vote_average").asDouble();
                Movie movie = Movie.builder()
                        .movieId(Integer.toString(id))
                        .title(title)
                        .adult(rating)
                                .posterPath(postPath).releaseDate(releaseDate).overview(overview).grade(grade).build();
                movieRepository.save(movie);
                System.out.println("ID: " + id + ", title: " + title + "rating: " + rating +
                        ", postPath: "+ postPath + "releaseDate: "+ releaseDate + "overview: " + overview);
            }
        }
    }


}
