package Controlador;
import Modelo.EscobaMarda.MazoEspanyol;
import Modelo.EscobaMarda.ConstructorSerializadorEscobaJson;
import java.io.File;
import javax.swing.JFileChooser;
import Modelo.ConstructorSerializadorAbstracto;
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

    public static void directorConstructor(ConstructorSerializadorAbstracto cs){
        File arch = elegirArchivo();
        try
        {
            // FileWriter archivo = new FileWriter(ruta);
            // PrintWriter escritor = new PrintWriter(archivo);
            Mazo mazo = cs.construirMazo(cs.leerArchivo(arch));
            // escritor.println(cs.inicioObjetop("Jugador 1")+informacionJugadorUno + cs.finObjeto());
            // escritor.close();
            // archivo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void directorSerializador(ConstructorSerializadorAbstracto cs){
        Mazo mazo = new MazoEspanyol();

        try {
            // FileWriter archivo = new FileWriter(ruta);
            // PrintWriter escritor = new PrintWriter(archivo);
            String informacionJugadorUno = cs.serializarMazo(mazo);
            // escritor.println(cs.inicioObjetop("Jugador 1")+informacionJugadorUno + cs.finObjeto());
            // escritor.close();
            // archivo.close();
            System.out.println(cs.inicioObjetop("Mazo")+informacionJugadorUno + cs.finObjeto());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        // MazoEspanyol mazo = new MazoEspanyol();
        // mazo.iniciarMazo();
        // mazo.imprimirMazo();

        ConstructorSerializadorEscobaJson cs = new ConstructorSerializadorEscobaJson(); 
        directorConstructor(cs);
    }
}

