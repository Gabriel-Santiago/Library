package com.Library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Library.model.ComicBooks;
import com.Library.service.ComicBooksService;

@RestController
@RequestMapping(path = "/ComicBooks")
public class ComicBooksController {

	@Autowired
	ComicBooksService service;
	
	@GetMapping
    public ResponseEntity<List<ComicBooks>> findAll() {
        return new ResponseEntity<List<ComicBooks>>(service.findAll(), HttpStatus.OK);
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<ComicBooks> find(@PathVariable("id") int id) {
    	ComicBooks comicBooks = service.find(id);
		
		if(comicBooks != null) {
			return new ResponseEntity<ComicBooks>(comicBooks, HttpStatus.OK);	
		} else {
			return new ResponseEntity<ComicBooks>(HttpStatus.NOT_FOUND);
		}
    }
    
    @GetMapping(path = "/{nota}")
	public ResponseEntity<List<ComicBooks>> findByNota(@PathVariable("nota") float nota) {
		return new ResponseEntity<List<ComicBooks>>(service.findByNota(nota), HttpStatus.OK);	
		
	}
    
    @PostMapping()
    public void save(@RequestBody ComicBooks ComicBooks) {
        service.save(0, ComicBooks);
    }
    
    @PutMapping(path = "/{id}")
    public void update(  @PathVariable("id") int id, @RequestBody ComicBooks ComicBooks) {
        service.save(id, ComicBooks);
    }
    
    @DeleteMapping(path = "/{id}")
    public void delete( @PathVariable("id") int id) {
        service.delete(id);
    }
}
