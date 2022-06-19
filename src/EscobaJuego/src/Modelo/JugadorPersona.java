package Modelo;
import java.util.ArrayList;

public class JugadorPersona extends Jugador{

    public JugadorPersona(String nombre){
        super(nombre);
    }

    @Override
    public Naipe descartarCarta(ArrayList<Naipe> naipes){
        Naipe resultado = null;
        ArrayList<Naipe> cartasCapturadas = this.obtenerCartas();
        String palo;
        int valor; 
        for(Naipe naipe: cartasCapturadas){
            palo = naipes.get(0).obtenerPalo();
            valor = naipes.get(0).obtenerValor();
            if(naipe.obtenerPalo().equals(palo) && naipe.obtenerValor() == valor){
                resultado = naipe;
                cartasCapturadas.remove(0);
                break;
            }
        }
        return resultado;
    }
}
