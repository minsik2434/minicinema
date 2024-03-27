package com.cinema.mini.repository;

import com.cinema.mini.domain.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
    @Query("select m from Movie m order by m.releaseDate DESC limit 5")
    List<Movie> findLastestMovieList();

    @Query("select m from Movie m order by m.grade DESC limit 5")
    List<Movie> findPopularMovieList();

    List<Movie> findByMovieGenres_Genre_GenreName(String genreName, Pageable pageable);

    List<Movie> findByTitleContaining(String title);
}
