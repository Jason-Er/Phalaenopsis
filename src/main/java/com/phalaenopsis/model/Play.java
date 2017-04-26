package com.phalaenopsis.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.phalaenopsis.model.label.View;

@Entity
public class Play {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long id;

	@OneToMany(mappedBy = "play", cascade = CascadeType.ALL)
	@JsonManagedReference
	@JsonView(View.Play.class)
	private List<Scene> scenes;
	
	@Column(nullable=false)
	private String name;
	@JsonView(View.PlayInfo.class)
	private String stillUrl;
	@JsonView(View.PlayInfo.class)
	private String extract;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Scene> getScenes() {
		return scenes;
	}

	public void setScenes(List<Scene> scenes) {
		this.scenes = scenes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStillUrl() {
		return stillUrl;
	}

	public void setStillUrl(String stillUrl) {
		this.stillUrl = stillUrl;
	}

	public String getExtract() {
		return extract;
	}

	public void setExtract(String extract) {
		this.extract = extract;
	}

}
