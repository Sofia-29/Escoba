package Controlador;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JFileChooser;

import Modelo.Carta;
import Modelo.Constructor;
import Modelo.Mesa;
import Modelo.Serializador;
import Modelo.JuegoEscoba.ConstructorEscoba;
import Modelo.JuegoEscoba.JugadorMaquina;
import Modelo.JuegoEscoba.JugadorPersona;
import Modelo.JuegoEscoba.MazoEspanyol;
import Modelo.JuegoEscoba.SerializadorEscoba;
import Vista.MesaVista;
import Vista.MesaVistaEscoba;
import Modelo.JuegoEscoba.MesaEscoba;

public class Controlador {
    public static void directorSerializador(Serializador cs){
        try {
            MazoEspanyol mazo = new MazoEspanyol();
            mazo.iniciarMazo();
            mazo.imprimirMazo();
            JugadorMaquina primerJugador = new JugadorMaquina();
            primerJugador.asignarNombre("Nombre 1");
            JugadorPersona segundoJugador = new JugadorPersona();
            segundoJugador.asignarNombre("Nombre 2");
            // Cartas en mesa
            cs.serializarMazo(mazo);
            // Jugadores
            cs.serializarJugador(primerJugador);
            cs.serializarJugador(segundoJugador);
            // Jugador Actual
            cs.serializarjugadorActual(segundoJugador.obtenerNombre());
            String result = cs.obtSerializacion();

            // Archivo serial
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void directorConstructor(Constructor constructorMesa){
        // Agregar esqueleto de mesa
        Mesa mesaConcreta = new MesaEscoba();
        constructorMesa.iniciarMesaConcreta(mesaConcreta);

        // Construir partes sobre el esqueleto de mesa 
        constructorMesa.leerArchivo();
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
        mesaConcreta = constructorMesa.obtenerMesa();
    }

    public static void main(String[] args) throws Exception {
        // Se descomenta para probar el serializador
        // SerializadorEscoba cs = new SerializadorEscoba(); 
        // directorSerializador(cs);

        // Se puede probar con el arch Partidas
        // ConstructorEscoba ce = new ConstructorEscoba();
        // directorConstructor(ce);


        MesaVista frame = new MesaVistaEscoba();

		Carta carta1 = new Carta(1, "Bastos", "");
		Carta carta2 = new Carta(2, "Copas", "");
		Carta carta3 = new Carta(3, "Oros", "");
		Carta carta4 = new Carta(4, "Espadas", "");
		ArrayList<Carta> cartas = new ArrayList<Carta>();
        ArrayList<Carta> cartas1 = new ArrayList<Carta>();
		cartas.add(carta1);
		cartas.add(carta2);
		cartas.add(carta3);
		cartas.add(carta4);
        cartas1.add(carta1);
		cartas1.add(carta2);
		cartas1.add(carta3);
		cartas1.add(carta4);
		frame.actualizarCartasEnMesa(cartas);
		frame.inicializarJugadores();
		frame.actualizarCartasJugadorUno(cartas);
		frame.actualizarCartasJugadorDos(cartas1);
		frame.deshabilitarCartasJugadores();
		frame.inicializarMazoComun();
		frame.inicializarMazoCartasDescartadas();
		frame.setVisible(true);
		frame.preguntarInformacionJugadorUno();
		frame.preguntarInformacionJugadorDos();
		frame.actualizarEtiquetaPuntajeJugador("12");
		//frame.setVisible(true);
		frame.iniciarBotonDescartarCartaJugadores();

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

