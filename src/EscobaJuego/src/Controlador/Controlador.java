package Controlador;

import Modelo.Naipe;

import java.util.ArrayList;

import Modelo.Jugador;
import Modelo.JugadorMaquina;
import Modelo.JugadorPersona;


public class Controlador {
    public static void main(String[] args) throws Exception {

       Naipe naipe1 = new Naipe(1, "Oro");
       Naipe naipe2 = new Naipe(2, "Copas");
       Naipe naipe3 = new Naipe(4, "Espadas");

       Naipe naipe11 = new Naipe(1, "Oro");
       Naipe naipe22 = new Naipe(4, "Copas");
       Naipe naipe33 = new Naipe(3, "Espadas");
       Naipe naipe44 = new Naipe(7, "Espadas");

       JugadorMaquina jugador = new JugadorMaquina("Carmelo");
       ArrayList<Naipe> cartas = new ArrayList<Naipe>();
       ArrayList<Naipe> naipe = new ArrayList<Naipe>();

       cartas.add(naipe1);
       cartas.add(naipe2);
       cartas.add(naipe3);
       naipe.add(naipe11);
       naipe.add(naipe22);
       naipe.add(naipe33);
       naipe.add(naipe44);

       jugador.asignarCartas(cartas);
       Naipe auxiliar = jugador.descartarCarta(naipe);
        auxiliar = jugador.obtenerCarta(4, "Bastos");
    }
}
