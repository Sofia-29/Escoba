package Modelo;
import java.util.ArrayList;

class MesaEscoba extends Mesa {

    public Jugador obtenerJugadorPersona(String nombre){
        if(primerJugador.obtenerNombre() == nombre){
            return primerJugador;
        }else{
            return segundoJugador;
        }
    }

    public ArrayList<Naipe> movimientoJugadorCapturarCarta(Naipe naipe, String nombreJugador){
        Naipe cartaDescartada = null;
        cartaDescartada = movimientoJugadorDescartarCarta(naipe, nombreJugador);
        ArrayList<Naipe> naipesCapturados = null;
        naipesCapturados = validar.validarCaptura(jugadorActual, cartaDescartada, cartasEnMesa);
        if(naipesCapturados != null){
            ultimoJugadorCaptura = jugadorActual;
        }
        return naipesCapturados;
    }

    public Naipe movimientoJugadorDescartarCarta(Naipe naipe, String nombreJugador){
        ArrayList<Naipe> naipes = new ArrayList<Naipe>();
        Naipe cartaDescartada = naipe;
        if(jugadorActual.obtenerNombre() == obtenerJugadorPersona(nombreJugador).obtenerNombre()){
            naipes.add(naipe);
            cartaDescartada = jugadorActual.descartarCarta(naipes);
        }
        jugadorActual.removerCarta(cartaDescartada);
        return cartaDescartada;
    }
}
