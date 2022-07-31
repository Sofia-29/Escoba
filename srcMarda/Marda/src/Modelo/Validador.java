package Modelo;

import java.util.ArrayList;

public abstract class Validador {
    private ArrayList<Regla> Reglas;

    public Validador(){
        this.Reglas = new ArrayList<Regla>();
    }

    /**
     * Método plantilla
     * @param primerJugador Jugador 1 al que se le contabilizan los puntos
     * @param segundoJugador Jugador 2 al que se le contabilizan los puntos
     */
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
     * @return Las cartas capturadas
     */
    public abstract ArrayList<Carta> validarJugada(ArrayList<Carta> cartas, Jugador jugador);

    /**
     * Inicializa las reglas concretas de cada juego
     */
    public abstract void inicializarReglas();

    /**
     * @return Las reglas del juego
     */
    public ArrayList<Regla> obtenerReglas()
    {
        return this.Reglas;
    }
}
