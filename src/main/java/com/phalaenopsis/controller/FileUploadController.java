package com.phalaenopsis.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.phalaenopsis.util.CustomErrorType;
import com.phalaenopsis.util.StringUtil;
import com.phalaenopsis.util.Tuple;

@Controller
@RequestMapping("/file/v1")
public class FileUploadController {

	public static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	private final StorageService storageService;

	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping("/")
	public String listUploadedFiles(Model model) throws IOException {

		model.addAttribute("files",
				storageService.loadAll()
						.map(path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class, "serveFile",
								"play", "1", "1", path.getFileName().toString()).build().toString())
						.collect(Collectors.toList()));

		return "uploadForm";
	}
	
	// ------------------- Retrieve Single File-----------------------------

	@GetMapping("/{play}/{scene}/{line}/audio/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String play, @PathVariable String scene,
			@PathVariable String line, @PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	// -------------------Add a File-------------------------------------------

	@PostMapping("/{play}/{scene}/{line}/audio")
	@ResponseBody
	public ResponseEntity<?> handleFileUpload(@PathVariable String play, @PathVariable String scene,
			@PathVariable String line, @RequestParam("file") MultipartFile file) {

		if (StringUtil.isEmpty(play) || StringUtil.isEmpty(scene) || StringUtil.isEmpty(line)) {
			logger.error("Unable to find parameters: audio with play {} scene {} line {} not found.", play, scene,
					line);
			return new ResponseEntity(
					new CustomErrorType(
							"Unable to find parameters: audio with play scene line not found." + play + " not found."),
					HttpStatus.NOT_FOUND);
		}
		storageService.store(file, Tuple.<String, String, String> of(play, scene, line));
		HttpHeaders headers = new HttpHeaders();
		// headers.setLocation(ucBuilder.path("/api/v1/play/{id}").buildAndExpand(0).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
}
