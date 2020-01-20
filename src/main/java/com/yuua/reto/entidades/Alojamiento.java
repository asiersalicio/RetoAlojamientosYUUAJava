package com.yuua.reto.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "talojamientos")
public class Alojamiento {

	@Id
	@Column(name = "idAlojamiento")
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	@Column(name = "lodgingtype")
	String tipo;
	@Column(name = "documentname")
	String nombre;
	@Column(name = "turismdescription",length=6000,columnDefinition = "TEXT")
	String descripcion;
	@Column(name = "phone")
	int telefono;
	@Column(name = "web")
	String web;
	@Column(name = "tourismemail")
	String email;
	@Column(name = "capacity")
	int capacidad;
	
	@OneToOne(cascade = {CascadeType.ALL}, targetEntity = Localizacion.class)
	Localizacion localizacion;

	protected Alojamiento() {
	}

	public Alojamiento(String tipo, String nombre, String descripcion, int telefono, String web, String email, int capacidad, Localizacion localizacion) {
		this.tipo = tipo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.telefono = telefono;
		this.web = web;
		this.email = email;
		this.capacidad = capacidad;
		this.localizacion = localizacion;
	}
	
	

	

	public Alojamiento(int id) {
		super();
		this.id = id;
	}

	public Localizacion getLocalizacion() {
		return localizacion;
	}

	
	public void setLocalizacion(Localizacion localizacion) {
		this.localizacion = localizacion;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
