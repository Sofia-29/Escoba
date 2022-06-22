package Controlador;
import Modelo.Naipe;
import java.util.ArrayList;
import Modelo.Juego;
import Modelo.Jugador;
import Modelo.JugadorMaquina;
import Modelo.JugadorPersona;
import Modelo.Mazo;
import Vista.Ventana;
import Vista.Vista;


public class Controlador {
    public static void main(String[] args) throws Exception {

        Juego juego = new Juego();
        Vista vista = new Vista();
        Jugador jugadorAuxiliar;
        ArrayList<Naipe> cartasJugador;
        String jugadorNombre;
        String jugadorOpcion;

        
        //Vista: se obtiene el nombre del jugador y su turno
        jugadorNombre = vista.preguntarNombreJugadorPersona();
        jugadorOpcion = vista.preguntarTurnoJugador();

        juego.iniciarPartida(jugadorNombre, jugadorOpcion);
        jugadorAuxiliar = juego.obtenerPrimerJugador();
        vista.actualizarTurnoJugador(jugadorAuxiliar.obtenerNombre());

        //ABSTRACTO: comunicación con la vista para darle las cartas que se repartieron
        juego.retornarCartasEnMesa();
        cartasJugador = juego.retornarCartasJugador(jugadorNombre);
        vista.iniciarPartida(cartasJugador);
        juego.retornarCartasJugador("Jugador Maquina");

        // while(juego.validarTerminarPartida()){
        //     if(juego.repartirCartas()){
        //         juego.repartirCartasJugadores();
        //         //ABSTRACTO: comunicación con la vista para darle las cartas que se repartieron
        //         //ArrayList<Naipe>
        //         juego.retornarCartasJugador(jugadorNombre);
        //         juego.retornarCartasJugador("Jugador Maquina");
        //     }else{
        //         //ABSTRACTO: comunicación con la vista para obtener la carta elegida del jugador

        //         //cartaDescartada = juego.movimientoJugadorDescartarCarta(valor, palo, nombreJugador);??
        //         //comunicación con la vista para darle la carta descartada
    
        //         //comunicación con la vista de que se descartó la carta y se agrega a las que existen en la mesa 
        //         //comunicación con la vista para saber que cartas existen en la mesa
        //         //cartasCapturadas = juego.movimientoJugadorCapturarCarta(valor, palo, nombreJugador);
    
        //         //comunicación con la vista para las cartas capturada
        //     }
        // //comunicación con la vista de que la partida se terminó
        // juego.pasarTurno();
        //comunicación con la vista de que es el turno del otro jugador
    }
}

