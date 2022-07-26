package Vista;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import Modelo.Carta;

public class JugadorVista {
    private ArrayList<JToggleButton> componenteCartasJugador;
    private ArrayList<Carta> cartasJugador;
    private JPanel panel; 
    private String nombre;
    private JLabel puntaje;

    JugadorVista(){
        componenteCartasJugador = new ArrayList<JToggleButton>();
        cartasJugador = new ArrayList<Carta>();
    }

    public JPanel obtenerPanel(){
        return this.panel;
    }

    public void asignarPanel(JPanel panel){
        this.panel = panel;
    }

    public String obtenerNombreJugador(){
        return this.nombre;
    }

    public void preguntarNombreJugador(){
        String mensaje = "Ingrese el nombre del jugador: ";
        this.nombre = JOptionPane.showInputDialog(mensaje,"");
    }

    public static int preguntarTurno(){
        String mensaje="¿Desea ser el primer jugador?";
        int respuesta = JOptionPane.showConfirmDialog(null, mensaje, "Escoger turno", JOptionPane.YES_NO_OPTION);
        return respuesta;
    }

    public void actualizarCartasJugador(ArrayList<Carta> cartas){
        this.cartasJugador = cartas;
		for(int indice = 0; indice < cartas.size(); indice++){
            String palo = cartas.get(indice).obtenerPalo();
            Integer valor = cartas.get(indice).obtenerValor();
            String ruta = "Imagenes/" + palo + "/" + valor.toString() + "-" + palo + ".jpg";
			ImageIcon imagen = new ImageIcon(this.getClass().getResource(ruta));
            JToggleButton boton = new JToggleButton();
            generarBotonConImagen(boton, valor+"-"+palo, imagen);
            this.componenteCartasJugador.add(boton);
            panel.add(boton);
            panel.revalidate();
            panel.repaint();
        }
    }

    public void deshabilitarCartasJugador(){
        for(int indice = 0; indice < this.componenteCartasJugador.size(); indice++){
            String ruta = "Imagenes/" + "Carta_Reverso/" + "carta_reverso.png";
			ImageIcon imagen = new ImageIcon(this.getClass().getResource(ruta));
            JToggleButton boton = this.componenteCartasJugador.get(indice);
            generarBotonConImagen(boton, "Carta_Reverso", imagen);
            boton.setEnabled(false);
            panel.revalidate();
            panel.repaint();
        }
    }

    public void generarBotonConImagen(JToggleButton boton, String nombre, ImageIcon icono){
        boton.setName(nombre);
        boton.setBorder(new EmptyBorder(5,5,5,5));
        boton.setSize(144,200);
        boton.setIcon(icono);
        boton.setEnabled(true);
		boton.setVisible(true);
    }
}
