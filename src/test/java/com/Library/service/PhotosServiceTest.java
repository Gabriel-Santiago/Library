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

import com.Library.model.Photos;
import com.Library.repository.PhotosRepository;

public class PhotosServiceTest {

	private static final int ID = 1;
	private static final String NOME = "Teste";
	private static final String TIPO = "Png";
	private static final byte[] PHOTO = null;
	
	@InjectMocks
	private PhotosService service;
	
	@Mock
	private PhotosRepository repository;
	
	private Photos photos;
	
	private void start() {
		photos = new Photos();
		photos.setId(ID);
		photos.setNome(NOME);
		photos.setTipo(TIPO);
		photos.setPhoto(PHOTO);
	}
	
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		start();
	}
	
	@Test
	public void whenDeleteVerifySuccess() { 
		when(repository.findById(anyInt())).thenReturn(Optional.of(photos));
		doNothing().when(repository).delete(photos);
			
		service.delete(ID);

		verify(repository).findById(anyInt());
		verify(repository).delete(any());
	}
	
	@Test
	public void whenFindAllThenReturnAnList() {
		when(repository.findAll()).thenReturn(List.of(photos));

		List<Photos> response =  service.findAll();

		assertNotNull(response);
		assertEquals(ID, response.get(0).getId());
		assertEquals(NOME, response.get(0).getNome());
		assertEquals(TIPO, response.get(0).getTipo());
		assertEquals(PHOTO, response.get(0).getPhoto());
	}
	
	@Test
	public void whenFindByIdThenReturnNullIfIdLessThan1() {
		Photos response = service.find(0);

		assertNull(response);
	}

	@Test
	public void whenFindByIdThenReturnNullIfOptionalNotPresent() {
		when(repository.findById(anyInt())).thenReturn(Optional.empty());

		Photos response = service.find(ID);

		assertNull(response);
	}
	
	@Test
	public void whenSaveVerifySuccess() throws ParseException, IOException {
		when(repository.save(any())).thenReturn(photos);
		service.save(1, photos);
		verify(repository).save(any());
	}
	
	@Test
	public void whenUpdateVerifySuccess() throws ParseException, IOException {
		when(repository.save(any())).thenReturn(photos);
		service.update(ID, photos);;
		verify(repository).save(any()); 
	}
	
	@Test
	public void whenFindAllOfBooksThenReturnAnList() {
		when(repository.findAll()).thenReturn(List.of(photos));

		List<Photos> response = service.findAllOfBooks(1);

		assertNotNull(response);
	}
	
	@Test
	public void whenFindAllOfComicBooksThenReturnAnList() {
		when(repository.findAll()).thenReturn(List.of(photos));

		List<Photos> response = service.findAllOfComicsBooks(1);

		assertNotNull(response);
	}
	
	@Test
	public void whenFindAllOfMangaThenReturnAnList() {
		when(repository.findAll()).thenReturn(List.of(photos));

		List<Photos> response = service.findAllOfManga(1);

		assertNotNull(response);
	}
}
