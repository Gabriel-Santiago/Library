package com.Library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Library.model.Photos;
import com.Library.repository.BooksRepository;
import com.Library.repository.ComicBooksRepository;
import com.Library.repository.MangaRepository;
import com.Library.repository.PhotosRepository;

@Service
public class PhotosService {

	@Autowired
	PhotosRepository photosRepository;
	
	@Autowired
	BooksRepository booksRepository;
	
	@Autowired
	ComicBooksRepository comicBooksRepository;
	
	@Autowired
	MangaRepository mangaRepository;
	
	public void save(int id, Photos entity) {
		if(id != 0) {
			entity.setId(id);
		}
		photosRepository.save(entity);
	}
	
	public Photos find(int id) {
		if (id < 1) {
			return null;
		}
		Optional<Photos> photos = photosRepository.findById(id);

		if (photos.isPresent()) {
			return photos.get();
		}
		return null;
	}

	public List<Photos> findAll() {
		return photosRepository.findAll();
	}
	
	public void delete(int id) {
		Photos photos = find(id);
		photosRepository.delete(photos);
	}
	
	public void update(int id, Photos entity) {	
		entity.setId(id);
		photosRepository.save(entity);				
	}
	
	public List<Photos> findAllOfBooks(int books_id) {
		List<Photos> photos = photosRepository.findByBooksId(books_id);
		return photos;
	}
	
	public List<Photos> findAllOfComicsBooks(int comicBooks_id) {
		List<Photos> photos = photosRepository.findByBooksId(comicBooks_id);
		return photos;
	}
	
	public List<Photos> findAllOfManga(int manga_id) {
		List<Photos> photos = photosRepository.findByBooksId(manga_id);
		return photos;
	}
}
