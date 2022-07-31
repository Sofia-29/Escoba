package Modelo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import Modelo.JuegoEscoba.MesaEscoba;

public abstract class Constructor {

    public Constructor(){
    }

    public MesaEscoba resultado;
    public String mesaSerial;

    public abstract void construirJugador(String jugador, int orden);
    public abstract void construirMazo(String mazo);
    public abstract void construirCartasEnMesa(String cartasEnMesa);
    public abstract void construirjugadorActual(String jugadorActual);
    public abstract void construirUltimaCaptura(String captura);
    public abstract String obtenerTipoObjeto(String objeto);
    public abstract String obtenerValorObjeto(String objeto);
    public abstract String siguienteObjeto();

    public void iniciarMesaConcreta(MesaEscoba mesaConcreta){
        this.resultado = mesaConcreta;
    }

    public MesaEscoba obtenerMesa(){
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