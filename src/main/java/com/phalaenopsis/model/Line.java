package com.phalaenopsis.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Line {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JsonBackReference
	private Scene scene;
	
	@Column(name = "scene_id", insertable = false, updatable = false)
	private Long sceneId;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JsonBackReference
	private RoleInPlay roleInPlay;
	
	@Column(nullable=false)
	private String text;
	
	@OneToOne(mappedBy = "line", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	private Voice voice;

	private Long ordinal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public Long getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(Long ordinal) {
		this.ordinal = ordinal;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}	

	public Voice getVoice() {
		return voice;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
	}

	public Long getSceneId() {
		return sceneId;
	}

	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}

	public RoleInPlay getRoleInPlay() {
		return roleInPlay;
	}

	public void setRoleInPlay(RoleInPlay roleInPlay) {
		this.roleInPlay = roleInPlay;
	}

}
