package Vista;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import Modelo.Mazo;
import Modelo.Mesa;
import Modelo.JuegoEscoba.MesaEscoba;
import Modelo.Serializador;
import Modelo.JuegoEscoba.MazoEspanyol;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GestorEventos {

    public GestorEventos(){}

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

    public static void directorSerializador(Serializador cs, String ruta, Mesa mesaConcreta){
        try {
            MesaEscoba mesaEscoba = (MesaEscoba)mesaConcreta;
            
            Mazo mazo = new MazoEspanyol();
            mazo.asignarMazo(mesaEscoba.obtenerMazo().obtenerGrupoDeCartas());

            Mazo cartasEnMesa = new MazoEspanyol();
            cartasEnMesa.asignarMazo(mesaEscoba.retornarCartasEnMesa());

            // Cartas en mesa
            cs.serializarCartasEnMesa(cartasEnMesa);

            // Cartas mazo
            cs.serializarMazo(mazo);

            // Jugadores
            cs.serializarJugador(mesaEscoba.obtenerPrimerJugador());
            cs.serializarJugador(mesaEscoba.obtenerSegundoJugador());

            // Jugador Actual
            cs.serializarjugadorActual(mesaEscoba.obtenerJugadorActual().obtenerNombre());
            cs.obtSerializacion(ruta);

            // JugadorUltimaCaptura
            cs.serializarjugadorUltimaCaptura(mesaEscoba.obtenerUltimoJugadorCaptura().obtenerNombre());
            cs.obtSerializacion(ruta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
