package com.stackroute.muzixapp.repository;

import com.stackroute.muzixapp.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track,Integer>{
    @Query("SELECT t FROM Track t WHERE t.name=?1")
    public List<Track> findByName(@Param("name") String name);
}
