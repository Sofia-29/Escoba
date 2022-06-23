package Controlador;
import Modelo.Naipe;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.Random;

import javax.swing.text.View;

import Modelo.Juego;
import Modelo.Jugador;
import Modelo.Mazo;
import Vista.Vista;

public class Controlador {
    public static void main(String[] args) throws Exception {
        Juego juego = new Juego();
        Vista vista = new Vista();
        Jugador jugadorAuxiliar;
        ArrayList<Naipe> cartasJugador;
        Naipe naipeAuxiliar = null;
        String jugadorNombre;
        int jugadorOpcion;
        Random rand = new Random();

        jugadorNombre = vista.preguntarNombreJugadorPersona();
        jugadorOpcion = vista.preguntarTurno();

        juego.iniciarPartida(jugadorNombre, jugadorOpcion);
        jugadorAuxiliar = juego.obtenerPrimerJugador();
        

        juego.retornarCartasEnMesa();
        cartasJugador = juego.retornarCartasJugador(jugadorNombre);
        jugadorAuxiliar = juego.obtenerJugadorActual();
        vista.iniciarPartida(cartasJugador);

        while(!juego.validarTerminarPartida()){
            vista.actualizarTurnoJugador(jugadorAuxiliar.obtenerNombre());
            if(juego.repartirCartas()){
                juego.repartirCartasJugadores();
                cartasJugador = juego.obtenerJugadorPersona(jugadorNombre).obtenerCartas();
                vista.actualizarCartasJugador(cartasJugador);
            }else{
                if(jugadorAuxiliar.obtenerNombre() == jugadorNombre){
                    naipeAuxiliar = vista.retornarNaipeSeleccionada();
                    while(naipeAuxiliar == null){
                        naipeAuxiliar = vista.retornarNaipeSeleccionada();
                    }
                    ArrayList<Naipe> naipe = new ArrayList<Naipe>();
                    naipe.add(naipeAuxiliar);
                    jugadorAuxiliar.descartarCarta(naipe);
                }else{
                    naipeAuxiliar = jugadorAuxiliar.descartarCarta(juego.retornarCartasEnMesa());
                }
            } 
            vista.actualizarCartasEnMesa(juego.retornarCartasEnMesa());
            jugadorAuxiliar = juego.pasarTurno();
        }
        juego.terminarPartida();
        jugadorAuxiliar = juego.obtenerJugadorActual();
        //comunicar el jugador que gano a la vista
    }
}


