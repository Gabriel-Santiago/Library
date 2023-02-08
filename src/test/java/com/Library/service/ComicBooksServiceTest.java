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

import com.Library.model.ComicBooks;
import com.Library.model.Photos;
import com.Library.repository.ComicBooksRepository;

public class ComicBooksServiceTest {

	private static final int ID = 1;
	private static final String NOME = "Batman: A piada Mortal";
	private static final double NOTA = 9.63;

	
	// Photos
	private static final int ID_1 = 1;
	private static final String NOME_1 = "Teste";
	private static final String TIPO = "PNG";
	
	@InjectMocks
	private ComicBooksService service;
	
	@Mock
	private ComicBooksRepository repository;
	
	private ComicBooks comicBooks;
	
	private Photos photos;
	
	private void start() {
		
		photos = new Photos(ID_1, NOME_1, TIPO, null, null, comicBooks, null);
		
		comicBooks = new ComicBooks(ID, NOME, NOTA, photos);
	}
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		start();
	}
	
	@Test
	public void whenFindByIdThenReturnNullIfIdLessThan1() {
		ComicBooks response = service.find(0);

		assertNull(response);
	}
	
	@Test
	public void whenSaveVerifySuccess() throws ParseException, IOException {
		when(repository.save(any())).thenReturn(comicBooks);
		service.save(1, comicBooks);
		verify(repository).save(any());
	}
	
	@Test
	public void whenUpdateVerifySuccess() throws ParseException, IOException {
		when(repository.save(any())).thenReturn(comicBooks);
		service.update(ID, comicBooks);;
		verify(repository).save(any()); 
	}
	

	@Test
	public void whenFindByIdThenReturnNullIfOptionalNotPresent() {
		when(repository.findById(anyInt())).thenReturn(Optional.empty());

		ComicBooks response = service.find(ID);

		assertNull(response);
	}	
	
	@Test
	public void whenDeleteVerifySuccess() {
		when(repository.findById(anyInt())).thenReturn(Optional.of(comicBooks));
		doNothing().when(repository).delete(comicBooks);
			
		service.delete(ID);

		verify(repository).findById(anyInt());
		verify(repository).delete(any());
	}
	
	@Test
	public void whenFindAllThenReturnAnList() {
		when(repository.findAll()).thenReturn(List.of(comicBooks));

		List<ComicBooks> response = service.findAll();

		assertNotNull(response);
		assertEquals(ID, response.get(0).getId());
		assertEquals(NOME, response.get(0).getNome());
		assertEquals(NOTA, response.get(0).getNota());
		assertEquals(photos, response.get(0).getPhotos());
	}
	
	@Test
	public void whenFindAllOfComicBooksNotaThenReturnAnList() {
		when(repository.findAll()).thenReturn(List.of(comicBooks));

		List<ComicBooks> response = service.findByNota(9.63);

		assertNotNull(response);
	}
}
