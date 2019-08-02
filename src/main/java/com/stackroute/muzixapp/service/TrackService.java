package com.stackroute.muzixapp.service;


import java.util.List;

import com.stackroute.muzixapp.Exception.TrackAlreadyExistsException;
import com.stackroute.muzixapp.Exception.TrackNotFound;
import com.stackroute.muzixapp.domain.Track;

public interface TrackService {

	public Track saveTrack(Track track) throws TrackAlreadyExistsException;

	public void deleteTrack(String id);

	public List<Track> getAllTracks();

	public Track getTrackById(String id);

	public boolean UpdateTrack(Track track) throws TrackNotFound;

	public List<Track> findByName(String name);

  void getTopTracks();



}
