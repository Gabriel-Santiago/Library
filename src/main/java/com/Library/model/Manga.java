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
@Table(name="manga")
public class Manga {

	@Id
	@GeneratedValue(generator="sequence",strategy=GenerationType.SEQUENCE) 
	@SequenceGenerator(name="sequence",sequenceName = "manga_id_seq", allocationSize=1)
	private int id;
	private String nome;
	private double nota;
	
	@OneToOne(mappedBy = "manga")
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

	public Photos getPhotos() {
		return photos;
	}

	public void setPhotos(Photos photos) {
		this.photos = photos;
	}

	@Override
	public String toString() {
		return "Manga [id=" + id + ", nome=" + nome + ", nota=" + nota + ", photos=" + photos + "]";
	}

	public Manga(int id, String nome, double nota, Photos photos) {
		super();
		this.id = id;
		this.nome = nome;
		this.nota = nota;
		this.photos = photos;
	}

	public Manga() {
		super();
	}
}
