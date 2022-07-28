package Modelo;

import java.util.ArrayList;

public abstract class Validador {
    private ArrayList<Regla> Reglas;

    public Validador(){
        this.Reglas = new ArrayList<Regla>();
    }

    public void contabilizarPuntos(Jugador primerJugador, Jugador segundoJugador){
        validarReglas(primerJugador, segundoJugador);
    }

    public abstract void validarReglas(Jugador primerJugador, Jugador segundoJugador);

    public abstract Jugador validarJugada(ArrayList<Carta> cartas, Jugador jugador);

    public abstract void inicializarReglas();

    public ArrayList<Regla> obtenerReglas()
    {
        return this.Reglas;
    }
}
