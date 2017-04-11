package com.phalaenopsis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phalaenopsis.model.Line;
import com.phalaenopsis.repository.LineRepository;
import com.phalaenopsis.repository.PlayRepository;

@Service("lineService")
@Transactional
public class LineServiceImpl implements LineService{

	@Autowired
	private LineRepository lineRepository;
	
	@Override
	public Line findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Line findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveLine(Line line) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateLine(Line line) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteLineById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllLines() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Line> findAllLines() {
		// TODO Auto-generated method stub
		return lineRepository.findAll();
	}

	@Override
	public Page<Line> findByPage(Pageable var1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPlayExist(Line line) {
		// TODO Auto-generated method stub
		return false;
	}

}
