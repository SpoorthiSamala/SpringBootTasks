package com.stackroute.muzixapp.service;

import com.stackroute.muzixapp.Exception.TrackAlreadyExistsException;
import com.stackroute.muzixapp.Exception.TrackNotFound;
import com.stackroute.muzixapp.domain.Track;
import com.stackroute.muzixapp.repository.TrackRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.xml.sax.helpers.AttributesImpl;

import javax.swing.plaf.SpinnerUI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrackServiceTest {
  Track track;
  @Mock
  TrackRepository trackRepository;
  @InjectMocks
  TrackServiceImpl trackService;
  List<Track> list=null;

  @Before
  public void setUp(){
    MockitoAnnotations.initMocks(this);
    track=new Track();
    track.setId(1);
    track.setName("spoorthi");
    track.setComment("do the work");
    list=new ArrayList<>();
    list.add(track);
  }
  @Test
  public void saveTrackTest() throws TrackAlreadyExistsException {
    when(trackRepository.save((Track)any())).thenReturn(track);
    Track savedTrack=trackService.saveTrack(track);
    assertEquals(track,savedTrack);
    verify(trackRepository,times(1)).save(track);
  }
  @Test
  public void getAllTracks(){
    trackRepository.save(track);
    when(trackRepository.findAll()).thenReturn(list);
    List<Track> trackList=trackService.getAllTracks();
    Assert.assertEquals(list,trackList);
  }

  @Test
  public void updateTrack() throws TrackNotFound {
    when(trackRepository.save(track)).thenReturn(track);
    Track savedTrack=trackService.UpdateTrack(track);
    assertEquals(track,savedTrack);
    verify(trackRepository,times(1)).save(track);

  }


}
