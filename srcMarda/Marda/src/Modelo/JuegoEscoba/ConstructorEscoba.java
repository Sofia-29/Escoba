package Modelo.JuegoEscoba;
import java.util.StringTokenizer;
import Modelo.Constructor;
import Modelo.Jugador;
import Modelo.Mazo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConstructorEscoba extends Constructor {
    private Gson gson;

    public ConstructorEscoba(){
        super();
        gson = new GsonBuilder().
            setPrettyPrinting().create();
    }

    @Override
    public void construirJugador(String jugador, int orden) {
        if(jugador.contains("Maquina")){
            switch (orden) {
                case 0:
                    resultado.asignarPrimerJugador(gson.fromJson(jugador, JugadorMaquina.class));
                    break;
                default:
                    resultado.asignarSegundoJugador(gson.fromJson(jugador, JugadorMaquina.class));
                    break;
            }
        }else{
            switch (orden) {
                case 0:
                    resultado.asignarPrimerJugador(gson.fromJson(jugador, JugadorPersona.class));
                    break;
                default:
                    resultado.asignarSegundoJugador(gson.fromJson(jugador, JugadorPersona.class));
                    break;
            }
        }
    }

    @Override
    public void construirMazo(String mazo) {
        Mazo mazoEspanyol = gson.fromJson(mazo, MazoEspanyol.class);
        resultado.asignarCartasEnMesa(mazoEspanyol.obtenerMazo());
    }

    @Override
    public void construirjugadorActual(String jugadorActual) {
        Jugador jugador1 = resultado.obtenerPrimerJugador();
        Jugador jugador2 = resultado.obtenerSegundoJugador();

        if(jugador1.obtenerNombre().equals(jugadorActual)){
            resultado.asignarJugadorActual(jugador1);
        }else{
            resultado.asignarJugadorActual(jugador2);
        }
    }

    @Override
    public void construirUltimaCapura(String captura) {
        System.out.println("--------------------------------------------------- Todo para jeremy -------------------------");
    }

    @Override
    public String obtenerTipoObjeto(String objeto) {
        StringTokenizer tokenizador = new StringTokenizer(objeto, "=");
        String tipo = tokenizador.nextToken();
        return tipo;
    }

    @Override
    public String obtenerValorObjeto(String objeto) {
        StringTokenizer tokenizador = new StringTokenizer(objeto, "=");
        String valor = "";
        tokenizador.nextToken();
        valor = tokenizador.nextToken();
        return valor;
    }

    @Override
    public String siguienteObjeto() {
        StringTokenizer tokenizador = new StringTokenizer(mesaSerial, "*");
        String objeto = "null";
        if(tokenizador.hasMoreTokens()){
            objeto = tokenizador.nextToken();
            this.mesaSerial = this.mesaSerial.replace(objeto, "");
        }
        return objeto;
    }
}
