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

import com.Library.model.Books;
import com.Library.service.BooksService;

@RestController
@RequestMapping(path = "/books")
public class BooksController {

	@Autowired
	BooksService service;
	
	@GetMapping
    public ResponseEntity<List<Books>> findAll() {
        return new ResponseEntity<List<Books>>(service.findAll(), HttpStatus.OK);
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<Books> find(@PathVariable("id") int id) {
    	Books books = service.find(id);
		
		if(books != null) {
			return new ResponseEntity<Books>(books, HttpStatus.OK);	
		} else {
			return new ResponseEntity<Books>(HttpStatus.NOT_FOUND);
		}
    }
    
    @GetMapping(path = "/{nota}")
	public ResponseEntity<List<Books>> findByNota(@PathVariable("nota") float nota) {
		return new ResponseEntity<List<Books>>(service.findByNota(nota), HttpStatus.OK);	
		
	}
    
    @PostMapping()
    public void save(@RequestBody Books books) {
        service.save(0, books);
    }
    
    @PutMapping(path = "/{id}")
    public void update(  @PathVariable("id") int id, @RequestBody Books books) {
        service.save(id, books);
    }
    
    @DeleteMapping(path = "/{id}")
    public void delete( @PathVariable("id") int id) {
        service.delete(id);
    }	
}
