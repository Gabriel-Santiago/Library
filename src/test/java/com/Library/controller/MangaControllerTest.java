package com.Library.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.Library.model.Manga;
import com.Library.model.Photos;
import com.Library.service.MangaService;

public class MangaControllerTest {

	private static final int ID = 1;
	private static final String NOME = "O jardim das palavras";
	private static final double NOTA = 9.00;

	
	// Photos
	private static final int ID_1 = 1;
	private static final String NOME_1 = "Teste";
	private static final String TIPO = "PNG";
	
	@InjectMocks
	private MangaController controller;
	
	@Mock
	private MangaService service;
	
	private Manga manga;
	
	private Photos photos;
	
	private void start() {
		
		photos = new Photos(ID_1, NOME_1, TIPO, null, null, null, manga);
		
		manga = new Manga(ID, NOME, NOTA, photos);
	}
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		start();
	}
	
	@Test
	public void whenFindByIdThenReturnSuccess() {
		when(service.find(anyInt())).thenReturn(manga);
		
		ResponseEntity<Manga> response = controller.find(ID);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(Manga.class, response.getBody().getClass());
		assertEquals(ID, response.getBody().getId());
		assertEquals(NOME, response.getBody().getNome());
		assertEquals(NOTA, response.getBody().getNota());
		assertEquals(photos, response.getBody().getPhotos());
	}
	
	@Test
	public void whenCreateThenReturnSuccess() throws ParseException, IOException, InterruptedException {
		controller.save(manga);

		verify(service).save(0, manga);
	}
	
	@Test
	public void whenFindByIdThenReturnNotFound() {
		when(service.find(anyInt())).thenReturn(null);

		ResponseEntity<Manga> response = controller.find(ID);

		assertNotNull(response);
		assertNull(response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	public void whenFindAllThenReturnSuccess() {
		when(service.findAll()).thenReturn(List.of(manga));
		
		ResponseEntity<List<Manga>> response = controller.findAll();
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ID, response.getBody().get(0).getId());
		assertEquals(NOME, response.getBody().get(0).getNome());
		assertEquals(NOTA, response.getBody().get(0).getNota());
		assertEquals(photos, response.getBody().get(0).getPhotos());
	}
	
	@Test
	public void whenFindByNotaThenReturnSuccess() {
		when(service.findByNota(8.64)).thenReturn(null);
		
		ResponseEntity<List<Manga>> response = controller.findByNota(NOTA);
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ID, response.getBody().get(0).getId());
		assertEquals(NOME, response.getBody().get(0).getNome());
		assertEquals(NOTA, response.getBody().get(0).getNota());
		assertEquals(photos, response.getBody().get(0).getPhotos());
	}

	@Test
	public void whenUpdateThenReturnSuccess() throws ParseException, IOException {
		doNothing().when(service).update(ID, manga);;

		controller.update(ID, manga);

		verify(service).update(ID, manga);
	}
	
	@Test
	public void whenDeleteThenReturnSuccess() {
		doNothing().when(service).delete(ID);

		controller.delete(ID);

		verify(service).delete(ID);
	}
}
