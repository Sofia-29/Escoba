package Modelo;

public class Carta {
    private int valor;
    private String palo;

    public Carta(){};

    public Carta(int valor, String palo){

        this.valor = valor;
        this.palo = palo;
    };

    public int obtenerValor(){
        return this.valor;
    }

    public String obtenerPalo(){
        return this.palo;
    }
}
