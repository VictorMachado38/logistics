package com.br.logistics.rest;

import com.br.logistics.model.bo.ClientBO;
import com.br.logistics.model.dao.impl.ClientInterfaceDAO;
import com.br.logistics.model.dao.impl.EnderecoInterfaceDAO;
import com.br.logistics.model.dto.FormDTO;
import com.br.logistics.model.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientRest {

    ClientBO bo = new ClientBO();

    @Autowired
    private ClientInterfaceDAO clientInterfaceDAO;
    @Autowired
    private EnderecoInterfaceDAO enderecoInterfaceDAO;

    @PostMapping("/save")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseDTO<FormDTO> saveClient(@RequestBody FormDTO cliente) {
        return bo.saveClient(cliente,clientInterfaceDAO,enderecoInterfaceDAO);
    }

    @GetMapping("/list")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseDTO<List<FormDTO>> getAllClientess() {
        return bo.getClient();
    }

}
