package Modelo.EscobaMarda;
import java.util.ArrayList;

import Modelo.Jugador; 
import Modelo.Carta;

public class JugadorPersona extends Jugador{
    private ArrayList<Carta> cartasCapturadas;

    public JugadorPersona(){
        super();
        cartasCapturadas = new ArrayList<Carta>();
    }

    @Override
    public Carta elegirCarta(ArrayList<Carta> carta){
        Carta resultado = this.obtenerCarta(carta.get(0).obtenerPalo(), carta.get(0).obtenerValor());
        return resultado;
    }

    public void capturarCartas(ArrayList<Carta> cartasCapturadas){
        if(cartasCapturadas != null){
            this.cartasCapturadas.addAll(cartasCapturadas);
        }
    }

    public ArrayList<Carta> obtenerCartasCapturadas(){
        return this.cartasCapturadas;
    }

    public int obtenerCantidadDeCartasCapturadas(){
        return this.cartasCapturadas.size();
    }
}
