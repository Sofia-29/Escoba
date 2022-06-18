package Modelo;

import java.util.ArrayList;

public abstract class Jugador {

    private ArrayList<Naipe> cartas;
    private int puntaje;
    private String nombre;
    private ArrayList<Naipe> cartasCapturadas;

    public Jugador(String nombre){
        this.nombre = nombre;
        this.puntaje = 0;
    }

    public abstract Naipe descartarCarta(ArrayList<Naipe> naipe);

    public ArrayList<Naipe> obtenerCartas(){
        return new ArrayList<Naipe>();
    };

    public Naipe obtenerCarta(int valor, String palo){
        return new Naipe();
    };

    public int obtenerPuntaje(){
        return puntaje;
    }

    public String obtenerNombre(){
        return nombre;
    }

    public void asignarCartas(ArrayList<Naipe> cartas){
        this.cartas = cartas;
    }

    public void asignarPuntaje(int puntaje){
        this.puntaje+=puntaje;
    }

    public void capturarCartas(ArrayList<Naipe> cartasCapturadas){

    }
    public ArrayList<Naipe> obtenerCartasCapturadas(){
        return cartasCapturadas;
    }

    public int obtenerNumeroCartasCapturadas(){
        return cartasCapturadas.size();
    }

    public int obtenerNumeroCartasEnJuego(){
        return cartas.size();
    }
}
