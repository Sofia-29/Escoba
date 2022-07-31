package Modelo;

public abstract class Serializador {

    public Serializador(){
    }


    /**
     * serializa el mazo 
     * @param mazo mazo de mesa
     */
    public abstract void serializarMazo(Mazo mazo);

    /**
     * serializa las CartasEnMesa 
     * @param mazo mazo de cartas en mesa
     */
    public abstract void serializarCartasEnMesa(Mazo mazo);

    /**
     * serializa el jugador 
     * @param jugador jugador a serializar
     */
    public abstract void serializarJugador(Jugador jugador);
     
    /**
     * serializa el jugadorActual
     * @param jugadorActual jugador a serializar
     */
    public abstract void serializarjugadorActual(String jugadorActual);
    
    /**
     * serializarjugadorUltimaCaptura
     * @param jugador jugador a serializar
     */
    public abstract void serializarjugadorUltimaCaptura(String jugador);

    /**
     * agrega un inicio de objeto a la serialización
     */
    public abstract void inicioObjeto(String nombre);

    /**
     * agrega un fin de objeto a la serialización
     */
    public abstract void finObjeto();

    /**
     * guarda la serializacion resultante en el archivo
     * @param rutaArchivo Archivo de la partida
     */
    public abstract String obtSerializacion(String rutaArchivo);
}