package Controlador;

import Modelo.Naipe;

import java.util.ArrayList;

import Modelo.Jugador;
import Modelo.JugadorPersona;


public class Controlador {
    public static void main(String[] args) throws Exception {

       Naipe naipe1 = new Naipe(1, "Oro");
       Naipe naipe2 = new Naipe(2, "Copas");
       Naipe naipe3 = new Naipe(3, "Espadas");
       Naipe naipe4 = new Naipe(4, "Bastos");

       JugadorPersona jugador = new JugadorPersona("Carmelo");
       ArrayList<Naipe> cartas = new ArrayList<Naipe>();
       ArrayList<Naipe> naipe = new ArrayList<Naipe>();

       cartas.add(naipe1);
       cartas.add(naipe2);
       cartas.add(naipe3);
       cartas.add(naipe4);
       naipe.add(naipe3);

       jugador.asignarCartas(cartas);
       jugador.asignarPuntaje(2);
       jugador.capturarCartas(cartas);
       jugador.descartarCarta(naipe);
       Naipe auxiliar = jugador.obtenerCarta(4, "Bastos");
    }
}
