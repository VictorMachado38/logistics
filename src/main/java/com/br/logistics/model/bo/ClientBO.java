package com.br.logistics.model.bo;


import com.br.logistics.model.dao.impl.ClientInterfaceDAO;
import com.br.logistics.model.dao.impl.ClienteDAO;
import com.br.logistics.model.dto.ClienteDTO;
import com.br.logistics.model.entity.Cliente;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


//@JsonSerialize
@Component
public class ClientBO {

    @Autowired
    ClienteDAO dao;

    public ResponseEntity<ClienteDTO> saveClient(ClienteDTO clienteDTO, ClientInterfaceDAO clientInterfaceDAO) {
        clientInterfaceDAO.save(clienteDTO.toEntity());
        return ResponseEntity.status(200).body(clienteDTO);
    }

    //    ClientDAO clientDAO = new ClientDAO();
    public String getClient() {
//        return clientDAO.getClient();
        return null;
    }
}
