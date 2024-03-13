package com.cinema.mini.repository;

import com.cinema.mini.domain.Genre;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class GenreRepositoryTest {
    @Autowired
    private GenreRepository genreRepository;

    @Test
    void findByGenreNameTest(){
        Genre findByGenreName = genreRepository.findByGenreName("로맨스").orElseThrow();
        Genre findByGenreId = genreRepository.findById("10749").orElseThrow();
        assertThat(findByGenreName.getGenreId()).isEqualTo(findByGenreId.getGenreId());
    }
}
