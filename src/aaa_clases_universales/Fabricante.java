package aaa_clases_universales;

import java.io.Serializable;

public class Fabricante implements Serializable {
    String nombre;

    public Fabricante(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
