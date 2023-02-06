package com.Library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Library.model.Photos;

@Repository
public interface PhotosRepository extends JpaRepository<Photos, Integer>{

	List<Photos> findByBooksId(int id);
	
	List<Photos> findByComicBooksId(int id);
	
	List<Photos> findByMangaId(int id);
	
}
