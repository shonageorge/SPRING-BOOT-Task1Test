package com.stackroute.muzix.service;

import com.stackroute.muzix.exception.TrackAlreadyExistException;
import com.stackroute.muzix.exception.TrackNotFoundException;
import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.repository.MusicRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MusicServiceTest {

    private Track track;

    //Create a mock for musicRepository
    @Mock
    private MusicRepository musicRepository;

    //Inject the mocks as dependencies into musicServiceImpl
    @InjectMocks
    private MusicServiceImpl musicService;
    List<Track> list= null;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setTrackId(5);
        track.setTrackName("Nan");
        track.setTrackComments("Tamil");
        list = new ArrayList<>();
        list.add(track);
    }

    @Test                                                                                    //test for saveTrack method in music service
    public void saveUserTestSuccess() throws TrackAlreadyExistException {

        when(musicRepository.save((Track)any())).thenReturn(track);
        Track saved = musicService.saveTrack(track);
        Assert.assertEquals(track,saved);

        //verify here verifies that userRepository save method is only called once
        verify(musicRepository,times(1)).save(track);

    }

    @Test(expected = TrackAlreadyExistException.class)
    public void saveUserTestFailure() throws TrackAlreadyExistException {
        when(musicRepository.save((Track) any())).thenReturn(null);
        when(musicRepository.existsById(track.getTrackId())).thenReturn(true);
        Track saved = musicService.saveTrack(track);
        System.out.println("savedTrack" + saved);
        Assert.assertEquals(track,saved);

    }

    @Test                                                                             //test for getAllTrack method in music service
    public void getAllUser() throws TrackNotFoundException{

        musicRepository.save(track);
        when(musicRepository.findAll()).thenReturn(list);
        List<Track> userlist = musicService.getAllTrack();
        Assert.assertEquals(list,userlist);
    }

    @Test                                                                               //test for updateComment method in music service
    public void updateTestSuccess() throws TrackNotFoundException {

        when(musicRepository.save((Track)any())).thenReturn(track);
        when(musicRepository.existsById(track.getTrackId())).thenReturn(true);
        Track saved = musicService.updateComment(track);
        Assert.assertEquals(track,saved);

        //verify here verifies that userRepository save method is only called once
        verify(musicRepository,times(1)).save(track);

    }

    @Test(expected = TrackNotFoundException.class)
    public void updateTestFailure() throws TrackNotFoundException {
        when(musicRepository.save((Track) any())).thenReturn(null);
        Track savedTrack = musicService.updateComment(track);
        System.out.println("savedMuzix" + savedTrack);
        Assert.assertEquals(track,savedTrack);

    }

    @Test                                                                                           //test for deleteTrack method in music service
    public void deleteTestSuccess() throws TrackNotFoundException {
        musicRepository.deleteById(track.getTrackId());
        when(musicRepository.findAll()).thenReturn(list);
        when(musicRepository.existsById(track.getTrackId())).thenReturn(true);
        List<Track> deleteTrack = musicService.deleteTrack(track.getTrackId());
        Assert.assertEquals(list,deleteTrack);

        //verify here verifies that userRepository save method is only called once
        verify(musicRepository,times(1));

    }
    @Test(expected = TrackNotFoundException.class)
    public void deleteTestFailure() throws TrackNotFoundException {
        musicRepository.deleteById(track.getTrackId());
        when(musicRepository.findAll()).thenReturn(null);
        List<Track> deleteTrack = musicService.deleteTrack(track.getTrackId());
        Assert.assertEquals(list,deleteTrack);
    }

    @Test                                                                                   //test for findByName method in music service
    public void getByName() throws TrackNotFoundException {

        musicRepository.save(track);
        when(musicRepository.findAll()).thenReturn(list);
        when(musicRepository.equals(track.getTrackName())).thenReturn(true);
        List<Track> userlist = musicService.findByName(track.getTrackName());
        Assert.assertEquals(musicRepository,userlist);
    }



    @After
    public void tearDown() throws Exception {
    }
}