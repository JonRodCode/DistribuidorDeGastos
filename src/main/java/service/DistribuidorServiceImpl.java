package service;

import model.DatosDeEntradaPersona;
import model.ResumenHogar;
import model.DatosDeSalidaPersona;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DistribuidorServiceImpl implements DistribuidorService {


    @Override
    public ResumenHogar calcularDistribucionDeGastosEntreMiembrosDelHogar(List<DatosDeEntradaPersona> gastosPorPersona) {

        //Creamos unas variables para guardar datos del hogar
        double gastoEquitativoHogar = 0, gastoIgualitarioHogar = 0;
        List<String> ajustesDeSaldos = new ArrayList<>();
        List<DatosDeSalidaPersona> resumenSalida = new ArrayList<>();
        int miembrosContribuyentes = gastosPorPersona.size();
        int miembrosBeneficiarios = 0;
        int miembrosTotales = miembrosContribuyentes;


        copiarDatosBasicosDeEntradaASalida(resumenSalida, gastosPorPersona);
        setearSueldoACadaIntegrante(resumenSalida);

        //Ahora hay que encontrar cual es el sueldo o ingreso del hogar,
        double sueldoHogar = resumenSalida.stream()
                .mapToDouble(DatosDeSalidaPersona::getSueldoTotal).sum();

        //En un for loop vamos a empezar a cargar los datos del resumen de salida, y aprovechamos el
        // loop para cargar algunos calculos
        for (int i = 0; i < gastosPorPersona.size(); i++) {

            DatosDeSalidaPersona persona = resumenSalida.get(i);  //atajo

            //Calculamos su porcentaje correspondiente
            double porcentajeCorrespondiente = (persona.getSueldoTotal() * 100) / sueldoHogar;
            persona.setPorcentajeCorrespondienteDelHogar(porcentajeCorrespondiente);

            //Variables para prox. calculos
            double gasto1, gasto2;

            //Sumamos los gastos equitativos
            gasto1 = Arrays.stream(gastosPorPersona.get(i).getGastosEquitativosPagados()).sum();

            //gasto2 = Arrays.stream(gastosPorPersona.get(i).getGastosEquitativosPendientes()).sum();
            gasto2 = Optional.ofNullable(gastosPorPersona.get(i).getGastosEquitativosPendientes())
                    .map(arr -> Arrays.stream(arr).sum())
                    .orElse(0.0);
            persona.setGastoEquitativo(gasto1 + gasto2);

            gastoEquitativoHogar += gasto1 + gasto2;  //Aprovechamos el loop y vamos calculando los gastos del hogar

            //Sumamos los gastos igualitarios
            gasto1 = Arrays.stream(gastosPorPersona.get(i).getGastosIgualitariosPagados()).sum();
            //gasto2 = Arrays.stream(gastosPorPersona.get(i).getGastosIgualitariosPendientes()).sum();
            gasto2 = Optional.ofNullable(gastosPorPersona.get(i).getGastosIgualitariosPendientes())
                    .map(arr -> Arrays.stream(arr).sum())
                    .orElse(0.0);
            persona.setGastoIgualitario(gasto1 + gasto2);

            gastoIgualitarioHogar += gasto1 + gasto2; //Aca lo mismo

            //Gasto total del hogar
            persona.setGastoHogarTotal(persona.getGastoEquitativo() + persona.getGastoIgualitario());

            // Aprovechamos el loop para hallar gastos personales
            for (DatosDeEntradaPersona prestamista : gastosPorPersona) {
                if (prestamista.getGastosPersonalesDeOtros() != null) {
                    for (Map.Entry<String, double[]> gasto : prestamista.getGastosPersonalesDeOtros().entrySet()) {
                        if (gasto.getValue() != null &&
                                persona.getNombre() != null &&
                                gasto.getKey().equals(persona.getNombre())) {

                            //Agregamos al gasto personal
                            double gastoPersonal = Arrays.stream(gasto.getValue()).sum();
                            persona.setPrestamoRecibido(persona.getPrestamoRecibido() + gastoPersonal);

                            //Agregamos el prestamo a quien presto
                            for (DatosDeSalidaPersona prestador : resumenSalida) {
                                if (prestador.getNombre().equals(prestamista.getNombre())) {
                                    prestador.setGastoPrestamistaTotal(prestador.getGastoPrestamistaTotal() + gastoPersonal);
                                    prestador.getGastoPersonalDeOtros().put(persona.getNombre(), gastoPersonal);
                                }
                            }
                        }
                    }
                }
            }
            // Aprovechamos el loop para contar la cantidad de miembros si alguno tiene personas a cargo
            miembrosTotales += persona.getPersonasACargo();
        }
        // Calculamos si hay beneficiarios
        miembrosBeneficiarios = miembrosTotales - miembrosContribuyentes;

        //Lo que sigue es calcular la parte correspondiente total a cada persona,
        //para eso calculamos la parte igualitaria y la equitativa, correspondiente
        for (DatosDeSalidaPersona persona : resumenSalida) {


            double parteIgualitariaCorrespondiente = (gastoIgualitarioHogar / miembrosTotales) *
                    (1 + persona.getPersonasACargo());


            double parteEquitativaCorrespondiente =
                    gastoEquitativoHogar * (persona.getPorcentajeCorrespondienteDelHogar() / 100);

            persona.setParteEquitativaCorrespondiente(parteEquitativaCorrespondiente);
            persona.setParteIgualitariaCorrespondiente(parteIgualitariaCorrespondiente);
            persona.setParteCorrespondienteTotal(parteIgualitariaCorrespondiente + parteEquitativaCorrespondiente);

            // De paso agregamos el gasto total
            persona.setGastoTotal(persona.getGastoHogarTotal() + persona.getGastoPrestamistaTotal());
        }

        //Ahora calculamos quien le debe a quien. La idea es que todos terminen con un saldo en 0
        Map<String, Double> saldos = new HashMap<>();

        //Calculamos saldo actual
        for (DatosDeSalidaPersona persona : resumenSalida) {
            double saldo = persona.getGastoTotal() - persona.getParteCorrespondienteTotal()
                    - persona.getPrestamoRecibido();
            saldos.put(persona.getNombre(), saldo);
        }

        //Separaramos saldos positivos y negativos
        List<Map.Entry<String, Double>> positivos = new ArrayList<>();
        List<Map.Entry<String, Double>> negativos = new ArrayList<>();
        for (Map.Entry<String, Double> registro : saldos.entrySet()) {
            if (registro.getValue() > 0) positivos.add(registro);
            else if (registro.getValue() < 0) negativos.add(registro);
        }

        //Ahora queda agarrar los saldos y compararlos
        int i = 0, j = 0;

        while (i < positivos.size() && j < negativos.size()) {
            Map.Entry<String, Double> cobrador = positivos.get(i);
            Map.Entry<String, Double> deudor = negativos.get(j);

            // .min devuelve el menor de 2 valores, asi determinamos quien pone el limite en la transferencia
            // ajuste = es el valor que el deudor le pagara al cobrador
            double ajuste = Math.min(cobrador.getValue(), -deudor.getValue());

            // Registrar la transacción
            ajustesDeSaldos.add(deudor.getKey() + " debe pagar " + ajuste + " a " + cobrador.getKey());

            for (int k = 0; k < resumenSalida.size(); k++) {

                if (resumenSalida.get(k).getNombre().equals(deudor.getKey())) {
                    resumenSalida.get(k).setEsDeudor(true);
                    resumenSalida.get(k).getDebeAODe().add(cobrador.getKey());
                    resumenSalida.get(k).getCantidadAPagarORecibir().add(ajuste);
                } else if (resumenSalida.get(k).getNombre().equals(cobrador.getKey())) {
                    resumenSalida.get(k).setEsDeudor(false);
                    resumenSalida.get(k).getDebeAODe().add(deudor.getKey());
                    resumenSalida.get(k).getCantidadAPagarORecibir().add(ajuste);
                }
            }

            // Ajustar los saldos
            cobrador.setValue(cobrador.getValue() - ajuste);
            deudor.setValue(deudor.getValue() + ajuste);

            // Avanzar al siguiente positivo o negativo si su saldo llega a 0
            if (cobrador.getValue() == 0) i++;
            if (deudor.getValue() == 0) j++;
        }

        //Listo! Ahora si tenemos todo calculado, solo queda guardar los valores en una clase ResumenHogar
        ResumenHogar resumenHogareno = new ResumenHogar(sueldoHogar, gastoEquitativoHogar,
                gastoIgualitarioHogar, resumenSalida, ajustesDeSaldos, miembrosContribuyentes,
                miembrosBeneficiarios,miembrosTotales);

        return resumenHogareno;
    }

    private void copiarDatosBasicosDeEntradaASalida(List<DatosDeSalidaPersona> resumenSalida, List<DatosDeEntradaPersona> gastosPorPersona){

        for (DatosDeEntradaPersona persona : gastosPorPersona) {
            DatosDeSalidaPersona integrante = new DatosDeSalidaPersona();
            //Cargamos el nombre, las ganancias de la persona, y su cantidad de personas a cargo
            integrante.setNombre(persona.getNombre());
            integrante.setGanancias(persona.getGanancias());
            integrante.setPersonasACargo(persona.getPersonasACargo());
            resumenSalida.add(integrante);
        }
    }

    private void calcularSueldoTotal(DatosDeSalidaPersona integrante) {
        double sueldoTotal = Arrays.stream(integrante.getGanancias()).sum();
        integrante.setSueldoTotal(sueldoTotal);
    }

    private void setearSueldoACadaIntegrante(List<DatosDeSalidaPersona> resumenSalida) {
        //2 procesos: copiar datos básicos y calcular sueldo total
        for (DatosDeSalidaPersona integrante : resumenSalida) {
            calcularSueldoTotal(integrante);
        }
    }
}
