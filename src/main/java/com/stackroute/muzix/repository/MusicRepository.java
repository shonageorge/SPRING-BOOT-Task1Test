package com.stackroute.muzix.repository;

import com.stackroute.muzix.domain.Track;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MusicRepository extends MongoRepository<Track,Integer> {
//    @Query("update track set trackName='Hello' Where id=1 ")
//    public Track update();
//    @Query(value = "select * from track where track_Name=?")
//    public List<Track> findByName(String trackName);

}
