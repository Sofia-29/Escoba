package Modelo;
import java.util.ArrayList;

public abstract class Mesa {
    
    protected Jugador primerJugador;
    protected Jugador segundoJugador;
    protected Jugador jugadorActual;
    protected ArrayList<Carta> cartasEnMesa;
    protected Validador validar;
    protected Mazo mazo;

    public Mesa(){

    }

    public Mesa(Validador validador){
        validar = validador;
    }

    public Mesa(Jugador primerJugador, Jugador segundoJugador, Mazo mazo, Jugador jugadorActual,ArrayList<Carta> cartasEnMesa, Validador validador){
        this.primerJugador = primerJugador;
        this.segundoJugador = segundoJugador;
        this.mazo = mazo;
        this.jugadorActual = jugadorActual;
        this.cartasEnMesa = cartasEnMesa;
        this.validar = validador;
    }

    public void asignarValidador(Validador validador){
        this.validar = validador;
    }

    public void asignarMazo(Mazo mazo){
        this.mazo = mazo;
    }

    public void asignarPrimerJugador(Jugador primerJugador){
        this.primerJugador = primerJugador;
    }

    public void asignarCartasEnMesa(ArrayList<Carta> cartasEnMesa){
        this.cartasEnMesa = cartasEnMesa;
    }

    public void asignarJugadorActual(Jugador jugadorActual){
        this.jugadorActual = jugadorActual;
    }

    public void asignarSegundoJugador(Jugador segundoJugador){
       this.segundoJugador = segundoJugador;
    }

    public Validador obtenerValidador(){
        return validar;
    }

    public Jugador obtenerPrimerJugador(){
        return primerJugador;
    }

    public Jugador obtenerSegundoJugador(){
        return segundoJugador;
    }
    
    public Mazo obtenerMazo(){
        return mazo;
    }

    public Jugador obtenerJugadorActual(){
        return jugadorActual;
    }

    public Jugador obtenerJugadorPorNombre(String nombreJugador){
        if(nombreJugador == primerJugador.obtenerNombre()){
            return primerJugador;
        }else{
            return segundoJugador;
        }
    }

    public ArrayList<Carta> obtenerCartasEnMesa(){
        return this.cartasEnMesa;
    }

    public abstract void iniciarPartida();

    public Jugador pasarTurno(){
        if(jugadorActual.obtenerNombre().equals(primerJugador.obtenerNombre())){
            jugadorActual = segundoJugador;
        } else {
            jugadorActual = primerJugador;
        }
        return jugadorActual; 
    }

    public ArrayList<Carta> retornarCartasJugador(String nombreJugador){
        return obtenerJugadorPorNombre(nombreJugador).obtenerCartas();
    }
    
    public ArrayList<Carta> retornarCartasEnMesa(){
        return cartasEnMesa;
    }

    public abstract Boolean validarRepartirCartas();

    public abstract Carta movimientoJugadorDescartarCarta(Carta naipe, String nombreJugador);

    public abstract ArrayList<Carta> movimientoJugadorCapturarCarta(Carta naipe, String nombreJugador);

    public void repartirCartasAJugador(String nombreJugador,int cantCartas){ 
       obtenerJugadorPorNombre(nombreJugador).asignarCartas(mazo.repartirMazo(cantCartas));
    }

    public void repartirCartasAMesa(int cantCartas){
        cartasEnMesa = mazo.repartirMazo(cantCartas);
    }

    public abstract Boolean validarTerminarPartida();

    public Jugador terminarPartida(){ 
        validar.contabilizarPuntos(primerJugador, segundoJugador);
        jugadorActual = declararGanador();
        return jugadorActual;
    }

    public abstract Jugador declararGanador();
}
