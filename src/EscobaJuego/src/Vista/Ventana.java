package Vista;

import Modelo.Naipe;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.awt.BorderLayout;

import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    private JPanel panelEtiquetas;
    private JPanel panelCartasCapturadas;
    private JButton descartar;
    private JButton guardarPartida;
    private String estadoGuardarPartida;
    private String palo; 
    private String valor;
    private JLabel etiquetaTurnoJugador;
    private JToggleButton reglasJuego;
    private JButton bot;

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
        this.estadoGuardarPartida = "-1";
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

    public String retornarEstadoGuardarPartida(){
        return estadoGuardarPartida;
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
        panelEtiquetas = generarPanel(2);
        descartar = new JButton("Descartar");
        descartar.setSize(100,100);
        descartar.setEnabled(false);
        guardarPartida = new JButton("Guardar Partida");
        guardarPartida.setSize(100,100);
        guardarPartida.setEnabled(false);
        accionGuardarPartida();
        accionDescarta();
        botonReglas();
        accionMostrarReglas();
        mostrarBot();
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
                panelCartasJugador.add(guardarPartida);
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
        reglasJuego.setVisible(false);
        bot.setVisible(false);
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
        reglasJuego.setVisible(true);
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

    private void accionGuardarPartida(){
        ActionListener accion = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent event){
                    String ruta = "";
                    String nombreArchivo = "";
                    String mensaje = "Ingrese el nombre de la partida que desea guardar: ";
                    JFileChooser selectorDeArchivo = new JFileChooser();
                    selectorDeArchivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    int respuesta = selectorDeArchivo.showOpenDialog(null);
                    if(respuesta == JFileChooser.APPROVE_OPTION){
                        ruta = selectorDeArchivo.getSelectedFile().getAbsolutePath();
                        nombreArchivo = JOptionPane.showInputDialog(mensaje,"");
                        ruta += "\\" + nombreArchivo + ".json"; 
                        estadoGuardarPartida = ruta;
                        JOptionPane.showMessageDialog(null, "Juego guardado");
                    }else{
                        estadoGuardarPartida = "-1";
                        JOptionPane.showMessageDialog(null, "Selecione un directorio", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                    dispose();
                }
        };
        guardarPartida.addActionListener(accion);
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
                    guardarPartida.setEnabled(false);
                    habilitarCartasJugador(false);
                }
        };
        descartar.addActionListener(accion);
    }

    private void accionMostrarReglas(){
        ActionListener accion = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent event){
                    String reglasJuego = reglasJuego();
                    JOptionPane.showMessageDialog(null,reglasJuego,"REGLAS DEL JUEGO",JOptionPane.INFORMATION_MESSAGE);
                }
        };
        reglasJuego.addActionListener(accion);
    }

    public String reglasJuego(){
        String juegoReglas = "Juego Escoba\n"+
            "Valores de cartas:\n"+
            "- La J (Usualmente, vale 10) vale 8.\n"+
            "- El caballo (Usualmente, vale 11) vale 9.\n"+
            "- El rey (Usualmente, vale 12) vale 10. \n"+
    
            "Partida: \n"+
            "- El turno del jugador consiste en descartar una de sus cartas, si esta suma 15 con las cartas de la mesa entonces puede recoger \n"+
            " dichas cartas, si no hay captura de cartas se deja la carta en la mesa.\n"+
            "- Una vez que todos los jugadores han jugado sus tres cartas, se reparten tres cartas nuevas a cada jugador, y así sucesivamente \n"+
            " hasta que se termine el mazo. Cuando el mazo se termina y los jugadores se quedan sin cartas entonces el juego termina.\n"+
            " - Una escoba pasa cuando un jugador captura todas las cartas de la mesa. Una escoba vale un punto extra para el jugador. \n"+
            
            "Puntos:\n"+
            "- Para cualquier jugador que tenga mayoría de cartas se otorga un punto a ese jugador. Si las cartas quedan 20-20 no se otorga ningún punto.\n"+
            "- Para cualquier jugador que tenga mayoría de cartas del palo de monedas se otorga un punto a ese jugador. Si las cartas quedan 5-5 no se otorga ningún punto.\n"+ 
            "- Para cualquier jugador que tome el 7 del palo de las monedas se le otorga un punto. \n"+
            " - El jugador que tenga más 7s gana un punto.\n"+
            
            "Elegir un ganador: \n"+
            "- El primer jugador en tener 21 o más puntos al final de una mano gana. Si ambos lados llegan a 21 en la misma mano, gana el lado con más puntos.";

            return juegoReglas;
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
    
    public void botonReglas(){
        JPanel panelReglas = generarPanel(2);
        reglasJuego = new JToggleButton();
        String ruta = "Imagenes\\Reglas\\reglas.png";
        ImageIcon imagen = new ImageIcon(this.getClass().getResource(ruta));
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(130,100,
        Image.SCALE_DEFAULT));
        reglasJuego.setBorder(new EmptyBorder(0,0,0,0));
        reglasJuego.setBackground(new java.awt.Color(28, 84, 45));
        reglasJuego.setIcon(icono);
        reglasJuego.setForeground(new java.awt.Color(28, 84, 45));
        panelReglas.add(reglasJuego);
        add(panelReglas,BorderLayout.EAST);
        reglasJuego.setVisible(true);
    }

    public JLabel construirEtiqueta(String text) {
        JLabel etiqueta = new JLabel();
        etiqueta.setText(text);
        etiqueta.setVisible(false);
        etiqueta.setFont(new Font("Arial", Font.PLAIN, 24));
        etiqueta.setOpaque(true);
        etiqueta.setBackground(new java.awt.Color(78, 59, 49));
        return etiqueta;
    }

    public void inicializarEtiquetas(){
        
        etiquetaTurnoJugador = construirEtiqueta("Turno");
        etiquetaTurnoJugador.setForeground(Color.white);
        panelEtiquetas.add(etiquetaTurnoJugador,BorderLayout.LINE_START);
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
            bot.setVisible(true);
            guardarPartida.setEnabled(false);
        } else{
            habilitarCartasJugador(true);
            bot.setVisible(false);
            guardarPartida.setEnabled(true);
        }
    }

    public void mostrarBot(){
        String ruta = "Imagenes\\Bot\\botcito.png";
        ImageIcon imagen = new ImageIcon(this.getClass().getResource(ruta));
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(130,100,
        Image.SCALE_DEFAULT));
        bot = new JButton();
        bot.setBorder(new EmptyBorder(0,0,0,0));
        bot.setBackground(new java.awt.Color(28, 84, 45));
        bot.setIcon(icono);
        bot.setForeground(new java.awt.Color(28, 84, 45));
        panelEtiquetas.add(bot,BorderLayout.CENTER);
        bot.setVisible(false);
    }

}
