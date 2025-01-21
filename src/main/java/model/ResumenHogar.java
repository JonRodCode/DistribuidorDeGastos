package model;

import java.util.List;

public class ResumenHogar {
    private double sueldoHogar;
    private double gastoEquitativo;
    private double gastoIgualitario;
    private List<ResumenSalidaPersona> detallePorPersona;
    private List<String> ajustesDeSaldos;

    public ResumenHogar(double sueldoHogar, double gastoEquitativo, double gastoIgualitario, List<ResumenSalidaPersona> detallePorPersona, List<String> ajustesDeSaldos) {
        this.sueldoHogar = sueldoHogar;
        this.gastoEquitativo = gastoEquitativo;
        this.gastoIgualitario = gastoIgualitario;
        this.detallePorPersona = detallePorPersona;
        this.ajustesDeSaldos = ajustesDeSaldos;
    }

    public double getSueldoHogar() {
        return sueldoHogar;
    }

    public void setSueldoHogar(double sueldoHogar) {
        this.sueldoHogar = sueldoHogar;
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

    public List<ResumenSalidaPersona> getDetallePorPersona() {
        return detallePorPersona;
    }

    public void setDetallePorPersona(List<ResumenSalidaPersona> detallePorPersona) {
        this.detallePorPersona = detallePorPersona;
    }

    public List<String> getAjustesDeSaldos() {
        return ajustesDeSaldos;
    }

    public void setAjustesDeSaldos(List<String> ajustesDeSaldos) {
        this.ajustesDeSaldos = ajustesDeSaldos;
    }
}
