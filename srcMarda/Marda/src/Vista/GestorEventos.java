package Vista;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import Modelo.Mazo;
import Modelo.Mesa;
import Modelo.Serializador;
import Modelo.JuegoEscoba.MazoEspanyol;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GestorEventos {

    public GestorEventos(){}

    /**
     * @param jugador al que se le debe agregar el evento de descartar carta
     */
    public void eventoDescartarCarta(JugadorVista jugador){
        JButton botonDescartarCarta = jugador.obtenerBotonDescartarCarta();
        ActionListener accion = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                eliminarBoton(jugador);
                botonDescartarCarta.setEnabled(false);
                jugador.deshabilitarCartasJugador();
            }
        };
        botonDescartarCarta.addActionListener(accion);
    } 

    /**
     * @param boton al que se le debe agregar el evento de seleccionar una carta
     * @param grupoCartasJugador boton de cartas del jugador
     * @param descartarCarta boton de descartar una carta si se selecciona el boton
     */
    public void eventoSeleccionarCarta(JToggleButton boton, ButtonGroup grupoCartasJugador, JButton descartarCarta){
        ActionListener accion = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                if(boton.isSelected()){
                    grupoCartasJugador.setSelected(boton.getModel(), true);
                    descartarCarta.setEnabled(true);
                }
            }
        };
        boton.addActionListener(accion);
    }

    /**
     * @param jugador elimina botones de un jugador de la clase JugadorVista
     */
    private void eliminarBoton(JugadorVista jugador){
        ButtonGroup grupoCartasJugador = jugador.obtenerGrupoCartasJugador();
        ButtonModel modeloBoton = grupoCartasJugador.getSelection();
        ArrayList<JToggleButton> componenteCartasJugador = jugador.obtenerBotonesCartasJugador();
        for(int indice = 0; indice < componenteCartasJugador.size();indice++){
            JToggleButton boton = componenteCartasJugador.get(indice);
            boton.setVisible(false);
            if(boton.getModel().equals(modeloBoton)){ 
                jugador.asignarCartaDescartada(boton.getName());
                componenteCartasJugador.remove(indice);
                grupoCartasJugador.remove(boton);
            }
        };
        grupoCartasJugador.clearSelection();
    }

    /**
     * @param mesa se agrega al boton de reglas del juego que se encuentra en la mesa el evento de mostrar reglas
     */
    public void accionMostrarReglas(MesaVista mesa){
        ActionListener accion = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent event){
                    String reglasJuego = mesa.reglasJuego();
                    JOptionPane.showMessageDialog(null,reglasJuego,"REGLAS DEL JUEGO",JOptionPane.INFORMATION_MESSAGE);
                }
        };
        mesa.reglasJuego.addActionListener(accion);
    }

    /**
     * @param mesa a la que se le agregara el evento de guardar
     * @param mesaConcreta mesa concreta para utilizar el serializador
     * @param serializador serializador para guardar una partida
     */
    public void accionGuardar(MesaVista mesa, Mesa mesaConcreta, Serializador serializador){
        ActionListener accion = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                String ruta = mesa.guardarPartida();
                directorSerializador(serializador, ruta, mesaConcreta);
                JOptionPane.showMessageDialog(null,"El juego ha sido guardado en "+ruta+" ","Confirmación",JOptionPane.INFORMATION_MESSAGE);
            }
        };
        mesa.botonGuardar.addActionListener(accion);
    }

    /**
     * @param cs serializar abstracto 
     * @param ruta ruta del archivo a guardar
     * @param mesaConcreta la mesa concreta que debe ser serializada
     */
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
}
