package com.example.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CepLogTest {

    @Test
    public void testCepLogGettersAndSetters() {
        CepLog log = new CepLog();
        LocalDateTime now = LocalDateTime.now();

        log.setId(1L);
        log.setCep("12345678");
        log.setResponse("{\"cep\":\"12345678\",\"logradouro\":\"Rua Exemplo\"}");
        log.setTimestamp(now);

        assertEquals(1L, log.getId());
        assertEquals("12345678", log.getCep());
        assertEquals("{\"cep\":\"12345678\",\"logradouro\":\"Rua Exemplo\"}", log.getResponse());
        assertEquals(now, log.getTimestamp());
    }
}