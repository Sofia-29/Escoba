package Vista;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import Modelo.Carta;

public class JugadorVista {
    private ArrayList<JToggleButton> cartasJugador;
    private JPanel panel; 
    private String nombre;

    JugadorVista(){
        cartasJugador = new ArrayList<JToggleButton>();
    }

    public JPanel obtenerPanel(){
        return this.panel;
    }

    public void asignarPanel(JPanel panel){
        this.panel = panel;
    }


    public void actualizarCartasJugador(ArrayList<Carta> cartas){
		for(int indice = 0; indice < cartas.size(); indice++){
            String palo = cartas.get(indice).obtenerPalo();
            Integer valor = cartas.get(indice).obtenerValor();
            String ruta = "Imagenes/" + palo + "/" + valor.toString() + "-" + palo + ".jpg";
			ImageIcon imagen = new ImageIcon(this.getClass().getResource(ruta));
            JToggleButton etiqueta = generarBotonConImagen(valor+"-"+palo, imagen);
            this.cartasJugador.add(etiqueta);
            panel.add(etiqueta);
            panel.revalidate();
            panel.repaint();
        }
    }

    public JToggleButton generarBotonConImagen(String nombre, ImageIcon icono){
        JToggleButton boton = new JToggleButton();
        boton.setName(nombre);
        boton.setBorder(new EmptyBorder(5,5,5,5));
        boton.setSize(144,200);
        boton.setIcon(icono);
        boton.setEnabled(true);
		boton.setVisible(true);
		return boton;
    }
}
