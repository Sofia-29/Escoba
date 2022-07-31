package Vista;
import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Modelo.Carta;
import Modelo.Mesa;
import Modelo.Serializador;

import javax.swing.JToggleButton;
import javax.swing.Icon;
import java.awt.Image;

public abstract class MesaVista extends JFrame {
	private JPanel panelMesa;
	private JPanel panelCartasMesa;
	private JPanel panelMazoComun;
	private JPanel panelCartasDescartadas;
	private JPanel panelEtiquetas;
	private JLabel turnoJugador;
	private JLabel puntajeJugador;
	public JToggleButton reglasJuego;
	public JToggleButton botonGuardar;
	private GestorEventos gestorEventos;
	private ArrayList<JLabel> cartasEnMesa;
	private JugadorVista jugadorUno;
	private JugadorVista jugadorDos;
	private JugadorVista JugadorActual;
	protected General ayudante;
	private Mesa mesaConcreta;
	private Serializador serializador;


	public MesaVista() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 744, 500);
		panelMesa = new JPanel();
		panelMesa.setBackground(new java.awt.Color(28, 84, 45));
		panelMesa.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelMesa);
		panelMesa.setLayout(new BorderLayout(0, 0));
		ayudante = new General();
		gestorEventos = new GestorEventos();
		cartasEnMesa = new ArrayList<JLabel>();
		panelCartasMesa = ayudante.generarPanel();
		panelMesa.add(panelCartasMesa, BorderLayout.CENTER);
		panelEtiquetas= ayudante.generarPanel();
		panelEtiquetas.setSize(200,200);
		inicializarEtiquetas();
		panelMesa.add(panelEtiquetas,BorderLayout.WEST);
		inicializarReglas();
		botonReglas();
	}

	/**
	 * Se inicializa el mazo comun para la vista
	 */
	public void inicializarMazoComun(){
		this.panelMazoComun = ayudante.generarPanel();
		panelMazoComun.setLayout(new BorderLayout(0, 0));
		String ruta = "Imagenes/Carta_Reverso/grupo_cartas.png";
		ImageIcon imagen = new ImageIcon(this.getClass().getResource(ruta));
		JLabel etiqueta = ayudante.generarEtiquetaConImagen("Grupo de cartas", imagen);
		etiqueta.setBorder(null);
		panelMazoComun.add(etiqueta,BorderLayout.CENTER);
		panelMesa.add(panelMazoComun, BorderLayout.EAST);
	}

	/**
	 * se inicializan los jugadores para la vista 
	 */
	public void inicializarJugadores(){
		jugadorUno = new JugadorVista();
		jugadorDos = new JugadorVista();
		JugadorActual = new JugadorVista();
		JPanel panelCartasJugadorUno = ayudante.generarPanel();
		JPanel panelCartasJugadorDos = ayudante.generarPanel();
		jugadorUno.asignarPanel(panelCartasJugadorUno);
		jugadorDos.asignarPanel(panelCartasJugadorDos);
		//actualizarEtiquetaTurnoJugador();
		panelMesa.add(panelCartasJugadorUno, BorderLayout.NORTH);
		panelMesa.add(panelCartasJugadorDos, BorderLayout.SOUTH);
	}

	/**
	 * @param cartas que posee el jugador uno son actualizadas 
	 */
	public void actualizarCartasJugadorUno(ArrayList<Carta> cartas){
		jugadorUno.actualizarCartasJugador(cartas);
	}

	/**
	 * @param cartas que posee el jugador dos son actualizadas
	 */
	public void actualizarCartasJugadorDos(ArrayList<Carta> cartas){
		jugadorDos.actualizarCartasJugador(cartas);
	}

	/**
	 * se inicializa el mazo de cartas descartadas para la vista
	 */
	public void inicializarMazoCartasDescartadas(){
		this.panelCartasDescartadas = ayudante.generarPanel();
		panelCartasDescartadas.setLayout(new BorderLayout(0, 0));
		String ruta = "Imagenes/Carta_Reverso/grupo_cartas.png";
		ImageIcon imagen = new ImageIcon(this.getClass().getResource(ruta));
		JLabel etiqueta = ayudante.generarEtiquetaConImagen("Grupo de cartas", imagen);
		etiqueta.setBorder(null);
		panelCartasDescartadas.add(etiqueta,BorderLayout.CENTER);
		panelMesa.add(panelCartasDescartadas, BorderLayout.WEST);
	}

	/**
	 * @param cartasEnMesa que son actualizas en la vista
	 */
	public void actualizarCartasEnMesa(ArrayList<Carta> cartasEnMesa){
		ayudante.limpiarComponenteJLabel(this.cartasEnMesa, this.panelCartasMesa);
		for(int indice = 0; indice < cartasEnMesa.size(); indice++){
            String palo = cartasEnMesa.get(indice).obtenerPalo();
            Integer valor = cartasEnMesa.get(indice).obtenerValor();
            String ruta = "Imagenes/" + palo + "/" + valor.toString() + "-" + palo + ".jpg";
			ImageIcon imagen = new ImageIcon(this.getClass().getResource(ruta));
            JLabel etiqueta = ayudante.generarEtiquetaConImagen(valor+"-"+palo, imagen);
            this.cartasEnMesa.add(etiqueta);
            this.panelCartasMesa.add(etiqueta);
            ayudante.actualizarPanel(this.panelMesa);
        }
	}

	/**
	 * @param cartas que se van a mostrar en la captura
	 */
	public void mostrarCaptura(ArrayList<Carta> cartas){
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
        JOptionPane.showMessageDialog(null,panel,"Captura",JOptionPane.INFORMATION_MESSAGE);
    }

	/**
	 * se inicializa el boton de los jugadores para descartar una carta 
	 */
	public void iniciarBotonDescartarCartaJugadores(){
		jugadorUno.iniciarBotonDescartarCarta();
		jugadorDos.iniciarBotonDescartarCarta();
		JPanel panelSur = jugadorDos.obtenerPanel();
		panelSur.add(jugadorDos.obtenerBotonDescartarCarta());
		JPanel panelNorte = jugadorUno.obtenerPanel();
		panelNorte.add(jugadorUno.obtenerBotonDescartarCarta());
	}

	/**
	 * se pregunta por el nombre del jugador uno y si este desea ser el primer jugador
	 */
	public void preguntarInformacionJugadorUno(){
		jugadorUno.preguntarNombreJugador();
		int opcion = jugadorUno.preguntarTurno();

		//Asignacion del turno segun lo que elija el jugador uno
		if(opcion == 0){
			JugadorActual.asignarNombre("");
		}else{
			JugadorActual.asignarNombre(jugadorUno.obtenerNombreJugador());
		}
		cambiarTurnoJugador();
	}

	/**
	 * se pregunta el nombre del jugador dos
	 */
	public void preguntarInformacionJugadorDos(){
		jugadorDos.preguntarNombreJugador();
	}

	/**
	 *  se actualiza la vista en la mesa al cambiar el turno de jugador
	 */
	public void cambiarTurnoJugador(){

		if(JugadorActual.obtenerNombreJugador().equals(jugadorUno.obtenerNombreJugador())){
			JugadorActual = jugadorDos;
			jugadorUno.deshabilitarCartasJugador();
		}else{
			JugadorActual = jugadorUno;
			jugadorDos.deshabilitarCartasJugador();
		}
		JugadorActual.habilitarCartasJugador();
		turnoJugador.setText("Turno de "+JugadorActual.obtenerNombreJugador());
		actualizarEtiquetaTurnoJugador(JugadorActual.obtenerNombreJugador());
		ayudante.actualizarPanel(this.panelMesa);
	}

	/**
	 *  se deshabilitan las cartas de los jugadores 
	 */
	public void deshabilitarCartasJugadores(){
		jugadorUno.deshabilitarCartasJugador();
		jugadorDos.deshabilitarCartasJugador();
	}

	/**
	 * @return el resultado true/false dependiendo de la respuesta del usuario sobre si desea cargar la partida
	 */
	public boolean preguntarCargarPartida(){
		boolean resultado = false;
		String mensaje="¿Desea cargar una partida?";
        int respuesta = JOptionPane.showConfirmDialog(null, mensaje, "Cargar partida", JOptionPane.YES_NO_OPTION);
		if(respuesta != 1){
			resultado = true;
		}
		return resultado;
	}

	/**
	 * @return la ruta elegiga por el usuario donde guardó la partida
	 */
	public String guardarPartida(){
		JOptionPane.showMessageDialog(null, "Ingrese la ruta y nombre del archivo a guardar");
		File rutaElegida = elegirArchivo();
		return rutaElegida.getPath();
	}

	/**
	 * @return el archivo que el usuario escogió para guardar la partida
	 */
	public File elegirArchivo(){
        JFileChooser selectorDeArchivo = new JFileChooser();
        int respuesta = selectorDeArchivo.showOpenDialog(null);
        
        if(respuesta == JFileChooser.APPROVE_OPTION){
            File file  = new File(selectorDeArchivo.getSelectedFile().getAbsolutePath());
            return file;
        }else{
            System.out.println("error al cargar el archivo");
            return null;
        }
    }

	/**
	 * @return la carta descartada del jugador actual
	 */
	public String obtenerCartaDescartada(){
		return JugadorActual.obtenerCartaDescartada();
	}

	/**
	 * @param nombre que se le asigna al jugador uno
	 */
	public void asignarInformacionJugadorUno(String nombre){
		jugadorUno.asignarNombre(nombre);
	}

	/**
	 * @param nombre que se le asigna al jugador dos
	 */
	public void asignarInformacionJugadorDos(String nombre){
		jugadorDos.asignarNombre(nombre);
	}

	/**
	 * @return el nombre del jugador uno
	 */
	public String obtenerNombreJugadorUno(){
		return jugadorUno.obtenerNombreJugador();
	}

	/**
	 * @return el nombre del jugador dos
	 */
	public String obtenerNombreJugadorDos(){
		return jugadorDos.obtenerNombreJugador();
	}

	/**
	 * se inicializan las etiquetas del turno y del puntaje
	 */
	private void inicializarEtiquetas(){
		turnoJugador = ayudante.generarEtiqueta("Turno");
		panelEtiquetas.add(turnoJugador, BorderLayout.NORTH);
		puntajeJugador = ayudante.generarEtiqueta("Turno");
		panelEtiquetas.add(puntajeJugador);
	}

	/**
	 * @param nombreJugador para la etiqueta del turno
	 */
	public void actualizarEtiquetaTurnoJugador(String nombreJugador){
		turnoJugador.setText("Turno de " +nombreJugador);
		turnoJugador.setVisible(true);
		ayudante.actualizarPanel(panelMesa);
	}
	
	/**
	 * @param puntaje para la etiqueta del puntaje
	 */
	public void actualizarEtiquetaPuntajeJugador(int puntaje){
		String texto = "Puntaje: " + Integer.toString(puntaje);
		puntajeJugador.setText(texto);
		puntajeJugador.setVisible(true);
		ayudante.actualizarPanel(panelMesa);
	}

	/**
	 * se inicializa el boton de las reglas del juego
	 */
	private void inicializarReglas(){
		reglasJuego = new JToggleButton();
		botonGuardar = new JToggleButton();
		String ruta = "Imagenes/Reglas/reglas.png";
        ImageIcon imagen = new ImageIcon(this.getClass().getResource(ruta));
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(130,100,
        Image.SCALE_DEFAULT));
        reglasJuego.setBorder(new EmptyBorder(0,0,0,0));
        reglasJuego.setBackground(new java.awt.Color(28, 84, 45));
        reglasJuego.setIcon(icono);
        reglasJuego.setForeground(new java.awt.Color(28, 84, 45));
		botonGuardar.setText("Guardar");
		panelEtiquetas.add(reglasJuego, BorderLayout.LINE_END);
		panelEtiquetas.add(botonGuardar, BorderLayout.LINE_START);
	}

	/**
	 * boton para que se muestren las reglas 
	 */
	public void botonReglas(){
		reglasJuego.setVisible(true);
		ayudante.actualizarPanel(panelMesa);
		gestorEventos.accionMostrarReglas(this);
	}

	/**
	 * boton para guardar la partida 
	 */
	public void botonGuardar(){
		botonGuardar.setVisible(true);
		ayudante.actualizarPanel(panelMesa);
		gestorEventos.accionGuardar(this, mesaConcreta, serializador);
	}

	/**
	 * @param mesaConcreta que se guarda
	 * @param serializador que guarda la mesa
	 */
	public void asignarMesa(Mesa mesaConcreta,  Serializador serializador){
		this.mesaConcreta = mesaConcreta;
		this.serializador = serializador;
		botonGuardar();
	}

	/**
	 * @return el nombre del jugador actual
	 */
	public String obtenerNombreJugadorActual(){
		return JugadorActual.obtenerNombreJugador();
	}

	/**
	 * se habilitan las cartas del jugador actual para que se muestren en la vista 
	 */
	public void mostrarCartasJugadorActual(){
		JugadorActual.habilitarCartasJugador();
	}

	protected abstract String reglasJuego();

	/**
	 * @param mensaje para mostrar quién ganó el juego 
	 */
	public void finalizarJuego(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }

	public void asignarJugadorActual(String nombre){
		if(nombre.equals(jugadorUno.obtenerNombreJugador())){
			JugadorActual = jugadorUno;
			jugadorDos.deshabilitarCartasJugador();
		}else{
			JugadorActual = jugadorDos;
			jugadorUno.deshabilitarCartasJugador();
		}
		JugadorActual.habilitarCartasJugador();
    }
	
	/**
	 * se actualiza el panel de las etiquetas
	 */
	public void refrescarPanelEtiquetas(){
		ayudante.actualizarPanel(panelEtiquetas);
	}


}