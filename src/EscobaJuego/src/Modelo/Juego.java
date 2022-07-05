
package Modelo;

import java.util.ArrayList;

public class Juego {
    
    private Jugador primerJugador;
    private Jugador segundoJugador;
    private Mazo mazo;
    private Jugador jugadorActual;
    private Jugador ultimoJugadorCaptura;
    private Validador validar;
    private ArrayList<Naipe> cartasEnMesa;

    public Juego(){
        validar = new Validador();
    }

    public Juego(Jugador primerJugador, Jugador segundoJugador, Mazo mazo, Jugador jugadorActual, ArrayList<Naipe> cartasEnMesa){
        this.primerJugador = primerJugador;
        this.segundoJugador = segundoJugador;
        this.mazo = mazo;
        this.jugadorActual = jugadorActual;
        this.cartasEnMesa = cartasEnMesa;
        validar = new Validador();
    }

    public void asignarPrimerJugador(Jugador jugadorUno){
        this.primerJugador=jugadorUno;
    }

    public Jugador obtenerPrimerJugador(){
        return primerJugador;
    }

    public Mazo obtenerMazo(){
        return mazo;
    }

    public Jugador obtenerJugadorActual(){
        return jugadorActual;
    }

    public Jugador obtenerUltimoJugadorCaptura(){
        return ultimoJugadorCaptura;
    }

    public Jugador obtenerSegundoJugador(){
        return segundoJugador;
    }

    public Jugador obtenerJugadorPersona(String nombre){
        if(primerJugador.obtenerNombre() == nombre){
            return primerJugador;
        }else{
            return segundoJugador;
        }
    }

    public void iniciarPartida(String jugadorNombre, int jugadorOpcion){
        if(jugadorOpcion == 0){
            primerJugador = new JugadorPersona(jugadorNombre);
            segundoJugador = new JugadorMaquina("Jugador Maquina");
            asignarPrimerJugador(primerJugador);
        }else if(jugadorOpcion == 1){
            primerJugador =  new JugadorMaquina("Jugador Maquina");
            segundoJugador = new JugadorPersona(jugadorNombre);
            asignarPrimerJugador(primerJugador);
        }
        jugadorActual = primerJugador;
        mazo = new Mazo();
        cartasEnMesa = mazo.repartirMazo(4);
        primerJugador.asignarCartas(mazo.repartirMazo(3));
        segundoJugador.asignarCartas(mazo.repartirMazo(3));
    }

    public ArrayList<Naipe> movimientoJugadorCapturarCarta(Naipe naipe, String nombreJugador){
        Naipe cartaDescartada = null;
        cartaDescartada = movimientoJugadorDescartarCarta(naipe, nombreJugador);
        ArrayList<Naipe> naipesCapturados = null;
        naipesCapturados = validar.validarCaptura(jugadorActual, cartaDescartada, cartasEnMesa);
        if(naipesCapturados != null){
            ultimoJugadorCaptura = jugadorActual;
        }
        return naipesCapturados;
    }

    public Naipe movimientoJugadorDescartarCarta(Naipe naipe, String nombreJugador){
        ArrayList<Naipe> naipes = new ArrayList<Naipe>();
        Naipe cartaDescartada = naipe;
        if(jugadorActual.obtenerNombre() == obtenerJugadorPersona(nombreJugador).obtenerNombre()){
            naipes.add(naipe);
            cartaDescartada = jugadorActual.descartarCarta(naipes);
        }
        jugadorActual.removerCarta(cartaDescartada);
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
        if(nombreJugador == obtenerJugadorPersona(nombreJugador).obtenerNombre()){
            return obtenerJugadorPersona(nombreJugador).obtenerCartas();
        }else{
            return jugadorActual.obtenerCartas();
        }
    }

    //RETROALIMENTACIÓN VISTA-CONTROLADOR-JUEGO
    public ArrayList<Naipe> retornarCartasEnMesa(){
        return cartasEnMesa;
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
        if(mazo.obtenerCantidadDeNaipes() == 0 && primerJugador.obtenerNumeroCartasEnJuego() == 0 && segundoJugador.obtenerNumeroCartasEnJuego() == 0){
            ultimoJugadorCaptura.capturarCartas(cartasEnMesa);
            cartasEnMesa.removeAll(cartasEnMesa);
            return true;
        }
        return false;
    }

    public Jugador terminarPartida(){
        validar.contabilizarPuntos(primerJugador, segundoJugador);
        jugadorActual = declararGanador();
        return jugadorActual;
    }

    private Jugador declararGanador(){
        int puntajePrimerJugador = primerJugador.obtenerPuntaje();
        int puntajeSegundoJugador = segundoJugador.obtenerPuntaje();
        if(puntajePrimerJugador>puntajeSegundoJugador){
            return primerJugador;
        }else{
            if(puntajeSegundoJugador>puntajePrimerJugador){
                return segundoJugador;
            }else{
                return null;
            }
        }
    }
}

