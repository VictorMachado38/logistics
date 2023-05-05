package com.br.logistics.model.dto;


import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.*;
import java.io.Serializable;
@Data
public class ResponseDTO<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private T data;
    private String message;
    private String description;
    private Integer status;

    @Builder
    public ResponseDTO(T data, String message, String description, Integer status) {
        this.data = data;
        this.message = message;
        this.description = description;
        this.status = status;
    }
}
