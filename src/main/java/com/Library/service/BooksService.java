package com.Library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.Library.model.Books;
import com.Library.repository.BooksRepository;

public class BooksService {
	
	@Autowired
	BooksRepository repository;
	
	public void save(int id, Books entity) {
		if(id != 0) {
			entity.setId(id);
		}
		repository.save(entity);
	}
	
	public Books find(int id) {
		if (id < 1) {
			return null;
		}
		Optional<Books> books = repository.findById(id);

		if (books.isPresent()) {
			return books.get();
		}
		return null;
	}

	public List<Books> findAll() {
		return repository.findAll();
	}
	
	public void delete(int id) {
		Books books = find(id);
		repository.delete(books);
	}
	
	public void update(int id, Books entity) {	
		entity.setId(id);
		repository.save(entity);				
	}

}
