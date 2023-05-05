package com.br.logistics.rest;

import com.br.logistics.model.bo.ClientBO;
import com.br.logistics.model.dao.impl.ClientInterfaceDAO;
import com.br.logistics.model.dao.impl.ClienteDAO;
import com.br.logistics.model.dao.impl.ClienteDAO2;
import com.br.logistics.model.dao.impl.EnderecoInterfaceDAO;
import com.br.logistics.model.dto.FormDTO;
import com.br.logistics.model.dto.ResponseDTO;
import com.br.logistics.model.entity.Cliente;
import com.br.logistics.model.entity.Endereco;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@JsonSerialize
@RequestMapping("/client")
public class ClientRest {

    ClientBO bo = new ClientBO();

    @Autowired
    private ClientInterfaceDAO clientInterfaceDAO;
    @Autowired
    private EnderecoInterfaceDAO enderecoInterfaceDAO;


    @PostMapping("/save3")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<FormDTO> saveClient3(@RequestBody FormDTO cliente) {
        return bo.saveClient3(cliente,clientInterfaceDAO);
    }


    @PostMapping("/save")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseDTO<FormDTO> saveClient(@RequestBody FormDTO cliente) {
        return bo.saveClient(cliente,clientInterfaceDAO,enderecoInterfaceDAO);
    }















    @PostMapping("/save2")
    public ResponseEntity<FormDTO> saveClient2(@RequestBody FormDTO cliente) {
        Cliente savedClient = clientInterfaceDAO.save(cliente.toClientEntity());
        return ResponseEntity.ok().build();
    }











    @GetMapping("/client")
    public String getClient() {
        return bo.getClient();
    }


    @Autowired
    private ClientInterfaceDAO clienteRepository;

    @GetMapping("/clientes")
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }


    ClienteDAO2 dao2 = new ClienteDAO2();
    ClienteDAO dao = new ClienteDAO();

    @GetMapping("/clientesBusca")
    public Cliente getAllClientess() {
        return dao.busca();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping(value = "/clientes3", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonSerialize
    @ResponseBody
    public ResponseEntity<Cliente> getAllClientesss() {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(dao2.tesets());
    }

    @GetMapping(value = "/clientes5", produces = MediaType.APPLICATION_JSON_VALUE)
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
