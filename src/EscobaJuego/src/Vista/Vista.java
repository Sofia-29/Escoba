package Vista;

import java.util.ArrayList;

import Modelo.Naipe;

public class Vista {
    public static void main(String[] args){
        Ventana ventana = new Ventana(1000,1000, "Juego Escoba");

        Naipe naipe1 = new Naipe(1, "Oros");
        Naipe naipe2 = new Naipe(8, "Copas");
        Naipe naipe3 = new Naipe(3, "Espadas");
        ArrayList<Naipe> cartas = new ArrayList<Naipe>();

        cartas.add(naipe1);
        cartas.add(naipe2);
        cartas.add(naipe3);
        ventana.actualizarComponentesCartasJugador(cartas);
        ventana.hacerVisible();
    }
}
