package com.br.logistics.model.bo;


import com.br.logistics.model.dao.impl.ClientInterfaceDAO;
import com.br.logistics.model.dao.impl.ClienteDAO;
import com.br.logistics.model.dao.impl.EnderecoInterfaceDAO;
import com.br.logistics.model.dto.FormDTO;
import com.br.logistics.model.dto.ResponseDTO;
import com.br.logistics.model.entity.Cliente;
import com.br.logistics.model.entity.Endereco;
import org.hibernate.PropertyValueException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

import static javax.ws.rs.core.Response.Status.*;


@Component
public class ClientBO {

    ClienteDAO dao = new ClienteDAO();

    public ResponseDTO<FormDTO> saveClient(FormDTO formDTO, ClientInterfaceDAO clientInterfaceDAO, EnderecoInterfaceDAO enderecoInterfaceDAO) {
        if (formDTO.getClienteId() == null) {
            Cliente clientSalvo = new Cliente();
            Endereco enderecoSalvo = new Endereco();
            Optional<Cliente> clienteExitente = Optional.empty();
            try {
                clientSalvo = clientInterfaceDAO.save(formDTO.toClientEntity());
                formDTO.setClienteId(clientSalvo.getId());
            } catch (Exception e) {
                if (e instanceof DataIntegrityViolationException) {
                    DataIntegrityViolationException cve = (DataIntegrityViolationException) e;
                    PropertyValueException cve2 = (PropertyValueException) cve.getCause();
                    System.out.println("Nome da restrição violada: " + cve2.getPropertyName());
                    if (cve2.getPropertyName() != null) {
                        return ResponseDTO.<FormDTO>builder()
                                .data(formDTO)
                                .message("Erro ao processar a requisição")
                                .description("Erro na propridede " + cve2.getPropertyName())
                                .status(PAYMENT_REQUIRED.getStatusCode())
                                .build();
                    } else
                        return ResponseDTO.<FormDTO>builder().data(formDTO).message("Erro ao processar a requisição").status(BAD_REQUEST.getStatusCode()).build();
                } else {
                    return ResponseDTO.<FormDTO>builder().data(formDTO).message("Erro ao processar a requisição").status(BAD_REQUEST.getStatusCode()).build();
                }
            }


            if (clientSalvo.getId() != null) {
                enderecoSalvo = enderecoInterfaceDAO.save(formDTO.toEnderecoEntity(clientSalvo));
                formDTO.setEnderecoId(enderecoSalvo.getId());
                System.out.println(clientSalvo.getId());
            }


            return ResponseDTO.<FormDTO>builder()
                    .data(formDTO)
                    .message("Client saved")
                    .description("The client " + formDTO.getNome() + " was successfully saved")
                    .status(OK.getStatusCode())
                    .build();
        }
        else {
           FormDTO formDTOup = dao.upgrade(formDTO);
           return ResponseDTO.<FormDTO>builder()
                   .data(formDTOup  )
                   .message("Client updated")
                   .description("The client " + formDTO.getNome() + " has been successfully updated")
                   .status(OK.getStatusCode())
                   .build();
        }
    }

    public ResponseDTO<List<FormDTO>> getClient() {
        List<FormDTO> list = dao.busca();
        return ResponseDTO.<List<FormDTO>>builder()
                .data(list)
                .message("List Clients")
                .description("Successfully Listed Clients")
                .status(OK.getStatusCode())
                .build();
    }
}
