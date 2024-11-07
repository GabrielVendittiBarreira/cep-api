package com.example.repository;

import com.example.model.CepLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class CepLogRepositoryTest {

    @Autowired
    private CepLogRepository cepLogRepository;

    @Test
    public void testSaveAndFindById() {
        CepLog log = new CepLog();
        log.setCep("12345678");
        log.setResponse("{\"cep\":\"12345678\",\"logradouro\":\"Rua Exemplo\"}");
        log.setTimestamp(LocalDateTime.now());

        CepLog savedLog = cepLogRepository.save(log);
        Optional<CepLog> retrievedLog = cepLogRepository.findById(savedLog.getId());

        assertTrue(retrievedLog.isPresent());
        assertEquals(savedLog.getId(), retrievedLog.get().getId());
        assertEquals("12345678", retrievedLog.get().getCep());
        assertEquals("{\"cep\":\"12345678\",\"logradouro\":\"Rua Exemplo\"}", retrievedLog.get().getResponse());
    }
}