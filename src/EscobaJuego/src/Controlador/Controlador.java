package Controlador;
import Modelo.Naipe;
import Modelo.Validador;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


import Modelo.Juego;
import Modelo.Jugador;
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

        Validador validadora = new Validador();

        jugadorNombre = vista.preguntarNombreJugadorPersona();
        jugadorOpcion = vista.preguntarTurno();

        juego.iniciarPartida(jugadorNombre, jugadorOpcion);
        jugadorAuxiliar = juego.obtenerPrimerJugador();

        cartasJugador = juego.retornarCartasJugador(jugadorNombre);
        jugadorAuxiliar = juego.obtenerJugadorActual();
        vista.iniciarPartida(cartasJugador, juego.retornarCartasEnMesa());
        vista.actualizarTurnoJugador(jugadorAuxiliar.obtenerNombre());
        while(!juego.validarTerminarPartida()){
            if(juego.repartirCartas()){
                juego.repartirCartasJugadores();
                cartasJugador = juego.obtenerJugadorPersona(jugadorNombre).obtenerCartas();
                vista.actualizarCartasJugador(cartasJugador);
            }
            if(jugadorAuxiliar.obtenerNombre() == jugadorNombre){
                while(true){
                    naipeAuxiliar = vista.retornarNaipeSeleccionada();
                    if(!naipeAuxiliar.obtenerPalo().equals("-1")){
                        break;
                    }
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("1. Estoy aca y no me salgo: " + jugadorAuxiliar.obtenerNombre());
                }
                System.out.println("2. Estoy aca y soy: " + jugadorAuxiliar.obtenerNombre());
            }else{
                naipeAuxiliar = jugadorAuxiliar.descartarCarta(juego.retornarCartasEnMesa());
                TimeUnit.SECONDS.sleep(3);
            }
                //????? To Do montoncito para obtener las caartas en una esquina del panel
            ArrayList<Naipe> cartasCapturadas = juego.movimientoJugadorCapturarCarta(naipeAuxiliar, jugadorAuxiliar.obtenerNombre());
            if(cartasCapturadas != null){
                vista.actualizarCartasCaptura(cartasCapturadas, validadora.esEscoba(juego.retornarCartasEnMesa()));
                TimeUnit.SECONDS.sleep(4);
                vista.limpiarComponeneteCartasCapturadas();
            }
            vista.actualizarCartasEnMesa(juego.retornarCartasEnMesa());
            if(naipeAuxiliar != null){
                jugadorAuxiliar = juego.pasarTurno();
                vista.actualizarTurnoJugador(jugadorAuxiliar.obtenerNombre());
                naipeAuxiliar = null;
            }
        }
        juego.terminarPartida();
        jugadorAuxiliar = juego.obtenerJugadorActual();
    }
}


