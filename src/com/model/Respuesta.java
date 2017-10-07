package com.model;

/**
 *	Bean con representacion de datos de respuesta. 
 */
public class Respuesta {

	private boolean isValido;

	public Respuesta() {
		super();
	}

	public Respuesta(boolean isValido) {
		super();
		this.isValido = isValido;
	}

	public boolean isValido() {
		return this.isValido;
	}

	public void setValido(boolean isValido) {
		this.isValido = isValido;
	}
	
	

}