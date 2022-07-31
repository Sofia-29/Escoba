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

    /**
     * @param validador que es asignado a la Mesa
     */
    public void asignarValidador(Validador validador){
        this.validar = validador;
    }

    /**
     * @param mazo que es asignado a la Mesa
     */
    public void asignarMazo(Mazo mazo){
        this.mazo = mazo;
    }

    /**
     * @param primerJugador que es asignado a la Mesa
     */
    public void asignarPrimerJugador(Jugador primerJugador){
        this.primerJugador = primerJugador;
    }

    /**
     * @param cartasEnMesa que son asignadas a la Mesa
     */
    public void asignarCartasEnMesa(ArrayList<Carta> cartasEnMesa){
        this.cartasEnMesa = cartasEnMesa;
    }

    /**
     * @param jugadorActual que es asignado en la mesa
     */
    public void asignarJugadorActual(Jugador jugadorActual){
        this.jugadorActual = jugadorActual;
    }

    /**
     * @param segundoJugador que es asignado en la mesa
     */
    public void asignarSegundoJugador(Jugador segundoJugador){
       this.segundoJugador = segundoJugador;
    }

    /**
     * @return el validador de la mesa
     */
    public Validador obtenerValidador(){
        return validar;
    }

    /**
     * @return el primer jugador de la mesa
     */
    public Jugador obtenerPrimerJugador(){
        return primerJugador;
    }

    /**
     * @return el segundo jugador de la mesa
     */
    public Jugador obtenerSegundoJugador(){
        return segundoJugador;
    }
    
    /**
     * @return el mazo de la mesa
     */
    public Mazo obtenerMazo(){
        return mazo;
    }

    /**
     * @return obtiene el jugador actual
     */
    public Jugador obtenerJugadorActual(){
        return jugadorActual;
    }

    /**
     * @param nombreJugador
     * @return el primer jugador o el segundo jugador dependiendo del nombre que se le pasa por parametro
     */
    public Jugador obtenerJugadorPorNombre(String nombreJugador){
        if(nombreJugador == primerJugador.obtenerNombre()){
            return primerJugador;
        }else{
            return segundoJugador;
        }
    }

    /**
     * @return las cartas que tiene la mesa
     */
    public ArrayList<Carta> obtenerCartasEnMesa(){
        return this.cartasEnMesa;
    }

    /**
     * método abstracto para iniciar la partida del juego
     */
    public abstract void iniciarPartida();

    /**
     * @return el jugador actual luego de que turno cambie
     */
    public Jugador pasarTurno(){
        if(jugadorActual.obtenerNombre().equals(primerJugador.obtenerNombre())){
            jugadorActual = segundoJugador;
        } else {
            jugadorActual = primerJugador;
        }
        return jugadorActual; 
    }

    /**
     * @param nombreJugador
     * @return las cartas del jugador segun su nombre 
     */
    public ArrayList<Carta> retornarCartasJugador(String nombreJugador){
        return obtenerJugadorPorNombre(nombreJugador).obtenerCartas();
    }
    
    /**
     * @return las cartas que posee la mesa
     */
    public ArrayList<Carta> retornarCartasEnMesa(){
        return cartasEnMesa;
    }

    /**
     * @return se valida si se necesitan repartir cartas a los jugadores 
     */
    public abstract Boolean validarRepartirCartas();

    /**
     * @param naipe
     * @param nombreJugador
     * @return la carta que el jugador descartó
     */
    public abstract Carta movimientoJugadorDescartarCarta(Carta naipe, String nombreJugador);

    /**
     * @param naipe
     * @param nombreJugador
     * @return un vector de los naipes que han sido capturados
     */
    public abstract ArrayList<Carta> movimientoJugadorCapturarCarta(Carta naipe, String nombreJugador);

    /**
     * @param nombreJugador 
     * @param cantCartas que se le reparten las cartas al jugador según su nombre 
     */
    public void repartirCartasAJugador(String nombreJugador,int cantCartas){ 
       obtenerJugadorPorNombre(nombreJugador).asignarCartas(mazo.repartirMazo(cantCartas));
    }

    /**
     * @param cantCartas que se le reparten a la mesa
     */
    public void repartirCartasAMesa(int cantCartas){
        cartasEnMesa = mazo.repartirMazo(cantCartas);
    }

    /**
     * @return se valida si la partida termina
     */
    public abstract Boolean validarTerminarPartida();

    /**
     * Metodo plantilla
     *  @return el jugador que gana la partida
     */
    public Jugador terminarPartida(){ 
        validar.contabilizarPuntos(primerJugador, segundoJugador);
        jugadorActual = declararGanador();
        return jugadorActual;
    }

    /**
     * @return el jugador que fue declarado como ganador
     */
    public abstract Jugador declararGanador();
}
