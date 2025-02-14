package service;

import model.DatosDeEntradaPersona;
import model.ResumenHogar;

import java.util.List;

public interface DistribuidorService {

    ResumenHogar calcularDistribucionDeGastosEntreMiembrosDelHogar(List<DatosDeEntradaPersona> gastosPorPersona);

}
