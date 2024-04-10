package com.cinema.mini.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(mappedBy = "movie",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovieGenre> movieGenres;

    @OneToMany(mappedBy = "movie")
    private List<Screening> screenings;

}
