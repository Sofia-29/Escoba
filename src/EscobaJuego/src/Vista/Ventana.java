package Vista;

import Modelo.Naipe;
import java.util.StringTokenizer;


import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
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


public class Ventana<Dimension> extends JFrame {
    private ArrayList<JToggleButton> cartasJugador;
    private ArrayList<JLabel> cartasMesa;
    private ArrayList<JLabel> cartasCapturadas;
    private ButtonGroup cartasJugadorGrupo;
    private JPanel panelPrincipal;
    private JPanel panelCartasJugador;
    private JPanel panelCartasMesa;
    private JPanel panelCartasCapturadas;
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
        add(panelCartasCapturadas, BorderLayout.NORTH);
        add(panelCartasMesa, BorderLayout.CENTER);

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
        cartasMesa = new ArrayList<JLabel>(4);
        cartasCapturadas = new ArrayList<JLabel>(4);
        cartasJugador = new ArrayList<JToggleButton>(3);
        cartasJugadorGrupo = new ButtonGroup();
        panelCartasJugador = generarPanel(1);
        panelCartasCapturadas = generarPanel(1);
        panelCartasMesa = generarPanel(1);
        panelPrincipal = generarPanel(4);
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
                panelCartasJugador.revalidate();
                panelCartasJugador.repaint();
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
        limpiarComponente(cartasMesa);
        actualizarComponente(cartasMesa, panelCartasMesa, cartas);
    }

    public void limpiarComponente(ArrayList<JLabel> componente){
        int indiceComponente = componente.size() - 1;
        while(indiceComponente >= 0){
            JLabel etiqueta = componente.get(indiceComponente);
            etiqueta.setVisible(false);
            componente.remove(indiceComponente);
            indiceComponente--;
        }
    }

    public void actualizarComponente(ArrayList<JLabel> etiquetas, JPanel componente, ArrayList<Naipe> cartas){
        for(int indice = 0; indice < cartas.size(); indice++){
            String palo = cartas.get(indice).obtenerPalo();
            Integer valor = cartas.get(indice).obtenerValor();
            String ruta = "Imagenes\\" + palo + "\\" + valor.toString() + "-" + palo + ".jpg";
            JLabel nuevaEtiqueta = new JLabel();
            nuevaEtiqueta.setName(valor+"-"+palo);
            nuevaEtiqueta.setBorder(new EmptyBorder(300,10,10,10));
            nuevaEtiqueta.setSize(144,200);
            ImageIcon imagen = new ImageIcon(this.getClass().getResource(ruta));
            Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(nuevaEtiqueta.getWidth(), nuevaEtiqueta.getHeight(),
             Image.SCALE_DEFAULT));
            nuevaEtiqueta.setIcon(icono);
            nuevaEtiqueta.setEnabled(true);
            etiquetas.add(nuevaEtiqueta);
            nuevaEtiqueta.setVisible(true);
            componente.add(nuevaEtiqueta, BorderLayout.CENTER);
            componente.revalidate();
            componente.repaint();
        }
    }

    public void actualizarComponentesCartasCapturadas(ArrayList<Naipe> cartas, boolean escoba){
        panelCartasCapturadas.setVisible(true);
        JLabel nuevaEtiqueta = generarEtiquetaCartasCapturadas(escoba);
        etiquetaTurnoJugador.setVisible(false);
        cartasCapturadas.add(nuevaEtiqueta);
        panelCartasCapturadas.add(nuevaEtiqueta, BorderLayout.CENTER);
        actualizarComponente(cartasCapturadas, panelCartasCapturadas, cartas);
    }

    public JLabel generarEtiquetaCartasCapturadas(boolean escoba){
        JLabel nuevaEtiqueta = new JLabel();
        nuevaEtiqueta.setBorder(new EmptyBorder(10,10,10,10));
        nuevaEtiqueta.setSize(100, 100);
        nuevaEtiqueta.setEnabled(true);
        nuevaEtiqueta.setVisible(true);
        nuevaEtiqueta.setFont(new Font("Arial", Font.PLAIN, 24));
        nuevaEtiqueta.setForeground(Color.WHITE);
        if (escoba){
            nuevaEtiqueta.setText("Escoba en el " + etiquetaTurnoJugador.getText());
            nuevaEtiqueta.setName("Escoba");
        }else{
            nuevaEtiqueta.setText("Captura en el " + etiquetaTurnoJugador.getText());
            nuevaEtiqueta.setName("Captura");
        }
        return nuevaEtiqueta;
    }

    public void limpiarComponeneteCartasCapturadas(){
        limpiarComponente(cartasCapturadas);
        panelCartasCapturadas.setVisible(false);
        etiquetaTurnoJugador.setVisible(true);
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
                    habilitarCartasJugador(false);
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
