package com.stackroute.muzix.service;

import com.stackroute.muzix.exception.TrackAlreadyExistException;
import com.stackroute.muzix.exception.TrackNotFoundException;
import com.stackroute.muzix.domain.Track;
//import com.stackroute.muzix.seeddata.MyApplicationListener;

import java.util.List;


public interface MusicService {                                                        // interface for music service
    public Track saveTrack(Track track) throws TrackAlreadyExistException;

    public List<Track> getAllTrack() throws TrackNotFoundException;

    public Track updateComment(Track track) throws TrackNotFoundException;

    public List<Track> deleteTrack(int trackId) throws TrackNotFoundException;

    public List<Track> findByName(String trackName) throws TrackNotFoundException;

    public void seedData(Track track);
}
