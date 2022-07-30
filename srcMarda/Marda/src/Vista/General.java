package Vista;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

public class General {
    public General(){}

    /**
     * @param panel panel a actualizar
     */
    public void actualizarPanel(JPanel panel){
        panel.revalidate();
        panel.repaint();
    }

    /**
     * @return el panel generado
     */
    public JPanel generarPanel(){
		JPanel panel = new JPanel();
		panel.setBackground(new java.awt.Color(28, 84, 45));
		return panel;
	}

    /**
     * @param boton al que se le agrega una imagen 
     * @param nombre del boton
     * @param icono del boton
     */
    public void generarBotonConImagen(JToggleButton boton, String nombre, ImageIcon icono){
        boton.setName(nombre);
        boton.setBorder(new EmptyBorder(5,5,5,5));
        boton.setSize(144,200);
        boton.setIcon(icono);
        boton.setEnabled(true);
		boton.setVisible(true);
    }

    /**
     * @param nombre de la etiqueta
     * @param icono de la etiqueta
     * @return un JLabel con las especificaciones
     */
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

    /**
     * @param texto que debe agregarse a la etiqueta
     * @return la etiqueta con los parametros necesarios
     */
    public JLabel generarEtiqueta(String texto){
        JLabel etiqueta = new JLabel();
        etiqueta.setText(texto);
        etiqueta.setVisible(false);
        etiqueta.setOpaque(true);
        etiqueta.setFont(new Font("Arial", Font.PLAIN, 24));
        etiqueta.setBackground(new java.awt.Color(255,255,255));
        etiqueta.setSize(200,100);
        return etiqueta;
    }

    /**
     * @param componente que debe ser limpiado
     * @param grupoBotones al que se le deben eliminar los componentes
     * @param panel al que se le debe eliminar los componentes
     */
    public void limpiarComponenteBotones(ArrayList<JToggleButton> componente, ButtonGroup grupoBotones, JPanel panel){
        int indiceComponente = componente.size() - 1;
        while(indiceComponente >= 0){
            JToggleButton boton = componente.get(indiceComponente);
            grupoBotones.remove(boton);
            panel.remove(boton);
            indiceComponente--;
        }
        componente.removeAll(componente);
    }

    /**
     * @param componente a limpiar
     * @param panel al que se le debe eliminar las etiquetas
     */
    public void limpiarComponenteJLabel(ArrayList<JLabel> componente, JPanel panel){
        int indiceComponente = componente.size() - 1;
        while(indiceComponente >= 0){
            JLabel etiqueta = componente.get(indiceComponente);
            etiqueta.setVisible(false);
            panel.remove(etiqueta);
            componente.remove(indiceComponente);
            indiceComponente--;
        }
    }
}
    
