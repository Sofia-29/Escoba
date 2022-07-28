package Controlador;
import java.io.File;
import Modelo.Constructor;
import Modelo.Mazo;
import Modelo.Mesa;
import Modelo.Serializador;
import Modelo.JuegoEscoba.ConstructorEscoba;
import Modelo.JuegoEscoba.JugadorMaquina;
import Modelo.JuegoEscoba.JugadorPersona;
import Modelo.JuegoEscoba.MazoEspanyol;
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
        MesaVista mesaVista = new MesaVista();

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
    }
}

