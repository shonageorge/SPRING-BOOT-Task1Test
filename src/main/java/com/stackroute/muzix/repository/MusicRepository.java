package com.stackroute.muzix.repository;

import com.stackroute.muzix.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MusicRepository extends JpaRepository<Track, Integer> {                   //music repository interface that extends jpaRepository

    @Query(value = "select * from track where track_Name=?", nativeQuery = true)           //query for search by name
    public List<Track> findByName(String trackName);

}
