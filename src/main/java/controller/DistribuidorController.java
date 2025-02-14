package controller;

import model.DatosDeEntradaPersona;
import model.ResumenHogar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.DistribuidorService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class DistribuidorController {

    @Autowired
    DistribuidorService service;

    @PostMapping(value="resumen", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResumenHogar> calcularResumen(@RequestBody List<DatosDeEntradaPersona> resumenEntrada) {
        ResumenHogar hogar = service.calcularDistribucionDeGastosEntreMiembrosDelHogar(resumenEntrada);
        return new ResponseEntity<ResumenHogar>(hogar, HttpStatus.OK);
    }
}
