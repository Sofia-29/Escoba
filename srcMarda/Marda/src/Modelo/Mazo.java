package Modelo;
import java.util.Collections;
import java.util.ArrayList;

public abstract class Mazo {
    private ArrayList<Carta> grupoDeCartas;

    public Mazo() {
        grupoDeCartas = new ArrayList<Carta>();
    }

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

    public void asignarMazo(ArrayList<Carta> mazo){
        this.grupoDeCartas = mazo;
    }

    public ArrayList<Carta> obtenerMazo(){
        return grupoDeCartas;
    }

    public int obtenerCantidadDeNaipes(){
        return grupoDeCartas.size();
    }

    public ArrayList<Carta> repartirMazo(int cantidadDeNaipes){
        int cartasElegidas = 0;
        ArrayList<Carta> cartas =  new ArrayList<Carta>();
        barajar();
        for (Carta naipe : grupoDeCartas) {
            cartas.add(naipe);
            cartasElegidas++;
            if(cartasElegidas == cantidadDeNaipes){
                break;
            }
        }

        for (Carta naipe : cartas) {
            grupoDeCartas.remove(naipe);
        }
        return cartas;
    }

    public void barajar(){
        Collections.shuffle(grupoDeCartas);
    }

    public void imprimirMazo(){
        for (Carta naipe : grupoDeCartas) {
            System.out.println("Palo: "+naipe.obtenerPalo()+" Valor: "+naipe.obtenerValor());
        }
    }
}