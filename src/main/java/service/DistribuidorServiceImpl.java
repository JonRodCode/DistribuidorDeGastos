package service;

import model.ResumenEntradaPersona;
import model.ResumenHogar;
import model.ResumenSalidaPersona;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DistribuidorServiceImpl implements DistribuidorService {

    //Este metodo hace los calculos para obtener un resumen con division de gastos
    @Override
    public ResumenHogar calcularResumenRapido(List<ResumenEntradaPersona> gastosPorPersona) {

        //Creamos unas variables para guardar datos del hogar
        double gastoEquitativoHogar =  0, gastoIgualitarioHogar = 0;
        List<String> ajustesDeSaldos = new ArrayList<>();
        List<ResumenSalidaPersona> resumenSalida = new ArrayList<>();

        //Lo primero seria calcular el sueldo total de cada Persona
        for (ResumenEntradaPersona persona : gastosPorPersona){
            //Creamos una persona
            ResumenSalidaPersona integrante = new ResumenSalidaPersona();

            //Cargamos el nombre y sus ganancias de la persona
            integrante.setNombre(persona.getNombre());
            integrante.setGanancias(persona.getGanancias());

            //Calculamos sueldo
            double sueldoTotal = Arrays.stream(integrante.getGanancias()).sum();
            integrante.setSueldoTotal(sueldoTotal);

            //Agregamos la persona a la lista
            resumenSalida.add(integrante);
        }

        //Ahora hay que encontrar cual es el sueldo o ingreso del hogar,
        double sueldoHogar = resumenSalida.stream()
                .mapToDouble(ResumenSalidaPersona::getSueldoTotal).sum();

        //En un for loop vamos a empezar a cargar los datos del resumen de salida, y aprovechamos el
        // loop para cargar algunos calculos
        for (int i = 0; i < gastosPorPersona.size(); i++){

            ResumenSalidaPersona persona = resumenSalida.get(i);  //atajo

            //Calculamos su porcentaje correspondiente
            double porcentajeCorrespondiente = (persona.getSueldoTotal() * 100) / sueldoHogar;
            persona.setPorcentajeCorrespondienteDelHogar(porcentajeCorrespondiente);

            //Variables para prox. calculos
            double gasto1,gasto2;

            //Sumamos los gastos equitativos
            gasto1 = Arrays.stream(gastosPorPersona.get(i).getGastosEquitativosPagados()).sum();
            gasto2 = Arrays.stream(gastosPorPersona.get(i).getGastosEquitativosPendientes()).sum();
            persona.setGastoEquitativo(gasto1 + gasto2);

            gastoEquitativoHogar += gasto1 + gasto2;  //Aprovechamos el loop y vamos calculando los gastos del hogar

            //Sumamos los gastos igualitarios
            gasto1 = Arrays.stream(gastosPorPersona.get(i).getGastosIgualitariosPagados()).sum();
            gasto2 = Arrays.stream(gastosPorPersona.get(i).getGastosIgualitariosPendientes()).sum();
            persona.setGastoIgualitario(gasto1 + gasto2);

            gastoIgualitarioHogar += gasto1 + gasto2; //Aca lo mismo

            //Gasto total del hogar
            persona.setGastoHogarTotal(persona.getGastoEquitativo() + persona.getGastoIgualitario());

            // Aprovechamos el loop para hallar gastos personales
            for (ResumenEntradaPersona prestamista : gastosPorPersona)  {
                if (prestamista.getGastosPersonalesDeOtros() != null){
                for (Map.Entry<String, double[]> gasto : prestamista.getGastosPersonalesDeOtros().entrySet()){
                    if (gasto.getValue() != null &&
                            persona.getNombre() != null &&
                            gasto.getKey().equals(persona.getNombre())){

                        //Agregamos al gasto personal
                        double gastoPersonal = Arrays.stream(gasto.getValue()).sum();
                        persona.setPrestamoTotal(persona.getPrestamoTotal() + gastoPersonal);

                        //Agregamos el prestamo a quien presto
                        for (ResumenSalidaPersona prestador : resumenSalida){
                            if (prestador.getNombre().equals(prestamista.getNombre())){
                                prestador.setGastoPrestamista(prestador.getGastoPrestamista() + gastoPersonal);
                                prestador.getGastoPersonalDeOtros().put(persona.getNombre(), gastoPersonal);
                            }
                        }
                    }
                    }
                }
            }
        }

        //Lo que sigue es calcular la parte correspondiente total a cada persona,
        //para eso calculamos la parte igualitaria y la equitativa, correspondiente
        double parteIgualitariaCorrespondiente = gastoIgualitarioHogar / resumenSalida.size();

        for (ResumenSalidaPersona persona : resumenSalida){
            double parteEquitativaCorrespondiente =
                    gastoEquitativoHogar * (persona.getPorcentajeCorrespondienteDelHogar() / 100);

            persona.setParteEquitativaCorrespondiente(parteEquitativaCorrespondiente);
            persona.setParteIgualitariaCorrespondiente(parteIgualitariaCorrespondiente);
            persona.setParteCorrespondienteTotal(parteIgualitariaCorrespondiente + parteEquitativaCorrespondiente);

            // De paso agregamos el gasto total
            persona.setGastoTotal(persona.getGastoHogarTotal() + persona.getGastoPrestamista());
        }

        //Ahora calculamos quien le debe a quien. La idea es que todos terminen con un saldo en 0
        Map<String, Double> saldos = new HashMap<>();

        //Calculamos saldo actual
        for (ResumenSalidaPersona persona : resumenSalida) {
            double saldo = persona.getGastoTotal() - persona.getParteCorrespondienteTotal()
                     - persona.getPrestamoTotal();
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
            double ajuste = Math.min(cobrador.getValue(), - deudor.getValue());

            // Registrar la transacción
            ajustesDeSaldos.add(deudor.getKey() + " debe pagar " + ajuste + " a " + cobrador.getKey());

            for (int k = 0; k < resumenSalida.size(); k++){

                if (resumenSalida.get(k).getNombre().equals(deudor.getKey())){
                    resumenSalida.get(k).setEsDeudor(true);
                    resumenSalida.get(k).getDebeAODe().add(cobrador.getKey());
                    resumenSalida.get(k).getCantidadAPagarORecibir().add(ajuste);
                }
                else if (resumenSalida.get(k).getNombre().equals(cobrador.getKey())){
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
                gastoIgualitarioHogar, resumenSalida, ajustesDeSaldos);

        return resumenHogareno;
    }


}
