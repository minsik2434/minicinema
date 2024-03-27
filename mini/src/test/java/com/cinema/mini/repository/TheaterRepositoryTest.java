package com.cinema.mini.repository;

import com.cinema.mini.domain.Theater;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class TheaterRepositoryTest {
    @Autowired
    TheaterRepository theaterRepository;


    @Test
    @Commit
    void saveTest(){
        Theater theater = Theater.builder().theaterName("9관").seatCount(150).build();
        Theater save = theaterRepository.save(theater);
        Theater findTheater = theaterRepository.findById(6L).orElseThrow();
        assertThat(save.getTheaterName()).isEqualTo(findTheater.getTheaterName());
    }

    @Test
    void findByIdTest(){
        theaterRepository.findById(1L);
    }
}
