package model;

import java.util.ArrayList;
import java.util.HashMap;

public class ResumenSalidaPersona extends Persona {

    private double sueldoTotal;
    private double porcentajeCorrespondienteDelHogar;

    private double gastoEquitativo;
    private double gastoIgualitario;
    private double gastoHogarTotal;      //Este atributo se va a cargar en el constructor

    private double parteEquitativaCorrespondiente ;
    private double parteIgualitariaCorrespondiente;
    private double parteCorrespondienteTotal;

    private double prestamoRecibido;
    private HashMap<String, Double> gastoPersonalDeOtros;
    private double gastoPrestamistaTotal;

    private double gastoTotal;

    private boolean esDeudor;

    private ArrayList<String> debeAODe;
    private ArrayList<Double> cantidadAPagarORecibir;

    public ResumenSalidaPersona() {
        super();
        this.cantidadAPagarORecibir = new ArrayList<>();
        this.debeAODe = new ArrayList<>();
        this.gastoPersonalDeOtros = new HashMap<String, Double>();
        this.prestamoRecibido = 0;
        this.gastoPrestamistaTotal = 0;
    }

    public double getSueldoTotal() {
        return sueldoTotal;
    }

    public void setSueldoTotal(double sueldoTotal) {
        this.sueldoTotal = sueldoTotal;
    }

    public double getPorcentajeCorrespondienteDelHogar() {
        return porcentajeCorrespondienteDelHogar;
    }

    public void setPorcentajeCorrespondienteDelHogar(double porcentajeCorrespondienteDelHogar) {
        this.porcentajeCorrespondienteDelHogar = porcentajeCorrespondienteDelHogar;
    }

    public double getGastoEquitativo() {
        return gastoEquitativo;
    }

    public void setGastoEquitativo(double gastoEquitativo) {
        this.gastoEquitativo = gastoEquitativo;
    }

    public double getGastoIgualitario() {
        return gastoIgualitario;
    }

    public void setGastoIgualitario(double gastoIgualitario) {
        this.gastoIgualitario = gastoIgualitario;
    }

    public double getGastoHogarTotal() {
        return gastoHogarTotal;
    }

    public void setGastoHogarTotal(double gastoTotal) {
        this.gastoHogarTotal = gastoTotal;
    }

    public double getParteEquitativaCorrespondiente() {
        return parteEquitativaCorrespondiente;
    }

    public void setParteEquitativaCorrespondiente(double parteEquitativaCorrespondiente) {
        this.parteEquitativaCorrespondiente = parteEquitativaCorrespondiente;
    }

    public double getParteIgualitariaCorrespondiente() {
        return parteIgualitariaCorrespondiente;
    }

    public void setParteIgualitariaCorrespondiente(double parteIgualitariaCorrespondiente) {
        this.parteIgualitariaCorrespondiente = parteIgualitariaCorrespondiente;
    }

    public double getParteCorrespondienteTotal() {
        return parteCorrespondienteTotal;
    }

    public void setParteCorrespondienteTotal(double parteCorrespondienteTotal) {
        this.parteCorrespondienteTotal = parteCorrespondienteTotal;
    }

    public boolean isEsDeudor() {
        return esDeudor;
    }

    public void setEsDeudor(boolean esDeudor) {
        this.esDeudor = esDeudor;
    }

    public ArrayList<String> getDebeAODe() {
        return debeAODe;
    }

    public void setDebeAODe(ArrayList<String> debeAODe) {
        this.debeAODe = debeAODe;
    }

    public ArrayList<Double> getCantidadAPagarORecibir() {
        return cantidadAPagarORecibir;
    }

    public void setCantidadAPagarORecibir(ArrayList<Double> cantidadAPagarORecibir) {
        this.cantidadAPagarORecibir = cantidadAPagarORecibir;
    }

    public double getPrestamoRecibido() {
        return prestamoRecibido;
    }

    public void setPrestamoRecibido(double prestamoRecibido) {
        this.prestamoRecibido = prestamoRecibido;
    }
    public HashMap<String, Double> getGastoPersonalDeOtros() {
        return gastoPersonalDeOtros;
    }

    public void setGastoPersonalDeOtros(HashMap<String, Double> gastoPersonalDeOtros) {
        this.gastoPersonalDeOtros = gastoPersonalDeOtros;
    }

    public double getGastoPrestamistaTotal() {
        return gastoPrestamistaTotal;
    }

    public void setGastoPrestamistaTotal(double gastoPrestamistaTotal) {
        this.gastoPrestamistaTotal = gastoPrestamistaTotal;
    }

    public double getGastoTotal() {
        return gastoTotal;
    }

    public void setGastoTotal(double gastoTotal) {
        this.gastoTotal = gastoTotal;
    }
}
