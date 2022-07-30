package Vista;
import Modelo.Carta;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MesaVistaEscoba extends MesaVista {
    
    public MesaVistaEscoba( ){
        super();
    }

    @Override
    protected String reglasJuego() {
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

    public void mostrarEscoba(ArrayList<Carta> cartas){
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
        panel.setBackground(new java.awt.Color(249,214,46));
        JOptionPane.showMessageDialog(null,panel,"Escoba",JOptionPane.INFORMATION_MESSAGE);
    }
}
