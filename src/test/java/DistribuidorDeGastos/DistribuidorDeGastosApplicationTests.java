package DistribuidorDeGastos;

import model.DatosDeEntradaPersona;
import model.DatosDeSalidaPersona;
import model.ResumenHogar;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import service.DistribuidorService;
import service.DistribuidorServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DistribuidorDeGastosApplicationTests {

	@Autowired
	DistribuidorService distribuidorService;

	List<DatosDeEntradaPersona> gastosPorPersona;
	DatosDeSalidaPersona datosDeSalidaPersona;

	@Before
	public void setUp() {
		gastosPorPersona = new ArrayList<>();
		datosDeSalidaPersona = new DatosDeSalidaPersona();
		distribuidorService = new DistribuidorServiceImpl();
	}

	@ParameterizedTest
	@MethodSource("cargarDatosDePrueba")
	void calcularDistribucionDeGastosEntreMiembrosDelHogarTests(List<DatosDeEntradaPersona> gastosPorPersona, ResumenHogar resumenValido) {

		ResumenHogar resumenCalculado = distribuidorService.calcularDistribucionDeGastosEntreMiembrosDelHogar(gastosPorPersona);

		assertEquals(resumenCalculado.getSueldoHogar(),resumenValido.getSueldoHogar());
		assertEquals(resumenCalculado.getGastoEquitativo(), resumenValido.getGastoEquitativo());
		assertEquals(resumenCalculado.getGastoIgualitario(), resumenValido.getGastoIgualitario());
		assertEquals(resumenCalculado.getMiembrosContribuyentes(), resumenValido.getMiembrosContribuyentes());
		assertEquals(resumenCalculado.getMiembrosBeneficiarios(), resumenValido.getMiembrosBeneficiarios());
		assertEquals(resumenCalculado.getTotalDeMiembros(), resumenValido.getTotalDeMiembros());
		verificarAjustesDeSaldo(resumenCalculado.getAjustesDeSaldos(), resumenValido.getAjustesDeSaldos());
		verificarDetalleDeSalida(resumenCalculado.getDetallePorPersona(), resumenValido.getDetallePorPersona());
	}

	void verificarAjustesDeSaldo(List<String> ajusteCalculado, List<String> ajusteValido){
		assertEquals(ajusteCalculado.size(), ajusteValido.size());
		for (int i = 0; i < ajusteCalculado.size(); i++){
			assertEquals(ajusteCalculado.get(i), ajusteValido.get(i));
		}
	}

	void verificarDetalleDeSalida(List<DatosDeSalidaPersona> detalleCalculado, List<DatosDeSalidaPersona> detalleValido){

		assertEquals(0,1);

	}

	private static Stream<Arguments> cargarDatosDePrueba(){

		DatosDeEntradaPersona persona1;
		DatosDeEntradaPersona persona2;

		String nombreDePersona1 = "Persona1";
		double[] gananciasDePersona1 = new double[] {732262.0};
		int personasACargoDePersona1 = 0;
		double[] gastosEquitativosPagados1 = new double[]{145400.0};
		double[] gastosEquitativosPendientes1 = new double[]{8415.0,28018.0,95604.0};
		double[] gastosIgualitariosPagados1 = new double[]{};
		double[] gastosIgualitariosPendientes1 = new double[]{19166,4145,5416,24570,44133,6164,6498,11161,15778,6715,19072,4838,13400,18400};
		HashMap<String, double[]> gastosPersonalesDeOtros1 = new HashMap<>(){{
			put("Persona2", new double[]{19973.0,8250.0});
		}};
		persona1 = new DatosDeEntradaPersona(nombreDePersona1,gananciasDePersona1,
				personasACargoDePersona1, gastosEquitativosPagados1, gastosEquitativosPendientes1,
				gastosIgualitariosPagados1, gastosIgualitariosPendientes1, gastosPersonalesDeOtros1
				);

		String nombreDePersona2 = "Persona2";
		double[] gananciasDePersona2 = new double[] {475650.0, 400000};
		int personasACargoDePersona2 = 0;
		double[] gastosEquitativosPagados2 = new double[]{162626.0};
		double[] gastosEquitativosPendientes2 = new double[]{};
		double[] gastosIgualitariosPagados2 = new double[]{};
		double[] gastosIgualitariosPendientes2 = new double[]{};
		HashMap<String, double[]> gastosPersonalesDeOtros2 = new HashMap<>(){{
			put("Persona1", new double[]{31699.0});
		}};
		persona2 = new DatosDeEntradaPersona(nombreDePersona2,gananciasDePersona2,
				personasACargoDePersona2, gastosEquitativosPagados2, gastosEquitativosPendientes2,
				gastosIgualitariosPagados2, gastosIgualitariosPendientes2, gastosPersonalesDeOtros2
		);

		List<DatosDeEntradaPersona> gastosPorPersona = new ArrayList<>();
		gastosPorPersona.add(persona1);
		gastosPorPersona.add(persona2);

		List<DatosDeSalidaPersona> resumenSalida = new ArrayList<>();
		List<String> ajustesDeSaldos = new ArrayList<>(){{
			add("Persona2 debe pagar 173279.14392205537 a Persona1");
		}};

		ResumenHogar resumenHogar = new ResumenHogar(1607912.0, 440063.0,
				199456.0, resumenSalida, ajustesDeSaldos, 2,
				0,2);

		return Stream.of(
				Arguments.of(gastosPorPersona, resumenHogar)
		);
	};
}
