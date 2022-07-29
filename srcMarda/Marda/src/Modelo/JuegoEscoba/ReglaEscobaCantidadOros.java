package Modelo.JuegoEscoba;

import Modelo.Regla;
import Modelo.Carta;
import java.util.ArrayList;

public class ReglaEscobaCantidadOros extends Regla{
    public ReglaEscobaCantidadOros(String nombre, int puntaje){
        super(nombre, puntaje);
    }

    @Override
    public int validarRegla(ArrayList<Carta> cartas)
    {
        int puntaje = 0;
        for (Carta carta : cartas) {
            if(carta.obtenerPalo().equals("Oros"))
            {
                puntaje += this.obtenerPuntaje();
            }
        }
        return puntaje;
    }
}