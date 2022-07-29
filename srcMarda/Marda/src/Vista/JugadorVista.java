package Vista;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import Modelo.Carta;

public class JugadorVista {
    private ArrayList<JToggleButton> componenteCartasJugador;
    private ButtonGroup grupoCartasJugador;
    private ArrayList<Carta> cartasJugador;
    private General ayudante;
    private GestorEventos gestorEventos;
    private JPanel panel; 
    private JButton botonDescartarCarta;
    private String nombre;
    private String cartaSeleccionada;
    private JLabel puntaje;

    JugadorVista(){
        componenteCartasJugador = new ArrayList<JToggleButton>();
        cartasJugador = new ArrayList<Carta>();
        ayudante = new General();
        gestorEventos = new GestorEventos();
        grupoCartasJugador = new ButtonGroup();
        botonDescartarCarta = new JButton("Descartar");
        cartaSeleccionada = "-1";
    }

    public void asignarPanel(JPanel panel){
        this.panel = panel;
    }

    public void asignarNombre(String nombre){
        this.nombre = nombre;
    }

    public void asignarCartaDescartada(String carta){
        this.cartaSeleccionada = carta;
    }

    public String obtenerCartaDescartada(){
        String carta = this.cartaSeleccionada;
        if(!carta.equals("-1")){
            asignarCartaDescartada("-1");
        }
        return carta;
    }

    public String obtenerNombreJugador(){
        return this.nombre;
    }

    public JPanel obtenerPanel(){
        return this.panel;
    }

    public ArrayList<Carta> obtenerCartasJugador(){
        return this.cartasJugador;
    }

    public ArrayList<JToggleButton> obtenerBotonesCartasJugador(){
        return this.componenteCartasJugador;
    }

    public ButtonGroup obtenerGrupoCartasJugador(){
        return this.grupoCartasJugador;
    }

    public JButton obtenerBotonDescartarCarta(){
        return this.botonDescartarCarta;
    }

    public void preguntarNombreJugador(){
        String mensaje = "Ingrese el nombre del jugador: ";
        this.asignarNombre(JOptionPane.showInputDialog(mensaje,""));
    }

    public int preguntarTurno(){
        String mensaje="¿Desea ser el primer jugador?";
        int respuesta = JOptionPane.showConfirmDialog(null, mensaje, "Escoger turno", JOptionPane.YES_NO_OPTION);
        return respuesta;
    }

    public void iniciarBotonDescartarCarta(){
        botonDescartarCarta.setSize(100,100);
        botonDescartarCarta.setEnabled(false);
        gestorEventos.eventoDescartarCarta(this);
    }

    public void actualizarCartasJugador(ArrayList<Carta> cartas){
        this.cartasJugador = cartas;
        //this.componenteCartasJugador.removeAll(this.componenteCartasJugador);
		for(int indice = 0; indice < cartas.size(); indice++){
            String palo = cartas.get(indice).obtenerPalo();
            Integer valor = cartas.get(indice).obtenerValor();
            String ruta = "Imagenes/" + palo + "/" + valor.toString() + "-" + palo + ".jpg";
			ImageIcon imagen = new ImageIcon(this.getClass().getResource(ruta));
            JToggleButton boton = new JToggleButton();
            ayudante.generarBotonConImagen(boton, valor+"-"+palo, imagen);
            this.componenteCartasJugador.add(boton);
            this.grupoCartasJugador.add(boton);
            gestorEventos.eventoSeleccionarCarta(boton, grupoCartasJugador, this.botonDescartarCarta);
            panel.add(boton);
            ayudante.actualizarPanel(panel);
        }
    }

    public void deshabilitarCartasJugador(){
        for(int indice = 0; indice < this.cartasJugador.size(); indice++){
            String ruta = "Imagenes/" + "Carta_Reverso/" + "carta_reverso.png";
			ImageIcon imagen = new ImageIcon(this.getClass().getResource(ruta));
            JToggleButton boton = this.componenteCartasJugador.get(indice);
            ayudante.generarBotonConImagen(boton, "Carta_Reverso", imagen);
            boton.setEnabled(false);
            ayudante.actualizarPanel(panel);
        }
    }

    public void habilitarCartasJugador(){
        for(int indice = 0; indice < this.cartasJugador.size(); indice++){
            String palo = this.cartasJugador.get(indice).obtenerPalo();
            Integer valor = this.cartasJugador.get(indice).obtenerValor();
            String ruta = "Imagenes/" + palo + "/" + valor.toString() + "-" + palo + ".jpg";
            ImageIcon imagen = new ImageIcon(this.getClass().getResource(ruta));
            JToggleButton boton = this.componenteCartasJugador.get(indice);
            ayudante.generarBotonConImagen(boton, palo+"-"+valor, imagen);
            boton.setEnabled(true);
            ayudante.actualizarPanel(panel);
        }
    }
}
