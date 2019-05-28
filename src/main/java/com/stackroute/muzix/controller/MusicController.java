package com.stackroute.muzix.controller;


import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.stackroute.muzix.Exception.AlreadyExistException;
import com.stackroute.muzix.Exception.TrackNotFound;
import com.stackroute.muzix.domain.Track;
//import com.stackroute.muzix.seeddata.MyApplicationListener;
import com.stackroute.muzix.service.MusicService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v1")
//@Configuration
//@PropertySource("classpath:application.properties")
public class MusicController {
    MusicService musicService;


    @Autowired
    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }


    @ApiOperation(value = "Save track")
    @PostMapping("track")
    public ResponseEntity<Track> saveTrack(@RequestBody Track track) throws AlreadyExistException{
        ResponseEntity responseEntity;
        try {
            musicService.saveTrack(track);
            responseEntity = new ResponseEntity("Successfully Created", HttpStatus.CREATED);
        }catch (AlreadyExistException alreadyExistException){
            responseEntity=new ResponseEntity(alreadyExistException.getMessage(),HttpStatus.CONFLICT);
            //return null;
           // throw alreadyExistException;
        }

        return responseEntity;
    }

    @ApiOperation(value ="Get All Track" )
   @GetMapping("track")
    public  ResponseEntity<?> getAllTrack(){
        ResponseEntity responseEntity;
            musicService.getAllTrack();
            responseEntity = new ResponseEntity<List<Track>>(musicService.getAllTrack(),HttpStatus.OK);

        return  responseEntity;
    }

    @ApiOperation(value = "Delete Track")
    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable int id){
        ResponseEntity responseEntity;
        try {
            musicService.deleteTrack(id);
            responseEntity=new ResponseEntity("Updated",HttpStatus.OK);
        }catch (TrackNotFound trackNotFound){
            responseEntity=new ResponseEntity(trackNotFound.getMessage(),HttpStatus.CONFLICT);
        }
        return  responseEntity;
    }

//    @ApiOperation(value = "Find Track by name")
//    @GetMapping("track/{trackName}")
//    public  ResponseEntity<?> findByName(@RequestBody Track track,@PathVariable String trackName){
//        ResponseEntity responseEntity;
//        try {
//            responseEntity= new ResponseEntity<List<Track>>(musicService.findByName(trackName), HttpStatus.OK);
//        }catch (TrackNotFound trackNotFound){
//          responseEntity=new ResponseEntity(trackNotFound.getMessage(),HttpStatus.CONFLICT);
//        }
//        return  responseEntity;
//    }

    @ApiOperation(value = "Update Track")
    @PutMapping("track")
    public ResponseEntity<?> updateComment(@RequestBody Track track) throws TrackNotFound{

        ResponseEntity responseEntity;
        try {
            musicService.updateComment(track);
            responseEntity=new ResponseEntity("Updated",HttpStatus.OK);
        }catch (TrackNotFound trackNotFound){
            responseEntity=new ResponseEntity(trackNotFound.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
}
