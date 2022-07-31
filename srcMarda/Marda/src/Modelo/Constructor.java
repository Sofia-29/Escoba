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

    /**
    * ensambla jugador
     */
    public abstract void construirJugador(String jugador, int orden);

    /**
    * ensambla mazo
     */
    public abstract void construirMazo(String mazo);

    /**
     * @return ensambla CartasEnMesa
     */
    public abstract void construirCartasEnMesa(String cartasEnMesa);

    /**
     * @return ensambla jugadorActual
     */
    public abstract void construirjugadorActual(String jugadorActual);

    /**
    * ensambla ultima captura
     */
    public abstract void construirUltimaCaptura(String captura);

    /**
     * @return obtiene siguiente tipo
     */
    public abstract String obtenerTipoObjeto(String objeto);

     /**
     * @return obtiene siguiente valor
     */
    public abstract String obtenerValorObjeto(String objeto);

    /**
     * @return obtiene siguiente objeto
     */
    public abstract String siguienteObjeto();

     /**
     * inicia la mesa resultado
     */
    public void iniciarMesaConcreta(MesaEscoba mesaConcreta){
        this.resultado = mesaConcreta;
    }

    /**
     * @return retorna la mesa resultado
     */
    public MesaEscoba obtenerMesa(){
        return resultado;
    }

    /**
     * Valida las reglas para ambos jugadores y lo guarda en mesaSerial
     * @param partida Archivo de la partida
     */
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