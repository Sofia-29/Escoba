package Modelo;
import java.util.ArrayList;

public abstract class Jugador {
    private ArrayList<Carta> cartas;
    private ArrayList<Carta> cartasCapturadas;
    private int puntaje;
    private String nombre;

    public Jugador(){
        this.puntaje = 0;
        cartasCapturadas = new ArrayList<Carta>();
    }

    /**
     * @return Nombre del jugador.
     */
    public String obtenerNombre(){
        return nombre;
    }

    /**
     * @return Puntaje del jugador.
     */
    public int obtenerPuntaje(){
        return this.puntaje;
    }

    /**
     * @return Cartas que el jugador esta jugando. 
     */
    public ArrayList<Carta> obtenerCartas(){
        return this.cartas;
    }

    
    /**
     * @param cartas asigna las cartas en juego al jugador.
     */
    public void asignarCartas(ArrayList<Carta> cartas){
        this.cartas = cartas;
    }

    /**
     * @param puntaje asigna el puntaje al jugador
     */
    public void asignarPuntaje(int puntaje){
        this.puntaje += puntaje;
    }

    /**
     * @param nombre asigna el nombre al jugador
     */
    public void asignarNombre(String nombre){
        this.nombre = nombre;
    }

    /**
     * @param puntaje aumenta el puntaje del jugador 
     */
    public void aumentarPuntaje(int puntaje){
        this.puntaje += puntaje;
    }

    /**
     * @return la cantidad de cartas en juego
     */
    public int obtenerCantidadCartasEnJuego(){
        return this.cartas.size();
    }

    /**
     * @param cartasCapturadas agrega las cartas a las cartas capturadas del jugador.
     */
    public void capturarCartas(ArrayList<Carta> cartasCapturadas){
        if(cartasCapturadas != null){
            this.cartasCapturadas.addAll(cartasCapturadas);
        }
    }

    /**
     * @return las cartas capturadas.
     */
    public ArrayList<Carta> obtenerCartasCapturadas(){
        return this.cartasCapturadas;
    }

    /**
     * @return la cantidad de cartas capturadas
     */
    public int obtenerCantidadDeCartasCapturadas(){
        return this.cartasCapturadas.size();
    }

    /**
     * @param palo el palo de la carta a retornar
     * @param valor el valor de la carta a retornar
     * @return la carta con el palo y valor especificado
     */
    public Carta obtenerCarta(String palo, int valor){
        Carta resultado = null;
        for(Carta carta:this.cartas){
            if(carta.obtenerPalo().equals(palo) && carta.obtenerValor() == valor){
                resultado = carta;
                break;
            }
        }
        return resultado; 
    }

    /**
     * @param palo el palo de la carta a eliminar
     * @param valor el valor de la carta a eliminar
     */
    public void removerCarta(String palo, int valor){
        Carta resultado = obtenerCarta(palo, valor);
        this.cartas.remove(resultado);
    }

    /**
     * @param carta cartas o carta seleccionada por el jugador
     * @return carta seleccionada
     */
    protected abstract Carta elegirCarta(ArrayList<Carta> carta);

    /**
     * Metodo plantilla
     * @param carta carta descartada
     * @return retorna la carta que descarto
     */
    public Carta descartarCarta(ArrayList<Carta> carta){
        Carta resultado = this.elegirCarta(carta);
        return resultado;
    }

    /**
     * @return cantidad de cartas en juego
     */
    public int obtenerNumeroCartasEnJuego(){
        return cartas.size();
    }
}
