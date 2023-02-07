package com.Library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Library.model.ComicBooks;
import com.Library.repository.ComicBooksRepository;

@Service
public class ComicBooksService {

	@Autowired
	ComicBooksRepository comicBooksRepository;
	
	public void save(int id, ComicBooks entity) {
		if(id != 0) {
			entity.setId(id);
		}
		comicBooksRepository.save(entity);
	}
	
	public ComicBooks find(int id) {
		if (id < 1) {
			return null;
		}
		Optional<ComicBooks> comicBooks = comicBooksRepository.findById(id);

		if (comicBooks.isPresent()) {
			return comicBooks.get();
		}
		return null;
	}

	public List<ComicBooks> findAll() {
		return comicBooksRepository.findAll();
	}
	
	public void delete(int id) {
		ComicBooks comicBooks = find(id);
		comicBooksRepository.delete(comicBooks);
	}
	
	public void update(int id, ComicBooks entity) {	
		entity.setId(id);
		comicBooksRepository.save(entity);				
	}
	
	public List<ComicBooks> findByNota(float nota) {
		return comicBooksRepository.findByNota(nota);
	}
}
