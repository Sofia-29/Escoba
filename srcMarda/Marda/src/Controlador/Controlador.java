package Controlador;
import Modelo.EscobaMarda.JugadorMaquina;
import Modelo.EscobaMarda.JugadorPersona;
import Modelo.EscobaMarda.MazoEspanyol;
import Modelo.EscobaMarda.SerializadorEscobaJson;
import java.io.File;
import javax.swing.JFileChooser;
import Modelo.SerializadorAbstracto;
import Modelo.Jugador;
import Modelo.Mazo;

public class Controlador {
    public static File elegirArchivo(){
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

    public static void directorSerializador(SerializadorAbstracto cs){
        try {
            MazoEspanyol mazo = new MazoEspanyol();
            mazo.iniciarMazo();
            mazo.imprimirMazo();
            JugadorMaquina jugador = new JugadorMaquina();
            JugadorPersona jugador2 = new JugadorPersona();

            cs.inicioObjeto("Juego");
            cs.serializarMazo(mazo);
            cs.serializarJugador(jugador);
            cs.serializarJugador(jugador2);
            cs.finObjeto();
            String result = cs.obtSerializacion();

            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        SerializadorEscobaJson cs = new SerializadorEscobaJson(); 
        directorSerializador(cs);
    }
}

