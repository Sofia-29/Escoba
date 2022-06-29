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
            String informacion = gson.toJson(juego);
            escritor.println(informacion);
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
