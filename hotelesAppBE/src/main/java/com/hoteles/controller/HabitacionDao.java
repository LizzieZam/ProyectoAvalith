package com.hoteles.controller;

import java.util.List;

import com.hoteles.model.Habitacion;

public interface HabitacionDao {
	public List<Habitacion> getAllHabitacions();
	public List<Habitacion> getAllHabitacions(String filter);
	public Habitacion getHabitacionById(int id);
	public Habitacion addHabitacion(Habitacion newHabitacion);
	 	
}
