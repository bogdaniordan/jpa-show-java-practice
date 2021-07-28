package com.codecool.moviesjpa.service;

import com.codecool.moviesjpa.entity.Episode;
import com.codecool.moviesjpa.reposity.EpisodeRepository;
import com.codecool.moviesjpa.reposity.SeasonRepository;
import com.codecool.moviesjpa.reposity.SeriesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Slf4j
@AllArgsConstructor
public class CommandLineAppStartupRunner implements CommandLineRunner {
    private final EpisodeRepository episodeRepository;
    private final SeasonRepository seasonRepository;
    private final SeriesRepository seriesRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Filling database with data...");
        Episode episode = Episode.builder()
                        .name("Traznitii").lengthInMinutes(60).releaseDate(LocalDate.now()).awarded(true).build();
//        episodeRepository.save(new Episode("Traznitii", 60, LocalDate.now(), true));
        episodeRepository.save(episode);
    }
}
