package Modelo;
import java.util.ArrayList;

public abstract class Jugador {
    private ArrayList<Carta> cartas;
    private int puntaje;
    private String nombre;

    public Jugador(){
        this.puntaje = 0;
    }

    public String obtenerNombre(){
        return nombre;
    }

    public int obtenerPuntaje(){
        return this.puntaje;
    }

    public ArrayList<Carta> obtenerCartas(){
        return this.cartas;
    }

    public void asignarCartas(ArrayList<Carta> cartas){
        this.cartas = cartas;
    }

    public void asignarPuntaje(int puntaje){
        this.puntaje += puntaje;
    }

    public void asignarNombre(String nombre){
        this.nombre = nombre;
    }

    public void aumentarPuntaje(int puntaje){
        this.puntaje += puntaje;
    }

    public Carta obtenerCarta(String palo, int valor){
        Carta resultado = null;
        for(Carta carta:this.cartas){
            if(carta.obtenerPalo().equals(palo) && carta.obtenerValor() == valor){
                resultado = carta;
                break;
            }
        }
        return resultado; 
    }

    protected abstract Carta elegirCarta(ArrayList<Carta> carta);

    public Carta descartarCarta(ArrayList<Carta> carta){
        return null;
    }
}
