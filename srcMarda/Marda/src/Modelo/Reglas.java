package Modelo;

import java.util.ArrayList;

public abstract class Reglas {
    private String nombre;
    private int puntaje;

    public Reglas(String nombre, int puntaje)
    {
        this.nombre = nombre;
        this.puntaje = puntaje;
    }

    public String obtenerNombre()
    {
        return this.nombre;
    }

    public int obtenerPuntaje()
    {
        return this.puntaje;
    }

    protected abstract int validarRegla(ArrayList<Carta> cartas);
}