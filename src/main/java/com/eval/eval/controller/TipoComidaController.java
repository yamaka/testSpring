package com.eval.eval.controller;

import com.eval.eval.model.TipoComida;
import com.eval.eval.repository.TipoComidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipos-comida")
public class TipoComidaController {
    @Autowired
    TipoComidaRepository tipoComidaRepository;

    @GetMapping
    public ResponseEntity<List<TipoComida>> getAllTiposComida() {
//        LocalDateTime dateconst = LocalDateTime.of(2021, Month.SEPTEMBER, 15, 14, 30);
//        TipoComida tipoComida1 = new TipoComida("test", "desc", dateconst, 1L, dateconst, 1L, dateconst, 1L,dateconst, 1L  );
//       return List.of(tipoComida1);

        try {
            List<TipoComida> tipoComidas = new ArrayList<TipoComida>();

            tipoComidaRepository.findAll().forEach(tipoComidas::add);

//            if (tipoComidas.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }

            return new ResponseEntity<>(tipoComidas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoComida> getTipoComidaById(@PathVariable("id") long id) {
        Optional<TipoComida> tipoComidaData = tipoComidaRepository.findById(id);

        if (tipoComidaData.isPresent()) {
            return new ResponseEntity<>(tipoComidaData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<TipoComida> createTipoComida(@RequestBody TipoComida tipoComida) {
        try {
            TipoComida _tipoComida = tipoComidaRepository
                    .save(new TipoComida(tipoComida.getNombre(), tipoComida.getDescription()));
            return new ResponseEntity<>(_tipoComida, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoComida> updateTipoComida(@PathVariable("id") long id, @RequestBody TipoComida tipoComida) {
        Optional<TipoComida> tipoComidaData = tipoComidaRepository.findById(id);

        if (tipoComidaData.isPresent()) {
            TipoComida _tipoComida = tipoComidaData.get();
            _tipoComida.setNombre(tipoComida.getNombre());
            _tipoComida.setDescription(tipoComida.getDescription());
            return new ResponseEntity<>(tipoComidaRepository.save(_tipoComida), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTipoComida(@PathVariable("id") long id) {
        try {
            tipoComidaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            tipoComidaRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
