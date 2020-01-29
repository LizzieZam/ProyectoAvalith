package com.hoteles.controller;

import java.util.List;

import com.hoteles.model.Cliente;

public interface ClienteDao {
	public List<Cliente> getAllClientes();
	public List<Cliente> getAllClientes(String filter);
	public Cliente getClienteById(int id);
	public Cliente addCliente(Cliente newCliente);
	 	
}
