package com.stackroute.muzixapp.service;

import java.util.List;

import com.stackroute.muzixapp.Exception.TrackAlreadyExistsException;
import com.stackroute.muzixapp.Exception.TrackNotFound;
import com.stackroute.muzixapp.repository.TrackRepository;

import org.springframework.beans.factory.annotation.Autowired;

import com.stackroute.muzixapp.domain.Track;
import org.springframework.stereotype.Service;

@Service
public class TrackServiceImpl implements TrackService {

	private TrackRepository trackRepository;

	@Autowired
	public TrackServiceImpl(TrackRepository trackRepository) {
		this.trackRepository=trackRepository;
	}


	@Override
	public Track saveTrack(Track track) throws TrackAlreadyExistsException {
		if(trackRepository.existsById(track.getId())){
			throw new TrackAlreadyExistsException();
		}
		Track savedTrack=trackRepository.save(track);
		if(savedTrack==null)
		{
			throw new TrackAlreadyExistsException();
		}
		return savedTrack;
	}
	//deleting the track by id
	@Override
	public boolean deleteTrack(int id) {
		trackRepository.deleteById(id);
		return true;
	}
	//getting all the tracks
	@Override
	public List<Track> getAllTracks() {
		return trackRepository.findAll();
	}

	@Override
	public Track getTrackById(int id) {
		return trackRepository.save(getTrackById(id));
	}
	//updating the track by setting name and comment
	@Override
	public Track UpdateTrack(Track track) throws TrackNotFound {
    if (trackRepository.existsById(track.getId())) {
      Track savedTrack = (Track)trackRepository.findAll();
    }
    return saveTrack(track);
  }
//	public List<Track> findByName(String name){
//		return trackRepository.findByName(name);
//	}
}
