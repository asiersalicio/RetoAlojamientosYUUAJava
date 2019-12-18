package com.yuua.reto.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Entity

@Table(name="talojamientos")
public class Alojamiento {
	
		@Id
		@Column(name = "idAlojamiento")
		int id;
		@Column(name = "lodgingtype")
		String tipo;
		@Column(name = "documentname")
		String nombre;
		@Column(name = "turismdescription")
		String descripcion;
		@Column(name = "phone")
		int telefono;
		@Column(name = "web")
		String web;
		@Column(name = "tourismemail")
		String email;
		@Column(name = "capacity")
		int capacidad;
		@OneToOne
		Localizacion localizacion;
		
		protected Alojamiento() {
		}
		
		
		
		public Alojamiento(int id, String tipo, String nombre, String descripcion, int telefono, String web,
				String email, int capacidad, Localizacion localizacion) {
			super();
			this.id = id;
			this.tipo = tipo;
			this.nombre = nombre;
			this.descripcion = descripcion;
			this.telefono = telefono;
			this.web = web;
			this.email = email;
			this.capacidad = capacidad;
			this.localizacion = localizacion;
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
