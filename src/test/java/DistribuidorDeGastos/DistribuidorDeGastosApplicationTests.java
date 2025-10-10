package DistribuidorDeGastos;

import model.DatosDeEntradaPersona;
import model.DatosDeSalidaPersona;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
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

		List<DatosDeEntradaPersona> gastosPorPersona = new ArrayList<>();
		DatosDeEntradaPersona datosDePersona1 = new DatosDeEntradaPersona();
		datosDePersona1.setNombre("Pedro");
		datosDePersona1.setGanancias(new double[] {25.0,5.2});
		datosDePersona1.setPersonasACargo(0);

		datosDePersona1.setGastosEquitativosPagados(new double[] {25.0,5.2});
		datosDePersona1.setGastosEquitativosPendientes(new double[] {25.0,5.2});
		datosDePersona1.setGastosIgualitariosPagados(new double[] {25.4,2.0});
		datosDePersona1.setGastosIgualitariosPendientes(new double[]{21.2,5.0});

		datosDePersona1.setGastosPersonalesDeOtros(new HashMap<String, double[]>(){{
			put("Hola", new double[]{22.2});

		}});
		//private HashMap<String, double[]> gastosPersonalesDeOtros;
















	}
}
