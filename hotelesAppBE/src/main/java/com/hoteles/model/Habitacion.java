package com.hoteles.model;

public class Habitacion {
   
	 private int idHabitacion;
	 private String descripcion;
	 private int numCamas;
	 private String estado;
	 private double precio;
	
	public Habitacion(int idHabitacion, String descripcion, int numCamas, String estado, double precio) {
		super();
		this.idHabitacion = idHabitacion;
		this.descripcion = descripcion;
		this.numCamas = numCamas;
		this.estado = estado;
		this.precio = precio;
		
	}
	public Habitacion() {
		
	}
	public int getIdHabitacion() {
		return idHabitacion;
	}
	public void setIdHabitacion(int idHabitacion) {
		this.idHabitacion = idHabitacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getNumCamas() {
		return numCamas;
	}
	public void setNumCamas(int numCamas) {
		this.numCamas = numCamas;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
	 
}
