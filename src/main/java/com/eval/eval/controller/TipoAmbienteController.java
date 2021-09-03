package com.eval.eval.controller;

import com.eval.eval.model.TipoAmbiente;
import com.eval.eval.model.TipoComida;
import com.eval.eval.repository.TipoAmbienteRepository;
import com.eval.eval.repository.TipoComidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipos-ambiente")
public class TipoAmbienteController {
    @Autowired
    TipoAmbienteRepository tipoAmbienteRepository;

    @GetMapping
    public ResponseEntity<List<TipoAmbiente>> getAllTiposAmbiente() {


        try {
            List<TipoAmbiente> tipoAmbientes = new ArrayList<TipoAmbiente>();

            tipoAmbienteRepository.findAll().forEach(tipoAmbientes::add);

            return new ResponseEntity<>(tipoAmbientes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoAmbiente> getTipoAmbienteById(@PathVariable("id") long id) {
        Optional<TipoAmbiente> tipoAmbienteData = tipoAmbienteRepository.findById(id);

        if (tipoAmbienteData.isPresent()) {
            return new ResponseEntity<>(tipoAmbienteData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<TipoAmbiente> createTipoAmbiente(@RequestBody TipoAmbiente tipoAmbiente) {
        try {
            TipoAmbiente _tipoAmbiente = tipoAmbienteRepository
                    .save(new TipoAmbiente(tipoAmbiente.getNombre(), tipoAmbiente.getDescription()));
            return new ResponseEntity<>(_tipoAmbiente, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoAmbiente> updateTipoAmbiente(@PathVariable("id") long id, @RequestBody TipoAmbiente tipoAmbiente) {
        Optional<TipoAmbiente> tipoAmbienteData = tipoAmbienteRepository.findById(id);

        if (tipoAmbienteData.isPresent()) {
            TipoAmbiente _tipoAmbiente = tipoAmbienteData.get();
            _tipoAmbiente.setNombre(tipoAmbiente.getNombre());
            _tipoAmbiente.setDescription(tipoAmbiente.getDescription());
            return new ResponseEntity<>(tipoAmbienteRepository.save(_tipoAmbiente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTipoAmbiente(@PathVariable("id") long id) {
        try {
            tipoAmbienteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            tipoAmbienteRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
