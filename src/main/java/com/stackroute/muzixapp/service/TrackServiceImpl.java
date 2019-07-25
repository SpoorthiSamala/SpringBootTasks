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
	public void deleteTrack(int id) {
		trackRepository.deleteById(id);
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
	public boolean UpdateTrack(Track track) throws TrackNotFound {
		if(trackRepository.existsById(track.getId())) {
			boolean result = false;
			Track savedTrack = trackRepository.getOne(track.getId());
			savedTrack.setName(track.getName());
			savedTrack.setComment(track.getComment());
			trackRepository.save(savedTrack);
			if (savedTrack != null) {
				result = true;
			}
			return result;
		}
		else
			throw new TrackNotFound();


	}
	public List<Track> findByName(String name){
		return trackRepository.findByName(name);
	}
}
