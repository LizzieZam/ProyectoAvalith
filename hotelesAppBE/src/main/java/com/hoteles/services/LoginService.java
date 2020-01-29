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

import com.hoteles.model.Acceso;
import com.hoteles.controller.AccesoDaoImpl;

@RestController
public class LoginService {

    @Autowired
    private AccesoDaoImpl hotelService;

    @PostMapping("/hotel/login/")
    public String getAllAccesos(@RequestBody Acceso access) {
        return hotelService.getAcceso(access);
    }
    @GetMapping("/hotel/login/{id}")
    public Acceso getAllAccesos(@PathVariable int id) {
        return hotelService.getAccesoById(id);
    }

    @PostMapping("/hotel/login/new/")
    public ResponseEntity<Void> addAcceso(@RequestBody Acceso user, UriComponentsBuilder builder) {
        Acceso hotelr = hotelService.addUsuario(user);
        if (hotelr == null) {
            return ResponseEntity.noContent().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/hotel/login/{id}").buildAndExpand(user.getIdAcceso()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

}
