package com.stackroute.muzixapp.service;


import java.util.List;

import com.stackroute.muzixapp.model.Track;

public interface TrackService {

	public Track saveTrack(Track track);

	public void deleteTrack(int id);

	public List<Track> getAllTracks();

	public Track getTrackById(int id);

	public boolean UpdateTrack(Track track);


   
}