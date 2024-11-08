package com.example.controller;

import com.example.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CepController {

    @Autowired
    private CepService cepService;

    @GetMapping("/buscar-cep")
    public String buscarCep(@RequestParam String cep) {
        return cepService.buscarCep(cep);
    }
}