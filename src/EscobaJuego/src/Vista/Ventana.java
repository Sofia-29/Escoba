package Vista;

import Modelo.Naipe;
import java.util.StringTokenizer;

import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;


public class Ventana extends JFrame {
    private ArrayList<JLabel> cartasEnMesa;
    private ArrayList<JToggleButton> cartasJugador;
    private ButtonGroup cartasJugadorGrupo;
    private JPanel panelPrincipal;
    private JButton descartar;
    private String palo; 
    private String valor;

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

    public void asignarNaipe(String datosNaipe){
        StringTokenizer tokenizador = new StringTokenizer(datosNaipe, "-");
        this.palo = tokenizador.nextToken();
        this.valor = tokenizador.nextToken();
    }

    private String[] obtenerNaipe(){
        String[] naipe = new String[2];
        naipe[0] = this.palo;
        naipe[1] = this.valor;
        return naipe;
     }

    private void iniciarComponentes(){

        cartasEnMesa = new ArrayList<JLabel>(4);
        cartasJugador = new ArrayList<JToggleButton>(3);  
        cartasJugadorGrupo = new ButtonGroup();
        actualizarComponenteJButtonJugador(3);
        panelPrincipal = generarPanel(4);
        descartar = new JButton("Descartar");
        descartar.setSize(100,100);
        descartar.setEnabled(false);
        accionDescarta();
    }

    private void actualizarComponenteJButtonJugador(int cantidadCartas){
        if(cartasJugador.size() < cantidadCartas){
            for(int indice = 0; indice < cantidadCartas; indice++){
                JToggleButton jButton = new JToggleButton();
                jButton.setBackground(new java.awt.Color(28, 84, 45));
                jButton.setSize(144, 200);
                jButton.setVisible(true);
                cartasJugador.add(jButton);
            }
        }else{
            for(int indice = cantidadCartas; indice < cartasJugador.size(); indice++){
                cartasJugador.get(indice).setVisible(false);
            }
        }
    }

    public void actualizarComponentesCartasJugador(ArrayList<Naipe> cartas){
        actualizarComponenteJButtonJugador(cartas.size());
        JPanel panelCartasJugador = generarPanel(1);
        for(int indice = 0; indice < cartas.size(); indice++){
                String palo = cartas.get(indice).obtenerPalo();
                Integer valor = cartas.get(indice).obtenerValor();
                String ruta = "Imagenes\\" + palo + "\\" + valor.toString() + "-" + palo + ".jpg";
                JToggleButton boton = cartasJugador.get(indice);
                ImageIcon imagen = new ImageIcon(this.getClass().getResource(ruta));
                Icon icon = new ImageIcon(imagen.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_DEFAULT));
                boton.setName(valor+"-"+palo);
                boton.setIcon(icon);
                boton.setBorder(new EmptyBorder(10,10,10,10));
                cartasJugadorGrupo.add(boton);
                accionSeleccionarCarta(boton);
                panelCartasJugador.add(boton, BorderLayout.CENTER);
        }
        panelCartasJugador.add(descartar);
        add(panelCartasJugador, BorderLayout.SOUTH);
    }

    private JPanel generarPanel(int alineacion){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(alineacion));
        panel.setBackground(new java.awt.Color(28, 84, 45));
        return panel; 
    }

    private void accionSeleccionarCarta(JToggleButton boton){
        ActionListener accion = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent event){
                    if(boton.isSelected()){
                        cartasJugadorGrupo.setSelected(boton.getModel(), true);
                        descartar.setEnabled(true);
                    }
                }
        };
        boton.addActionListener(accion);
    }

    private void accionDescarta(){
        ActionListener accion = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent event){
                    ButtonModel modeloBoton = cartasJugadorGrupo.getSelection();
                    JToggleButton boton = obtenerBoton(modeloBoton);
                    asignarNaipe(boton.getName());
                    cartasJugadorGrupo.clearSelection();
                    descartar.setEnabled(false);
                }
        };
        descartar.addActionListener(accion);
    }

    private JToggleButton obtenerBoton(ButtonModel modeloBoton){
        JToggleButton boton = null;
        int indice = 0;
        while(true){
            boton = cartasJugador.get(indice);
            if(boton.isEnabled()){
                if(modeloBoton.equals(boton.getModel())){
                    break;
                }
            }
            indice++;
        }
        return boton;
    }
}
