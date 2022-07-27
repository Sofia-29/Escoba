package Modelo.EscobaMarda;
import Modelo.SerializadorAbstracto;
import Modelo.Jugador;
import Modelo.Mazo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SerializadorEscobaJson extends SerializadorAbstracto{
    private Gson gson;
    private String serialResult = "";

    public SerializadorEscobaJson(){
        super();
        gson = new GsonBuilder().
            setPrettyPrinting().create();
    }

    @Override
    public void serializarJugador(Jugador jugador) {
        inicioObjeto("Jugador");
        String result =gson.toJson(jugador);
        serialResult += result;
        finObjeto();
    }

    @Override
    public void serializarMazo(Mazo mazo) {
        inicioObjeto("Mazo");
        String result =gson.toJson(mazo);
        serialResult += result;
        finObjeto();
    }

    @Override
    public void serializarjugadorActual(String jugadorActual) {
        inicioObjeto("JugadorActual");
        serialResult += jugadorActual;
        finObjeto();
    }

    @Override
    public void serializarUltimaCapura(String captura) {
        inicioObjeto("UltimaCapura");
        serialResult += captura;
        finObjeto();
    }

    @Override
    public void inicioObjeto(String nombre) {
        serialResult += "*"+nombre+":";
    }

    @Override
    public void finObjeto() {
        serialResult +=  "*";
    }

    @Override
    public String obtSerializacion() {
        return serialResult;
    }
}
