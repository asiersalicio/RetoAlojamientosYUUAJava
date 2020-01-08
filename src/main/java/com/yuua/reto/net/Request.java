package com.yuua.reto.net;

import java.io.Serializable;

public class Request implements Serializable {
	private static final long serialVersionUID = -8074719251631061375L;
	private int codigoPeticion = 0;
	private Object objetoEnviado;

	

	public int getCodigoPeticion() {
		return codigoPeticion;
	}

	public Request(int codigoPeticion, Object objetoMandado) {
		super();
		this.codigoPeticion = codigoPeticion;
		this.objetoEnviado = objetoMandado;
	}

	public Object getObjetoEnviado() {
		return objetoEnviado;
	}
}
