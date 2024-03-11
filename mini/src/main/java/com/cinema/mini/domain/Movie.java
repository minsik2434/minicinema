package com.cinema.mini.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
public class Movie {
    @Id
    private String movieId;
    @Column
    private String title;
    @Column
    private boolean adult;
    @Column
    private String posterPath;
    @Column
    private LocalDate releaseDate;
    @Column
    private String overview;
    @Column
    private double grade;

    public Movie() {

    }
}
