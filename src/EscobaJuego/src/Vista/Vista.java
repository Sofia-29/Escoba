package Vista;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import Modelo.Naipe;
import Modelo.Juego;

public class Vista {

    private Ventana ventana;
    private static String nombreJugador;
    private static String opcionJugador;

    public Vista(){
        ventana = new Ventana(1000, 1000, "Juego Escoba");
    }

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

    public void iniciarPartida(ArrayList<Naipe> cartasJugador){
        ventana.actualizarComponentesCartasJugador(cartasJugador, -1);
        ventana.hacerVisible();
    }

    public Naipe retornarNaipeSeleccionada(){
        String[] valorNaipe = ventana.obtenerNaipe();
        Naipe naipeAuxiliar = new Naipe(Integer.parseInt(valorNaipe[0]), valorNaipe[1]);
        return naipeAuxiliar;
    }
}
