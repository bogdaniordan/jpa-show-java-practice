package com.codecool.moviesjpa.service;

import com.codecool.moviesjpa.entity.Episode;
import com.codecool.moviesjpa.entity.Genre;
import com.codecool.moviesjpa.entity.Season;
import com.codecool.moviesjpa.entity.Series;
import com.codecool.moviesjpa.reposity.EpisodeRepository;
import com.codecool.moviesjpa.reposity.SeasonRepository;
import com.codecool.moviesjpa.reposity.SeriesRepository;
import com.codecool.moviesjpa.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Slf4j
@AllArgsConstructor
public class CommandLineAppStartupRunner implements CommandLineRunner {
    private final SeriesRepository seriesRepository;


    @Override
    public void run(String... args) {
        log.info("Filling database with data...");

        int episodeLength = 40;
        List<String> firstSeasonTitles = List.of("Pilot", "Error", "Inside track", "Dirty", "Bail out", "Tricks of the Trade", "Play the man");
        List<String> secondSeasonTitles = List.of("She knows", "The choice", "Meet", "All in", "Sucker punch", "war", "Black");

        Season firstSeason = Season.builder()
                .name("First Season")
                .lengthInMinutes(firstSeasonTitles.size() * episodeLength)
                .releaseDate(LocalDate.now())
                .episodes(
                        IntStream.range(0, firstSeasonTitles.size()).boxed().map(i ->
                            Episode.builder()
                                    .name(firstSeasonTitles.get(i))
                                    .awarded(Utils.RANDOM.nextBoolean())
                                    .lengthInMinutes(episodeLength)
                                    .releaseDate(LocalDate.now())
                                    .build()).collect(Collectors.toList())).build();

       Season secondSeason = Season.builder()
               .characters(List.of("Harvery", "Mike Ross", "Louis"))
                .name("First Season")
                .lengthInMinutes(secondSeasonTitles.size() * episodeLength)
                .releaseDate(LocalDate.now())
                .episodes(
                        IntStream.range(0, firstSeasonTitles.size()).boxed().map(i ->
                            Episode.builder()
                                    .name(firstSeasonTitles.get(i))
                                    .awarded(Utils.RANDOM.nextBoolean())
                                    .lengthInMinutes(episodeLength)
                                    .releaseDate(LocalDate.of(2002, 2, 2))
                                    .build()).collect(Collectors.toList())).build();

       firstSeason.getEpisodes().forEach(episode -> {
           episode.setSeason(firstSeason);
       });
       secondSeason.getEpisodes().forEach(episode -> {episode.setSeason(secondSeason);});

       Series suits = Series.builder()
               .name("Suits")
               .genre(Genre.COMEDY)
               .releaseDate(LocalDate.of(2020,1, 20))
               .lengthInMinutes((secondSeason.getEpisodes().size() + firstSeason.getEpisodes().size()) * episodeLength)
               .season(firstSeason)
               .season(secondSeason)
               .build();
        firstSeason.setSeries(suits);
        secondSeason.setSeries(suits);
       seriesRepository.save(suits);
    }
}
