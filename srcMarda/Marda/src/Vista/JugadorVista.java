package Vista;
import java.util.ArrayList;

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

    JugadorVista(){
        componenteCartasJugador = new ArrayList<JToggleButton>();
        cartasJugador = new ArrayList<Carta>();
        ayudante = new General();
        gestorEventos = new GestorEventos();
        grupoCartasJugador = new ButtonGroup();
        botonDescartarCarta = new JButton("Descartar");
        cartaSeleccionada = "-1";
    }

    /**
     * @param panel asigna el panel del jugador
     */
    public void asignarPanel(JPanel panel){
        this.panel = panel;
    }

    /**
     * @param nombre del jugador
     */
    public void asignarNombre(String nombre){
        this.nombre = nombre;
    }

    /**
     * @param carta descartada
     */
    public void asignarCartaDescartada(String carta){
        this.cartaSeleccionada = carta;
    }

    /**
     * @return la carta descartada
     */
    public String obtenerCartaDescartada(){
        String carta = this.cartaSeleccionada;
        if(!carta.equals("-1")){
            asignarCartaDescartada("-1");
        }
        return carta;
    }

    /**
     * @return el nombre del jugador
     */
    public String obtenerNombreJugador(){
        return this.nombre;
    }

    /**
     * @return el panel del jugador
     */
    public JPanel obtenerPanel(){
        return this.panel;
    }

    /**
     * @return las cartas del jugador
     */
    public ArrayList<Carta> obtenerCartasJugador(){
        return this.cartasJugador;
    }

    /**
     * @return el ArrayList de botones de las cartas del jugador
     */
    public ArrayList<JToggleButton> obtenerBotonesCartasJugador(){
        return this.componenteCartasJugador;
    }

    /**
     * @return los grupos de cartas del jugador 
     */
    public ButtonGroup obtenerGrupoCartasJugador(){
        return this.grupoCartasJugador;
    }

    /**
     * @return el boton de descartar carta
     */
    public JButton obtenerBotonDescartarCarta(){
        return this.botonDescartarCarta;
    }

    /**
     * pregunta el nombre del jugador 
     */
    public void preguntarNombreJugador(){
        String mensaje = "Ingrese el nombre del jugador: ";
        this.asignarNombre(JOptionPane.showInputDialog(mensaje,""));
    }

    /**
     * @return pregunta el turno del jugador
     */
    public int preguntarTurno(){
        String mensaje="¿Desea ser el primer jugador?";
        int respuesta = JOptionPane.showConfirmDialog(null, mensaje, "Escoger turno", JOptionPane.YES_NO_OPTION);
        return respuesta;
    }

    /**
     * Inicializa el boton de descartar carta
     */
    public void iniciarBotonDescartarCarta(){
        botonDescartarCarta.setSize(100,100);
        botonDescartarCarta.setEnabled(false);
        gestorEventos.eventoDescartarCarta(this);
    }

    /**
     * @param cartas actualiza las cartas del jugador
     */
    public void actualizarCartasJugador(ArrayList<Carta> cartas){
        this.cartasJugador = cartas;
        ayudante.limpiarComponenteBotones(componenteCartasJugador, grupoCartasJugador, panel);
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



    /**
     * deshabilita las cartas del jugador
     */
    public void deshabilitarCartasJugador(){
        for(int indice = 0; indice < this.componenteCartasJugador.size(); indice++){
            String ruta = "Imagenes/" + "Carta_Reverso/" + "carta_reverso.png";
			ImageIcon imagen = new ImageIcon(this.getClass().getResource(ruta));
            JToggleButton boton = this.componenteCartasJugador.get(indice);
            ayudante.generarBotonConImagen(boton, "Carta_Reverso", imagen);
            boton.setEnabled(false);
            ayudante.actualizarPanel(panel);
        }
    }

    /**
     * habilita las cartas del jugador
     */
    public void habilitarCartasJugador(){
        for(int indice = 0; indice < this.componenteCartasJugador.size(); indice++){
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

    /**
     * @param cartas que el jugador capturo
     * @param escoba si el usuario hizo escoba o no 
     */
    public void mostrarCaptura(ArrayList<Carta> cartas, boolean escoba){
        JPanel panel = new JPanel();
		ArrayList<JLabel> cartasCapturadas = new ArrayList<JLabel>();
		for(int indice = 0; indice < cartas.size(); indice++){
            String palo = cartas.get(indice).obtenerPalo();
            Integer valor = cartas.get(indice).obtenerValor();
            String ruta = "Imagenes/" + palo + "/" + valor.toString() + "-" + palo + ".jpg";
			ImageIcon imagen = new ImageIcon(this.getClass().getResource(ruta));
            JLabel etiqueta = ayudante.generarEtiquetaConImagen(valor+"-"+palo, imagen);
            cartasCapturadas.add(etiqueta);
        }
		for(int indice = 0; indice < cartasCapturadas.size(); indice++){
			panel.add(cartasCapturadas.get(indice));
		}
        if(escoba){
            panel.setBackground(new java.awt.Color(249,214,46));
            JOptionPane.showMessageDialog(null,panel,"Escoba",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null,panel,"Captura",JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
