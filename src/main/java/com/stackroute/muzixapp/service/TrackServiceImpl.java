package com.stackroute.muzixapp.service;

import java.util.List;

import javax.transaction.Transactional;

import com.stackroute.muzixapp.repository.TrackRepository;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stackroute.muzixapp.model.Track;
import org.springframework.stereotype.Service;

@Service
public class TrackServiceImpl implements TrackService {

	private TrackRepository trackRepository;

	@Autowired
	public TrackServiceImpl(TrackRepository trackRepository) {
		this.trackRepository=trackRepository;
	}


	@Override
	public Track saveTrack(Track track) {
		Track savedTrack=trackRepository.save(track);
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
	public boolean UpdateTrack(Track track) {
		boolean result=false;
		Track savedTrack=trackRepository.getOne(track.getId());
		savedTrack.setName(track.getName());
		savedTrack.setComment(track.getComment());
		trackRepository.save(savedTrack);
		if(savedTrack!=null){
			result=true;
		}
			return result;

	}
	public List<Track> findByName(String name){
		return trackRepository.findByName(name);
	}
}
