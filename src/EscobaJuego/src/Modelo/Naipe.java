package Modelo;

public class Naipe {

    private int valor;
    private String palo;

    public Naipe(){};

    public Naipe(int valor, String palo){

        this.valor = valor;
        this.palo = palo;
    };

    public int obtenerPalo(){
        return valor;
    }

    public String obtenerValor(){
        return palo;
    }
}
