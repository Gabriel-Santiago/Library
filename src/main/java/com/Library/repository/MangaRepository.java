package com.Library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Library.model.Manga;

@Repository
public interface MangaRepository extends JpaRepository<Manga, Integer>{

	List<Manga> findByNota(double nota);
	
}
