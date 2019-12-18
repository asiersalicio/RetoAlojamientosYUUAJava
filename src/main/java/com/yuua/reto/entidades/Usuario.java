package com.yuua.reto.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@Column(name = "idDni")
	private String idDni;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "apellidos")
	private String apellidos;
	@Column(name = "tipoUsuario")
	private String tipoUsuario;
	@Column(name = "nombreUsuario")
	private String nombreUsuario;
	@Column(name = "contrasena")
	private String contrasena;
	@Column(name = "fechaNacimiento")
	private Date fechaNacimiento;
	@Column(name = "correo")
	private String correo;
	@Column(name = "telefono")
	private long telefono;

	protected Usuario() {

	}

	public Usuario(String idDni, String nombre, String apellidos, String tipoUsuario, String nombreUsuario, String contrasena, Date fechaNacimiento, String correo, long telefono) {
		super();
		this.idDni = idDni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.tipoUsuario = tipoUsuario;
		this.nombreUsuario = nombreUsuario;
		this.contrasena = contrasena;
		this.fechaNacimiento = fechaNacimiento;
		this.correo = correo;
		this.telefono = telefono;
	}

	public String getIdDni() {
		return idDni;
	}

	public void setIdDni(String idDni) {
		this.idDni = idDni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public long getTelefono() {
		return telefono;
	}

	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}

}
