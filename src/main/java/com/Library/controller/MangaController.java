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

import com.Library.model.Manga;
import com.Library.service.MangaService;

@RestController
@RequestMapping(path = "/manga")
public class MangaController {

	@Autowired
	MangaService service;
	
	@GetMapping
    public ResponseEntity<List<Manga>> findAll() {
        return new ResponseEntity<List<Manga>>(service.findAll(), HttpStatus.OK);
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<Manga> find(@PathVariable("id") int id) {
    	Manga manga = service.find(id);
		
		if(manga != null) {
			return new ResponseEntity<Manga>(manga, HttpStatus.OK);	
		} else {
			return new ResponseEntity<Manga>(HttpStatus.NOT_FOUND);
		}
    }
    
    @GetMapping(path = "/search")
    public ResponseEntity<Manga> findByGenero(@PathVariable("genero") String genero) {
    	Manga manga = service.findByGenero(genero);
		
		if(manga != null) {
			return new ResponseEntity<Manga>(manga, HttpStatus.OK);	
		} else {
			return new ResponseEntity<Manga>(HttpStatus.NOT_FOUND);
		}
    }
    
    @GetMapping(path = "/searchNota")
	public ResponseEntity<List<Manga>> findByNota(@PathVariable("nota") double nota) {
		return new ResponseEntity<List<Manga>>(service.findByNota(nota), HttpStatus.OK);	
		
	}
    
    @PostMapping()
    public void save(@RequestBody Manga manga) {
        service.save(0, manga);
    }
    
    @PutMapping(path = "/{id}")
    public void update(  @PathVariable("id") int id, @RequestBody Manga manga) {
        service.save(id, manga);
    }
    
    @DeleteMapping(path = "/{id}")
    public void delete( @PathVariable("id") int id) {
        service.delete(id);
    }
}
