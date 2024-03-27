package com.cinema.mini.repository;

import com.cinema.mini.domain.Screening;
import com.cinema.mini.domain.Theater;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class TheaterRepositoryTest {
    @Autowired
    TheaterRepository theaterRepository;


    @Test
    void saveTest(){
        Theater theater1 = getTheater("1관", 30);
        Theater theater2 = getTheater("2관", 130);
        Theater save1 = theaterRepository.save(theater1);
        Theater save2 = theaterRepository.save(theater2);

        Theater findTheater1 = theaterRepository.findByTheaterName("1관").orElseThrow();
        Theater findTheater2 = theaterRepository.findByTheaterName("2관").orElseThrow();

        assertThat(save1.getTheaterId()).isEqualTo(findTheater1.getTheaterId());
        assertThat(save2.getTheaterId()).isEqualTo(findTheater2.getTheaterId());
    }

    private Theater getTheater(String theaterName, int seatCount) {
        List<Screening> list = new ArrayList<>();
        return Theater.builder().theaterName(theaterName).seatCount(seatCount).screenings(list).build();
    }
}
