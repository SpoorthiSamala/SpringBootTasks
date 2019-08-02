package com.stackroute.muzixapp.service;


import java.util.List;

import com.stackroute.muzixapp.Exception.TrackAlreadyExistsException;
import com.stackroute.muzixapp.Exception.TrackNotFound;
import com.stackroute.muzixapp.domain.Track;

public interface TrackService {

	public Track saveTrack(Track track) throws TrackAlreadyExistsException;

	public boolean deleteTrack(int id);

	public List<Track> getAllTracks();

	public Track getTrackById(int id);

	public Track UpdateTrack(Track track) throws TrackNotFound;

//	public List<Track> findByName(String name);



}
