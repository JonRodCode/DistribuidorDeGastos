package model;

public abstract class Persona {
    private String nombre;
    private double[] ganancias;
    private int personasACargo;

    public Persona() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double[] getGanancias() {
        return ganancias;
    }

    public void setGanancias(double[] ganancias) {
        this.ganancias = ganancias;
    }

    public int getPersonasACargo() {
        return personasACargo;
    }

    public void setPersonasACargo(int personasACargo) {
        this.personasACargo = personasACargo;
    }
}
