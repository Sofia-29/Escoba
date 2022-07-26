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
	private JPanel mazoComun;

	private ArrayList<JLabel> cartasEnMesa;
	private JugadorVista jugadorUno;
	private JugadorVista jugadorDos;
	private JugadorVista JugadorActual;


	public MesaVista() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 744, 500);
		panelMesa = new JPanel();
		panelMesa.setBackground(new java.awt.Color(28, 84, 45));
		panelMesa.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelMesa);
		panelMesa.setLayout(new BorderLayout(0, 0));

		cartasEnMesa = new ArrayList<JLabel>();
		jugadorUno = new JugadorVista();
		jugadorDos = new JugadorVista();
		JugadorActual = new JugadorVista();

		
		panelCartasMesa = this.generarPanel();
		JPanel panelCartasJugadorUno = this.generarPanel();
		JPanel panelCartasJugadorDos = this.generarPanel();
		mazoComun = this.generarPanel();

		jugadorUno.asignarPanel(panelCartasJugadorUno);
		jugadorDos.asignarPanel(panelCartasJugadorDos);
		JugadorActual = jugadorUno;
		panelMesa.add(panelCartasMesa, BorderLayout.CENTER);
		panelMesa.add(panelCartasJugadorUno, BorderLayout.NORTH);
		panelMesa.add(panelCartasJugadorDos, BorderLayout.SOUTH);
		panelMesa.add(mazoComun, BorderLayout.EAST);
	}

	public JPanel generarPanel(){
		JPanel panel = new JPanel();
		panel.setBackground(new java.awt.Color(28, 84, 45));
		return panel;
	}

	public JLabel generarEtiquetaConImagen(String nombre, ImageIcon icono){
		JLabel etiqueta = new JLabel();
        etiqueta.setName(nombre);
        etiqueta.setBorder(new EmptyBorder(60,5,5,5));
        etiqueta.setSize(144,200);
        etiqueta.setIcon(icono);
        etiqueta.setEnabled(true);
		etiqueta.setVisible(true);
		return etiqueta;
	}

	public void actualizarCartasJugador(ArrayList<Carta> cartas){
		jugadorUno.actualizarCartasJugador(cartas);
		jugadorDos.actualizarCartasJugador(cartas);
	}

	public void preguntarInformacionJugadorUno(){
		jugadorUno.preguntarNombreJugador();
		int opcion = jugadorUno.preguntarTurno();
		asignarTurnoJugador(jugadorUno.obtenerNombreJugador());
	}

	public void preguntarInformacionJugadorDos(){
		jugadorDos.preguntarNombreJugador();
	}

	public void asignarTurnoJugador(String nombre){
		if(jugadorUno.obtenerNombreJugador().equals(nombre)){
			JugadorActual = jugadorUno;
			jugadorDos.deshabilitarCartasJugador();
		}else{
			JugadorActual = jugadorDos;
			jugadorUno.deshabilitarCartasJugador();
		}
	}

	public void actualizarCartasEnMesa(ArrayList<Carta> cartasEnMesa){
		this.cartasEnMesa = new ArrayList<JLabel>();
		for(int indice = 0; indice < cartasEnMesa.size(); indice++){
            String palo = cartasEnMesa.get(indice).obtenerPalo();
            Integer valor = cartasEnMesa.get(indice).obtenerValor();
            String ruta = "Imagenes/" + palo + "/" + valor.toString() + "-" + palo + ".jpg";
			ImageIcon imagen = new ImageIcon(this.getClass().getResource(ruta));
            JLabel etiqueta = generarEtiquetaConImagen(valor+"-"+palo, imagen);
            this.cartasEnMesa.add(etiqueta);
            this.panelCartasMesa.add(etiqueta);
            this.panelCartasMesa.revalidate();
            this.panelCartasMesa.repaint();
        }
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		MesaVista frame = new MesaVista();

		Carta carta1 = new Carta(1, "Bastos");
		Carta carta2 = new Carta(2, "Copas");
		Carta carta3 = new Carta(3, "Oros");
		Carta carta4 = new Carta(4, "Espadas");
		ArrayList<Carta> cartas = new ArrayList<Carta>();
		cartas.add(carta1);
		cartas.add(carta2);
		cartas.add(carta3);
		cartas.add(carta4);
		frame.actualizarCartasEnMesa(cartas);
		frame.actualizarCartasJugador(cartas);
		frame.setVisible(true);
		frame.preguntarInformacionJugadorUno();
		frame.preguntarInformacionJugadorDos();
		frame.asignarTurnoJugador("Carmelo");
	}
}
