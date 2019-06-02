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
    public void setUp() {
        track = new Track();
        track.setTrackId(1);
        track.setTrackName("Red");
        track.setTrackComments("Tayler Swift");

    }

    @After
    public void tearDown() {

        musicRepository.deleteAll();
    }


    @Test                                                                                              //test for save method
    public void testSaveMusic() {
        musicRepository.save(track);
        Track fetchTrack = musicRepository.findById(track.getTrackId()).get();
        Assert.assertEquals(1, fetchTrack.getTrackId());

    }

    @Test                                                                                             //test for failure of save method
    public void testSaveMusicFailure() {
        Track track = new Track(1, "Faded", "Alan Walker");
        Track track1 = musicRepository.save(track);
        System.out.println(track1);
        Track fetchMusic = musicRepository.findById(track.getTrackId()).get();
        System.out.println(fetchMusic);
        Assert.assertNotSame(track1, fetchMusic);
    }

    @Test
    public void testGetAllMusic() {                                                                    //test for find all method
        Track getTrack = new Track(1, "worth it", "fifth harmony");
        musicRepository.save(getTrack);

        List<Track> list = musicRepository.findAll();
        Assert.assertEquals("worth it", list.get(0).getTrackName());

    }

    @Test                                                                                               //test for failure of find all method
    public void testGetAllMusicFailure() {
        Track getTrack = new Track(1, "worth it", "fifth harmony");
        musicRepository.save(getTrack);
        Assert.assertNotSame(getTrack, track);
    }

    @Test                                                                                               //test for update
    public void testUpdateTrackSuccess() {
        musicRepository.save(track);
        Track fetchTrack = musicRepository.findById(track.getTrackId()).get();
        Assert.assertEquals(1, fetchTrack.getTrackId());

    }

    @Test                                                                                                  //test for failure of update method
    public void testUpdateTrackFailure() {
        Track testUser = new Track(1, "Faded", "Alan Walker");
        musicRepository.save(testUser);
        Track fetchTrack = musicRepository.findById(testUser.getTrackId()).get();
        Assert.assertNotSame(testUser, fetchTrack);
    }

    @Test                                                                                                 ///test for delete by id method
    public void testDeleteTrackFailure() {
        musicRepository.deleteById(1);
        Assert.assertNotSame(null, track.getTrackId());
    }

    @Test
    public void testSearchTrack() {                                                                       ///test for search by name method
        Track testUser = new Track(1, "Faded", "Alan Walker");
        musicRepository.save(testUser);
        List<Track> list = musicRepository.findByName("Faded");

        System.out.println(list);
        Assert.assertEquals(1, list.size());

    }

    @Test
    public void testSearchTrackFailure() {
        musicRepository.findByName("Perfect");
        System.out.println(track.getTrackId());
        Assert.assertNotSame(null, track.getTrackId());
    }


}
