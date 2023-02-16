package com.Library.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="books")
public class Books {

	@Id
	@GeneratedValue(generator="sequence",strategy=GenerationType.SEQUENCE) 
	@SequenceGenerator(name="sequence",sequenceName = "books_id_seq", allocationSize=1)
	private int id;
	private String nome;
	private double nota;
	private String genero;
	
	@OneToOne(mappedBy = "books")
	@Lob
	private Photos photos;
	
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
	
	public double getNota() {
		return nota;
	}
	
	public void setNota(double nota) {
		this.nota = nota;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Photos getPhotos() {
		return photos;
	}

	public void setPhotos(Photos photos) {
		this.photos = photos;
	}

	@Override
	public String toString() {
		return "Books [id=" + id + ", nome=" + nome + ", nota=" + nota + ", genero=" + genero + ", photos=" + photos
				+ "]";
	}

	public Books(int id, String nome, double nota, String genero, Photos photos) {
		super();
		this.id = id;
		this.nome = nome;
		this.nota = nota;
		this.genero = genero;
		this.photos = photos;
	}

	public Books() {
		super();
	}
}
