package br.com.starwars.models;

import org.bson.types.ObjectId;

public class Planeta {
	
	private ObjectId id;
	private String nome;
	private String clima;
	private String terreno;
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getClima() {
		return clima;
	}
	public void setClima(String clima) {
		this.clima = clima;
	}
	public String getTerreno() {
		return terreno;
	}
	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}
	public Planeta criarId() {
		setId(new ObjectId());
		
		return this;
	}

}
