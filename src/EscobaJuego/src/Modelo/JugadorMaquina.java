package Modelo;
import java.util.ArrayList;

public class JugadorMaquina extends Jugador{

    JugadorMaquina(String nombre){
        super(nombre);
    }

    public Naipe descartarCarta(ArrayList<Naipe> cartasEnMesa){
        return new Naipe();
    }
}
