package Controlador;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.JFileChooser;
import Modelo.Carta;
import Modelo.Constructor;
import Modelo.Mazo;
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
        MesaVista mesaVista = new MesaVistaEscoba();

        // Cargar partida
        Mesa mesaConcreta = new MesaEscoba();
        boolean cargarPartida = mesaVista.preguntarCargarPartida();
        if(cargarPartida){
            Constructor constructor = new ConstructorEscoba();
            directorConstructor(constructor, mesaConcreta, mesaVista.elegirArchivo());
        }
        System.out.print(mesaConcreta.obtenerPrimerJugador().obtenerNombre());
        System.out.print(mesaConcreta.obtenerSegundoJugador().obtenerNombre());

        
        // Guardar partida
        String ruta = mesaVista.guardarPartida();
        System.out.print("guardando en: "+ruta);
        Serializador serializador = new SerializadorEscoba(); 
        directorSerializador(serializador, ruta, mesaConcreta);

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

