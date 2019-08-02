package com.stackroute.muzixapp.repository;

import com.stackroute.MuzixServiceApplication;
import com.stackroute.muzixapp.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MuzixServiceApplication.class)
@DataJpaTest
public class TrackRepositoryTest {

  @Autowired
  TrackRepository trackRepository;
  Track track;
  @Before
  public void setUp(){
    track=new Track();
    track.setId(10);
    track.setName("John");
    track.setComment("nothing to do");
  }
  @After
  public void tearDown(){
    trackRepository.deleteAll();
  }
  @Test
  public void testSaveTrack(){
    trackRepository.save(track);
    Track fetchTrack=trackRepository.findById(track.getId()).get();
    Assert.assertEquals(10,fetchTrack.getId());
  }
  @Test
  public void testGetAllTrack(){
    Track t=new Track(10,"John","nothing");
    Track track1 = new Track(11,"spoo","finish the work");
    trackRepository.save(t);
    trackRepository.save(track1);
    List<Track> list=trackRepository.findAll();
    Assert.assertEquals("John",list.get(0).getName());
  }
  @Test
  public void testDeleteTrack(){
    trackRepository.delete(track);
    List<Track> list=trackRepository.findAll();
    List<Track> trackList=new ArrayList<>();
    Assert.assertEquals(trackList,list);
  }
  @Test
  public void testUpdateTest()
  {
   Track track1= trackRepository.save(track);
    Track track2=trackRepository.findById(track.getId()).get();
    Assert.assertEquals(track1,track2);
  }

}
