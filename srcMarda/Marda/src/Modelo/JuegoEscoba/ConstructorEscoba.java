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
        resultado.asignarMazo(mazoEspanyol);
    }

    @Override
    public void construirjugadorActual(String jugadorActual) {
        if(jugador1EsNombre(jugadorActual)){
            resultado.asignarJugadorActual(resultado.obtenerPrimerJugador());
        }else{
            resultado.asignarJugadorActual(resultado.obtenerSegundoJugador());
        }
    }

    @Override
    public void construirUltimaCaptura(String captura) {
        if(jugador1EsNombre(captura)){
            resultado.asignarJugadorUltimaCaptura(resultado.obtenerPrimerJugador());
        }else{
            resultado.asignarJugadorUltimaCaptura(resultado.obtenerSegundoJugador());
        }
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

    private boolean jugador1EsNombre(String nombre){
        boolean esIgual = false;
        Jugador jugador1 = resultado.obtenerPrimerJugador();

        if(jugador1.obtenerNombre().equals(nombre)){
            esIgual = true;
        }
        return esIgual;
    }

    @Override
    public void construirCartasEnMesa(String cartasEnMesa) {
        Mazo cartas = gson.fromJson(cartasEnMesa, MazoEspanyol.class);
        resultado.asignarCartasEnMesa(cartas.obtenerGrupoDeCartas());
    }
}
