package com.yuua.reto.entidades;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tterritorio")
public class Territorio {
	@Id
	@Column(name = "territorycode")
	int id;

	@Column(name = "territory")
	String nombre;

	@OneToMany(mappedBy = "tterritorio")
	private Set<Localizacion> tlocalizacion;

	public Territorio() {

	}

	public Territorio(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	

	public Set<Localizacion> getTlocalizacion() {
		return tlocalizacion;
	}

	public void setTlocalizacion(Set<Localizacion> tlocalizacion) {
		this.tlocalizacion = tlocalizacion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
