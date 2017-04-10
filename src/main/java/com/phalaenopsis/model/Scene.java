package com.phalaenopsis.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Scene {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(optional = false)
	private Play play;
	
	@OneToMany(mappedBy = "scene", cascade = CascadeType.ALL)
	private List<Line> lines;

	private String name;
	
	private Long ordinal;

	public Play getPlay() {
		return play;
	}

	public void setPlay(Play play) {
		this.play = play;
	}

	public List<Line> getLines() {
		return lines;
	}

	public void setLines(List<Line> lines) {
		this.lines = lines;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(Long ordinal) {
		this.ordinal = ordinal;
	}
	
	
}
