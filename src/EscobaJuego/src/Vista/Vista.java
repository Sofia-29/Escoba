package Vista;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import Modelo.Naipe;

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

    public static int preguntarCargarPartida(){
        String mensaje = "¿Desea cargar una partida?";
        int respuesta = JOptionPane.showConfirmDialog(null, mensaje, "Cargar partida", JOptionPane.YES_NO_OPTION);
        return respuesta;
    }

    public static int preguntarTurno(){
        String mensaje="¿Desea ser el primer jugador?";
        int respuesta = JOptionPane.showConfirmDialog(null, mensaje, "Escoger turno", JOptionPane.YES_NO_OPTION);
        return respuesta;
    }

    public void actualizarCartasEnMesa(ArrayList<Naipe> cartasEnMesa){
      ventana.actualizarComponentesCartasMesa(cartasEnMesa);
    }

    public void actualizarCartasCaptura(ArrayList<Naipe> cartas, boolean escoba){
        ventana.actualizarComponentesCartasCapturadas(cartas, escoba);
    }

    public void limpiarComponeneteCartasCapturadas(){
        ventana.limpiarComponeneteCartasCapturadas();
    }

    public void actualizarTurnoJugador(String nombreJugador){
        ventana.actualizarTurnoJugador(nombreJugador);
    }

    public void actualizarPuntajeJugador(int puntaje){
        ventana.actualizarPuntajeJugador(puntaje);
    }

    public void iniciarPartida(ArrayList<Naipe> cartasJugador, ArrayList<Naipe> cartasMesa){
        ventana.actualizarComponentesCartasJugador(cartasJugador, -1);
        ventana.actualizarComponentesCartasMesa(cartasMesa);
        ventana.hacerVisible();
    }

    public void actualizarCartasJugador(ArrayList<Naipe> cartasJugador){
        ventana.actualizarComponentesCartasJugador(cartasJugador, -1);
    }

    public Naipe retornarNaipeSeleccionada(){
        String[] valorNaipe = ventana.obtenerNaipe();
        Naipe naipeAuxiliar = null;
        naipeAuxiliar = new Naipe(Integer.parseInt(valorNaipe[1]), valorNaipe[0]);
        return naipeAuxiliar;
    }

    public String retornarEstadoGuardarPartida(){
        return ventana.retornarEstadoGuardarPartida();
    }

    public void finalizarJuego(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public File elegirArchivo(){
        JFileChooser selectorDeArchivo = new JFileChooser();
        int respuesta = selectorDeArchivo.showOpenDialog(null);
        
        if(respuesta == JFileChooser.APPROVE_OPTION){
            File file  = new File(selectorDeArchivo.getSelectedFile().getAbsolutePath());
            return file;
        }else{
            System.out.println("error al cargar el archivo");
            return null;
        }
    }

}
