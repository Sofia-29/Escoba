package Modelo.JuegoEscoba;

import java.util.ArrayList;

import Modelo.Regla;
import Modelo.Carta;
import Modelo.Jugador;
import Modelo.Validador;

public class ValidadorEscoba extends Validador{
    public ValidadorEscoba(){
        super();
        inicializarReglas();
    }

    @Override
    public void inicializarReglas()
    {
        ArrayList<Regla> reglas = this.obtenerReglas();
        reglas.add(new ReglaEscoba("Escoba", 1));
        reglas.add(new ReglaEscobaCantidadOros("Cantidad Oros", 1));
        reglas.add(new ReglaEscobaCantidadSietes("Cantidad Sietes", 1));
        reglas.add(new ReglaEscobaSietes("Sietes", 1));
        reglas.add(new ReglaEscobaOros("Oros", 1));
        reglas.add(new ReglaEscobaSieteOros("Siete Oros", 1));
    }

    @Override
    public void validarReglas(Jugador primerJugador, Jugador segundoJugador){
        String stringABuscar = "Cantidad";
        for (Regla regla : this.obtenerReglas()) {
            if(regla.obtenerNombre().equals("Escoba")){}
            else{
                if(regla.obtenerNombre().contains(stringABuscar)){
                    int puntuacionJugadorUno = validarReglaJugador(primerJugador, regla);
                    int puntuacionJugadorDos = validarReglaJugador(segundoJugador, regla);
                    Jugador ganador = comparacionMayor(primerJugador, segundoJugador, puntuacionJugadorUno, puntuacionJugadorDos);
                    if(ganador != null)
                    {
                        ganador.asignarPuntaje(1);
                    }
                }else{
                    primerJugador.asignarPuntaje(validarReglaJugador(primerJugador, regla));
                    segundoJugador.asignarPuntaje(validarReglaJugador(segundoJugador, regla));
                }
            }
        }
    }

    @Override
    public Jugador validarJugada(ArrayList<Carta> cartas, Jugador jugador){
        Regla escoba = this.obtenerReglas().get(0);
        jugador.asignarPuntaje(escoba.validarRegla(cartas));
        return jugador;
    }

    public int validarReglaJugador(Jugador jugador, Regla regla)
    {
        return regla.validarRegla(jugador.obtenerCartasCapturadas());
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

    // public static void main(String[] args) {
    //     ValidadorEscoba validadorEscoba = new ValidadorEscoba();
    //     Jugador primerJugador = new JugadorPersona();
    //     Jugador segundoJugador = new JugadorMaquina();
    //     ArrayList<Carta> cartas = new ArrayList<Carta>();
    //     ArrayList<Carta> cartas2 = new ArrayList<Carta>();
    //     ArrayList<Carta> cartasEnMesa = new ArrayList<Carta>();
    //     Carta carta = new Carta(7, "Oros", "");
    //     Carta carta2 = new Carta(7, "Copas", "");
    //     Carta carta3 = new Carta(7, "Espadas", "");
    //     Carta carta4 = new Carta(7, "Bastos", "");
    //     for (int i = 0; i < 10; i++) {
    //         if (i != 6){
    //             cartas.add(new Carta(i+1, "Oros", ""));
    //         }
    //     }
    //     cartas2.add(carta);
    //     cartas2.add(carta2);
    //     cartas2.add(carta3);
    //     cartas2.add(carta4);
    //     primerJugador.asignarCartasCapturadas(cartas);
    //     segundoJugador.asignarCartasCapturadas(cartas2);
    //     validadorEscoba.validarJugada(cartasEnMesa, primerJugador);
    //     validadorEscoba.validarReglas(primerJugador, segundoJugador);
    // }
}
