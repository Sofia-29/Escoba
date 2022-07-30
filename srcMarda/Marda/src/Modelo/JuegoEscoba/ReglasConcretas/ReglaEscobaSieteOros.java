package Modelo.JuegoEscoba.ReglasConcretas;

import Modelo.Regla;
import Modelo.Carta;
import java.util.ArrayList;

public class ReglaEscobaSieteOros extends Regla{
    public ReglaEscobaSieteOros(String nombre, int puntaje){
        super(nombre, puntaje);
    }

    @Override
    public int validarRegla(ArrayList<Carta> cartas)
    {
        int puntaje = 0;
        for (Carta carta : cartas) {
            if(carta.obtenerValor() == 7 && carta.obtenerPalo().equals("Oros"))
            {
                puntaje += this.obtenerPuntaje();
            }
        }
        return puntaje;
    }
}