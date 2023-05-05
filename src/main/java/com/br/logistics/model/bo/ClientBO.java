package com.br.logistics.model.bo;


import com.br.logistics.model.dao.impl.ClientInterfaceDAO;
import com.br.logistics.model.dao.impl.ClienteDAO;
import com.br.logistics.model.dao.impl.EnderecoInterfaceDAO;
import com.br.logistics.model.dto.FormDTO;
import com.br.logistics.model.dto.ResponseDTO;
import com.br.logistics.model.entity.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

import static javax.ws.rs.core.Response.Status.*;


//@JsonSerialize
@Component
public class ClientBO {

    @Autowired
    ClienteDAO dao;

    //    ClientDAO clientDAO = new ClientDAO();

    public ResponseEntity<FormDTO> saveClient3(FormDTO formDTO, ClientInterfaceDAO clientInterfaceDAO) {
        clientInterfaceDAO.save(formDTO.toClientEntity());
        return ResponseEntity.status(200).body(formDTO);
    }

    public ResponseDTO<FormDTO> saveClient(FormDTO formDTO, ClientInterfaceDAO clientInterfaceDAO, EnderecoInterfaceDAO enderecoInterfaceDAO) {
        Cliente clientSalvo = new Cliente();
        try{
            clientSalvo  = clientInterfaceDAO.save(formDTO.toClientEntity());
        }
        catch (Exception e){
            if (e instanceof DataIntegrityViolationException) {
                DataIntegrityViolationException cve = (DataIntegrityViolationException) e;
                PropertyValueException cve2 = (PropertyValueException) cve.getCause();
                System.out.println("Nome da restrição violada: " + cve2.getPropertyName());
                if(cve2.getPropertyName() != null){
                    return ResponseDTO.<FormDTO>builder()
                            .data(formDTO)
                            .message("Erro ao processar a requisição")
                            .description("Erro na propridede " + cve2.getPropertyName())
                            .status(PAYMENT_REQUIRED.getStatusCode())
                            .build();
                }
                else
                    return ResponseDTO.<FormDTO>builder().data(formDTO).message("Erro ao processar a requisição").status(BAD_REQUEST.getStatusCode()).build();
            } else {
                return ResponseDTO.<FormDTO>builder().data(formDTO).message("Erro ao processar a requisição").status(BAD_REQUEST.getStatusCode()).build();
            }
        }


        if (clientSalvo.getId() != null) {
            enderecoInterfaceDAO.save(formDTO.toEnderecoEntity(clientSalvo));
            System.out.println(clientSalvo.getId());
        }



//        enderecoInterfaceDAO.save(formDTO.toEnderecoEntity());
        return ResponseDTO.<FormDTO>builder()
                .data(formDTO)
                .message("Cliente salvo com sucesso")
                .description("O cliente " + formDTO.getNome() + " foi salvo com sucesso")
                .status(OK.getStatusCode())
                .build();
    }


    public String getClient() {
//        return clientDAO.getClient();
        return null;
    }
}
