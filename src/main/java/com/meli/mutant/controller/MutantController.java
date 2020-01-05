package com.meli.mutant.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meli.mutant.dto.DNASequenceDTO;
import com.meli.mutant.dto.StatsDTO;
import com.meli.mutant.service.MutantService;

@RestController
public class MutantController {

    @Autowired
    private MutantService mutantService;

    public MutantController(MutantService mutantService) {
        this.mutantService = mutantService;
    }

    @PostMapping(value = "/mutant", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@RequestBody DNASequenceDTO dnaSequence)
            throws InterruptedException, ExecutionException {
        Boolean isMutant = mutantService.isMutant(dnaSequence).get();
        ResponseEntity<String> response = null;
        if (isMutant) {
            response = new ResponseEntity<>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return response;
    }

    @GetMapping(value = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StatsDTO> getStats() {
        StatsDTO statsDTO = mutantService.getStats();
        return new ResponseEntity<StatsDTO>(statsDTO, HttpStatus.OK);
    }

}
