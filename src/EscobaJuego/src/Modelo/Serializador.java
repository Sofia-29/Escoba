package Modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
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
            BufferedReader br = new BufferedReader(new FileReader(partida));
            String jsonString = "";
            String st;
            while ((st = br.readLine()) != null){
                // if(st.contains("segundoJugador") || st.contains("primerJugador") || st.contains("mazo")|| st.contains("jugadorActual")){
                //     if(st.contains("primerJugador")){
                //         st = "{";
                //     }
                //     jsonString += "*"+st+"\n";
                // }else{
                    jsonString += st+"\n";
               // }
            }
            System.out.println("Archivo cargado: ");
            System.out.println(jsonString);

            int attributeCounter = 0;
            StringTokenizer st2 = new StringTokenizer(jsonString, "*");
            while (st2.hasMoreTokens()) {
                String line = st2.nextToken();
                // switch (attributeCounter) {
                //     case 0:
                //         // line.trim();
                //         line = line.substring(0, line.length()-2);
                //         System.out.println(line);
                //         System.out.println("----------------------------");
                //         JugadorPersona jugadorPersona = gson.fromJson(line, JugadorPersona.class);
                //         System.out.println("aaaaaa");
                //         System.out.println(jugadorPersona.toString());
                //         System.out.println(jugadorPersona.obtenerNombre());

                //         break;
                //     case 2:
                //         // line.trim();
                //         line = line.substring(0, line.length()-2);
                //         System.out.println(line);
                //         System.out.println("----------------------------");
                //         JugadorPersona jugadorPersona = gson.fromJson(line, JugadorPersona.class);
                //         System.out.println("aaaaaa");
                //         System.out.println(jugadorPersona.toString());
                //         System.out.println(jugadorPersona.obtenerNombre());

                //         break;
                
                //     default:
                //         break;
                // }
                System.out.println(line);
                System.out.println("----------------------------");
                attributeCounter +=1;
            }
            
            // Juego juego = new Juego(); gson.fromJson(jsonString, Juego.class);
            // System.out.println(juego.toString());
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
