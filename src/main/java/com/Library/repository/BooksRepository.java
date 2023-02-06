package com.Library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Library.model.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books, Integer> {
	
	List<Books> findByNota(int nota);

}
