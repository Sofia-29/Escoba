package Controlador;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.JFileChooser;
import Modelo.Jugador;
import Modelo.Carta;
import Modelo.Constructor;
import Modelo.Mazo;
import Modelo.Mesa;
import Modelo.Serializador;
import Modelo.Validador;
import Modelo.JuegoEscoba.ConstructorEscoba;
import Modelo.JuegoEscoba.JugadorMaquina;
import Modelo.JuegoEscoba.JugadorPersona;
import Modelo.JuegoEscoba.MazoEspanyol;
import Modelo.JuegoEscoba.SerializadorEscoba;
import Modelo.JuegoEscoba.ValidadorEscoba;
import Vista.MesaVista;
import Vista.MesaVistaEscoba;
import Modelo.JuegoEscoba.MesaEscoba;
import Modelo.JuegoEscoba.SerializadorEscoba;
import Vista.MesaVista;

public class Controlador {
    public static void directorSerializador(Serializador cs, String ruta, Mesa mesaConcreta){
        try {
            Mazo cartasEnMesa = new MazoEspanyol();
            cartasEnMesa.asignarMazo(mesaConcreta.retornarCartasEnMesa());

            // Cartas en mesa
            cs.serializarMazo(cartasEnMesa);

            // Jugadores
            cs.serializarJugador(mesaConcreta.obtenerPrimerJugador());
            cs.serializarJugador(mesaConcreta.obtenerSegundoJugador());

            // Jugador Actual
            cs.serializarjugadorActual(mesaConcreta.obtenerJugadorActual().obtenerNombre());
            cs.obtSerializacion(ruta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Director constructor para objetos del tipo Mesa
     */
    public static void directorConstructor(Constructor constructorMesa, Mesa mesaConcretaVacia, File archivo){
        constructorMesa.iniciarMesaConcreta(mesaConcretaVacia);

        // Construir partes sobre el esqueleto de mesa 
        constructorMesa.leerArchivo(archivo);
        String atributo = constructorMesa.siguienteObjeto();
        int posicionJugador = 0;
        while(atributo != "null"){
            String tipo = constructorMesa.obtenerTipoObjeto(atributo);
            String Valor = constructorMesa.obtenerValorObjeto(atributo);
            
            switch (tipo) {
                case "Mazo":
                    constructorMesa.construirMazo(Valor);
                    break;

                case "Jugador":
                    constructorMesa.construirJugador(Valor, posicionJugador);
                    posicionJugador += 1;
                    break;
                
                case "JugadorActual":
                    constructorMesa.construirjugadorActual(Valor);
                    break;
            
                default:
                    break;
            }

            atributo = constructorMesa.siguienteObjeto();
            if(atributo.length() <= 1){
                break;
            }
        }

        // Obtener mesa resultante
        mesaConcretaVacia = constructorMesa.obtenerMesa();
    }

    public static void main(String[] args) throws Exception {
        // Cargar partida
        Mesa mesaConcreta = new MesaEscoba();
        MesaVista frame = new MesaVistaEscoba();
        boolean cargarPartida = frame.preguntarCargarPartida();
        frame.inicializarMazoComun();
		frame.inicializarMazoCartasDescartadas();
        frame.setVisible(true);
        if(cargarPartida){
            Constructor constructor = new ConstructorEscoba();
            directorConstructor(constructor, mesaConcreta, frame.elegirArchivo());
        }else{
            frame.inicializarJugadores();
            frame.preguntarInformacionJugadorUno();
            frame.preguntarInformacionJugadorDos();
        }

        // Se asignan atributos a la mesa.
        Jugador primerJugador = frame.obtenerJugadorPersona();
        Jugador segundoJugador = frame.obtenerJugadorMaquina();
        mesaConcreta.asignarPrimerJugador(primerJugador);
        mesaConcreta.asignarSegundoJugador(segundoJugador);
        if(frame.obtenerNombreJugadorActual().equals(primerJugador.obtenerNombre()))
        {
            mesaConcreta.asignarJugadorActual(primerJugador);
        }else{
            mesaConcreta.asignarJugadorActual(segundoJugador);
        }
        Validador validadora = new ValidadorEscoba();
        mesaConcreta.asignarValidador(validadora);
        Mazo mazo = new MazoEspanyol();
        mazo.iniciarMazo();
        mesaConcreta.asignarMazo(mazo);

        // Se inicia la partida, se reparten cartas.
        mesaConcreta.iniciarPartida();

        
        // Asigna mesa y serializador
        Serializador serializador = new SerializadorEscoba();
        frame.asignarMesa(mesaConcreta, serializador);
        
		frame.actualizarCartasEnMesa(mesaConcreta.obtenerCartasEnMesa());
		frame.actualizarCartasJugadorUno(mesaConcreta.obtenerPrimerJugador().obtenerCartas());
		frame.actualizarCartasJugadorDos(mesaConcreta.obtenerSegundoJugador().obtenerCartas());
		frame.deshabilitarCartasJugadores();
        frame.mostrarCartasJugadorActual();
		frame.actualizarEtiquetaPuntajeJugador(mesaConcreta.obtenerJugadorActual().obtenerPuntaje());
		frame.iniciarBotonDescartarCartaJugadores();

        // Empieza el ciclo de descartar cartas y jugar hasta que ya no hayan cartas.
		String carta = "-1";
		while(true){
			carta = frame.obtenerCartaDescartada();
			if(!carta.equals("-1")){
				break;
			}
			TimeUnit.SECONDS.sleep(1);
		}
		System.out.println(carta);

		frame.cambiarTurnoJugador();
    }
}