package com.codecool.moviesjpa.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "episode")
@Builder
@Data
@NoArgsConstructor
public class Episode {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "length_in_minutes")
    private int lengthInMinutes;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    @Transient
    private boolean awarded;

    public Episode(String name, int lengthInMinutes, LocalDate releaseDate, boolean awarded) {
        this.name = name;
        this.lengthInMinutes = lengthInMinutes;
        this.releaseDate = releaseDate;
        this.awarded = awarded;
    }
}
