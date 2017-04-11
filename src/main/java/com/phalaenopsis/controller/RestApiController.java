package com.phalaenopsis.controller;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.phalaenopsis.model.Line;
import com.phalaenopsis.model.Play;
import com.phalaenopsis.model.User;
import com.phalaenopsis.service.LineService;
import com.phalaenopsis.service.PlayService;
import com.phalaenopsis.util.CustomErrorType;

@RestController
@RequestMapping("/api/v1")
public class RestApiController {
	
	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);
	
	@Autowired
    PlayService playService;
	
	@Autowired
    LineService lineService;
 
	/*
	@RequestMapping(value = "/play/", method = RequestMethod.GET)
    public ResponseEntity<List<Play>> listAllPlays() {
        List<Play> plays = playService.findAllPlays();
        if (plays.isEmpty()) {
        	logger.info("has not plays!");	
            return new ResponseEntity(HttpStatus.NO_CONTENT);            
        }
        return new ResponseEntity<List<Play>>(plays, HttpStatus.OK);
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
	*/
	
	@RequestMapping(value = "/play/", method = RequestMethod.GET)
	@ResponseBody
    public List<Play> listAllPlays() {
        List<Play> plays = playService.findAllPlays();
        if (plays.isEmpty()) {
        	logger.info("has not plays!");	
            return null;
        }
        return plays;
    }
	
	@RequestMapping(value = "/line/", method = RequestMethod.GET)
	@ResponseBody 
    public List<Line> listAllLines() {
        List<Line> lines = lineService.findAllLines();
        if (lines.isEmpty()) {
        	logger.info("has not lines!");	
            return null;            
        }
        return lines;
    }
}
