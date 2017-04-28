package com.phalaenopsis.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.phalaenopsis.util.Tuple;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

	private final Path rootLocation;

	@Autowired
	public FileSystemStorageService(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Override
	public Path store(MultipartFile file, Tuple tuple) {
		Path filePath = null;
		try {
			Path path = Files.createDirectories(
					Paths.get(rootLocation.toString() + "/" + tuple._1().get() + "/" + tuple._2().get()));

			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
			}
			String fileName = file.getOriginalFilename();
			String prefix = fileName.substring(fileName.lastIndexOf("."));
			CopyOption[] options = new CopyOption[] { REPLACE_EXISTING };
			Files.copy(file.getInputStream(), path.resolve(tuple._3().get() + prefix), options);
			filePath = path.resolve(tuple._3().get() + prefix);
		} catch (IOException e) {
			throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
		}
		return filePath;
	}
	
	@Override
	public Path storeStill(MultipartFile file, String play) {
		Path filePath = null;
		try {
			Path path = Files.createDirectories(
					Paths.get(rootLocation.toString() + "/" + play));

			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
			}
			String fileName = file.getOriginalFilename();
			String prefix = fileName.substring(fileName.lastIndexOf("."));
			CopyOption[] options = new CopyOption[] { REPLACE_EXISTING };
			Files.copy(file.getInputStream(), path.resolve(fileName), options);
			filePath = path.resolve(fileName);
		} catch (IOException e) {
			throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
		}
		return filePath;
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation).filter(path -> !path.equals(this.rootLocation))
					.filter(path -> Files.isRegularFile(path)).map(path -> this.rootLocation.relativize(path));
		} catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}

	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename, Tuple tuple) {
		try {
			Path file = Paths
					.get(rootLocation.toString() + "/" + tuple._1().get() + "/" + tuple._2().get() + "/" + filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageFileNotFoundException("Could not read file: " + filename);
			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	@Override
	public Resource loadStillAsResource(String filename, String play) {
		try {
			Path file = Paths
					.get(rootLocation.toString() + "/" + play + "/" + filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageFileNotFoundException("Could not read file: " + filename);
			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}
	
	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		try {
			Files.createDirectory(rootLocation);
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}

	@Override
	public boolean findResource(String filename, Tuple tuple) {
		Path path = Paths
				.get(rootLocation.toString() + "/" + tuple._1().get() + "/" + tuple._2().get() + "/" + filename);
		return Files.isRegularFile(path);
	}

	@Override
	public boolean findStillResource(String filename, String play) {
		Path path = Paths
				.get(rootLocation.toString() + "/" + play + "/" + filename);
		return Files.isRegularFile(path);
	}
	
	@Override
	public boolean deleteResource(String filename, Tuple tuple) {
		boolean status = false;
		Path path = Paths
				.get(rootLocation.toString() + "/" + tuple._1().get() + "/" + tuple._2().get() + "/" + filename);
		try {
			Files.delete(path);
			status = true;
		} catch (IOException e) {
			status = false;
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean deleteStillResource(String filename, String play) {
		boolean status = false;
		Path path = Paths
				.get(rootLocation.toString() + "/" + play + "/" + filename);
		try {
			Files.delete(path);
			status = true;
		} catch (IOException e) {
			status = false;
			e.printStackTrace();
		}
		return status;
	}
	
}
