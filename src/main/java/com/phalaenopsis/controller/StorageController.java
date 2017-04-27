package com.phalaenopsis.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.phalaenopsis.storage.StorageService;
import com.phalaenopsis.storage.UploadAudioStatus;
import com.phalaenopsis.util.AuthUtil;
import com.phalaenopsis.util.CustomErrorType;
import com.phalaenopsis.util.StringUtil;
import com.phalaenopsis.util.Tuple;

@Controller
@RequestMapping("/v1/file")
public class StorageController {

	public static final Logger logger = LoggerFactory.getLogger(StorageController.class);

	private final StorageService storageService;

	@Autowired
	public StorageController(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping("/")
	public String listUploadedFiles(Model model) throws IOException {

		logger.info("User " + AuthUtil.getAuthUserName() + "using addFileUpload!");
		
		model.addAttribute("files",
				storageService.loadAll()
						.map(path -> MvcUriComponentsBuilder.fromMethodName(StorageController.class, "getFileUpload",
								"play", "1", "1", path.getFileName().toString()).build().toString())
						.collect(Collectors.toList()));

		return "uploadForm";
	}

	// ------------------- Retrieve Single File-----------------------------

	@GetMapping("/{play}/{scene}/{line}/audio/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFileUpload(@PathVariable String play, @PathVariable String scene,
			@PathVariable String line, @PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename, Tuple.<String, String, String> of(play, scene, line));
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	// ------------------- Delete Single File-----------------------------

	@DeleteMapping("/{play}/{scene}/{line}/audio/{filename:.+}")
	@ResponseBody
	public ResponseEntity<?> deleteFileUpload(@PathVariable String play, @PathVariable String scene,
			@PathVariable String line, @PathVariable String filename) {

		if (storageService.findResource(filename, Tuple.<String, String, String> of(play, scene, line))) {
			boolean isDelete = storageService.deleteResource(filename,
					Tuple.<String, String, String> of(play, scene, line));
		} else {
			return new ResponseEntity(new CustomErrorType("Play with id " + filename + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(HttpStatus.OK);
	}

	// -------------------Add a File-------------------------------------------

	@PostMapping("/{play}/{scene}/{line}/audio")
	@ResponseBody
	public ResponseEntity<?> addFileUpload(@PathVariable String play, @PathVariable String scene,
			@PathVariable String line, @RequestParam("file") MultipartFile file) {

		logger.info("User " + AuthUtil.getAuthUserName() + "using addFileUpload!");
		
		if (StringUtil.isEmpty(play) || StringUtil.isEmpty(scene) || StringUtil.isEmpty(line)) {
			logger.error("Unable to find parameters: audio with play {} scene {} line {} not found.", play, scene,
					line);
			return new ResponseEntity(
					new CustomErrorType(
							"Unable to find parameters: audio with play scene line not found." + play + " not found."),
					HttpStatus.NOT_FOUND);
		}
		Path path = storageService.store(file, Tuple.<String, String, String> of(play, scene, line));
		String url = MvcUriComponentsBuilder.fromMethodName(StorageController.class, "getFileUpload", play, scene, line,
				path.getFileName().toString()).build().toString();
		UploadAudioStatus uploadAudioStatus = new UploadAudioStatus();
		uploadAudioStatus.setPlay(play);
		uploadAudioStatus.setScene(scene);
		uploadAudioStatus.setLine(line);
		uploadAudioStatus.setUrl(url);
		return new ResponseEntity<UploadAudioStatus>(uploadAudioStatus, HttpStatus.CREATED);
	}

}
