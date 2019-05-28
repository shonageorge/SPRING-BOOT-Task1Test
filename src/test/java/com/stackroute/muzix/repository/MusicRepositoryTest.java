package com.stackroute.muzix.repository;

import com.stackroute.muzix.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MusicRepositoryTest {


@Autowired
    private MusicRepository musicRepository;
    private Track track;
    @Before
    public void setUp()
    {
        track = new Track();
        track.setTrackId(1);
        track.setTrackName("Red");
        track.setTrackComments("Tayler Swift");

    }

    @After
    public void tearDown(){

        musicRepository.deleteAll();
    }


    @Test
    public void testSaveMusic(){
        musicRepository.save(track);
        Track fetchTrack = musicRepository.findById(track.getTrackId()).get();
        Assert.assertEquals(1,fetchTrack.getTrackId());

    }

    @Test
    public void testSaveMusicFailure(){
        Track testTrack = new Track(1,"Faded","Alan Walker");
        musicRepository.save(track);
        Track fetchMusic= musicRepository.findById(track.getTrackId()).get();
        Assert.assertNotSame(testTrack,fetchMusic);
    }

    @Test
    public void testGetAllMusic(){
        Track getTrack = new Track(1,"worth it","fifth harmony");
        musicRepository.save(getTrack);

        List<Track> list = musicRepository.findAll();
        Assert.assertEquals("worth it",list.get(0).getTrackName());

    }

    @Test
    public void testGetAllMusicFailure(){
        Track getTrack = new Track(1,"worth it","fifth harmony");
        musicRepository.save(getTrack);
        Assert.assertNotSame(getTrack,track);
    }

    @Test
    public void testUpdateTrackSuccess(){
        musicRepository.save(track);
        Track fetchTrack = musicRepository.findById(track.getTrackId()).get();
        Assert.assertEquals(1,fetchTrack.getTrackId());

    }

    @Test
    public void testUpdateTrackFailure(){
        Track testUser = new Track(12,"Diamonds","Rihanna");
        musicRepository.save(testUser);
        Track fetchTrack = musicRepository.findById(testUser.getTrackId()).get();
        Assert.assertNotSame(testUser,fetchTrack);
    }

    @Test
    public void testDeleteTrack(){

       musicRepository.deleteById(1);
        Optional<Track> optional =musicRepository.findById(1);
        System.out.println(optional);
        Assert.assertEquals(Optional.empty(),optional);

    }

    @Test
    public void testDeleteTrackFailure(){
        musicRepository.deleteById(1);
        Assert.assertNotSame(null,track.getTrackId());
    }

//    @Test
//    public void testSearchTrack(){
//        musicRepository.findByName("Red");
//
//        System.out.println(track.getTrackId());//Muzix fetchMuzix = muzixRepository.searchByName(muzix.getTrackName());
//        Assert.assertEquals(1,track.getTrackId());
//
//    }
//
//    @Test
//    public void testSearchTrackFailure(){
//        //Muzix testUser = new Muzix(11,"Diamonds","Rihanna");
//        musicRepository.findByName("Perfect");
//        System.out.println(track.getTrackId());
//        //Muzix fetchMuzix = muzixRepository.searchByName(muzix.getTrackName()).addAll();
//        Assert.assertNotSame(null,track.getTrackId());
//    }

}
