package com.phalaenopsis.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.phalaenopsis.model.Line;

public interface LineService {
	Line findById(Long id);

	Line findByName(String name);

	void saveLine(Line line);

	void updateLine(Line line);

	void deleteLineById(Long id);

	void deleteAllLines();

	List<Line> findAllLines();

	Page<Line> findByPage(Pageable var1);

	boolean isPlayExist(Line line);
}
