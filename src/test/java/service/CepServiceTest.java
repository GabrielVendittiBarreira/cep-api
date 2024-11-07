package com.example.service;

import com.example.model.CepLog;
import com.example.repository.CepLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import software.amazon.awssdk.services.s3.S3Client;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CepServiceTest {

    @Mock
    private CepLogRepository cepLogRepository;

    @Mock
    private S3Client s3Client;

    @InjectMocks
    private CepService cepService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarCep() {
        String cep = "12345678";
        String expectedResponse = "{\"cep\":\"12345678\",\"logradouro\":\"Rua Exemplo\"}";

        RestTemplate restTemplate = mock(RestTemplate.class);
        when(restTemplate.getForObject(any(String.class), eq(String.class))).thenReturn(expectedResponse);

        CepService service = new CepService();
        service.setRestTemplate(restTemplate);
        service.setCepLogRepository(cepLogRepository);
        service.setS3Client(s3Client);

        String response = service.buscarCep(cep);

        assertEquals(expectedResponse, response);
        verify(cepLogRepository, times(1)).save(any(CepLog.class));
    }
}