package Modelo;
import java.util.ArrayList;

public class JugadorMaquina extends Jugador{

    JugadorMaquina(String nombre){
        super(nombre);
    }

    @Override
    public Naipe descartarCarta(ArrayList<Naipe> cartasEnMesa){
        return new Naipe();
    }
}
