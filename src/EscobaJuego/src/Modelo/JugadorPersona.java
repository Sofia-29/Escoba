package Modelo;
import java.util.ArrayList;

public class JugadorPersona extends Jugador{

    public JugadorPersona(String nombre){
        super(nombre);
    }

    @Override
    public Naipe descartarCarta(ArrayList<Naipe> naipes){
        Naipe resultado = null;
        ArrayList<Naipe> cartas = this.obtenerCartas();
        String palo;
        int valor; 
        int indice = 0;
        for(Naipe naipe: cartas){
            palo = naipes.get(indice).obtenerPalo();
            valor = naipes.get(indice).obtenerValor();
            if(naipe.obtenerPalo().equals(palo) && naipe.obtenerValor() == valor){
                resultado = naipe;
                break;
            }
        }
        return resultado;
    }
}
