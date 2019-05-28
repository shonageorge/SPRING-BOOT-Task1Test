package com.stackroute.muzix.seeddata;

import com.stackroute.muzix.Exception.AlreadyExistException;
import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.repository.MusicRepository;
import com.stackroute.muzix.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;


@Component
@Configuration
@PropertySource("classpath:application.properties")
public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logs = Logger.getLogger("MyApplicationListener.class");

    private MusicRepository musicRepository;

    Track track=new Track();

    MusicService musicService;

    @Autowired
    public MyApplicationListener(MusicService musicService) {
        this.musicService = musicService;
    }

//    @Autowired
//    public MyApplicationListener(MusicRepository musicRepository) {
//        this.musicRepository= musicRepository;
//    }

    @Autowired
    Environment env;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)  {

        track.setTrackId(Integer.parseInt(env.getProperty("track.id3")));
        track.setTrackName(env.getProperty("track.name4"));
        track.setTrackComments(env.getProperty("track.Comment3"));

       musicService.seedData(track);
       // System.out.println(musicService.getAllTrack());

//        logs.info("Inserting data");
//
//        Track track1=new Track(1,"Red","Good");
//        Track track2=new Track(2,"Faded","Alan Walker");
//        musicRepository.save(track1);
//
//        logs.info("data Successfully inserted");


    }
}
