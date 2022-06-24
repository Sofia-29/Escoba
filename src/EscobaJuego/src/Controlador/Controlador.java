package Controlador;
import Modelo.Naipe;
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
            TimeUnit.SECONDS.sleep(2);
            if(jugadorAuxiliar.obtenerNombre() == jugadorNombre){
                naipeAuxiliar = vista.retornarNaipeSeleccionada();
                while(naipeAuxiliar == null){
                    naipeAuxiliar = vista.retornarNaipeSeleccionada();
                }
                TimeUnit.SECONDS.sleep(3);
            }else{
                naipeAuxiliar = jugadorAuxiliar.descartarCarta(juego.retornarCartasEnMesa());
                //TimeUnit.SECONDS.sleep(3);
            }
                //????? To Do montoncito para obtener las caartas en una esquina del panel
            juego.movimientoJugadorCapturarCarta(naipeAuxiliar, jugadorNombre);
            TimeUnit.SECONDS.sleep(3);
            vista.actualizarCartasEnMesa(juego.retornarCartasEnMesa());
            if(naipeAuxiliar != null){
                //TimeUnit.SECONDS.sleep(3);
                jugadorAuxiliar = juego.pasarTurno();
                vista.actualizarTurnoJugador(jugadorAuxiliar.obtenerNombre());
                TimeUnit.SECONDS.sleep(2);
                naipeAuxiliar = null;
            }
        }
        juego.terminarPartida();
        jugadorAuxiliar = juego.obtenerJugadorActual();
    }
}


