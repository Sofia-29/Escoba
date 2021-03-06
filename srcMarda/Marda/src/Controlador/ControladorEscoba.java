package Controlador;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import Modelo.Jugador;
import Modelo.Carta;
import Modelo.Constructor;
import Modelo.Mazo;
import Modelo.Mesa;
import Modelo.Serializador;
import Modelo.Validador;
import Modelo.JuegoEscoba.ConstructorEscoba;
import Modelo.JuegoEscoba.MazoEspanyol;
import Modelo.JuegoEscoba.SerializadorEscoba;
import Modelo.JuegoEscoba.ValidadorEscoba;
import Vista.MesaVista;
import Vista.MesaVistaEscoba;
import Modelo.JuegoEscoba.MesaEscoba;

public class ControladorEscoba extends Controlador {
    public ControladorEscoba(Mesa mesaConcreta, MesaVista mesaVistaConcreta, Constructor constructorConcreto, Serializador serializador){
        super(mesaConcreta, mesaVistaConcreta, constructorConcreto, serializador);
    }

    @Override
    public void inicializarMesa() {
        MesaVista mesaVistaConcreta = obtenerMesaVistaConcreta();
        MesaEscoba mesa = (MesaEscoba)obtenerMesaConcreta();
        Jugador primerJugador = mesa.crearJugadorPersona();
        Jugador segundoJugador = mesa.crearJugadorMaquina();
        mesaVistaConcreta.inicializarJugadores();
        mesaVistaConcreta.preguntarInformacionJugadorUno();
        mesaVistaConcreta.asignarInformacionJugadorDos("Maquina");
        primerJugador.asignarNombre(mesaVistaConcreta.obtenerNombreJugadorUno());
        mesa.asignarPrimerJugador(primerJugador);
        mesa.asignarSegundoJugador(segundoJugador);
        if(mesaVistaConcreta.obtenerNombreJugadorActual().equals(mesa.obtenerPrimerJugador().obtenerNombre()))
        {
            mesa.asignarJugadorActual(mesa.obtenerPrimerJugador());
        }else{
            mesa.asignarJugadorActual(mesa.obtenerSegundoJugador());
        }
        Mazo mazo = new MazoEspanyol();
        mazo.iniciarMazo();
        mesa.asignarMazo(mazo);
        mesaVistaConcreta.inicializarMazoCartasDescartadas();
        mesaVistaConcreta.inicializarMazoComun();
        mesaVistaConcreta.iniciarBotonDescartarCartaJugadores();
    }

    @Override
    public void realizarTurnoJugador() {
        MesaVistaEscoba mesaVistaConcreta = (MesaVistaEscoba)obtenerMesaVistaConcreta();
        MesaEscoba mesa = (MesaEscoba)obtenerMesaConcreta();
        Jugador jugadorActual = mesa.obtenerJugadorActual();
        ValidadorEscoba validador = (ValidadorEscoba)mesa.obtenerValidador();
        Carta cartaDescartada = null; 
        ArrayList<Carta> cartasCapturadas = null;
        if(mesa.validarRepartirCartas()){
            mesa.repartirCartasAJugador(mesa.obtenerPrimerJugador().obtenerNombre(), 3);
            mesa.repartirCartasAJugador(mesa.obtenerSegundoJugador().obtenerNombre(), 3);
            mesaVistaConcreta.actualizarCartasJugadorUno(mesa.obtenerPrimerJugador().obtenerCartas());
            mesaVistaConcreta.actualizarCartasJugadorDos(mesa.obtenerSegundoJugador().obtenerCartas());
            mesaVistaConcreta.deshabilitarCartasJugadores();
            mesaVistaConcreta.mostrarCartasJugadorActual();
            mesaVistaConcreta.refrescarPanelEtiquetas();
            System.out.println(mesa.obtenerMazo().obtenerCartasActuales());
        }

        if(!mesa.obtenerJugadorActual().obtenerNombre().equals("Maquina")){
            String cartaSeleccionada = "-1";
            while(true){
                cartaSeleccionada = mesaVistaConcreta.obtenerCartaDescartada();
                if(!cartaSeleccionada.equals("-1")){
                    break;
                }
                esperar(1);
            }
            StringTokenizer tokenizador = new StringTokenizer(cartaSeleccionada, "-");
            String palo = tokenizador.nextToken();
            String valor = tokenizador.nextToken();
            cartaDescartada = jugadorActual.obtenerCarta(palo, Integer.parseInt(valor));
            cartasCapturadas = mesa.movimientoJugadorCapturarCarta(cartaDescartada, jugadorActual.obtenerNombre());
        }else{
            esperar(2);
            cartaDescartada = jugadorActual.descartarCarta(mesa.retornarCartasEnMesa());
            cartasCapturadas = mesa.movimientoJugadorCapturarCarta(cartaDescartada, jugadorActual.obtenerNombre());
            mesaVistaConcreta.actualizarCartasJugadorDos(jugadorActual.obtenerCartas());
            esperar(1);
        }

        if(cartasCapturadas != null){
            boolean capturaEscoba = validador.esEscoba(mesa.retornarCartasEnMesa());
            if(capturaEscoba){
                mesaVistaConcreta.mostrarEscoba(cartasCapturadas);
            }else{
                mesaVistaConcreta.mostrarCaptura(cartasCapturadas);
            }
        }
        mesaVistaConcreta.actualizarCartasEnMesa(mesa.retornarCartasEnMesa());
        mesaVistaConcreta.actualizarEtiquetaPuntajeJugador(jugadorActual.obtenerPuntaje());
    }

    private void esperar(int segundos){
        try {
            TimeUnit.SECONDS.sleep(segundos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inicializarMesaCargada() {
        MesaVista mesaVistaConcreta = obtenerMesaVistaConcreta();
        MesaEscoba mesa = (MesaEscoba)obtenerMesaConcreta();
        Jugador primerJugador = mesa.obtenerPrimerJugador();
        Jugador segundoJugador = mesa.obtenerSegundoJugador();
        mesaVistaConcreta.inicializarJugadores();
        mesaVistaConcreta.asignarInformacionJugadorDos(segundoJugador.obtenerNombre());
        mesaVistaConcreta.asignarInformacionJugadorUno(primerJugador.obtenerNombre());
        mesaVistaConcreta.inicializarMazoCartasDescartadas();
        mesaVistaConcreta.inicializarMazoComun();
        mesaVistaConcreta.iniciarBotonDescartarCartaJugadores();
        mesaVistaConcreta.actualizarCartasEnMesa(mesa.obtenerCartasEnMesa());
        mesaVistaConcreta.actualizarCartasJugadorDos(segundoJugador.obtenerCartas());
        mesaVistaConcreta.actualizarCartasJugadorUno(primerJugador.obtenerCartas());
        mesaVistaConcreta.asignarJugadorActual(mesa.obtenerJugadorActual().obtenerNombre());
        mesaVistaConcreta.actualizarEtiquetaPuntajeJugador(mesa.obtenerJugadorActual().obtenerPuntaje());

    }

    public static void main(String[] args){
        Validador validador = new ValidadorEscoba();
        Mesa mesa = new MesaEscoba(validador);
        MesaVista mesaVista = new MesaVistaEscoba();
        Constructor constructor = new ConstructorEscoba();
        Serializador serializador = new SerializadorEscoba();
        Controlador controlador = new ControladorEscoba(mesa, mesaVista, constructor, serializador);
        controlador.iniciarJuego();
        System.exit(0);
    }
}