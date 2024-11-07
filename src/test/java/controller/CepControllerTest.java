package com.example.controller;

import com.example.service.CepService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CepController.class)
public class CepControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CepService cepService;

    @Test
    public void testBuscarCep() throws Exception {
        String cep = "12345678";
        String expectedResponse = "{\"cep\":\"12345678\",\"logradouro\":\"Rua Exemplo\"}";

        when(cepService.buscarCep(cep)).thenReturn(expectedResponse);

        mockMvc.perform(get("/buscar-cep").param("cep", cep))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }
}