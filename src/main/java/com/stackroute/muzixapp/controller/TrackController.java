package com.stackroute.muzixapp.controller;

import com.stackroute.muzixapp.model.Track;
import com.stackroute.muzixapp.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
public class TrackController {

	ResponseEntity responseEntity;
	@Autowired
	private TrackService trackService;

	public TrackController(TrackService trackService)
	{
		this.trackService=trackService;
	}
	//to save the track
	@PostMapping("track")
	public ResponseEntity<?> saveUser(@RequestBody Track track) {

		try
		{
		trackService.saveTrack(track);
		responseEntity = new ResponseEntity<String>("successfully created", HttpStatus.CREATED);
	}
	catch (Exception e){
		responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
	}
		return responseEntity;

	}
	//to get all the tracks
	@GetMapping("track")
	public ResponseEntity<?> getAllUsers(){
		return new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
	}
	//to delete a track
	@DeleteMapping("delete")
	public ResponseEntity<?> deleteTrack(@RequestBody Track track)
	{

		try {
			trackService.deleteTrack(track.getId());
			responseEntity = new ResponseEntity<String>("successfully deleted", HttpStatus.OK);
		}
		catch (Exception e){
			responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
		}
		return responseEntity;
	}
	//to update a track
	@PutMapping("update")
	public ResponseEntity<?> updateTrack(@RequestBody Track track)
	{

		try {
			trackService.UpdateTrack(track);
			responseEntity = new ResponseEntity<Track>(track, HttpStatus.OK);
		}
		catch (Exception e){
			responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
		}
		return responseEntity;
	}

}