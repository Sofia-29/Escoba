package Modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
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


    public void cargarJuego(File partida){
        try
        {
            // Atributos del juego
            Juego juegoCargado = null;
            JugadorPersona jugadorPersona = null;
            JugadorMaquina jugadorMaquina = null;
            Mazo mazo = null;
            Mazo cartasEnMesa = null;
            String jugadorActual = "";

            String juegoSerializado = leerArchivo(partida);
            int IdAtributo = 0;
            StringTokenizer tokenizador = new StringTokenizer(juegoSerializado, "*");
            while (tokenizador.hasMoreTokens()) {
                String line = tokenizador.nextToken();
                switch (IdAtributo) {
                    case 0:
                    case 1:
                        if(line.contains("Jugador Maquina")){
                            jugadorMaquina = gson.fromJson(line, JugadorMaquina.class);
                        }else{
                            jugadorPersona = gson.fromJson(line, JugadorPersona.class);
                        }
                        break;
                    case 2:
                        mazo = gson.fromJson(line, Mazo.class);
                        break;
                    case 3:
                        cartasEnMesa = gson.fromJson(line, Mazo.class);
                        break;
                    case 4:
                        jugadorActual = line;
                        break;
                
                    default:
                        break;
                }
                IdAtributo +=1;
            }

            if(jugadorActual.equals("Jugador Maquina")){
                juegoCargado = new Juego(jugadorPersona, jugadorMaquina, mazo, jugadorMaquina, cartasEnMesa.obtenerMazo());
            }else{
                juegoCargado = new Juego(jugadorPersona, jugadorMaquina, mazo, jugadorPersona, cartasEnMesa.obtenerMazo());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String leerArchivo(File partida){
        String salida = "";
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(partida));
            salida = "";
            String st;
            while ((st = br.readLine()) != null){
                salida += st+"\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salida;
    }

    

    public static void main(String[] args){
        Juego juego = new Juego();
        juego.iniciarPartida("xd", 0);
        Serializador serializador = new Serializador();

        serializador.guardarJuego(juego, "Prueba");
    }
}
