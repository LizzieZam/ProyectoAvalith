package com.hoteles.model;

import java.util.Date;

public class Consulta {
	 private String placa;
	 private Date fecha;
	 private Date hora;
	public Consulta() {
		super();
		// TODO Auto-generated constructor stub
		this.placa = "asd-232";
		this.fecha = new Date();
		this.hora =  new Date();
	}
	public Consulta(String placa, Date fecha, Date hora) {
		super();
		this.placa = placa;
		this.fecha = fecha;
		this.hora = hora;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Date getHora() {
		return hora;
	}
	public void setHora(Date hora) {
		this.hora = hora;
	}
	 
	 
}
