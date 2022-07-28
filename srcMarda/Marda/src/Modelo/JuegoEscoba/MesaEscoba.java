package Modelo.JuegoEscoba;
import java.util.ArrayList;
import Modelo.Carta;
import Modelo.Jugador;
import Modelo.Mesa;


public class MesaEscoba extends Mesa {

    public Jugador obtenerJugadorPersona(String nombre){
        if(primerJugador.obtenerNombre() == nombre){
            return primerJugador;
        }else{
            return segundoJugador;
        }
    }

    @Override
    protected ArrayList<Carta> movimientoJugadorCapturarCarta(Carta naipe, String nombreJugador){
        Carta cartaDescartada = null;
        cartaDescartada = movimientoJugadorDescartarCarta(naipe, nombreJugador);
        ArrayList<Carta> naipesCapturados = null;
        /* 
        naipesCapturados = validar.validarCaptura(jugadorActual, cartaDescartada, cartasEnMesa);
        if(naipesCapturados != null){
            ultimoJugadorCaptura = jugadorActual;
        }
        */
        return naipesCapturados;
    }
    
    @Override
    protected Boolean validarRepartirCartas() {
        if(primerJugador.obtenerNumeroCartasEnJuego()==0 && segundoJugador.obtenerNumeroCartasEnJuego()==0){
            return true;
        }
        else 
        {
            return false;
        }
    }

    @Override
    protected Boolean validarTerminarPartida() {
        //if(mazo.obtenerCantidadDeNaipes() == 0 && primerJugador.obtenerNumeroCartasEnJuego() == 0 && segundoJugador.obtenerNumeroCartasEnJuego() == 0){
            //ultimoJugadorCaptura.capturarCartas(cartasEnMesa);
            cartasEnMesa.removeAll(cartasEnMesa);
            return true;
        //}
        //return false;
    }

    @Override
    protected Jugador declararGanador() {
        int puntajePrimerJugador = primerJugador.obtenerPuntaje();
        int puntajeSegundoJugador = segundoJugador.obtenerPuntaje();
        if(puntajePrimerJugador>puntajeSegundoJugador){
            return primerJugador;
        }else{
            if(puntajeSegundoJugador>puntajePrimerJugador){
                return segundoJugador;
            }else{
                return null;
            }
        }
    }

    @Override
    protected Carta movimientoJugadorDescartarCarta(Carta naipe, String nombreJugador) {
        ArrayList<Carta> naipes = new ArrayList<Carta>();
        Carta cartaDescartada = naipe;
        if(jugadorActual.obtenerNombre() == obtenerJugadorPersona(nombreJugador).obtenerNombre()){
            naipes.add(naipe);
            cartaDescartada = jugadorActual.descartarCarta(naipes);
        }
        //*jugadorActual.removerCarta(cartaDescartada);
        return cartaDescartada;
    }
}
