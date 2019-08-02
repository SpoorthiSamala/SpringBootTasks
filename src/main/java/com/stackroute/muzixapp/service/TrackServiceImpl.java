package com.stackroute.muzixapp.service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.stackroute.muzixapp.Exception.TrackAlreadyExistsException;
import com.stackroute.muzixapp.Exception.TrackNotFound;
import com.stackroute.muzixapp.repository.TrackRepository;

import org.springframework.beans.factory.annotation.Autowired;

import com.stackroute.muzixapp.domain.Track;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TrackServiceImpl implements TrackService {

  private TrackRepository trackRepository;

  @Autowired
  public TrackServiceImpl(TrackRepository trackRepository) {
    this.trackRepository = trackRepository;
  }


  @Override
  public Track saveTrack(Track track) throws TrackAlreadyExistsException {
    if (trackRepository.existsById(track.getId())) {
      throw new TrackAlreadyExistsException();
    }
    Track savedTrack = trackRepository.save(track);
    if (savedTrack == null) {
      throw new TrackAlreadyExistsException();
    }
    return savedTrack;
  }

  //deleting the track by id
  @Override
  public void deleteTrack(String id) {
    trackRepository.deleteById(id);
  }

  //getting all the tracks
  @Override
  public List<Track> getAllTracks() {
    return trackRepository.findAll();
  }

  @Override
  public Track getTrackById(String id) {
    return trackRepository.save(getTrackById(id));
  }

  //updating the track by setting name and comment
  @Override
  public boolean UpdateTrack(Track track) throws TrackNotFound {
    if (trackRepository.existsById(track.getId())) {
      boolean result = false;
      Track savedTrack = trackRepository.getOne(track.getId());
      savedTrack.setName(track.getName());
      savedTrack.setComment(track.getComment());
      trackRepository.save(savedTrack);
      if (savedTrack != null) {
        result = true;
      }
      return result;
    } else
      throw new TrackNotFound();


  }

  public List<Track> findByName(String name) {
    return trackRepository.findByName(name);
  }

  public void getTopTracks() {
    final String uri = "http://ws.audioscrobbler.com/2.0/?method=geo.gettoptracks&country=spain&api_key=ebce25cf0c684cba5efc22e9c7f5b8bd&format=json";

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);


    //Object Mapper to access the JSON from the response entity
    ObjectMapper mapper = new ObjectMapper();
    JsonNode root = null;

    //read the response body to get JSON object
    try {
      root = mapper.readTree(result.getBody());
      ArrayNode arrayNode = (ArrayNode) root.path("tracks").path("track");

      //iterate the JSON array
      for (int i = 0; i < arrayNode.size(); i++) {
        //get a new Track object and fill it with data using setters
        Track track = new Track();
        track.setName(arrayNode.get(i).path("name").asText());
        track.setComment(arrayNode.get(i).path("artist").path("name").asText());
        //save the track to database
        trackRepository.save(track);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
