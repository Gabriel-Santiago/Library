package com.Library.controller;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Library.model.Photos;
import com.Library.service.PhotosService;

@RestController
@RequestMapping(path = "/photos")
public class PhotosController {

	@Autowired
	PhotosService service;
	
	@GetMapping("/info/{id}") 
	public Photos getPhotosInfo(@PathVariable("id") int id) {
        return service.find(id);      
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<byte[]> getPhotos(@PathVariable("id") int id){

        Photos photos = service.find(id);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(photos.getTipo()))
                .body(photos.getPhoto());
    }
    
    @DeleteMapping("/{id}")
	public void deletePhotos(@PathVariable("id") int id) {
		service.delete(id);
	}
    
    @PostMapping("/books/{id}/photos") 
	public void uploadPhotoBook(@PathVariable("id") int id,@RequestParam("files") MultipartFile[] files) throws IOException {
		
	    Arrays.asList(files).stream().forEach(file -> {
	    	Photos photoBook = new Photos();

			try {
				photoBook.setPhoto(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			photoBook.setTipo(file.getContentType());
			service.save(id, photoBook);
	    });
    }
    
    @PostMapping("/comicBooks/{id}/photos") 
	public void uploadPhotoComicBook(@PathVariable("id") int id,@RequestParam("files") MultipartFile[] files) throws IOException {
		
	    Arrays.asList(files).stream().forEach(file -> {
	    	Photos photoComicBook = new Photos();

			try {
				photoComicBook.setPhoto(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			photoComicBook.setTipo(file.getContentType());
			service.save(id, photoComicBook);
	    });
    }
    
    @PostMapping("/manga/{id}/photos") 
	public void uploadPhotoManga(@PathVariable("id") int id,@RequestParam("files") MultipartFile[] files) throws IOException {
		
	    Arrays.asList(files).stream().forEach(file -> {
	    	Photos photoManga = new Photos();

			try {
				photoManga.setPhoto(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			photoManga.setTipo(file.getContentType());
			service.save(id, photoManga);
	    });
    }
}
