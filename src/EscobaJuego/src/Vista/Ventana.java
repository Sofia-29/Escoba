package Vista;

import Modelo.Naipe;
import java.util.StringTokenizer;

import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.text.Normalizer.Form;
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


public class Ventana<Dimension> extends JFrame {
    private ArrayList<JToggleButton> cartasJugador;
    private ArrayList<JLabel> cartasMesa;
    private ArrayList<JLabel> etiquetaRobot;
    private ButtonGroup cartasJugadorGrupo;
    private JPanel panelPrincipal;
    private JPanel panelCartasJugador;
    private JPanel panelCartasMesa;
    private JPanel panelRobot;
    private JButton descartar;
    private String palo; 
    private String valor;
    private JLabel etiquetaTurnoJugador;

    public Ventana(int ancho, int altura, String titulo){
        setSize(ancho, altura);
        setTitle(titulo);
        setLayout(new BorderLayout());
        iniciarComponentes();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(panelPrincipal);
        add(panelCartasJugador, BorderLayout.SOUTH);
        add(panelCartasMesa, BorderLayout.CENTER);
        add(panelRobot, BorderLayout.NORTH);

        this.palo = "-1";
        this.valor = "-1";
    }

    public void hacerVisible(){
        setVisible(true);
    }

    private void asignarNaipe(String datosNaipe){
        StringTokenizer tokenizador = new StringTokenizer(datosNaipe, "-");
        this.palo = tokenizador.nextToken();
        this.valor = tokenizador.nextToken();
    }

    public String[] obtenerNaipe(){
        String[] naipe = new String[2];
        naipe[0] = this.valor;
        naipe[1] = this.palo;
        return naipe;
     }

    private void iniciarComponentes(){

        panelCartasMesa = generarPanel(1);
        cartasMesa = new ArrayList<JLabel>(4);  
        cartasJugador = new ArrayList<JToggleButton>(3);  
        etiquetaRobot = new ArrayList<JLabel>(1);  
        cartasJugadorGrupo = new ButtonGroup();
        panelCartasJugador = generarPanel(1);
        panelCartasMesa = generarPanel(1);
        panelPrincipal = generarPanel(4);
        panelRobot = generarPanel(1);
        descartar = new JButton("Descartar");
        descartar.setSize(100,100);
        descartar.setEnabled(false);
        accionDescarta();
    }

