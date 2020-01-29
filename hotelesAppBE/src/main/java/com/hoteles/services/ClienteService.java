package com.hoteles.services;


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

import com.hoteles.model.Cliente;
import com.hoteles.controller.ClienteDaoImpl;

@RestController
public class ClienteService {
@Autowired
private ClienteDaoImpl hotelService;
@GetMapping("/hotel/clientes/")
public List<Cliente> getAllClientes(){
	  return hotelService.getAllClientes();
	 }
@GetMapping("/hotel/clientes/{filter}/")
public List<Cliente> getAllClientes(@PathVariable String filter){
	  return hotelService.getAllClientes(filter);
	 }

@GetMapping("/hotel/clientes/{clienteId}")
public Cliente getClienteById(@PathVariable int hotel) {
 return hotelService.getClienteById(hotel);
}
	 

@PostMapping("/hotel/clientes/")
public ResponseEntity<Void> addCliente(@RequestBody Cliente hotel, UriComponentsBuilder builder){
	Cliente hotelr = hotelService.addCliente(hotel);
	  if(hotelr == null) {
	   return ResponseEntity.noContent().build();
	  }
	  
	  HttpHeaders headers = new HttpHeaders();
	  headers.setLocation(builder.path("/hotel/cliente/{id}").buildAndExpand(hotel.getIdCliente()).toUri());
	  return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	 }
	
	
}
