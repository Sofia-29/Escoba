package Modelo.JuegoEscoba;

import Modelo.Regla;
import Modelo.Carta;
import java.util.ArrayList;

public class ReglaEscobaCantidadSietes extends Regla{
    public ReglaEscobaCantidadSietes(String nombre, int puntaje){
        super(nombre, puntaje);
    }

    @Override
    public int validarRegla(ArrayList<Carta> cartas)
    {
        int puntaje = 0;
        for (Carta carta : cartas) {
            if(carta.obtenerValor() == 7)
            {
                puntaje += 1;
            }
        }
        return puntaje;
    }
}