package com.stackroute.muzix.service;

import com.stackroute.muzix.exception.TrackAlreadyExistException;
import com.stackroute.muzix.exception.TrackNotFoundException;
import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceImpl implements MusicService {                                        //MusicService Interface implementation class

    MusicRepository musicRepository;

    @Autowired
    public MusicServiceImpl(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;                                               //autowiring music repository
    }

    @Value("${exception.msg1}")
    private String msg1;

    @Value("${exception.msg2}")
    private String msg2;
    //overring methods of MusicService Interface
    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistException {

        Track saveTrack = new Track();

        if (musicRepository.existsById(track.getTrackId())) {
            throw new TrackAlreadyExistException(msg2);
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

        throw new TrackNotFoundException(msg1);
    }

    @Override
    public List<Track> deleteTrack(int trackId) throws TrackNotFoundException {
        if (musicRepository.existsById(trackId)) {
            musicRepository.deleteById(trackId);                                                //calling deleteBy method of music repository
            return musicRepository.findAll();

        }
        throw new TrackNotFoundException(msg1);
    }

    //
    @Override
    public List<Track> findByName(String trackName) throws TrackNotFoundException {
        if (musicRepository.equals(trackName)) {
            return musicRepository.findByName(trackName);
        } else {
            throw new TrackNotFoundException(msg1);
        }
    }

    @Override
    public void seedData(Track track) {
        musicRepository.save(track);
    }
}
