package com.phalaenopsis.controller;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.phalaenopsis.model.Line;
import com.phalaenopsis.model.Play;
import com.phalaenopsis.model.User;
import com.phalaenopsis.service.LineService;
import com.phalaenopsis.service.PlayService;
import com.phalaenopsis.storage.StorageService;
import com.phalaenopsis.util.CustomErrorType;
import com.phalaenopsis.util.StringUtil;
import com.phalaenopsis.util.Tuple;

@RestController
@RequestMapping("/api/v1")
public class RestApiController {
	
	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);
	
	@Autowired
    PlayService playService;
	
	@Autowired
    LineService lineService;
 	
	@Autowired
	StorageService storageService;
	// -------------------Retrieve All Plays---------------------------------------------
	
	@RequestMapping(value = "/play/", method = RequestMethod.GET)
    public ResponseEntity<List<Play>> listAllPlays() {
        List<Play> plays = playService.findAllPlays();
        if (plays.isEmpty()) {
        	logger.info("has not plays!");	
            return new ResponseEntity(HttpStatus.NO_CONTENT);            
        }
        return new ResponseEntity<List<Play>>(plays, HttpStatus.OK);
    }
	
	// -------------------Retrieve Single Play------------------------------------------
	 
    @RequestMapping(value = "/play/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPlay(@PathVariable("id") long id) {
        logger.info("Fetching Play with id {}", id);
        Play play = playService.findById(id);
        if (play == null) {
            logger.error("Play with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Play with id " + id 
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Play>(play, HttpStatus.OK);
    }
    
    // -------------------Create a Play-------------------------------------------
    
    @RequestMapping(value = "/play/", method = RequestMethod.POST)
    public ResponseEntity<?> createPlay(@RequestBody Play play, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Play : {}", play);
         
        playService.savePlay(play);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/v1/play/{id}").buildAndExpand(play.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    // ------------------- Update a User ------------------------------------------------
    
    @RequestMapping(value = "/play/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePlay(@PathVariable("id") long id, @RequestBody Play play) {
        logger.info("Updating Play with id {}", id);
 
        Play currentPlay = playService.findById(id);
 
        if (currentPlay == null) {
            logger.error("Unable to update. Play with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. Play with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
 
        currentPlay = play;
 
        playService.updatePlay(currentPlay);
        return new ResponseEntity<Play>(currentPlay, HttpStatus.OK);
    }
    
    // ------------------- Delete a Play-----------------------------------------
    
    @RequestMapping(value = "/play/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePlay(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting Play with id {}", id);
 
        Play play = playService.findById(id);
        if (play == null) {
            logger.error("Unable to delete. Play with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Play with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        playService.deletePlayById(id);
        return new ResponseEntity<Play>(HttpStatus.NO_CONTENT);
    }
    
    // ------------------- Delete All Users-----------------------------
    
    @RequestMapping(value = "/play/", method = RequestMethod.DELETE)
    public ResponseEntity<Play> deleteAllUsers() {
        logger.info("Deleting All Plays");
 
        playService.deleteAllPlays();
        return new ResponseEntity<Play>(HttpStatus.NO_CONTENT);
    }
    
    // ------------------- Retrieve Single File-----------------------------
    
    @GetMapping("/file/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }
    
    // -------------------Add a File-------------------------------------------
    
    @PostMapping("/file/audio/{play}/{scene}/{line}/")
    @ResponseBody
    public ResponseEntity<?> handleFileUpload(@PathVariable String play,
    		@PathVariable String scene,
    		@PathVariable String line,
    		@RequestParam("file") MultipartFile file,
    		UriComponentsBuilder ucBuilder) {

    	if( StringUtil.isEmpty(play) || StringUtil.isEmpty(scene) || StringUtil.isEmpty(line)) {
    		logger.error("Unable to find parameters: audio with play {} scene {} line {} not found.", play, scene, line);
            return new ResponseEntity(new CustomErrorType("Unable to find parameters: audio with play scene line not found." + play + " not found."),
                    HttpStatus.NOT_FOUND);
    	}
    	// storageService.store(file, Tuple.<String, String, String>of(play,scene,line));
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/v1/play/{id}").buildAndExpand(0).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);        
    }
    
	@RequestMapping(value = "/line/", method = RequestMethod.GET)
    public ResponseEntity<List<Line>> listAllLines() {
        List<Line> lines = lineService.findAllLines();
        if (lines.isEmpty()) {
        	logger.info("has not lines!");	
            return new ResponseEntity(HttpStatus.NO_CONTENT);            
        }
        return new ResponseEntity<List<Line>>(lines, HttpStatus.OK);
    }
	
	
}
