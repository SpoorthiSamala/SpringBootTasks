package com.stackroute.muzixapp.controller;

import com.stackroute.muzixapp.Exception.TrackAlreadyExistsException;
import com.stackroute.muzixapp.Exception.TrackNotFound;
import com.stackroute.muzixapp.domain.Track;
import com.stackroute.muzixapp.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
public class TrackController {
	private TrackService trackService;
  @Autowired
	public TrackController(TrackService trackService)
	{
		this.trackService=trackService;
	}
	//to save the track
	@PostMapping("track")
	public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackAlreadyExistsException{
		ResponseEntity responseEntity;
		trackService.saveTrack(track);
		responseEntity = new ResponseEntity<String>("successfully created", HttpStatus.CREATED);
		return responseEntity;

	}
	//to get all the tracks
	@GetMapping("track")
	public ResponseEntity<?> getAllTracks(){
		return new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
	}
	//to delete a track
	@DeleteMapping("delete")
	public ResponseEntity<?> deleteTrack(@RequestBody Track track)
	{
		ResponseEntity responseEntity;
		try{
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
	public ResponseEntity<?> updateTrack(@RequestBody Track track) throws TrackNotFound
	{
		ResponseEntity responseEntity;
			trackService.UpdateTrack(track);
			responseEntity = new ResponseEntity<Track>(track, HttpStatus.OK);
		return responseEntity;
	}
/*	@GetMapping("/retrieve/{name}")
	public ResponseEntity<?> findByName(@PathVariable(value = "name") String name){
		ResponseEntity responseEntity;
		try {
			responseEntity= new ResponseEntity<List>(trackService.findByName(name),HttpStatus.OK);
		}
		catch (Exception e){
			responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
		}
		return responseEntity;
	}*/

}
