package com.stackroute.muzix.service;

import com.stackroute.muzix.exception.TrackAlreadyExistException;
import com.stackroute.muzix.exception.TrackNotFoundException;
import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceImpl implements MusicService {                                        //MusicService Interface implementation class

    MusicRepository musicRepository;

    @Autowired
    public MusicServiceImpl(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;                                               //autowiring music repository
    }

    //overring methods of MusicService Interface
    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistException {

        Track saveTrack = new Track();

        if (musicRepository.existsById(track.getTrackId())) {
            throw new TrackAlreadyExistException("Track Already Exist exception occured");
        }

        saveTrack = musicRepository.save(track);                                                //calling save method of music repository
        return saveTrack;
    }


    @Override
    public List<Track> getAllTrack() throws TrackNotFoundException {
        List<Track> list = musicRepository.findAll();                                             //calling findall method of music repository
        if (list.isEmpty()) {
            throw new TrackNotFoundException();
        }
        return list;
    }

    @Override
    public Track updateComment(Track track) throws TrackNotFoundException {

        Track track1 = new Track();

        if (musicRepository.existsById(track.getTrackId())) {

            return musicRepository.save(track);
        }

        throw new TrackNotFoundException("Track not Found exception occured");
    }

    @Override
    public List<Track> deleteTrack(int trackId) throws TrackNotFoundException {
        if (musicRepository.existsById(trackId)) {
            musicRepository.deleteById(trackId);                                                //calling deleteBy method of music repository
            return musicRepository.findAll();

        }
        throw new TrackNotFoundException("Track not Found exception occured");
    }

    //
    @Override
    public List<Track> findByName(String trackName) throws TrackNotFoundException {
        if (musicRepository.equals(trackName)) {
            return musicRepository.findByName(trackName);
        } else {
            throw new TrackNotFoundException("Track not Found exception occured");
        }
    }

    @Override
    public void seedData(Track track) {
        musicRepository.save(track);
    }
}
