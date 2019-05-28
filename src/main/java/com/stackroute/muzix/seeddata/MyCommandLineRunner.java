package com.stackroute.muzix.seeddata;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.service.MusicService;
import com.stackroute.muzix.service.MusicServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
//@Configuration
//@PropertySource("classpath:application.properties")
public class MyCommandLineRunner implements CommandLineRunner {
    Track track=new Track();
    //public static final Logger logs = LoggerFactory.getLogger(MyCommandLineRunner.class);
    private MusicService musicService;

    @Autowired
    public MyCommandLineRunner(MusicService musicService){
        this.musicService=musicService;
    }

    @Value("${track.id1}")
    private int id1;
    @Value("${track.name1}")
    private String name1;
    @Value("${track.Comment1}")
    private String comment1;

    @Value("${track.id2}")
    private int id2;
    @Value("${track.name2}")
    private String name2;
    @Value("${track.Comment2}")
    private String comment2;



    @Override
    public void run(String... args) throws Exception{

        track.setTrackId(id1);
        track.setTrackName(name1);
        track.setTrackComments(comment1);
        musicService.seedData(track);

        track.setTrackId(id2);
        track.setTrackName(name2);
        track.setTrackComments(comment2);
        musicService.seedData(track);

    }
}