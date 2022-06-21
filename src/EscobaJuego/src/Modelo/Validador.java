package Modelo;

import java.util.ArrayList;

public class Validador {
    public Jugador primerJugador;
    public Jugador segundoJugador;

    public Validador(Jugador jugadorUno, Jugador jugadorDos){
        this.primerJugador=jugadorUno;
        this.segundoJugador=jugadorDos;
    }

    public ArrayList<Naipe> validarCaptura(Naipe naipe, ArrayList<Naipe> naipes){
        return null;
    }

    public void contabilizarPuntos(Jugador jugador){}

    public Jugador reglaCantidadCartas(){
        return null;
    }

    public Jugador reglaPaloDeOros(){
        return null;
    }

    public Jugador reglaSieteDeOros(){
        return null;
    }
    
    public Jugador reglacantidadDeSietes(){
        return null;
    }

    public Boolean validadEscoba(ArrayList<Naipe> naipes){
        return true;
    }
}
