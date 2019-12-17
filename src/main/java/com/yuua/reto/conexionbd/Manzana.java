package com.yuua.reto.conexionbd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "manzanas")
public class Manzana {

	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	int id;
	@Column(name = "peso")
	double peso;
	@Column(name = "especie")
	String especie;

	protected Manzana() {
	}

	public Manzana(int id, double peso, String especie) {
		super();
		this.id = id;
		this.peso = peso;
		this.especie = especie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
