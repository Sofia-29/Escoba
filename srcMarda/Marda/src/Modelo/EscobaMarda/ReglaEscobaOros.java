package Modelo.EscobaMarda;

import Modelo.Regla;
import Modelo.Carta;
import java.util.ArrayList;

public class ReglaEscobaOros extends Regla{
    public ReglaEscobaOros(String nombre, int puntaje){
        super(nombre, puntaje);
    }

    @Override
    public int validarRegla(ArrayList<Carta> cartas)
    {
        int puntaje = 0;
        for (Carta carta : cartas) {
            if(carta.obtenerPalo().equals("Oros"))
            {
                puntaje += 1;
            }
        }
        return puntaje;
    }
}