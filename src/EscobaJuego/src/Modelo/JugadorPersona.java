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
        for(Naipe naipe: cartasCapturadas){
            if(naipe.obtenerPalo().equals(naipes.get(0).obtenerPalo()) && naipe.obtenerValor() == naipes.get(0).obtenerValor()){
                resultado = naipe;
                cartasCapturadas.remove(0);
                break;
            }
        }
        return resultado;
    }
}
