package com.Library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Library.model.Books;
import com.Library.repository.BooksRepository;

@Service
public class BooksService {
	
	@Autowired
	BooksRepository booksRepository;
	
	public void save(int id, Books entity) {
		if(id != 0) {
			entity.setId(id);
		}
		booksRepository.save(entity);
	}
	
	public Books find(int id) {
		if (id < 1) {
			return null;
		}
		Optional<Books> books = booksRepository.findById(id);

		if (books.isPresent()) {
			return books.get();
		}
		return null;
	}

	public List<Books> findAll() {
		return booksRepository.findAll();
	}
	
	public void delete(int id) {
		Books books = find(id);
		booksRepository.delete(books);
	}
	
	public void update(int id, Books entity) {	
		entity.setId(id);
		booksRepository.save(entity);				
	}

	public List<Books> findByNota(float nota) {
		return booksRepository.findByNota(nota);
	}
}
