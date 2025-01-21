package service;

import model.ResumenEntradaPersona;
import model.ResumenHogar;

import java.util.List;

public interface DistribuidorService {

    ResumenHogar calcularResumenRapido(List<ResumenEntradaPersona> gastosPorPersona);

}
