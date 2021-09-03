package com.eval.eval.controller;

import com.eval.eval.model.Restaurante;
import com.eval.eval.model.TipoComida;
import com.eval.eval.repository.RestauranteRepository;
import com.eval.eval.repository.TipoComidaRepository;

import com.eval.eval.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurante")
public class RestauranteController {
    @Autowired
    RestauranteRepository restauranteRepository;
//    @Autowired


    @GetMapping
    public ResponseEntity<List<Restaurante>> getAllRestaurantes() {

        try {
            List<Restaurante> restaurantes = new ArrayList<Restaurante>();

            restauranteRepository.findAll().forEach(restaurantes::add);

            return new ResponseEntity<>(restaurantes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> getRestauranteById(@PathVariable("id") long id) {
        Optional<Restaurante> restauranteData = restauranteRepository.findById(id);

        if (restauranteData.isPresent()) {
            return new ResponseEntity<>(restauranteData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Restaurante> createRestaurante(@RequestBody Restaurante restaurante) {
        try {
            Optional<Restaurante> restauranteData = restauranteRepository.findByName(restaurante.getNombre());
            if(restauranteData.isPresent()){
                throw new IllegalStateException("el restaurante ya esta dado de alta!");
            }
            Restaurante _restaurante = restauranteRepository
                    .save(new Restaurante(restaurante.getNombre(), restaurante.getDescription()));
            return new ResponseEntity<>(_restaurante, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Restaurante> uploadFile(@PathVariable("id") long id, @RequestParam("file") MultipartFile file) throws IOException {
        String message = "";
        try {

            Optional<Restaurante> restauranteData = restauranteRepository.findById(id);

            if (restauranteData.isPresent()) {

                Restaurante _restaurante = restauranteData.get();

                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                _restaurante.setLogo("logos/"+id+"/"+fileName);

                Restaurante _restauranteSaved = restauranteRepository.save(_restaurante);

                String uploadDir = "logos/" + _restauranteSaved.getId();

                FileUploadUtil.saveFile(uploadDir, fileName, file);

                return new ResponseEntity<>(_restauranteSaved, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> updateRestaurante(@PathVariable("id") long id, @RequestBody Restaurante restaurante) {
        Optional<Restaurante> restauranteData = restauranteRepository.findById(id);

        if (restauranteData.isPresent()) {
            Restaurante _restaurante = restauranteData.get();
            _restaurante.setNombre(restaurante.getNombre());
            _restaurante.setDescription(restaurante.getDescription());
            return new ResponseEntity<>(restauranteRepository.save(_restaurante), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRestaurante(@PathVariable("id") long id) {
        try {
            restauranteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllRestaurantes() {
        try {
            restauranteRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/internal-server-error")
    public ResponseEntity<HttpStatus> getHttpStatusInternalServerError(final HttpServletResponse response) throws IOException {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
