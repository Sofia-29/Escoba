package Modelo;
import java.util.ArrayList;

public abstract class Mesa {
    
    protected Jugador primerJugador;
    protected Jugador segundoJugador;
    protected Jugador jugadorActual;
    protected ArrayList<Naipe> cartasEnMesa;
    private Mazo mazo;

    public Mesa(){
       // validar = new Validador();
    }

    public Mesa(Jugador primerJugador, Jugador segundoJugador, Mazo mazo, Jugador jugadorActual,ArrayList<Naipe> cartasEnMesa){
        this.primerJugador = primerJugador;
        this.segundoJugador = segundoJugador;
        this.mazo = mazo;
        this.jugadorActual = jugadorActual;
        //this.ultimoJugadorCaptura = jugadorUltimaCaptura;
        this.cartasEnMesa = cartasEnMesa;
        //validar = new Validador();
    }

    public void asignarPrimerJugador(String nombreJugador){
        primerJugador = new Jugador(nombreJugador);
    }

    public void asignarSegundoJugador(String nombreJugador){
        segundoJugador = new Jugador(nombreJugador);
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

    public void iniciarPartidao(){
        jugadorActual = primerJugador;
        //ultimoJugadorCaptura = primerJugador;
        mazo = new Mazo();
        cartasEnMesa = mazo.repartirMazo(4);
        primerJugador.asignarCartas(mazo.repartirMazo(3)); //asignar cartas a jugador, metodo que reciba un entero y reparta las cartas conforme a ese entero
        segundoJugador.asignarCartas(mazo.repartirMazo(3));
    }

    public Jugador pasarTurno(){
        if(jugadorActual.obtenerNombre().equals(primerJugador.obtenerNombre())){
            jugadorActual = segundoJugador;
        } else {
            jugadorActual = primerJugador;
        }
        return jugadorActual; // Hace falta retornarlo?
    }

    public ArrayList<Naipe> retornarCartasJugador(String nombreJugador){
        if(nombreJugador == obtenerJugadorPersona(nombreJugador).obtenerNombre()){ //esto esta mal, deberia retornar cartas de un jugador dependiendo del nombre de ese jugador
            return obtenerJugadorPersona(nombreJugador).obtenerCartas();
        }else{
            return jugadorActual.obtenerCartas();
        }
    }

    public ArrayList<Naipe> retornarCartasEnMesa(){
        return cartasEnMesa;
    }

    public Boolean validarRepartirCartas(){ //esto es asi?
        if(primerJugador.obtenerNumeroCartasEnJuego()==0 && segundoJugador.obtenerNumeroCartasEnJuego()==0){
            return true;
        }
        else 
        {
            return false;
        }
    }

    public void repartirCartasAJugadores(){ //aca deberia de recibir cuantas cartas repartir, no asumir que son 3
        primerJugador.asignarCartas(mazo.repartirMazo(3));
        segundoJugador.asignarCartas(mazo.repartirMazo(3));
    }

    public void repartirCartasAMesa(int cantCartas){
        cartasEnMesa = mazo.repartirMazo(cantCartas);
    }

    public Boolean validarTerminarPartida(){ //esto deberia ser abstracto creo 
        if(mazo.obtenerCantidadDeNaipes() == 0 && primerJugador.obtenerNumeroCartasEnJuego() == 0 && segundoJugador.obtenerNumeroCartasEnJuego() == 0){
            ultimoJugadorCaptura.capturarCartas(cartasEnMesa);
            cartasEnMesa.removeAll(cartasEnMesa);
            return true;
        }
        return false;
    }

    public Jugador terminarPartida(){ //este podria ser un metodo plantilla
        validar.contabilizarPuntos(primerJugador, segundoJugador);
        jugadorActual = declararGanador();
        return jugadorActual;
    }

    private Jugador declararGanador(){ //hacer metodo abstracto 
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
