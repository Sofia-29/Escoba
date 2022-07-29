package Vista;

import java.awt.BorderLayout;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToggleButton;
import javax.swing.Icon;
import java.awt.Image;
import java.awt.FlowLayout;
import Modelo.Carta;


public class MesaVista extends JFrame {

	private JPanel panelMesa;
	private JPanel panelCartasMesa;
	private JPanel panelMazoComun;
	private JPanel panelCartasDescartadas;
	private JPanel panelEtiquetas;
	private JLabel turnoJugador;
	private JLabel puntajeJugador;
	public JToggleButton reglasJuego;
	private GestorEventos gestorEventos;
	private ArrayList<JLabel> cartasEnMesa;
	private JugadorVista jugadorUno;
	private JugadorVista jugadorDos;
	private JugadorVista JugadorActual;
	private General ayudante;


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

	public void actualizarCartasJugadorUno(ArrayList<Carta> cartas){
		jugadorUno.actualizarCartasJugador(cartas);
	}

	public void actualizarCartasJugadorDos(ArrayList<Carta> cartas){
		jugadorDos.actualizarCartasJugador(cartas);
	}

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

	public void actualizarCartasEnMesa(ArrayList<Carta> cartasEnMesa){
		this.cartasEnMesa = new ArrayList<JLabel>();
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

	public void iniciarBotonDescartarCartaJugadores(){
		jugadorUno.iniciarBotonDescartarCarta();
		jugadorDos.iniciarBotonDescartarCarta();
		JPanel panelSur = jugadorDos.obtenerPanel();
		panelSur.add(jugadorDos.obtenerBotonDescartarCarta());
		JPanel panelNorte = jugadorUno.obtenerPanel();
		panelNorte.add(jugadorUno.obtenerBotonDescartarCarta());
	}

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

	public void preguntarInformacionJugadorDos(){
		jugadorDos.preguntarNombreJugador();
	}


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
		this.panelMesa.revalidate();
    	this.panelMesa.repaint();
	}

	public void deshabilitarCartasJugadores(){
		jugadorUno.deshabilitarCartasJugador();
		jugadorDos.deshabilitarCartasJugador();
	}

	public String obtenerCartaDescartada(){
		return JugadorActual.obtenerCartaDescartada();
	}

	private void inicializarEtiquetas(){
		turnoJugador = ayudante.generarEtiqueta("Turno");
		panelEtiquetas.add(turnoJugador, BorderLayout.NORTH);
		puntajeJugador = ayudante.generarEtiqueta("Turno");
		panelEtiquetas.add(puntajeJugador);
	}

	private void actualizarEtiquetaTurnoJugador(String nombreJugador){
		turnoJugador.setText("Turno de " +nombreJugador);
		turnoJugador.setVisible(true);
		ayudante.actualizarPanel(panelMesa);
	}
	
	public void actualizarEtiquetaPuntajeJugador(String puntaje){
		puntajeJugador.setText("Puntaje: " +puntaje);
		puntajeJugador.setVisible(true);
		ayudante.actualizarPanel(panelMesa);
	}

	private void inicializarReglas(){
		reglasJuego = new JToggleButton();
		String ruta = "Imagenes/Reglas/reglas.png";
        ImageIcon imagen = new ImageIcon(this.getClass().getResource(ruta));
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(130,100,
        Image.SCALE_DEFAULT));
        reglasJuego.setBorder(new EmptyBorder(0,0,0,0));
        reglasJuego.setBackground(new java.awt.Color(28, 84, 45));
        reglasJuego.setIcon(icono);
        reglasJuego.setForeground(new java.awt.Color(28, 84, 45));
		panelEtiquetas.add(reglasJuego, BorderLayout.LINE_END);
	}

	public void botonReglas(){
		reglasJuego.setVisible(true);
		ayudante.actualizarPanel(panelMesa);
		gestorEventos.accionMostrarReglas(this);
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
            "- Para cualquier jugador, por cada carta del palo de Oros capturada se le otorga un punto.\n"+ 
            "- Para cualquier jugador que tome el 7 del palo de Oros se le otorga un punto. \n"+
            "- Para cualquier jugador, por cada carta de valor 7 capturada se le otorga un punto.\n"+
            "- El jugador que tenga más 7s gana un punto. Si las cartas quedan 2-2 no se otorga ningún punto\n"+
            "- El jugador que tenga mayoría de cartas del palo de monedas se otorga un punto a ese jugador. Si las cartas quedan 5-5 no se otorga ningún punto.\n"+

            "Elegir un ganador: \n"+
            "- El jugador que sume más puntos al final de la partida es declarado como ganador.";

        return juegoReglas;
    }
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		MesaVista frame = new MesaVista();

		Carta carta1 = new Carta(1, "Bastos", "");
		Carta carta2 = new Carta(2, "Copas", "");
		Carta carta3 = new Carta(3, "Oros", "");
		Carta carta4 = new Carta(4, "Espadas", "");
		ArrayList<Carta> cartas = new ArrayList<Carta>();
		cartas.add(carta1);
		cartas.add(carta2);
		cartas.add(carta3);
		cartas.add(carta4);
		frame.actualizarCartasEnMesa(cartas);
		frame.inicializarJugadores();
		frame.actualizarCartasJugadorUno(cartas);
		frame.actualizarCartasJugadorDos(cartas);
		frame.deshabilitarCartasJugadores();
		frame.inicializarMazoComun();
		frame.inicializarMazoCartasDescartadas();
		frame.setVisible(true);
		frame.preguntarInformacionJugadorUno();
		frame.preguntarInformacionJugadorDos();
		frame.actualizarEtiquetaPuntajeJugador("12");
		//frame.setVisible(true);
		frame.iniciarBotonDescartarCartaJugadores();

		String carta = "-1";
		while(carta.equals("-1")){
			carta = frame.obtenerCartaDescartada();
		}
		System.out.println(carta);

		frame.cambiarTurnoJugador();
	}
	//frame.actualizarEtiquetaTurnoJugador();
}
