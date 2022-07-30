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

    /**
     * Valida las reglas para ambos jugadores
     * @param primerJugador Primer Jugador del juego
     * @param segundoJugador Segundo Jugador del juego
     */
    public abstract void validarReglas(Jugador primerJugador, Jugador segundoJugador);

    /**
     * Valida la jugada hecha por un jugador
     * @param cartas Las cartas en mesa y la carta tirada
     * @param jugador El jugador que hizo la jugada
     */
    public abstract void validarJugada(ArrayList<Carta> cartas, Jugador jugador);

    /**
     * Inicializa las reglas concretas de cada juego
     */
    public abstract void inicializarReglas();

    public ArrayList<Regla> obtenerReglas()
    {
        return this.Reglas;
    }

    public ArrayList<Carta> validarCaptura(Jugador jugadorActual, Carta cartaDescartada,
            ArrayList<Carta> cartasEnMesa) {
        return null;
    }
}
