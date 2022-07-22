package Modelo;

public class Carta {
    private int valor;
    private String palo;
    private String ruta;

    public Carta(){};

    public Carta(int valor, String palo, String ruta){

        this.valor = valor;
        this.palo = palo;
        this.ruta = ruta;
    };

    public int obtenerValor(){
        return this.valor;
    }

    public String obtenerPalo(){
        return this.palo;
    }

    public String obtenerRuta(){
        return this.ruta;
    }
}
