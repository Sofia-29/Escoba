package Modelo;
import java.util.Collections;
import java.util.ArrayList;

public abstract class Mazo {
    private ArrayList<Carta> grupoDeCartas;

    public Mazo() {
        grupoDeCartas = new ArrayList<Carta>();
    }

    /**
     * Metodo Plantilla que inicializa cualquier tipo de mazo
     */
    public void iniciarMazo(){
        ArrayList<String> palos = obtenerPalos();
        int numeroCartas = obtenerCantidadCartas();

        for (String palo : palos) {
            for (int valor = 1; valor < numeroCartas; valor++) {
                grupoDeCartas.add(new Carta(valor, palo, obtenerRuta(palo, valor)));
            }
        }
    }

    /**
     * @return retorna una lista de strings que contiene los palos del mazo
     */
    public abstract ArrayList<String> obtenerPalos();

    /**
     * @return retorna la cantidad de cartas del mazo
     */
    public abstract int obtenerCantidadCartas();

    /**
     * @return retorna la ruta de la carta
     */
    public abstract String obtenerRuta(String palo, int valor);

    /**
     * @return retorna las cartas actuales
     */
    public int obtenerCartasActuales(){
        return this.grupoDeCartas.size();
    }

     /**
     * @return retorna asigna el mazo
     */
    public void asignarMazo(ArrayList<Carta> mazo){
        this.grupoDeCartas = mazo;
    }

     /**
     * @return retorna las cartas actuales
     */
    public ArrayList<Carta> obtenerGrupoDeCartas(){
        return grupoDeCartas;
    }

     /**
     * @return retorna la cantidad de cartas
     */
    public int obtenerCantidadDecartas(){
        return grupoDeCartas.size();
    }

    /**
     * @return reparte cartas del mazo
     */
    public ArrayList<Carta> repartirMazo(int cantidadDecartas){
        int cartasElegidas = 0;
        ArrayList<Carta> cartas =  new ArrayList<Carta>();
        barajar();
        for (Carta carta : grupoDeCartas) {
            cartas.add(carta);
            cartasElegidas++;
            if(cartasElegidas == cantidadDecartas){
                break;
            }
        }

        for (Carta carta : cartas) {
            grupoDeCartas.remove(carta);
        }
        return cartas;
    }

    /**
     *  baraja cartas del mazo
     */
    public void barajar(){
        Collections.shuffle(grupoDeCartas);
    }

    /**
     *  Muestra el mazo
     */
    public void imprimirMazo(){
        for (Carta carta : grupoDeCartas) {
            System.out.println("Palo: "+carta.obtenerPalo()+" Valor: "+carta.obtenerValor()+" Ruta: "+carta.obtenerRuta());
        }
    }
}