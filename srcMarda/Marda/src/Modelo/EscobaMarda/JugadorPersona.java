package Modelo.EscobaMarda;
import java.util.ArrayList;

import Modelo.Jugador; 
import Modelo.Carta;

public class JugadorPersona extends Jugador{

    JugadorPersona(){
        super();
    }

    @Override
    public Carta elegirCarta(ArrayList<Carta> carta){
        Carta resultado = this.obtenerCarta(carta.get(0).obtenerPalo(), carta.get(0).obtenerValor());
        return resultado;
    }
}
