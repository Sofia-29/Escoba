package Modelo;
import java.util.ArrayList;

public abstract class Mesa {
    
    protected Jugador primerJugador;
    protected Jugador segundoJugador;
    protected Jugador jugadorActual;
    protected ArrayList<Carta> cartasEnMesa;
    //private Mazo mazo;

    public Mesa(){
       // validar = new Validador();
    }

    public Mesa(Jugador primerJugador, Jugador segundoJugador /*Mazo mazo*/, Jugador jugadorActual,ArrayList<Carta> cartasEnMesa){
        this.primerJugador = primerJugador;
        this.segundoJugador = segundoJugador;
        //this.mazo = mazo;
        this.jugadorActual = jugadorActual;
        this.cartasEnMesa = cartasEnMesa;
        //validar = new Validador();
    }

    public void asignarPrimerJugador(Jugador primerJugador){
        this.primerJugador = primerJugador;
    }

    public void asignarSegundoJugador(Jugador segundoJugador){
       this.segundoJugador = segundoJugador;
    }

    public Jugador obtenerPrimerJugador(){
        return primerJugador;
    }

    public Jugador obtenerSegundoJugador(){
        return segundoJugador;
    }
    /* 
    public Mazo obtenerMazo(){
        return mazo;
    }
    */
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

    public void iniciarPartidao(){
        jugadorActual = primerJugador;
        //mazo = new Mazo();
        repartirCartasAMesa(4);
        repartirCartasAJugador(primerJugador.obtenerNombre(),3);
        repartirCartasAJugador(segundoJugador.obtenerNombre(),3);
    }

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

    public ArrayList<Carta> retornarCartasMesa(){
        return cartasEnMesa;
    }
    
    public ArrayList<Carta> retornarCartasEnMesa(){
        return cartasEnMesa;
    }

    protected abstract Boolean validarRepartirCartas();

    protected abstract Carta movimientoJugadorDescartarCarta(Carta naipe, String nombreJugador);

    protected abstract ArrayList<Carta> movimientoJugadorCapturarCarta(Carta naipe, String nombreJugador);

    public void repartirCartasAJugador(String nombreJugador,int cantCartas){ 
       // obtenerJugadorPorNombre(nombreJugador).asignarCartas(mazo.repartirMazo(cantCartas));
    }

    public void repartirCartasAMesa(int cantCartas){
        //cartasEnMesa = mazo.repartirMazo(cantCartas);
    }

    protected abstract Boolean validarTerminarPartida();

    public Jugador terminarPartida(){ 
        //validar.contabilizarPuntos(primerJugador, segundoJugador);
        jugadorActual = declararGanador();
        return jugadorActual;
    }

    protected abstract Jugador declararGanador();
}
