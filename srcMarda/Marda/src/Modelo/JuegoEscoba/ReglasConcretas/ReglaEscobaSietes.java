package Modelo.JuegoEscoba.ReglasConcretas;

import Modelo.Regla;
import Modelo.Carta;
import java.util.ArrayList;

public class ReglaEscobaSietes extends Regla{
    public ReglaEscobaSietes(String nombre, int puntaje){
        super(nombre, puntaje);
    }

    @Override
    public int validarRegla(ArrayList<Carta> cartas)
    {
        int puntaje = 0;
        for (Carta carta : cartas) {
            if(carta.obtenerValor() == 7)
            {
                puntaje += this.obtenerPuntaje();
            }
        }
        return puntaje;
    }
}