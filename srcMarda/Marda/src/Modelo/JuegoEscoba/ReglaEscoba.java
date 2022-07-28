package Modelo.JuegoEscoba;

import Modelo.Regla;
import Modelo.Carta;
import java.util.ArrayList;

public class ReglaEscoba extends Regla{
    public ReglaEscoba(String nombre, int puntaje){
        super(nombre, puntaje);
    }

    @Override
    public int validarRegla(ArrayList<Carta> cartas)
    {
        int puntaje = 0;
        if(cartas.isEmpty()){
            puntaje += 1;
        }
        return puntaje;
    }
}