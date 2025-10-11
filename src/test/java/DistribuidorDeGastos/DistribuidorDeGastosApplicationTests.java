package DistribuidorDeGastos;

import model.DatosDeEntradaPersona;
import model.DatosDeSalidaPersona;
import model.ResumenHogar;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import service.DistribuidorService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class DistribuidorDeGastosApplicationTests {

	DistribuidorService distribuidorService;

	List<DatosDeEntradaPersona> gastosPorPersona;
	DatosDeSalidaPersona datosDeSalidaPersona;

	@Before
	public void setUp() {
		gastosPorPersona = new ArrayList<>();
		datosDeSalidaPersona = new DatosDeSalidaPersona();
	}

	@ParameterizedTest
	@MethodSource("cargarDatosDePrueba")
	void calcularDistribucionDeGastosEntreMiembrosDelHogarTests(List<DatosDeEntradaPersona> gastosPorPersona) {

		ResumenHogar resumen = distribuidorService.calcularDistribucionDeGastosEntreMiembrosDelHogar(gastosPorPersona);

		List<DatosDeSalidaPersona> datosDeSalida = resumen.getDetallePorPersona();


	}

	private static Stream<Arguments> cargarDatosDePrueba(){

		DatosDeEntradaPersona persona1;
		DatosDeEntradaPersona persona2;

		String nombreDePersona1 = "Persona1";
		double[] gananciasDePersona1 = new double[] {25.0};
		int personasACargoDePersona1 = 0;
		double[] gastosEquitativosPagados1 = new double[]{};
		double[] gastosEquitativosPendientes1 = new double[]{};
		double[] gastosIgualitariosPagados1 = new double[]{};
		double[] gastosIgualitariosPendientes1 = new double[]{};
		HashMap<String, double[]> gastosPersonalesDeOtros1 = new HashMap<>(){{
			put("", new double[]{22.2});
		}};
		persona1 = new DatosDeEntradaPersona(nombreDePersona1,gananciasDePersona1,
				personasACargoDePersona1, gastosEquitativosPagados1, gastosEquitativosPendientes1,
				gastosIgualitariosPagados1, gastosIgualitariosPendientes1, gastosPersonalesDeOtros1
				);

		String nombreDePersona2 = "Persona2";
		double[] gananciasDePersona2 = new double[] {25.0};
		int personasACargoDePersona2 = 0;
		double[] gastosEquitativosPagados2 = new double[]{};
		double[] gastosEquitativosPendientes2 = new double[]{};
		double[] gastosIgualitariosPagados2 = new double[]{};
		double[] gastosIgualitariosPendientes2 = new double[]{};
		HashMap<String, double[]> gastosPersonalesDeOtros2 = new HashMap<>(){{
			put("", new double[]{22.2});
		}};
		persona2 = new DatosDeEntradaPersona(nombreDePersona2,gananciasDePersona2,
				personasACargoDePersona2, gastosEquitativosPagados2, gastosEquitativosPendientes2,
				gastosIgualitariosPagados2, gastosIgualitariosPendientes2, gastosPersonalesDeOtros2
		);

		List<DatosDeEntradaPersona> gastosPorPersona = new ArrayList<>();
		gastosPorPersona.add(persona1);
		gastosPorPersona.add(persona2);

		return Stream.of(
				Arguments.of(gastosPorPersona)
		);
	};
}
