package Vista;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

public class General {
    public General(){}

    public void actualizarPanel(JPanel panel){
        panel.revalidate();
        panel.repaint();
    }

    public JPanel generarPanel(){
		JPanel panel = new JPanel();
		panel.setBackground(new java.awt.Color(28, 84, 45));
		return panel;
	}

    public void generarBotonConImagen(JToggleButton boton, String nombre, ImageIcon icono){
        boton.setName(nombre);
        boton.setBorder(new EmptyBorder(5,5,5,5));
        boton.setSize(144,200);
        boton.setIcon(icono);
        boton.setEnabled(true);
		boton.setVisible(true);
    }

    public JLabel generarEtiquetaConImagen(String nombre, ImageIcon icono){
		JLabel etiqueta = new JLabel();
        etiqueta.setName(nombre);
        etiqueta.setBorder(new EmptyBorder(60,5,5,0));
        etiqueta.setSize(144,200);
        etiqueta.setIcon(icono);
        etiqueta.setEnabled(true);
		etiqueta.setVisible(true);
		return etiqueta;
	}
}
    
