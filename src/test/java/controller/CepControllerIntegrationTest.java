package com.example.controller;

import com.example.model.CepLog;
import com.example.repository.CepLogRepository;
import com.example.service.CepService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import software.amazon.awssdk.services.s3.S3Client;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CepControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CepService cepService;

    @MockBean
    private CepLogRepository cepLogRepository;

    @MockBean
    private S3Client s3Client;

    @MockBean
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        Mockito.reset(cepService, cepLogRepository, s3Client, restTemplate);
    }

    @Test
    public void testBuscarCep() throws Exception {
        String cep = "12345678";
        String expectedResponse = "{\"cep\":\"12345678\",\"logradouro\":\"Rua Exemplo\"}";

        when(restTemplate.getForObject(any(String.class), any(Class.class))).thenReturn(expectedResponse);

        CepLog log = new CepLog();
        log.setCep(cep);
        log.setResponse(expectedResponse);
        log.setTimestamp(LocalDateTime.now());

        when(cepLogRepository.save(any(CepLog.class))).thenReturn(log);

        mockMvc.perform(get("/buscar-cep").param("cep", cep))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }
}