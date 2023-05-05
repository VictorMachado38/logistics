package com.br.logistics.model.bo;


import com.br.logistics.model.dao.impl.ClientInterfaceDAO;
import com.br.logistics.model.dao.impl.ClienteDAO;
import com.br.logistics.model.dto.ClienteDTO;
import com.br.logistics.model.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static javax.ws.rs.core.Response.Status.BAD_GATEWAY;


//@JsonSerialize
@Component
public class ClientBO {

    @Autowired
    ClienteDAO dao;

    //    ClientDAO clientDAO = new ClientDAO();

    public ResponseEntity<ClienteDTO> saveClient(ClienteDTO clienteDTO, ClientInterfaceDAO clientInterfaceDAO) {
        clientInterfaceDAO.save(clienteDTO.toEntity());
        return ResponseEntity.status(200).body(clienteDTO);
    }

    public ResponseDTO<ClienteDTO> saveClient2(ClienteDTO clienteDTO, ClientInterfaceDAO clientInterfaceDAO) {
        clientInterfaceDAO.save(clienteDTO.toEntity());
        return ResponseDTO.<ClienteDTO>builder()
                .data(clienteDTO)
                .status(BAD_GATEWAY)
                .build();
    }
    public String getClient() {
//        return clientDAO.getClient();
        return null;
    }
}
