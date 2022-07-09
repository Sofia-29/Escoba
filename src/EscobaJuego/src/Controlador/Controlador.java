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
            vista.actualizarPuntajeJugador(jugadorAuxiliar.obtenerPuntaje());
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
                        boolean capturaEscoba = validadora.esEscoba(juego.retornarCartasEnMesa());
                        vista.actualizarCartasCaptura(cartasCapturadas, capturaEscoba);
                        if(capturaEscoba){
                            TimeUnit.SECONDS.sleep(7);
                        }else{
                            TimeUnit.SECONDS.sleep(4);
                        }
                        vista.limpiarComponeneteCartasCapturadas();
                    }
                    vista.actualizarCartasEnMesa(juego.retornarCartasEnMesa());
                    if(naipeAuxiliar != null){
                        jugadorAuxiliar = juego.pasarTurno();
                        vista.actualizarPuntajeJugador(jugadorAuxiliar.obtenerPuntaje());
                        vista.actualizarTurnoJugador(jugadorAuxiliar.obtenerNombre());
                        naipeAuxiliar = null;
                    }
                    cartasJugador = jugadorAuxiliar.obtenerCartas();
                }
            }
            vista.actualizarCartasEnMesa(juego.retornarCartasEnMesa());
            Jugador ganador = juego.terminarPartida();
            String mensaje="Juego finalizado\n"+juego.obtenerPrimerJugador().obtenerNombre()+ " termina con "
            + juego.obtenerPrimerJugador().obtenerPuntaje() +" puntos\n" + juego.obtenerSegundoJugador().obtenerNombre()+ " termina con "
            + juego.obtenerSegundoJugador().obtenerPuntaje() + "puntos \nEl ganador es: " + ganador.obtenerNombre();
            vista.finalizarJuego(mensaje);
    }
}