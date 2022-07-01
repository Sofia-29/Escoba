package Modelo;
import java.util.Collections;
import java.util.ArrayList;

public class Mazo {
    private ArrayList<Naipe> mazo = new ArrayList<Naipe>();
    ArrayList<String> palos; 

    public Mazo(){
        palos = new ArrayList<String>() {
            {
                add("Copas");
                add("Espadas");
                add("Bastos");
                add("Oros");
            }
        };

        for (String palo : palos) {
            for (int valor = 1; valor < 11; valor++) {
                mazo.add(new Naipe(valor, palo));
            }
        }
    }

    public void asignarMazo(ArrayList<Naipe> mazo){
        this.mazo = mazo;
    }

    public ArrayList<Naipe> obtenerMazo(){
        return mazo;
    }

    public int obtenerCantidadDeNaipes(){
        return mazo.size();
    }

    public ArrayList<Naipe> repartirMazo(int cantidadDeNaipes){
        int cartasElegidas = 0;
        ArrayList<Naipe> cartas =  new ArrayList<Naipe>();
        barajar();
        for (Naipe naipe : mazo) {
            cartas.add(naipe);
            cartasElegidas++;
            if(cartasElegidas == cantidadDeNaipes){
                break;
            }
        }

        for (Naipe naipe : cartas) {
            mazo.remove(naipe);
        }
        return cartas;
    }

    public void barajar(){
        Collections.shuffle(mazo);
    }

    public void imprimirMazo(){
        for (Naipe naipe : mazo) {
            System.out.println("Palo: "+naipe.obtenerPalo()+" Valor: "+naipe.obtenerValor());
        }
    }
}
