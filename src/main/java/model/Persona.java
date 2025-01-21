package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Persona {
    private String nombre;
    private double[] ganancias;

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


}
