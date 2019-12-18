package com.yuua.reto.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tterritorio")
public class Territorio {
	@Id
	@Column(name ="territorycode")
	int id;
	
	@Column(name = "territory")
	String nombre;
}
