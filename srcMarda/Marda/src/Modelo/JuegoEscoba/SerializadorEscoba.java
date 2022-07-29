package Modelo.JuegoEscoba;
import Modelo.Serializador;
import Modelo.Jugador;
import Modelo.Mazo;
import java.io.FileWriter;
import java.io.PrintWriter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SerializadorEscoba extends Serializador{
    private Gson gson;
    private String resultadoSerial = "";

    public SerializadorEscoba(){
        super();
        gson = new GsonBuilder().
            setPrettyPrinting().create();
    }

    @Override
    public void serializarJugador(Jugador jugador) {
        inicioObjeto("Jugador");
        String result =gson.toJson(jugador);
        resultadoSerial += result;
        finObjeto();
    }

    @Override
    public void serializarMazo(Mazo mazo) {
        inicioObjeto("Mazo");
        String result =gson.toJson(mazo);
        resultadoSerial += result;
        finObjeto();
    }

    @Override
    public void serializarjugadorActual(String jugadorActual) {
        inicioObjeto("JugadorActual");
        resultadoSerial += jugadorActual;
        finObjeto();
    }

    // @Override
    // public void serializarUltimaCaptura(String captura) {
    //     inicioObjeto("UltimaCapura");
    //     resultadoSerial += captura;
    //     finObjeto();
    // }

    @Override
    public void inicioObjeto(String nombre) {
        resultadoSerial += nombre+"=";
    }

    @Override
    public void finObjeto() {
        resultadoSerial +=  "*";
    }

    @Override
    public String obtSerializacion(String rutaArchivo) {
        try {
            FileWriter fichero = new FileWriter(rutaArchivo);
            PrintWriter escritor = new PrintWriter(fichero);
            escritor.println(resultadoSerial);
            escritor.close();
        } catch (Exception e) {
            //TODO: handle exception
        }
        return resultadoSerial;
    }
}
