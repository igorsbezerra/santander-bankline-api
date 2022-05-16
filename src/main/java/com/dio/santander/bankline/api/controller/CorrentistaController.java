package com.dio.santander.bankline.api.controller;

import com.dio.santander.bankline.api.dto.NovoCorrentistaDTO;
import com.dio.santander.bankline.api.model.Correntista;
import com.dio.santander.bankline.api.repository.CorrentistaRepository;
import com.dio.santander.bankline.api.services.CorrentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/correntistas")
public class CorrentistaController {

    @Autowired
    private CorrentistaRepository repostitory;

    @Autowired
    CorrentistaService service;

    @GetMapping
    public List<Correntista> findAll() {
        return this.repostitory.findAll();
    }

    @PostMapping
    public void save(@RequestBody NovoCorrentistaDTO correntistaDTO) {
        service.save(correntistaDTO);
    }
}
