package com.stackroute.muzix.service;

import com.stackroute.muzix.Exception.AlreadyExistException;
import com.stackroute.muzix.Exception.TrackNotFound;
import com.stackroute.muzix.domain.Track;
//import com.stackroute.muzix.seeddata.MyApplicationListener;

import java.util.List;


public interface MusicService  {
    public Track saveTrack(Track track) throws AlreadyExistException;
    public List<Track> getAllTrack() ;
    public Track updateComment(Track track) throws TrackNotFound;
    public List<Track> deleteTrack(int trackId)throws TrackNotFound;
    //public List<Track> findByName(String trackName) throws TrackNotFound;
    public void seedData(Track track);
}
