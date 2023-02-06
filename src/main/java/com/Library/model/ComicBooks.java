package com.Library.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="comicBooks")
public class ComicBooks {

	@Id
	@GeneratedValue(generator="sequence",strategy=GenerationType.SEQUENCE) 
	@SequenceGenerator(name="sequence",sequenceName = "comicBooks_id_seq", allocationSize=1)
	private int id;
	private String nome;
	private int nota;
	
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
	
	public int getNota() {
		return nota;
	}
	
	public void setNota(int nota) {
		this.nota = nota;
	}

	@Override
	public String toString() {
		return "ComicBooks [id=" + id + ", nome=" + nome + ", nota=" + nota + "]";
	}

	public ComicBooks(int id, String nome, int nota) {
		super();
		this.id = id;
		this.nome = nome;
		this.nota = nota;
	}

	public ComicBooks() {
		super();
	}	
}
