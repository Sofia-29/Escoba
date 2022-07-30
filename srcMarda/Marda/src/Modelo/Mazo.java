package Modelo;
import java.util.Collections;
import java.util.ArrayList;

public abstract class Mazo {
    private ArrayList<Carta> grupoDeCartas;

    public Mazo() {
        grupoDeCartas = new ArrayList<Carta>();
    }

    // Plantilla
    public void iniciarMazo(){
        ArrayList<String> palos = obtenerPalos();
        int numeroCartas = obtenerCantidadCartas();

        for (String palo : palos) {
            for (int valor = 1; valor < numeroCartas; valor++) {
                grupoDeCartas.add(new Carta(valor, palo, obtenerRuta(palo, valor)));
            }
        }
    }

    public abstract ArrayList<String> obtenerPalos();
    public abstract int obtenerCantidadCartas();
    public abstract String obtenerRuta(String palo, int valor);

    public int obtenerCartasActuales(){
        return this.grupoDeCartas.size();
    }

    public void asignarMazo(ArrayList<Carta> mazo){
        this.grupoDeCartas = mazo;
    }

    public ArrayList<Carta> obtenerMazo(){
        return grupoDeCartas;
    }

    public int obtenerCantidadDecartas(){
        return grupoDeCartas.size();
    }

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

    public void barajar(){
        Collections.shuffle(grupoDeCartas);
    }

    public void imprimirMazo(){
        for (Carta carta : grupoDeCartas) {
            System.out.println("Palo: "+carta.obtenerPalo()+" Valor: "+carta.obtenerValor()+" Ruta: "+carta.obtenerRuta());
        }
    }
}