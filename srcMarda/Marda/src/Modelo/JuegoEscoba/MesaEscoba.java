package Modelo.JuegoEscoba;
import java.util.ArrayList;
import Modelo.Carta;
import Modelo.Jugador;
import Modelo.Mazo;
import Modelo.Mesa;
import Modelo.Validador;


public class MesaEscoba extends Mesa {

    private Jugador ultimoJugadorCaptura;

    public MesaEscoba(Jugador primerJugador, Jugador segundoJugador, Mazo mazo, Jugador jugadorActual,ArrayList<Carta> cartasEnMesa){
        super(primerJugador,segundoJugador,mazo,jugadorActual,cartasEnMesa);
        this.ultimoJugadorCaptura = this.obtenerJugadorActual();
    }

    public MesaEscoba(){
        super();
        Validador validar;
    }

    public Jugador obtenerJugadorPersona(String nombre){
        if(primerJugador.obtenerNombre() == nombre){
            return primerJugador;
        }else{
            return segundoJugador;
        }
    }

    @Override
    public ArrayList<Carta> movimientoJugadorCapturarCarta(Carta naipe, String nombreJugador){
        Carta cartaDescartada = null;
        cartaDescartada = movimientoJugadorDescartarCarta(naipe, nombreJugador);
        ArrayList<Carta> naipesCapturados = null;
        naipesCapturados = validar.validarCaptura(jugadorActual, cartaDescartada, cartasEnMesa);
        if(naipesCapturados != null){
            ultimoJugadorCaptura = jugadorActual;
        }
        return naipesCapturados;
    }
    
    @Override
    public Boolean validarRepartirCartas() {
        if(primerJugador.obtenerNumeroCartasEnJuego()==0 && segundoJugador.obtenerNumeroCartasEnJuego()==0){
            return true;
        }
        else 
        {
            return false;
        }
    }

    @Override
    public Boolean validarTerminarPartida() {
        if(mazo.obtenerCantidadCartas() == 0 && primerJugador.obtenerNumeroCartasEnJuego() == 0 && segundoJugador.obtenerNumeroCartasEnJuego() == 0){
            ultimoJugadorCaptura.capturarCartas(cartasEnMesa);
            cartasEnMesa.removeAll(cartasEnMesa);
            return true;
        }
        return false;
    }

    @Override
    public Jugador declararGanador() {
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
    public Carta movimientoJugadorDescartarCarta(Carta naipe, String nombreJugador) {
        ArrayList<Carta> naipes = new ArrayList<Carta>();
        Carta cartaDescartada = naipe;
        if(jugadorActual.obtenerNombre() == obtenerJugadorPersona(nombreJugador).obtenerNombre()){
            naipes.add(naipe);
            cartaDescartada = jugadorActual.descartarCarta(naipes);
        }
        jugadorActual.removerCarta(naipe.obtenerPalo(),naipe.obtenerValor());
        return cartaDescartada;
    }

    public Jugador obtenerUltimoJugadorCaptura(){
        return ultimoJugadorCaptura;
    }


}
