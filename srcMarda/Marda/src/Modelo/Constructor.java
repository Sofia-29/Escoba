package Modelo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public abstract class Constructor {

    public Constructor(){
    }

    public Mesa resultado;
    public String mesaSerial;

    public abstract void construirJugador(String jugador, int orden);
    public abstract void construirMazo(String mazo);
    public abstract void construirjugadorActual(String jugadorActual);
    public abstract void construirUltimaCapura(String captura);
    public abstract String obtenerTipoObjeto(String objeto);
    public abstract String obtenerValorObjeto(String objeto);
    public abstract String siguienteObjeto();

    public void iniciarMesaConcreta(Mesa mesaConcreta){
        this.resultado = mesaConcreta;
    }

    public Mesa obtenerMesa(){
        return resultado;
    }

    public void leerArchivo(File partida){
        String salida = "";
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(partida));
            salida = "";
            String st;
            while ((st = br.readLine()) != null){
                salida += st+"\n";
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mesaSerial = salida;
    }
}