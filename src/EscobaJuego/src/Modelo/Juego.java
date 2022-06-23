
package Modelo;

import java.util.ArrayList;

public class Juego {
    
    private Jugador primerJugador;
    private Jugador segundoJugador;
    private Mazo mazo;
    private Jugador jugadorActual;
    private Validador validar;
    private ArrayList<Naipe> cartasEnMesa;
    public Jugador obtenerPrimerJugador;

    public Juego(){}

    public void asignarPrimerJugador(Jugador jugadorUno){
        this.primerJugador=jugadorUno;
    }

    public Jugador obtenerPrimerJugador(){
        return primerJugador;
    }

    private Jugador obtenerSegundoJugador(){
        return segundoJugador;
    }


    public void iniciarPartida(String jugadorNombre, String jugadorOpcion){
        if(jugadorOpcion.equals("Primero")){
            primerJugador = new JugadorPersona(jugadorNombre);
            segundoJugador = new JugadorMaquina("Jugador Maquina");
            asignarPrimerJugador(primerJugador);
        }else if(jugadorOpcion.equals("Segundo")){
            primerJugador =  new JugadorMaquina("Jugador Maquina");
            segundoJugador = new JugadorPersona(jugadorNombre);
            asignarPrimerJugador(primerJugador);
        }
        jugadorActual = primerJugador;
        mazo = new Mazo();
        //mazo.barajar();
        cartasEnMesa = mazo.repartirMazo(4);
        primerJugador.asignarCartas(mazo.repartirMazo(3));
        segundoJugador.asignarCartas(mazo.repartirMazo(3));
    }

    public ArrayList<Naipe> movimientoJugadorCapturarCarta(int valor, String palo, String nombreJugador){
        Naipe cartaDescartada = null;
        cartaDescartada = movimientoJugadorDescartarCarta(valor, palo, nombreJugador);
        ArrayList<Naipe> naipesCapturados = null;
        retornarJugadorActual(nombreJugador);
        naipesCapturados =validar.validarCaptura(jugadorActual, cartaDescartada, cartasEnMesa);
        jugadorActual.capturarCartas(naipesCapturados);
        return naipesCapturados;
    }

    public Naipe movimientoJugadorDescartarCarta(int valor, String palo, String nombreJugador){
        ArrayList<Naipe> naipes = new ArrayList<Naipe>();
        Naipe cartaDescartada = null;
        retornarJugadorActual(nombreJugador);
        naipes.add(jugadorActual.obtenerCarta(valor, palo));
        cartaDescartada = jugadorActual.descartarCarta(naipes);
        return cartaDescartada;
    }

    public Jugador pasarTurno(){
        if(jugadorActual.obtenerNombre().equals(primerJugador.obtenerNombre())){
            jugadorActual = segundoJugador;
        } else {
            jugadorActual = primerJugador;
        }
        return jugadorActual;
    }

    //RETROALIMENTACIÓN VISTA-CONTROLADOR-JUEGO
    public ArrayList<Naipe> retornarCartasJugador(String nombreJugador){
        retornarJugadorActual(nombreJugador);
        return jugadorActual.obtenerCartas();
    }

    //RETROALIMENTACIÓN VISTA-CONTROLADOR-JUEGO
    public ArrayList<Naipe> retornarCartasEnMesa(){
        return cartasEnMesa;
    }

    private void retornarJugadorActual(String nombreJugador){
        if(primerJugador.obtenerNombre().equals(nombreJugador)){
            jugadorActual = primerJugador;
        } else {
            jugadorActual = segundoJugador;
        }
    }

    public Boolean repartirCartas(){
        if(primerJugador.obtenerNumeroCartasEnJuego()==0 && segundoJugador.obtenerNumeroCartasEnJuego()==0){
            return true;
        }
        else 
        {
            return false;
        }
    }

    public void repartirCartasJugadores(){
        primerJugador.asignarCartas(mazo.repartirMazo(3));
        segundoJugador.asignarCartas(mazo.repartirMazo(3));
    }

    public Boolean validarTerminarPartida(){
        if(mazo.obtenerCantidadDeNaipes()==0){
            return true;
        }
        return false;
    }

    public void terminarPartida(){
        if(mazo.obtenerCantidadDeNaipes()==0){
            declararGanador();
        }
    }

    private Jugador declararGanador(){
        int puntajePrimerJugador = primerJugador.obtenerPuntaje();
        int puntajeSegundoJugador = segundoJugador.obtenerPuntaje();
        if(puntajePrimerJugador>=21 && puntajeSegundoJugador>=21){
           if(puntajePrimerJugador>puntajeSegundoJugador){
                return primerJugador;
            }else if(puntajeSegundoJugador>puntajePrimerJugador){
                return segundoJugador;
            }
            //empate??
            /* else {
                partidaEnCurso();
            }
            */ 
       }else if(puntajePrimerJugador>=21 && puntajeSegundoJugador<=21){
            return primerJugador;
       }else{
            return segundoJugador;
       }
        return null;
    }
}

