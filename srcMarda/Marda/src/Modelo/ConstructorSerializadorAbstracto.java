package Modelo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public abstract class ConstructorSerializadorAbstracto {

    public ConstructorSerializadorAbstracto(){
    }

    // Constructor
    public abstract Jugador construirJugador(String jugador);

    public abstract Mazo construirMazo(String mazo);

    public abstract String construirjugadorActual(String jugadorActual);

    public abstract String construirUltimaCapura(String captura);

    // Serializador
    public abstract String serializarJugador(Jugador jugador);

    public abstract String serializarMazo(Mazo mazo);

    public abstract String serializarjugadorActual(String jugadorActual);

    public abstract String serializarUltimaCapura(String captura);


    public abstract String inicioObjetop(String nombre);
    public abstract String finObjeto();


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
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salida;
    }
}