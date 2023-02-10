package com.Library.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.Library.model.Photos;
import com.Library.service.PhotosService;

public class PhotosControllerTest {

	private static final int ID = 1;
	private static final String NOME = "Teste";
	private static final String TIPO = MediaType.IMAGE_PNG_VALUE;
	private static final byte[] PHOTO = null;
	
	@InjectMocks
	private PhotosController controller;
	
	@Mock
	private PhotosService service;
	
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
	public void whenGetPhotosInfoThenReturnSuccess() {
		when(service.find(anyInt())).thenReturn(photos);

		Photos response = controller.getPhotosInfo(ID);
		assertNotNull(response);
		assertEquals(Photos.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NOME, response.getNome());
		assertEquals(TIPO, response.getTipo());
		assertEquals(PHOTO, response.getPhoto());
	}
	
	@Test
	public void whenGetPhotosThenReturnSuccess() {
		when(service.find(anyInt())).thenReturn(photos);

		ResponseEntity<byte[]> response = controller.getPhotos(ID);
		assertNotNull(response);
		assertEquals(PHOTO, response.getBody());
	}
	
	@Test
	public void whenUploadBooksPhotosThenReturnSuccess() throws IOException {		
		doNothing().when(service).save(0, photos);;
		
		MultipartFile[] file = new MultipartFile[1];
		byte[] b = new byte[1];
		file[0] = new MockMultipartFile("fileItem","teste", "image/png", b);
		
		controller.uploadPhotoBook(ID, file);	
	}
	
	@Test
	public void whenUploadComicBooksPhotosThenReturnSuccess() throws IOException {		
		doNothing().when(service).save(0, photos);;
		
		MultipartFile[] file = new MultipartFile[1];
		byte[] b = new byte[1];
		file[0] = new MockMultipartFile("fileItem","teste", "image/png", b);
		
		controller.uploadPhotoComicBook(ID, file);	
	}
	
	@Test
	public void whenUploadMangaPhotosThenReturnSuccess() throws IOException {		
		doNothing().when(service).save(0, photos);;
		
		MultipartFile[] file = new MultipartFile[1];
		byte[] b = new byte[1];
		file[0] = new MockMultipartFile("fileItem","teste", "image/png", b);
		
		controller.uploadPhotoManga(ID, file);	
	}
	
	@Test
	public void whenDeleteThenReturnSuccess() {
		doNothing().when(service).delete(ID);

		controller.deletePhotos(ID);

		verify(service).delete(ID);
	}	
}
