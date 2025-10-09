package DistribuidorDeGastos;

import model.DatosDeEntradaPersona;
import model.DatosDeSalidaPersona;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class DistribuidorDeGastosApplicationTests {

	List<DatosDeEntradaPersona> gastosPorPersona;
	DatosDeSalidaPersona datosDeSalidaPersona;

	@Before
	public void setUp() {
		gastosPorPersona = new ArrayList<>();
		datosDeSalidaPersona = new DatosDeSalidaPersona();
	}

	@Test
	void calcularDistribucionDeGastosEntreMiembrosDelHogarTests() {


	}
}
