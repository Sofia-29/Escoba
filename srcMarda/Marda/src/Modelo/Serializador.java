package Modelo;

import java.io.File;

import javax.swing.JFileChooser;

public abstract class Serializador {

    public Serializador(){
    }

    public abstract void serializarJugador(Jugador jugador);
    public abstract void serializarMazo(Mazo mazo);
    public abstract void serializarjugadorActual(String jugadorActual);

    public abstract void inicioObjeto(String nombre);
    public abstract void finObjeto();
    public abstract String obtSerializacion();
}