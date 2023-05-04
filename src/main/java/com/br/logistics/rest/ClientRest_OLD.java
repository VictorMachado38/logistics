package com.br.logistics.rest;

import com.br.logistics.model.bo.ClientBO;
import com.br.logistics.model.dao.impl.ClientInterfaceDAO;
import com.br.logistics.model.dao.impl.ClienteDAO2;
import com.br.logistics.model.entity.Cliente;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@JsonSerialize
public class ClientRest_OLD {


    ClientBO bo = new ClientBO();

    @GetMapping("old/client")
    public String getClient() {
        return bo.getClient();
    }


    @Autowired
    private ClientInterfaceDAO clienteRepository;

    @GetMapping("old/clientes")
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }


    ClienteDAO2 dao2 = new ClienteDAO2();

    @GetMapping("old/clientes2")
    public Cliente getAllClientess() {
        return dao2.tesets();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping(value = "old/clientes3", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonSerialize
    @ResponseBody
    public ResponseEntity<Cliente> getAllClientesss() {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(dao2.tesets());
    }

    @GetMapping(value = "old/clientes5", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @JsonSerialize
    public ResponseEntity<List<Cliente>> getAllClientesaasss() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente(1L, "teste", "teste", "teste"));
        clientes.add(new Cliente(3L, "teste", "teste", "teste"));
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(clientes);
    }
}
