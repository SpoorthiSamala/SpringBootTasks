package com.stackroute.muzixapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.MuzixServiceApplication;
import com.stackroute.muzixapp.domain.Track;
import com.stackroute.muzixapp.service.TrackService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MuzixServiceApplication.class)
@WebMvcTest
public class TrackControllerTest {
  @Autowired
  private MockMvc mockMvc;
  private Track track;
  @MockBean
  private TrackService trackService;
  @InjectMocks
  private TrackController trackController;
  private List<Track> list=null;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    mockMvc= MockMvcBuilders.standaloneSetup(trackController).build();
    track=new Track();
    track.setId(1);
    track.setName("spoorthi");
    track.setComment("hiii");
    list=new ArrayList<>();
    list.add(track);
  }
  @Test
  public void testSaveTrack() throws Exception{
    when(trackService.saveTrack(any())).thenReturn(track);
    mockMvc.perform(post("/api/v1/track")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .accept(MediaType.APPLICATION_JSON)
      .content((asJsonString(track))))
      .andExpect(status().isCreated())
      .andDo(print());
  }
  @Test
  public void getAllTracks() throws Exception{
    when(trackService.getAllTracks()).thenReturn(list);
    mockMvc.perform(get("/api/v1/track")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .accept(MediaType.APPLICATION_JSON)
      .content(asJsonString(track)))
      .andExpect(status().isOk())
      .andDo(print());


  }
  @Test
  public void deleteTrack() throws Exception{
    when(trackService.deleteTrack(track.getId())).thenReturn(true);
    mockMvc.perform(delete("/api/v1/delete")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .accept(MediaType.APPLICATION_JSON)
      .content(asJsonString(track)))
      .andExpect(status().isOk())
      .andDo(print());


  }
  @Test
  public void updateTrack() throws Exception{
    when(trackService.getAllTracks()).thenReturn(list);
    mockMvc.perform(put("/api/v1/update")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .accept(MediaType.APPLICATION_JSON)
      .content(asJsonString(track)))
      .andExpect(status().isOk())
      .andDo(print());


  }

  private static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }
  @After
  public void tearDown() throws Exception {
  }
}
