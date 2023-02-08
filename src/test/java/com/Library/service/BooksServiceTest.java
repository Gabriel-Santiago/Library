package com.Library.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Library.model.Books;
import com.Library.model.Photos;
import com.Library.repository.BooksRepository;

public class BooksServiceTest {
	
	private static final int ID = 1;
	private static final String NOME = "Gente Pobre";
	private static final double NOTA = 8.64;

	
	// Photos
	private static final int ID_1 = 1;
	private static final String NOME_1 = "Teste";
	private static final String TIPO = "PNG";
	
	@InjectMocks
	private BooksService service;
	
	@Mock
	private BooksRepository repository;
	
	private Books books;
	
	private Photos photos;
	
	private void start() {
		
		photos = new Photos(ID_1, NOME_1, TIPO, null, books, null, null);
		
		books = new Books(ID, NOME, NOTA, photos);
	}
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		start();
	}
	
	@Test
	public void whenFindByIdThenReturnNullIfIdLessThan1() {
		Books response = service.find(0);

		assertNull(response);
	}
	
	@Test
	public void whenSaveVerifySuccess() throws ParseException, IOException {
		when(repository.save(any())).thenReturn(books);
		service.save(1, books);
		verify(repository).save(any());
	}
	
	@Test
	public void whenUpdateVerifySuccess() throws ParseException, IOException {
		when(repository.save(any())).thenReturn(books);
		service.update(ID, books);;
		verify(repository).save(any()); 
	}
	

	@Test
	public void whenFindByIdThenReturnNullIfOptionalNotPresent() {
		when(repository.findById(anyInt())).thenReturn(Optional.empty());

		Books response = service.find(ID);

		assertNull(response);
	}	
	
	@Test
	public void whenDeleteVerifySuccess() {
		when(repository.findById(anyInt())).thenReturn(Optional.of(books));
		doNothing().when(repository).delete(books);
			
		service.delete(ID);

		verify(repository).findById(anyInt());
		verify(repository).delete(any());
	}
	
	@Test
	public void whenFindAllThenReturnAnList() {
		when(repository.findAll()).thenReturn(List.of(books));

		List<Books> response = service.findAll();

		assertNotNull(response);
		assertEquals(ID, response.get(0).getId());
		assertEquals(NOME, response.get(0).getNome());
		assertEquals(NOTA, response.get(0).getNota());
		assertEquals(photos, response.get(0).getPhotos());
	}
	
	@Test
	public void whenFindAllOfUserThenReturnAnList() {
		when(repository.findAll()).thenReturn(List.of(books));

		List<Books> response = service.findByNota(8.64);

		assertNotNull(response);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
}
