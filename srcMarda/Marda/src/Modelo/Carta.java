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

    /**
     * @return el valor de la carta
     */
    public int obtenerValor(){
        return this.valor;
    }

    /**
     * @return el palo de la carta
     */
    public String obtenerPalo(){
        return this.palo;
    }

    /**
     * @return la ruta en donde se encuentra la imagen de la carta
     */
    public String obtenerRuta(){
        return this.ruta;
    }
}
