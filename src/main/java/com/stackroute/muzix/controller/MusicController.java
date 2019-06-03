package com.stackroute.muzix.controller;

import com.stackroute.muzix.exception.TrackAlreadyExistException;
import com.stackroute.muzix.exception.TrackNotFoundException;
import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.service.MusicService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
@Configuration
@PropertySource("classpath:application.properties")
public class MusicController {
    MusicService musicService;


    @Autowired
    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }


    @ApiOperation(value = "Save track")
    @PostMapping("track")
    //handler method to save all tracks
    public ResponseEntity<Track> saveTrack(@RequestBody Track track) {
        ResponseEntity responseEntity;
        try {
            musicService.saveTrack(track);                                                  // save method of musicservice
            responseEntity = new ResponseEntity("Successfully Created", HttpStatus.CREATED);
        } catch (TrackAlreadyExistException trackAlreadyExistException) {
            responseEntity = new ResponseEntity(trackAlreadyExistException.getMessage(), HttpStatus.CONFLICT);
        }

        return responseEntity;
    }

    @ApiOperation(value = "Get All Track")
    @GetMapping("tracks")
    // handler method to get all track
    public ResponseEntity<?> getAllTrack() {
        ResponseEntity responseEntity;
        try {
            musicService.getAllTrack();                                                  //get alltrack method of music service
            responseEntity = new ResponseEntity<List<Track>>(musicService.getAllTrack(), HttpStatus.FOUND);
        } catch (TrackNotFoundException trackNotFoundException) {
            responseEntity = new ResponseEntity(trackNotFoundException.getMessage(), HttpStatus.NOT_FOUND);//track not found exception caught
        }

        return responseEntity;
    }

    @ApiOperation(value = "Delete Track")
    @DeleteMapping("track/{id}")
    // handler method to delete track by id
    public ResponseEntity<?> deleteTrack(@PathVariable int id) {
        ResponseEntity responseEntity;
        try {
            musicService.deleteTrack(id);                                                    //deletetrack method of musicservice
            responseEntity = new ResponseEntity("Updated", HttpStatus.GONE);
        } catch (TrackNotFoundException trackNotFoundException) {
            responseEntity = new ResponseEntity(trackNotFoundException.getMessage(), HttpStatus.NOT_FOUND);// track not found exception caught
        }
        return responseEntity;
    }


    @ApiOperation(value = "Find Track by name")
    @GetMapping("track/{trackName}")
    //handler method to get track by name
    public ResponseEntity<?> findByName(@RequestBody Track track, @PathVariable String trackName) {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<List<Track>>(musicService.findByName(trackName), HttpStatus.FOUND);
        } catch (TrackNotFoundException trackNotFoundException) {
            responseEntity = new ResponseEntity(trackNotFoundException.getMessage(), HttpStatus.NOT_FOUND);   //track not found exception caught
        }
        return responseEntity;
    }

    @ApiOperation(value = "Update Track")
    @PutMapping("track")
    //handler method to update track
    public ResponseEntity<?> updateComment(@RequestBody Track track) {

        ResponseEntity responseEntity;
        try {
            musicService.updateComment(track);
            responseEntity = new ResponseEntity("Updated", HttpStatus.OK);
        } catch (TrackNotFoundException trackNotFoundException) {
            responseEntity = new ResponseEntity(trackNotFoundException.getMessage(), HttpStatus.NOT_FOUND);//track not found exception caught
        }
        return responseEntity;
    }
}
