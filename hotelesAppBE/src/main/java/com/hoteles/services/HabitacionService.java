package com.hoteles.services;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.hoteles.model.Habitacion;
import com.hoteles.controller.HabitacionDaoImpl;

@RestController
public class HabitacionService {
@Autowired
private HabitacionDaoImpl hotelService;
@GetMapping("/hotel/habitaciones/")
public List<Habitacion> getAllHabitacions(){
	  return hotelService.getAllHabitacions();
	 }
@GetMapping("/hotel/habitaciones/{filter}/")
public List<Habitacion> getAllHabitacions(@PathVariable String filter){
	  return hotelService.getAllHabitacions(filter);
	 }

@GetMapping("/hotel/habitaciones/{habitacionId}")
public Habitacion getHabitacionById(@PathVariable int hotel) {
 return hotelService.getHabitacionById(hotel);
}
	 

@PostMapping("/hotel/habitaciones/")
public ResponseEntity<Void> addHabitacion(@RequestBody Habitacion hotel, UriComponentsBuilder builder){
	Habitacion hotelr = hotelService.addHabitacion(hotel);
	  if(hotelr == null) {
	   return ResponseEntity.noContent().build();
	  }
	  
	  HttpHeaders headers = new HttpHeaders();
	  headers.setLocation(builder.path("/hotel/{id}").buildAndExpand(hotel.getIdHabitacion()).toUri());
	  return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	 }
	
	
}
