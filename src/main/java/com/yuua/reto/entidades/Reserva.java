package com.yuua.reto.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reserva")
public class Reserva {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "idReserva") 
	private int id;
	@Column(name = "fechaEntrada")
	private Date fechaEntrada;
	@Column(name = "fechaSalida")
	private Date fechaSalida;
	@OneToOne
    @JoinColumn(name = "idAlojamiento")
    private Alojamiento alojamiento;
	@OneToOne
    @JoinColumn(name = "idDni")
    private Usuario usuario;
	
	protected Reserva() {
		
	}

	public Reserva(int id, Date fechaEntrada, Date fechaSalida, Alojamiento alojamiento, Usuario usuario) {
		super();
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.alojamiento = alojamiento;
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Alojamiento getAlojamiento() {
		return alojamiento;
	}

	public void setAlojamiento(Alojamiento alojamiento) {
		this.alojamiento = alojamiento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
