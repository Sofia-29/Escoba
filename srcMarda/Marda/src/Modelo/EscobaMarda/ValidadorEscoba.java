package Modelo.EscobaMarda;

import java.util.ArrayList;

import Modelo.Regla;
import Modelo.Carta;
import Modelo.Jugador;
import Modelo.Validador;

public class ValidadorEscoba extends Validador{
    private ArrayList<Regla> Reglas;

    public ValidadorEscoba(){
    }

    @Override
    public void validarReglas(Jugador primerJugador, Jugador segundoJugador){
        String stringABuscar = "Cantidad";
        for (Regla regla : this.Reglas) {
            if(regla.obtenerNombre().contains(stringABuscar)){
                int puntuacionJugadorUno = validarReglaJugador(primerJugador, regla);
                int puntuacionJugadorDos = validarReglaJugador(segundoJugador, regla);
                comparacionMayor(primerJugador, segundoJugador, puntuacionJugadorUno, puntuacionJugadorDos).asignarPuntaje(1);
            }else{
                primerJugador.asignarPuntaje(validarReglaJugador(primerJugador, regla));
                segundoJugador.asignarPuntaje(validarReglaJugador(segundoJugador, regla));
            }
        }
    }

    @Override
    public Jugador validarJugada(ArrayList<Carta> cartas, Jugador jugador){
        return jugador;
    }

    public int validarReglaJugador(Jugador jugador, Regla regla)
    {
        return regla.validarRegla(jugador.obtenerCartas());
    }

    private Jugador comparacionMayor(Jugador primerJugador, Jugador segundoJugador, int puntuacionJugadorUno, int puntuacionJugadorDos)
    {
        if(puntuacionJugadorUno > puntuacionJugadorDos)
        {
            return primerJugador;
        }else{
            if(puntuacionJugadorUno < puntuacionJugadorDos){
                return segundoJugador;
            }
            return null;
        }
    }
}
