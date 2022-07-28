package Controlador;
import java.io.File;
import javax.swing.JFileChooser;

import Modelo.Constructor;
import Modelo.Mesa;
import Modelo.Serializador;
import Modelo.JuegoEscoba.ConstructorEscoba;
import Modelo.JuegoEscoba.JugadorMaquina;
import Modelo.JuegoEscoba.JugadorPersona;
import Modelo.JuegoEscoba.MazoEspanyol;
import Modelo.JuegoEscoba.SerializadorEscoba;
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
    }
}

