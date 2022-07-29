package Vista;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JToggleButton;

import Modelo.Carta;

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
        ArrayList<Carta> cartasJugador = jugador.obtenerCartasJugador();
        for(int indice = 0; indice < componenteCartasJugador.size();indice++){
            JToggleButton boton = componenteCartasJugador.get(indice);
            boton.setVisible(false);
            if(boton.getModel().equals(modeloBoton)){ 
                jugador.asignarCartaDescartada(boton.getName());
                cartasJugador.remove(indice);
                componenteCartasJugador.remove(indice);
                grupoCartasJugador.remove(boton);
            }
        };
        grupoCartasJugador.clearSelection();
    }
}
