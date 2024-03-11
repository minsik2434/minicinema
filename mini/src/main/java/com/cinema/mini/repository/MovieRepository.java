package com.cinema.mini.repository;

import com.cinema.mini.domain.Movie;
import com.cinema.mini.dto.HomeMovieListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, String> {
    @Query("select m from Movie m order by m.releaseDate DESC limit 5")
    List<Movie> findLastestMovieList();

    @Query("select m from Movie m order by m.grade DESC limit 5")
    List<Movie> findPopularMovieList();

}
