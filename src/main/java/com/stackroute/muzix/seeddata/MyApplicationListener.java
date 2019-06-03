//seeding data using ApplicationListener interface
package com.stackroute.muzix.seeddata;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
@Configuration
@PropertySource("classpath:application.properties")
public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    Track track = new Track();

    MusicService musicService;

    @Autowired
    public MyApplicationListener(MusicService musicService) {                             //autowiring music service by constructor
        this.musicService = musicService;
    }

    @Autowired
    Environment env;                                                                    //autowiring environment variable


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {      //Overring method of ApplicationListener Interface

        //getting the values using env.getproperties from application.properties and setting it to the corresponding variable

        track.setTrackId(Integer.parseInt(env.getProperty("track1.id3")));
        track.setTrackName(env.getProperty("track1.name4"));
        track.setTrackComments(env.getProperty("track1.Comment3"));
        musicService.seedData(track);                                                      //calling the seeddata method of musicservice

    }
}
