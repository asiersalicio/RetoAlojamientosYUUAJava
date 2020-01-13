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
	public char[] id;

	@Column(name = "territory")
	public String nombre;

	@OneToMany(mappedBy = "tterritorio")
	private transient Set<Localizacion> tlocalizacion;

	public Territorio() {

	}
	

	public Territorio(char[] id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	public Territorio recrearTerritorio() {
		return new Territorio(this.id, this.nombre);
	}
	

	public Set<Localizacion> getTlocalizacion() {
		return tlocalizacion;
	}

	public void setTlocalizacion(Set<Localizacion> tlocalizacion) {
		this.tlocalizacion = tlocalizacion;
	}

	public char[] getId() {
		return id;
	}

	public void setId(char[] id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
