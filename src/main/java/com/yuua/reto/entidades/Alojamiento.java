package com.yuua.reto.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Entity
@Table(name="alojamientos")
public class Alojamiento {
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Id
		@Column(name = "idAlojamiento")
		int id;
		@Column(name = "templatetype")
		String tipo;
		@Column(name = "documentname")
		String nombre;
		@Column(name = "turismdescription")
		String descripcion;
		@Column(name = "address")
		String direccion;
		@Column(name = "municipality")
		String municipio;
		@Column(name = "territory")
		String provincia;
		@Column(name = "phone")
		int telefono;
		@Column(name = "web")
		String web;
		@Column(name = "tourismemail")
		String email;
		@Column(name = "latwgs84")
		int latitud;
		@Column(name = "lonwgs84")
		int longitud;
		
		protected Alojamiento() {
		}
		
		

		public Alojamiento(int id, String tipo, String nombre, String descripcion, String direccion, String municipio,
				String provincia, int telefono, String web, String email, int latitud, int longitud) {
			super();
			this.id = id;
			this.tipo = tipo;
			this.nombre = nombre;
			this.descripcion = descripcion;
			this.direccion = direccion;
			this.municipio = municipio;
			this.provincia = provincia;
			this.telefono = telefono;
			this.web = web;
			this.email = email;
			this.latitud = latitud;
			this.longitud = longitud;
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

		public String getDireccion() {
			return direccion;
		}

		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}

		public String getMunicipio() {
			return municipio;
		}

		public void setMunicipio(String municipio) {
			this.municipio = municipio;
		}

		public String getProvincia() {
			return provincia;
		}

		public void setProvincia(String provincia) {
			this.provincia = provincia;
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

		public int getLatitud() {
			return latitud;
		}

		public void setLatitud(int latitud) {
			this.latitud = latitud;
		}

		public int getLongitud() {
			return longitud;
		}

		public void setLongitud(int longitud) {
			this.longitud = longitud;
		}
}
