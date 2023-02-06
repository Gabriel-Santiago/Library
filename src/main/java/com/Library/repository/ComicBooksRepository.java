package com.Library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Library.model.ComicBooks;

@Repository
public interface ComicBooksRepository extends JpaRepository<ComicBooks, Integer>{

	List<ComicBooks> findByNota(int nota);
	
}
