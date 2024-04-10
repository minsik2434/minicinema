package com.cinema.mini.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Genre {
    @Id
    private String genreId;
    @Column
    private String genreName;
    @OneToMany(mappedBy = "genre")
    private List<MovieGenre> movieGenreList;
}
