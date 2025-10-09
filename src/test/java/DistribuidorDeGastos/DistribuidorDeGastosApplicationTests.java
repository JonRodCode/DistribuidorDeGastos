package DistribuidorDeGastos;

import model.DatosDeEntradaPersona;
import model.DatosDeSalidaPersona;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class DistribuidorDeGastosApplicationTests {

	DatosDeEntradaPersona datosDeEntradaPersona;
	DatosDeSalidaPersona datosDeSalidaPersona;

	@Before
	public void setUp() {
		datosDeEntradaPersona = new DatosDeEntradaPersona();
		datosDeSalidaPersona = new DatosDeSalidaPersona();
	}

	@Test
	void calcularDistribucionDeGastosEntreMiembrosDelHogarTests() {


	}

}
