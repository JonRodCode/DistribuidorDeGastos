package model;

import java.util.HashMap;

public class DatosDeEntradaPersona extends Persona{
    private double[] gastosEquitativosPagados;
    private double[] gastosEquitativosPendientes;
    private double[] gastosIgualitariosPagados;
    private double[] gastosIgualitariosPendientes;
    private HashMap<String, double[]> gastosPersonalesDeOtros;

    public DatosDeEntradaPersona() {
        super();
    }
    public DatosDeEntradaPersona(String nombre, double[] ganancias, int personasACargo) {
        super(nombre, ganancias, personasACargo);
    }
    public DatosDeEntradaPersona(String nombre, double[] ganancias, int personasACargo,
                                 double[] gastosEquitativosPagados, double[] gastosEquitativosPendientes,
                                 double[] gastosIgualitariosPagados, double[] gastosIgualitariosPendientes,
                                 HashMap<String, double[]> gastosPersonalesDeOtros) {
        super(nombre, ganancias, personasACargo);
        this.gastosEquitativosPagados = gastosEquitativosPagados;
        this.gastosEquitativosPendientes = gastosEquitativosPendientes;
        this.gastosIgualitariosPagados = gastosIgualitariosPagados;
        this.gastosIgualitariosPendientes = gastosIgualitariosPendientes;
        this.gastosPersonalesDeOtros = gastosPersonalesDeOtros;
    }

    public double[] getGastosEquitativosPagados() {
        return gastosEquitativosPagados;
    }

    public void setGastosEquitativosPagados(double[] gastosEquitativosPagados) {
        this.gastosEquitativosPagados = gastosEquitativosPagados;
    }

    public double[] getGastosEquitativosPendientes() {
        return gastosEquitativosPendientes;
    }

    public void setGastosEquitativosPendientes(double[] gastosEquitativosPendientes) {
        this.gastosEquitativosPendientes = gastosEquitativosPendientes;
    }

    public double[] getGastosIgualitariosPagados() {
        return gastosIgualitariosPagados;
    }

    public void setGastosIgualitariosPagados(double[] gastosIgualitariosPagados) {
        this.gastosIgualitariosPagados = gastosIgualitariosPagados;
    }

    public double[] getGastosIgualitariosPendientes() {
        return gastosIgualitariosPendientes;
    }

    public void setGastosIgualitariosPendientes(double[] gastosIgualitariosPendientes) {
        this.gastosIgualitariosPendientes = gastosIgualitariosPendientes;
    }

    public HashMap<String, double[]> getGastosPersonalesDeOtros() {
        return gastosPersonalesDeOtros;
    }

    public void setGastosPersonalesDeOtros(HashMap<String, double[]> gastosPorOtros) {
        this.gastosPersonalesDeOtros = gastosPorOtros;
    }
}
