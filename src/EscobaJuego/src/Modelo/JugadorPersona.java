package Modelo;
import java.util.ArrayList;

public class JugadorPersona extends Jugador{

    JugadorPersona(String nombre){
        super(nombre);
    }

    public Naipe descartarCarta(int valor, String palo){
        return new Naipe();
    }

}
