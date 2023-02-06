package com.Library.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="photos")
public class Photos {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "foto_generator")
	@SequenceGenerator(name="foto_generator", sequenceName = "photos_id_seq", allocationSize=1)
	private int id;
	private String nome;
	private String tipo;
	
	@JsonIgnore
	private byte[] photo;
	
	@OneToOne
	@JoinColumn(name = "bookPhoto")
	@JsonIgnore
	private Books books;
	
	@OneToOne
	@JoinColumn(name = "comicBookPhoto")
	@JsonIgnore
	private ComicBooks comicBooks;
	
	@OneToOne
	@JoinColumn(name = "mangaPhoto")
	@JsonIgnore
	private Manga manga;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Books getBooks() {
		return books;
	}

	public void setBooks(Books books) {
		this.books = books;
	}

	public ComicBooks getComicBooks() {
		return comicBooks;
	}

	public void setComicBooks(ComicBooks comicBooks) {
		this.comicBooks = comicBooks;
	}

	public Manga getManga() {
		return manga;
	}

	public void setManga(Manga manga) {
		this.manga = manga;
	}

	public Photos(int id, String nome, String tipo, byte[] photo, Books books, ComicBooks comicBooks, Manga manga) {
		super();
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
		this.photo = photo;
		this.books = books;
		this.comicBooks = comicBooks;
		this.manga = manga;
	}

	public Photos() {
		super();
	}
}
