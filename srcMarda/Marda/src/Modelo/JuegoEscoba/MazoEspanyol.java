package Modelo.JuegoEscoba;
import java.util.ArrayList;
import Modelo.Mazo; 

public class MazoEspanyol extends Mazo{
    @Override
    public ArrayList<String> obtenerPalos() {
        return new ArrayList<String>() {
            {
                add("Copas");
                add("Espadas");
                add("Bastos");
                add("Oros");
            }
        };
    }

    @Override
    public int obtenerCantidadCartas() {
        return 11;
    }

    @Override
    public String obtenerRuta(String palo, int valor) {
        return "img/Espanyol/"+valor+"-"+palo;
    }
}