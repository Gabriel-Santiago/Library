package com.Library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Library.model.Manga;
import com.Library.repository.MangaRepository;

@Service
public class MangaService {

	@Autowired
	MangaRepository mangaRepository;
	
	public void save(int id, Manga entity) {
		if(id != 0) {
			entity.setId(id);
		}
		mangaRepository.save(entity);
	}
	
	public Manga find(int id) {
		if (id < 1) {
			return null;
		}
		Optional<Manga> manga = mangaRepository.findById(id);

		if (manga.isPresent()) {
			return manga.get();
		}
		return null;
	}

	public List<Manga> findAll() {
		return mangaRepository.findAll();
	}
	
	public void delete(int id) {
		Manga manga = find(id);
		mangaRepository.delete(manga);
	}
	
	public void update(int id, Manga entity) {	
		entity.setId(id);
		mangaRepository.save(entity);				
	}

	public List<Manga> findByNota(double nota) {
		return mangaRepository.findByNota(nota);
	}
}
