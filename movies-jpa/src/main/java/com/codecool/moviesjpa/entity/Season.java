package com.codecool.moviesjpa.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "season")
@Builder
@Data
@NoArgsConstructor
public class Season {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "length_in_minutes")
    private int lengthInMinutes;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    @OneToMany
    private List<Episode> episodes;


    public Season(String name, int lengthInMinutes, LocalDate releaseDate, List<Episode> episodes) {
        this.name = name;
        this.lengthInMinutes = lengthInMinutes;
        this.releaseDate = releaseDate;
        this.episodes = episodes;
    }
}
