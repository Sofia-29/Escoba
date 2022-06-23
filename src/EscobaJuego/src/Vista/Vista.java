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
        ventana.hacerVisible();
        ventana.inicializarEtiquetas();
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

    public static int preguntarTurno(){
        String mensaje="¿Desea ser el primer jugador?";
        int respuesta = JOptionPane.showConfirmDialog(null, mensaje, "Escoger turno", JOptionPane.YES_NO_OPTION);
        return respuesta;
    }

    public void actualizarCartasEnMesa(ArrayList<Naipe> cartasEnMesa){
      ventana.actualizarComponentesCartasMesa(cartasEnMesa);
    }


    public void actualizarTurnoJugador(String nombreJugador){
        ventana.actualizarTurnoJugador(nombreJugador);
    }

    public void iniciarPartida(ArrayList<Naipe> cartasJugador){
        ventana.actualizarComponentesCartasJugador(cartasJugador, -1);
        ventana.hacerVisible();
    }

    public void actualizarCartasJugador(ArrayList<Naipe> cartasJugador){
        ventana.actualizarComponentesCartasJugador(cartasJugador, -1);
    }

    public Naipe retornarNaipeSeleccionada(){
        String[] valorNaipe = ventana.obtenerNaipe();
        Naipe naipeAuxiliar = null;
        if(valorNaipe[0] != "-1"){
            naipeAuxiliar = new Naipe(Integer.parseInt(valorNaipe[1]), valorNaipe[0]);
        }
        return naipeAuxiliar;
    }
}
