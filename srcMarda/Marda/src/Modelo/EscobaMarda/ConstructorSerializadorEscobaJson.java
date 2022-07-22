package Modelo.EscobaMarda;
import Modelo.ConstructorSerializadorAbstracto;
import Modelo.Jugador;
import Modelo.Mazo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConstructorSerializadorEscobaJson extends ConstructorSerializadorAbstracto{
    private Gson gson;

    public ConstructorSerializadorEscobaJson(){
        super();
        gson = new GsonBuilder().
            setPrettyPrinting().create();
    }

    @Override
    public Jugador construirJugador(String jugador) {
        Jugador jugadorResultado = gson.fromJson(jugador, Jugador.class);
        return jugadorResultado;
    }

    @Override
    public Mazo construirMazo(String mazo) {
        Mazo mazoResultado = gson.fromJson(mazo, Mazo.class);
        return mazoResultado;
    }

    @Override
    public String construirjugadorActual(String jugadorActual) {
        return jugadorActual;
    }

    @Override
    public String construirUltimaCapura(String captura) {
        return captura;
    }

    @Override
    public String serializarJugador(Jugador jugador) {
        String result =gson.toJson(jugador);
        return result;
    }

    @Override
    public String serializarMazo(Mazo mazo) {
        String result =gson.toJson(mazo);
        return result;
    }

    @Override
    public String serializarjugadorActual(String jugadorActual) {
        return jugadorActual;
    }

    @Override
    public String serializarUltimaCapura(String captura) {
        return captura;
    }

    @Override
    public String inicioObjetop(String nombre) {
        return "*"+nombre+":";
    }

    @Override
    public String finObjeto() {
        return "*";
    }
}
