package Vista;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import Modelo.Naipe;
import Modelo.Juego;

public class Vista {
    static String nombreJugador;
    static String opcionJugador;

    public static String preguntarNombreJugadorPersona(){
        String mensaje = "Ingrese el nombre del jugador";
        nombreJugador= JOptionPane.showInputDialog(mensaje,"");
        return nombreJugador;
    }

    public static String preguntarTurnoJugador(){
        String mensaje="Escoja su turno: Primero o Segundo";
        String opcionJugador = JOptionPane.showInputDialog(mensaje,"");
        if(!(opcionJugador.equals("Primero"))){
            if(!(opcionJugador.equals("Segundo"))){
                String mensajeError = "Escoja su turno nuevamente: Primero o Segundo";
                opcionJugador = JOptionPane.showInputDialog(null, mensajeError, "Error!", JOptionPane.ERROR_MESSAGE);
            }
        } 
        return opcionJugador;
    }

    public static void main(String[] args){
        Ventana ventana = new Ventana(1920,1024, "Juego Escoba");
        ventana.hacerVisible();
    
        //nombreJugador = preguntarNombreJugadorPersona();
        //opcionJugador = preguntarTurnoJugador();

        ventana.inicializarEtiquetas();
        ventana.actualizarTurnoJugador("Ny");
        //Juego juego = new Juego();
        //juego.iniciarPartida(nombreJugador, opcionJugador);

        Naipe naipe1 = new Naipe(1, "Oros");
        Naipe naipe2 = new Naipe(8, "Copas");
        Naipe naipe3 = new Naipe(3, "Espadas");
        ArrayList<Naipe> cartas = new ArrayList<Naipe>();

        cartas.add(naipe1);
        cartas.add(naipe2);
        cartas.add(naipe3);
        ventana.actualizarComponentesCartasJugador(cartas);
        ventana.hacerVisible();
    }
}
