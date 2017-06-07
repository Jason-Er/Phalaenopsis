package com.phalaenopsis.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.phalaenopsis.util.Tuple;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

	void init();	
	
	Path storeAudio(String username, MultipartFile file, Tuple tuple);

	Path storeStill(MultipartFile file, String play);
	
	Stream<Path> loadAll();

	Path load(String filename);

	Resource loadStillAsResource(String filename, String play);
	
	Resource loadAudioAsResource(String username, String filename, Tuple tuple);

	boolean findAudioResource(String filename, Tuple tuple);

	boolean findStillResource(String filename, String play);
	
	boolean deleteAudioResource(String filename, Tuple tuple);

	boolean deleteStillResource(String filename, String play);
	
	void deleteAll();

}
