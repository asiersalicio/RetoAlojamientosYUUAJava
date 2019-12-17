package com.yuua.reto.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reserva")
public class Reserva {

	@Id
	@Column(name = "idReserva") 
	private int id;
	@Column(name = "fechaEntrada")
	private Date fechaEntrada;
	@Column(name = "fechaSalida")
	private Date fechaSalida;
	
	@OneToOne
    @JoinColumn(name = "idDni")
    private Usuario usuario;
}

//RESERVA (#ID_RESERVA(PK), FECHA_ENTRADA, FECHA_SALIDA, ID_ALOJAMIENTO(FK), ID_DNI(FK))