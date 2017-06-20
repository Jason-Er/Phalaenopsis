package com.phalaenopsis.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UploadAudioStatus {

	@JsonProperty("play_name_id")
	private String play;
	@JsonProperty("scene_ordinal")
	private String scene;
	@JsonProperty("line_ordinal")
	private String line;
	private String url;

	public String getPlay() {
		return play;
	}

	public void setPlay(String play) {
		this.play = play;
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
