package com.phalaenopsis.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.phalaenopsis.storage.StorageService;

@Controller
public class HtmlController {
	
	private final StorageService storageService;

    @Autowired
    public HtmlController(StorageService storageService) {
        this.storageService = storageService;
    }
    
	@GetMapping("/file")
    public String listUploadedFiles(Model model) throws IOException {

		/*
        model.addAttribute("files", storageService
                .loadAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(RestApiController.class, "handleFileUpload", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList()));
		*/
        return "uploadForm";
    }
}
