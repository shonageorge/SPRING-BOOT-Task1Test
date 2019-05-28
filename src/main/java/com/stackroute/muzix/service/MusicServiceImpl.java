package com.stackroute.muzix.service;

import com.stackroute.muzix.Exception.AlreadyExistException;
import com.stackroute.muzix.Exception.TrackNotFound;
import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.repository.MusicRepository;
//import com.stackroute.muzix.seeddata.MyApplicationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceImpl implements MusicService{

    MusicRepository musicRepository;

    @Autowired
    public MusicServiceImpl(MusicRepository musicRepository){
        this.musicRepository=musicRepository;
    }

    @Override
    public Track saveTrack(Track track) throws AlreadyExistException {

        Track saveTrack=new Track();
//        if(musicRepository.existsById(track.getTrackId())){
//            throw new AlreadyExistException("Track Already Exist exception occured");
//        }
        if(musicRepository.existsById(track.getTrackId())) {
            throw new AlreadyExistException("Track Already Exist exception occured");
        }

        saveTrack = musicRepository.save(track);
        return saveTrack;
    }


    @Override
    public List<Track> getAllTrack() {
        List<Track> list=musicRepository.findAll();
        return list;
    }

    @Override
    public Track updateComment(Track track) throws TrackNotFound {

        Track track1=new Track();
//        Track updateTrack= musicRepository.findById(track.getTrackId()).get();
//        updateTrack.setTrackComments(updateTrack.getTrackComments());
       //return musicRepository.save(track);

        if(musicRepository.existsById(track.getTrackId())){

            return musicRepository.save(track);
       }

        throw new TrackNotFound("Track not Found exception occured");
    }

    @Override
    public List<Track> deleteTrack(int trackId) throws TrackNotFound {
        if(musicRepository.existsById(trackId)) {
//        List<Track> list=musicRepository.findAll();
//        if (list.isEmpty()) {
            musicRepository.deleteById(trackId);
            return musicRepository.findAll();

        }
//        else{
//            throw new TrackNotFound("Track not Found exception occured");
//            //track1=new Track()
//        }
        throw new TrackNotFound("Track not Found exception occured");
    }
//
//    @Override
//    public List<Track> findByName(String trackName) throws TrackNotFound {
//        if(musicRepository.equals(trackName)) {
//            return musicRepository.findByName(trackName);
//        }
//        else{
//            throw new TrackNotFound("Track not Found exception occured");
//            //track1=new Track()
//        }
//    }

    @Override
    public void seedData(Track track) {
        musicRepository.save(track);
    }
}
