package com.phalaenopsis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phalaenopsis.model.Play;
import com.phalaenopsis.repository.PlayRepository;

@Service("playService")
@Transactional
public class PlayServiceImpl implements PlayService{

	@Autowired
	private PlayRepository playRepository;
	
	@Override
	public Play findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Play findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePlay(Play play) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePlay(Play play) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePlayById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllPlays() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Play> findAllPlays() {
		return playRepository.findAll();
	}

	@Override
	public Page<Play> findByPage(Pageable var1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPlayExist(Play play) {
		// TODO Auto-generated method stub
		return false;
	}

}