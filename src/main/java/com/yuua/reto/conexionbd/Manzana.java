package com.yuua.reto.conexionbd;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Manzana {
	@Id
	int id;
	
	double peso;
	String especie;
	
	
	public Manzana(int id, double peso, String especie) {
		super();
		this.id = id;
		this.peso = peso;
		this.especie = especie;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	
	
}
