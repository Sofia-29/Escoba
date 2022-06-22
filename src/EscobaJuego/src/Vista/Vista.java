package Vista;

import java.util.ArrayList;

import Modelo.Naipe;

public class Vista {

    private Ventana ventana;

    public Vista(){
        ventana = new Ventana(1000, 1000, "Juego Escoba");
    }

    public static void main(String[] args){
        Ventana ventana = new Ventana(1000,1000, "Juego Escoba");

        Naipe naipe1 = new Naipe(1, "Oros");
        Naipe naipe2 = new Naipe(8, "Copas");
        Naipe naipe3 = new Naipe(3, "Espadas");
        ArrayList<Naipe> cartas = new ArrayList<Naipe>();

        cartas.add(naipe1);
        cartas.add(naipe2);
        cartas.add(naipe3);
        ventana.actualizarComponentesCartasJugador(cartas, -1);
        ventana.hacerVisible();

    }

    public void iniciarPartida(ArrayList<Naipe> cartasJugador){
        ventana.actualizarComponentesCartasJugador(cartasJugador, -1);
        ventana.hacerVisible();
    }

    public Naipe retornarNaipeSeleccionada(){
        String[] valorNaipe = ventana.obtenerNaipe();
        Naipe naipeAuxiliar = new Naipe(Integer.parseInt(valorNaipe[0]), valorNaipe[1]);
        return naipeAuxiliar;
    }
}
