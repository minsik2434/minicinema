package com.cinema.mini.repository;

import com.cinema.mini.domain.Genre;
import com.cinema.mini.domain.Movie;
import com.cinema.mini.domain.Screening;
import com.cinema.mini.domain.Theater;
import com.cinema.mini.util.MovieUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Transactional
public class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    GenreRepository genreRepository;
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    ScreeningRepository screeningRepository;
    @Autowired
    MovieUtil movieUtil;

    @Test
    void findLastestMovieList(){
        List<Movie> createMovieList = new ArrayList<>();
        createMovieList.add(movieUtil.buildTestMovie("test01","테스트1", "2024-03-01",4.5));
        createMovieList.add(movieUtil.buildTestMovie("test02","테스트2", "2024-03-02",4.5));
        createMovieList.add(movieUtil.buildTestMovie("test03","테스트3", "2024-03-03",4.5));
        createMovieList.add(movieUtil.buildTestMovie("test04","테스트4", "2024-03-04",4.5));
        createMovieList.add(movieUtil.buildTestMovie("test05","테스트5", "2024-03-05",4.5));
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
        createMovieList.add(movieUtil.buildTestMovie("test01","테스트1", "2024-03-01",1.5));
        createMovieList.add(movieUtil.buildTestMovie("test02","테스트2", "2024-03-02",2.5));
        createMovieList.add(movieUtil.buildTestMovie("test03","테스트3", "2024-03-03",3.5));
        createMovieList.add(movieUtil.buildTestMovie("test04","테스트4", "2024-03-04",4.5));
        createMovieList.add(movieUtil.buildTestMovie("test05","테스트5", "2024-03-05",5.0));
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
        List<Movie> RomanceMovieList = new ArrayList<>();
        Movie movie1 = movieUtil.createMovieAndMovieGenre("test01",
                "테스트1", "2024-03-01",4,new String[] {"액션","로맨스"});
        Movie movie2 = movieUtil.createMovieAndMovieGenre("test02",
                "테스트2", "2024-03-01",4,new String[] {"가족","로맨스"});
        Movie movie3 = movieUtil.createMovieAndMovieGenre("test03",
                "테스트3", "2024-03-01",4,new String[] {"가족","액션"});

        RomanceMovieList.add(movie1);
        RomanceMovieList.add(movie2);

        Pageable pageable = PageRequest.of(0,5);
        List<Movie> list = movieRepository.findByMovieGenres_Genre_GenreName("로맨스", pageable);
        assertThat(list).hasSize(2);
        Genre genre = genreRepository.findByGenreName("로맨스").orElseThrow();

        list.forEach(movie -> {
            boolean containsRomance = movie.getMovieGenres().stream()
                    .anyMatch(movieGenre -> movieGenre.getGenre().equals(genre));
            assertThat(containsRomance).isTrue();
        });

        list.forEach(movie -> {
            boolean isRomance = RomanceMovieList.contains(movie);
            assertThat(isRomance).isTrue();
        });
    }


    @Test
    void findByTitleIsLikeTest(){
        List<Movie> movieList = new ArrayList<>();
        movieList.add(movieUtil.buildTestMovie("test01", "테스트1", "2024-03-01", 4.5));
        movieList.add(movieUtil.buildTestMovie("test02", "테스트2", "2024-03-01", 4.5));
        movieList.add(movieUtil.buildTestMovie("test03", "테스트3", "2024-03-01", 4.5));
        movieList.add(movieUtil.buildTestMovie("test04", "abc", "2024-03-01", 4.5));
        movieRepository.saveAll(movieList);


        List<Movie> list = movieRepository.findByTitleContaining("테스트");
        assertThat(list).hasSize(3);
        for (Movie movie : list) {
            assertThat(movie.getTitle()).contains("테스트");
        }
    }

    @Test
    void playingMovieTest(){
        String startTime = "2024-01-01T13:30:30";
        String endTime = "2024-01-01T15:30:30";
        Movie saveMovie = movieUtil.createMovieAndMovieGenre("test", "테스트영화", "2024-01-01", 4.5, new String[]{"액션", "드라마"});
        Theater theater = Theater.builder().theaterName("테스트 상영관").seatCount(20).build();
        Theater saveTheater = theaterRepository.save(theater);
        Screening saveScreening = Screening.builder().startTime(LocalDateTime.parse(startTime)).endTime(LocalDateTime.parse(endTime)).movie(saveMovie).theater(saveTheater).build();
        screeningRepository.save(saveScreening);
        //TODO 이 부분 수정이 필요함
        saveMovie.getScreenings().add(saveScreening);
        List<Movie> playingMovie = movieRepository.findByPlayingMovie();
        assertThat(playingMovie).isNotEmpty();
        for (Movie movie : playingMovie) {
            log.info(movie.getTitle());
            assertThat(movie.getScreenings()).isNotEmpty();
        }
    }
}
