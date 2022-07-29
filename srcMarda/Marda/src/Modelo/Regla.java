package Modelo;

import java.util.ArrayList;

public abstract class Regla {
    private String nombre;
    private int puntaje;

    public Regla(String nombre, int puntaje){
        this.nombre = nombre;
        this.puntaje = puntaje;
    }

    public String obtenerNombre(){
        return this.nombre;
    }

    public int obtenerPuntaje(){
        return this.puntaje;
    }

    public abstract int validarRegla(ArrayList<Carta> cartas);
}