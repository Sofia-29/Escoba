package Modelo;

import java.io.FileWriter;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Serializador {
    private Gson gson;
    
    public Serializador(){
        gson = new GsonBuilder().
            setPrettyPrinting().create();
    }

    public void guardarJuego(Juego juego, String nombreArchivo){
        try
        {
            String ruta =  "src\\EscobaJuego\\src\\Modelo\\JuegosGuardados\\" + nombreArchivo + ".json";
            FileWriter archivo = new FileWriter(ruta);
            PrintWriter escritor = new PrintWriter(archivo);
            String informacionJugadorUno = gson.toJson(juego.obtenerPrimerJugador());
            String informacionJugadorDos = gson.toJson(juego.obtenerSegundoJugador());
            String informacionMazo = gson.toJson(juego.obtenerMazo());
            String informacionCartasEnMesa = gson.toJson(juego.retornarCartasEnMesa());
            String informacionJugadorActual = juego.obtenerJugadorActual().obtenerNombre();

            escritor.println(informacionJugadorUno + "*");
            escritor.println(informacionJugadorDos + "*");
            escritor.println(informacionMazo + "*");
            escritor.println(informacionCartasEnMesa + "*");
            escritor.println(informacionJugadorActual);
            escritor.close();
            archivo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    public static void main(String[] args){
        Juego juego = new Juego();
        juego.iniciarPartida("xd", 0);
        Serializador serializador = new Serializador();

        serializador.guardarJuego(juego, "Prueba");
    }
}
