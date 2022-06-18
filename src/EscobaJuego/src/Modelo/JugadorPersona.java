package Modelo;
import java.util.ArrayList;

public class JugadorPersona extends Jugador{

    JugadorPersona(String nombre){
        super(nombre);
    }

    @Override
    public Naipe descartarCarta(ArrayList<Naipe> naipe){
        return new Naipe();
    }
}
