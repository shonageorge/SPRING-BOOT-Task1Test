package com.stackroute.muzix.service;

import com.stackroute.muzix.Exception.AlreadyExistException;
import com.stackroute.muzix.Exception.TrackNotFound;
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

import static org.junit.Assert.*;
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

    @Test
    public void saveUserTestSuccess() throws AlreadyExistException {

        when(musicRepository.save((Track)any())).thenReturn(track);
        Track saved = musicService.saveTrack(track);
        Assert.assertEquals(track,saved);

        //verify here verifies that userRepository save method is only called once
        verify(musicRepository,times(1)).save(track);

    }

    @Test(expected = AlreadyExistException.class)
    public void saveUserTestFailure() throws AlreadyExistException {
        when(musicRepository.save((Track) any())).thenReturn(null);
        when(musicRepository.existsById(track.getTrackId())).thenReturn(true);
        Track saved = musicService.saveTrack(track);
        System.out.println("savedTrack" + saved);
        Assert.assertEquals(track,saved);

      /*doThrow(new UserAlreadyExistException()).when(userRepository).findById(eq(101));
      userService.saveUser(user);*/


    }

    @Test
    public void getAllUserSuccess()throws TrackNotFound{

        musicRepository.save(track);
        when(musicRepository.findAll()).thenReturn(list);

        List<Track> userlist = musicService.getAllTrack();
        Assert.assertEquals(list,userlist);
    }

    @Test
    public void updateTestSuccess() throws TrackNotFound {

        when(musicRepository.save((Track)any())).thenReturn(track);
        when(musicRepository.existsById(track.getTrackId())).thenReturn(true);
        Track saved = musicService.updateComment(track);
        Assert.assertEquals(track,saved);

        //verify here verifies that userRepository save method is only called once
        verify(musicRepository,times(1)).save(track);

    }

    @Test(expected = TrackNotFound.class)
    public void updateTestFailure() throws TrackNotFound {
        when(musicRepository.save((Track) any())).thenReturn(null);
        Track savedTrack = musicService.updateComment(track);
        System.out.println("savedMuzix" + savedTrack);
        Assert.assertEquals(track,savedTrack);

    }

    @Test
    public void deleteTestSuccess() throws TrackNotFound {
        musicRepository.deleteById(track.getTrackId());
        when(musicRepository.findAll()).thenReturn(list);
        when(musicRepository.existsById(track.getTrackId())).thenReturn(true);
        List<Track> deleteTrack = musicService.deleteTrack(track.getTrackId());
        Assert.assertEquals(list,deleteTrack);

        //verify here verifies that userRepository save method is only called once
        verify(musicRepository,times(1));

    }
    @Test(expected = TrackNotFound.class)
    public void deleteTestFailure() throws TrackNotFound {
        musicRepository.deleteById(track.getTrackId());
        when(musicRepository.findAll()).thenReturn(null);
        List<Track> deleteTrack = musicService.deleteTrack(track.getTrackId());
        Assert.assertEquals(list,deleteTrack);
    }

    @Test
//    public void getByName() throws TrackNotFound{
//
//        musicRepository.save(track);
//        when(musicRepository.findAll()).thenReturn(list);
//        when(musicRepository.equals(track.getTrackName())).thenReturn(true);
//        List<Track> userlist = musicService.findByName(track.getTrackName());
//        Assert.assertEquals(list,userlist);
//    }



    @After
    public void tearDown() throws Exception {
    }
}