package Controlador;
import Modelo.Naipe;
import Modelo.Validador;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import Modelo.Juego;
import Modelo.Jugador;
import Vista.Vista;
import Modelo.Serializador;

public class Controlador {
    public static void main(String[] args) throws Exception {
        Juego juego = new Juego();
        Vista vista = new Vista();
        Serializador serializador = new Serializador();
        Jugador jugadorAuxiliar;

        // File partida = vista.elegirArchivo();
        // System.out.println(partida);

        // Serializador serializador = new Serializador();
        // serializador.cargarJuego(partida);


        ArrayList<Naipe> cartasJugador;
        Naipe naipeAuxiliar = null;
        String jugadorNombre;
        String guardarPartida = "-1";
        int cargarPartida;
        int jugadorOpcion;

        Validador validadora = new Validador();

        cargarPartida = vista.preguntarCargarPartida();
        if(cargarPartida == 0){
            File partida = vista.elegirArchivo();
            juego = serializador.cargarJuego(partida);
            jugadorAuxiliar = juego.obtenerJugadorActual();
            jugadorNombre = juego.obtenerJugadorActual().obtenerNombre();

        }else{
                jugadorNombre = vista.preguntarNombreJugadorPersona();
                jugadorOpcion = vista.preguntarTurno();
                juego.iniciarPartida(jugadorNombre, jugadorOpcion);
            }

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
                cartasJugador = jugadorAuxiliar.obtenerCartas();
                if(jugadorAuxiliar.obtenerNombre() == jugadorNombre){
                    while(true){
                        naipeAuxiliar = vista.retornarNaipeSeleccionada();
                        guardarPartida = vista.retornarEstadoGuardarPartida();
                        if(!naipeAuxiliar.obtenerPalo().equals("-1") || !guardarPartida.equals("-1")){
                            break;
                        }
                        TimeUnit.SECONDS.sleep(1);
                    }
                }else{
                    naipeAuxiliar = jugadorAuxiliar.descartarCarta(juego.retornarCartasEnMesa());
                    TimeUnit.SECONDS.sleep(3);
                }
                if(!guardarPartida.equals("-1")){
                    serializador.guardarJuego(juego, guardarPartida);
                    System.exit(0);
                } else{
                    ArrayList<Naipe> cartasCapturadas = juego.movimientoJugadorCapturarCarta(naipeAuxiliar, jugadorAuxiliar.obtenerNombre());
                    if(cartasCapturadas != null){
                        vista.actualizarCartasCaptura(cartasCapturadas, validadora.esEscoba(juego.retornarCartasEnMesa()));
                        vista.actualizarPuntajeJugador(jugadorAuxiliar.obtenerPuntaje());
                        TimeUnit.SECONDS.sleep(4);
                        vista.limpiarComponeneteCartasCapturadas();
                    }
                    vista.actualizarCartasEnMesa(juego.retornarCartasEnMesa());
                    if(naipeAuxiliar != null){
                        jugadorAuxiliar = juego.pasarTurno();
                        vista.actualizarTurnoJugador(jugadorAuxiliar.obtenerNombre());
                        naipeAuxiliar = null;
                    }
                    cartasJugador = jugadorAuxiliar.obtenerCartas();
                }
            }
            vista.finalizarJuego();
            //juego.terminarPartida();
            //jugadorAuxiliar = juego.obtenerJugadorActual();
    }
}