    public void actualizarComponentesCartasJugador(ArrayList<Naipe> cartas, int indiceCartas){
        if(indiceCartas != -1){
            JToggleButton boton = cartasJugador.get(indiceCartas);
            boton.setVisible(false);
            cartasJugadorGrupo.remove(boton);
            cartasJugador.remove(indiceCartas);
        } else{
            int cantidadCartas = cartasJugador.size();
            for(int indice = cantidadCartas; indice < cartas.size(); indice++){
                String palo = cartas.get(indice).obtenerPalo();
                Integer valor = cartas.get(indice).obtenerValor();
                String ruta = "Imagenes\\" + palo + "\\" + valor.toString() + "-" + palo + ".jpg";
                JToggleButton boton = new JToggleButton();
                boton.setName(valor+"-"+palo);
                boton.setBorder(new EmptyBorder(10,10,10,10));
                boton.setSize(144,200);
                ImageIcon imagen = new ImageIcon(this.getClass().getResource(ruta));
                Icon icon = new ImageIcon(imagen.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_DEFAULT));
                boton.setIcon(icon);
                boton.setEnabled(true);
                cartasJugadorGrupo.add(boton);
                cartasJugador.add(boton);
                accionSeleccionarCarta(boton);
                panelCartasJugador.add(boton, BorderLayout.CENTER);
            }
            if(cantidadCartas == 0){
                panelCartasJugador.add(descartar);
            }
        }
    }

    private void habilitarCartasJugador(boolean opcion){
        for(int indice = 0; indice < cartasJugador.size(); indice++){
            JToggleButton boton = cartasJugador.get(indice);
            boton.setEnabled(opcion);
        }
    }

    public void actualizarComponentesCartasMesa(ArrayList<Naipe> cartas){
        int indiceCartasMesa = cartasMesa.size() - 1;
        while(indiceCartasMesa >= 0){
            JLabel boton = cartasMesa.get(indiceCartasMesa);
            boton.setVisible(false);
            cartasMesa.remove(indiceCartasMesa);
            indiceCartasMesa--;
        }

        for(int indice = 0; indice < cartas.size(); indice++){
            String palo = cartas.get(indice).obtenerPalo();
            Integer valor = cartas.get(indice).obtenerValor();
            String ruta = "Imagenes\\" + palo + "\\" + valor.toString() + "-" + palo + ".jpg";
            JLabel etiqueta = new JLabel();
            etiqueta.setName(valor+"-"+palo);
            etiqueta.setBorder(new EmptyBorder(300,10,10,10));
            etiqueta.setSize(144,200);
            ImageIcon imagen = new ImageIcon(this.getClass().getResource(ruta));
            Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(etiqueta.getWidth(), etiqueta.getHeight(),
             Image.SCALE_DEFAULT));
            etiqueta.setIcon(icono);
            etiqueta.setEnabled(true);
            cartasMesa.add(etiqueta);
            etiqueta.setVisible(true);
            panelCartasMesa.add(etiqueta, BorderLayout.CENTER);
            panelCartasMesa.revalidate();
            panelCartasMesa.repaint();
        }
    }

    public void iniciarComponenteRobot(){
            String ruta = "Imagenes\\" + "Robot" + "\\" + "robot" + ".png";
            JLabel etiqueta = new JLabel();
            etiqueta.setName("Robot");
            etiqueta.setBorder(new EmptyBorder(10,10,300,10));
            etiqueta.setSize(144,200);
            ImageIcon imagen = new ImageIcon(this.getClass().getResource(ruta));
            Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(etiqueta.getWidth(), etiqueta.getHeight(),
             Image.SCALE_DEFAULT));
            etiqueta.setIcon(icono);
            etiqueta.setEnabled(true);
            etiquetaRobot.add(etiqueta);
            etiqueta.setVisible(true);
            panelRobot.add(etiqueta, BorderLayout.NORTH);
            panelRobot.revalidate();
            panelRobot.repaint();
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
                    int indice = obtenerBoton(modeloBoton);
                    JToggleButton boton = cartasJugador.get(indice);
                    asignarNaipe(boton.getName());
                    actualizarComponentesCartasJugador(null, indice);
                    cartasJugadorGrupo.clearSelection();
                    descartar.setEnabled(false);
                }
        };
        descartar.addActionListener(accion);
    }

    private int obtenerBoton(ButtonModel modeloBoton){
        JToggleButton boton = null;
        int indice = 0;
        while(true){
            boton = cartasJugador.get(indice);
            if(modeloBoton.equals(boton.getModel())){
                break;
            }
            indice++;
        }
        return indice;
    }
   
    //
    public JLabel construirEtiqueta(String text) {
        JLabel etiqueta = new JLabel();
        etiqueta.setText(text);
        etiqueta.setVisible(false);
        etiqueta.setFont(new Font("Arial", Font.PLAIN, 24));
        etiqueta.setOpaque(true);
        etiqueta.setBackground(new java.awt.Color(24, 61, 97));
        return etiqueta;
    }

    public void inicializarEtiquetas(){
        JPanel panelEtiquetas = generarPanel(2);
        etiquetaTurnoJugador = construirEtiqueta("Turno");
        etiquetaTurnoJugador.setForeground(Color.white);
        panelEtiquetas.add(etiquetaTurnoJugador);
        add(panelEtiquetas,BorderLayout.WEST);
        etiquetaTurnoJugador.setVisible(false);
    }

    public void actualizarTurnoJugador(String nombreJugadorActual) {
        etiquetaTurnoJugador.setText("Turno de " +nombreJugadorActual+ " ");
        etiquetaTurnoJugador.setVisible(true);
        String texto = etiquetaTurnoJugador.getText();
        if(texto.equals("Turno de Jugador Maquina ")){
            this.palo = "-1";
            this.valor = "-1";
            habilitarCartasJugador(false);
        } else{
            habilitarCartasJugador(true);
        }
    }

}
