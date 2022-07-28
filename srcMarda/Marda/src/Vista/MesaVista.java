package Vista;

import java.awt.BorderLayout;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.Carta;


public class MesaVista extends JFrame {

	private JPanel panelMesa;
	private JPanel panelCartasMesa;
	private JPanel panelMazoComun;
	private JPanel panelCartasDescartadas;

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
		cartasEnMesa = new ArrayList<JLabel>();
		panelCartasMesa = ayudante.generarPanel();
		panelMesa.add(panelCartasMesa, BorderLayout.CENTER);
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		MesaVista frame = new MesaVista();

		Carta carta1 = new Carta(1, "Bastos", "Imagenes/Bastos/1-Bastos.jpg");
		Carta carta2 = new Carta(2, "Copas", "Imagenes/Copas/2-Copas.jpg");
		Carta carta3 = new Carta(3, "Oros", "Imagenes/Oros/3-Oros.jpg");
		Carta carta4 = new Carta(4, "Espadas", "Imagenes/Espadas/4-oros.jpg");
		ArrayList<Carta> cartas = new ArrayList<Carta>();
		ArrayList<Carta> cartas1 = new ArrayList<Carta>();
		cartas.add(carta1);
		cartas.add(carta2);
		cartas.add(carta3);
		cartas.add(carta4);
		cartas1.add(carta1);
		cartas1.add(carta2);
		cartas1.add(carta3);
		cartas1.add(carta4);
		frame.actualizarCartasEnMesa(cartas);
		frame.inicializarJugadores();
		frame.actualizarCartasJugadorUno(cartas);
		frame.actualizarCartasJugadorDos(cartas1);
		frame.deshabilitarCartasJugadores();
		frame.inicializarMazoComun();
		frame.inicializarMazoCartasDescartadas();
		frame.setVisible(true);
		frame.preguntarInformacionJugadorUno();
		frame.preguntarInformacionJugadorDos();
		frame.iniciarBotonDescartarCartaJugadores();

		String carta = "-1";
		while(carta.equals("-1")){
			carta = frame.obtenerCartaDescartada();
		}
		System.out.println(carta);

		frame.cambiarTurnoJugador();
	}
}
