package com.sisol.sys_citas.clients.reciec.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sisol.sys_citas.clients.reciec.dto.ResponseClientReniecDTO;
import com.sisol.sys_citas.clients.reciec.exception.ClientReniecApiException;

@Service
public class ClientReniecApiService {

    private final RestTemplate reniecRestTemplate;

    // inyectar el bean template
    public ClientReniecApiService(@Qualifier("reniecRestTemplate") RestTemplate reniecRestTemplate) {
        this.reniecRestTemplate = reniecRestTemplate;
    }

    // metodo para buscar y obtener los datos de la persona

    public ResponseClientReniecDTO identificarPersonaPorDni(String dni) {
        String url = dni;

        try {
            return reniecRestTemplate.getForObject(url, ResponseClientReniecDTO.class);
        }

        // excepciones que manejadas que ocurre el el api
        catch (HttpStatusCodeException ex) {

            String error = "Error en Sevicio de Reniec";

            // capturar el mensaje de cliente
            try {

                JsonNode json = new ObjectMapper().readTree(ex.getResponseBodyAsString());
                if (json.has("error")) {
                    error = json.get("error").asText();

                }

            } catch (IOException e) {
                 // no se pudo parsear el a formato json,

            }

            throw new ClientReniecApiException(error);

        }

        // error de conexion con el api

        catch(ResourceAccessException ex){
            throw new ClientReniecApiException("No se pudo conectar a Reniec");
        }

    }

}
