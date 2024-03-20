package com.cinema.mini.repository;

import com.cinema.mini.domain.Genre;
import com.cinema.mini.domain.Movie;
import com.cinema.mini.domain.MovieGenre;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Transactional
public class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    GenreRepository genreRepository;

    @Test
    void findLastestMovieList(){
        List<Movie> createMovieList = new ArrayList<>();
        createMovieList.add(createTestMovie("test01","테스트1", "2024-03-01",4.5));
        createMovieList.add(createTestMovie("test02","테스트2", "2024-03-02",4.5));
        createMovieList.add(createTestMovie("test03","테스트3", "2024-03-03",4.5));
        createMovieList.add(createTestMovie("test04","테스트4", "2024-03-04",4.5));
        createMovieList.add(createTestMovie("test05","테스트5", "2024-03-05",4.5));
        movieRepository.saveAll(createMovieList);
        List<Movie> lastestMovieList = movieRepository.findLastestMovieList();

        assertThat(lastestMovieList).hasSize(5);
        for (int i = 0; i < lastestMovieList.size(); i++) {
            assertThat(lastestMovieList.get(i).getMovieId())
                    .isEqualTo(createMovieList.get(createMovieList.size() - 1 - i).getMovieId());
        }
    }

    @Test
    void findPopularMovieList(){
        List<Movie> createMovieList = new ArrayList<>();
        createMovieList.add(createTestMovie("test01","테스트1", "2024-03-01",1.5));
        createMovieList.add(createTestMovie("test02","테스트2", "2024-03-02",2.5));
        createMovieList.add(createTestMovie("test03","테스트3", "2024-03-03",3.5));
        createMovieList.add(createTestMovie("test04","테스트4", "2024-03-04",4.5));
        createMovieList.add(createTestMovie("test05","테스트5", "2024-03-05",5.0));
        movieRepository.saveAll(createMovieList);
        List<Movie> popularMovieList = movieRepository.findPopularMovieList();
        assertThat(popularMovieList).hasSize(5);
        for (int i = 0; i < popularMovieList.size(); i++) {
            assertThat(popularMovieList.get(i).getMovieId())
                    .isEqualTo(createMovieList.get(createMovieList.size() - 1 - i).getMovieId());
        }
    }

    @Test
    void findRomanceMovieList(){
        Pageable pageable = PageRequest.of(0,5);
        List<Movie> list = movieRepository.findByMovieGenres_Genre_GenreName("로맨스", pageable);
        Genre genre = genreRepository.findByGenreName("로맨스").orElseThrow();
        list.forEach(movie -> {
            boolean containsRomance = movie.getMovieGenres().stream()
                    .anyMatch(movieGenre -> movieGenre.getGenre().equals(genre));
            assertThat(containsRomance).isTrue();
        });
    }

    @Test
    void findByTitleIsLikeTest(){
        List<Movie> list = movieRepository.findByTitleContaining("가");
        List<String> nameList = new ArrayList<>();
        nameList.add("가여운 것들");
        nameList.add("아가일");
        list.forEach(movie -> {
            boolean containMovie = nameList.contains(movie.getTitle());
            assertThat(containMovie).isTrue();
        });
    }

    private Movie createTestMovie(String movieId, String movieTitle, String localDate, double grade) {
        return Movie.builder()
                .movieId(movieId)
                .title(movieTitle)
                .adult(true)
                .posterPath("testPosterPath")
                .releaseDate(LocalDate.parse(localDate))
                .overview("테스트 영화")
                .grade(grade).build();
    }
}

