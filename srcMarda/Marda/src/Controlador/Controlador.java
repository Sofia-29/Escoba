package Controlador;
import Modelo.EscobaMarda.MazoEspanyol;


// public void Director(){
    
// }

public class Controlador {
    public static void main(String[] args) throws Exception {
        MazoEspanyol mazo = new MazoEspanyol();
        mazo.iniciarMazo();
        mazo.imprimirMazo();
    }
}

