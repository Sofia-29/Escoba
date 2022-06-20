package Vista;

import Modelo.Naipe;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.BorderLayout;

import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Ventana extends JFrame {
    private ArrayList<JLabel> cartasEnMesa;
    private ArrayList<JLabel> cartasJugador;
    private JPanel panelPrincipal;

    public Ventana(int ancho, int altura, String titulo){
        setSize(ancho, altura);
        setTitle(titulo);
        setLayout(new BorderLayout());
        iniciarComponentes();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(panelPrincipal);
    }

    public void hacerVisible(){
        // for(int indice = 0; indice < cartasJugador.size(); indice++){
        //     JLabel jlabel = cartasJugador.get(indice);
        //     jlabel.setEnabled(true);
        // }
        setVisible(true);
    }

    private void iniciarComponentes(){
        cartasEnMesa = new ArrayList<JLabel>(4);
        cartasJugador = new ArrayList<JLabel>(3);  
        actualizarComponenteJLabel(4, "CartasEnMesa");
        actualizarComponenteJLabel(3, "Jugador");
        panelPrincipal = generarPanel(4);
    }

    private void actualizarComponenteJLabel(int cantidadCartas, String vista){
        ArrayList<JLabel> auxiliar;
        if(vista == "Jugador"){
            auxiliar = cartasJugador;
        }else{
            auxiliar = cartasEnMesa;
        }
        if(auxiliar.size() < cantidadCartas){
            for(int indice = 0; indice < cantidadCartas; indice++){
                JLabel jlabel = new JLabel();
                jlabel.setSize(144, 200);
                jlabel.setEnabled(true);
                auxiliar.add(jlabel);
            }
        }else{
            for(int indice = cantidadCartas; indice < auxiliar.size(); indice++){
                auxiliar.get(indice).setEnabled(false);;
            }
        }
    }

    public void actualizarComponentesCartasJugador(ArrayList<Naipe> cartas){
        actualizarComponenteJLabel(cartas.size(), "Jugador");
        JPanel panelCartasJugador = generarPanel(1);
        for(int indice = 0; indice < cartas.size(); indice++){
                String palo = cartas.get(indice).obtenerPalo();
                Integer valor = cartas.get(indice).obtenerValor();
                String ruta = "Imagenes\\" + palo + "\\" + valor.toString() + "-" + palo + ".jpg";
                JLabel label = cartasJugador.get(indice);
                ImageIcon imagen = new ImageIcon(this.getClass().getResource(ruta));
                Icon icon = new ImageIcon(imagen.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
                label.setIcon(icon);
                panelCartasJugador.add(label);
        }
        add(panelCartasJugador, BorderLayout.SOUTH);
    }

    private JPanel generarPanel(int alineacion){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(alineacion));
        panel.setBackground(new java.awt.Color(28, 84, 45));
        return panel; 
    }
}
