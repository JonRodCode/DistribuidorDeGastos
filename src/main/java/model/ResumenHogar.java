package model;

import java.util.List;

public class ResumenHogar {
    private double sueldoHogar;
    private double gastoEquitativo;
    private double gastoIgualitario;
    private int miembrosContribuyentes;
    private int miembrosBeneficiarios;
    private int totalDeMiembros;
    private List<ResumenSalidaPersona> detallePorPersona;
    private List<String> ajustesDeSaldos;

    public ResumenHogar(double sueldoHogar, double gastoEquitativo, double gastoIgualitario,
                        List<ResumenSalidaPersona> detallePorPersona, List<String> ajustesDeSaldos,
                        int miembrosContribuyentes, int miembrosBeneficiarios, int cantidadTotalDeMiembros) {
        this.sueldoHogar = sueldoHogar;
        this.gastoEquitativo = gastoEquitativo;
        this.gastoIgualitario = gastoIgualitario;
        this.totalDeMiembros = cantidadTotalDeMiembros;
        this.miembrosContribuyentes = miembrosContribuyentes;
        this.miembrosBeneficiarios = miembrosBeneficiarios;
        this.ajustesDeSaldos = ajustesDeSaldos;
        this.detallePorPersona = detallePorPersona;
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

    public int getMiembrosContribuyentes() {
        return miembrosContribuyentes;
    }

    public void setMiembrosContribuyentes(int miembrosContribuyentes) {
        this.miembrosContribuyentes = miembrosContribuyentes;
    }

    public int getMiembrosBeneficiarios() {
        return miembrosBeneficiarios;
    }

    public void setMiembrosBeneficiarios(int miembrosBeneficiarios) {
        this.miembrosBeneficiarios = miembrosBeneficiarios;
    }

    public int getTotalDeMiembros() {
        return totalDeMiembros;
    }

    public void setTotalDeMiembros(int totalDeMiembros) {
        this.totalDeMiembros = totalDeMiembros;
    }
}
