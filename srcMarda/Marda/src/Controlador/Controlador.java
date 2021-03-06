package Controlador;
import java.io.File;
import Modelo.Jugador;
import Modelo.Constructor;
import Modelo.Mesa;
import Modelo.Serializador;
import Modelo.JuegoEscoba.MesaEscoba;
import Vista.MesaVista;

public abstract class Controlador {

    private Mesa mesaConcreta;
    private MesaVista mesaVistaConcreta; 
    private Constructor constructorConcreto;
    private Serializador serializador;

    public Controlador(Mesa mesaConcreta, MesaVista mesaVistaConcreta, Constructor constructorConcreto, Serializador serializador){
        this.mesaConcreta = mesaConcreta;
        this.mesaVistaConcreta = mesaVistaConcreta;
        this.constructorConcreto = constructorConcreto;
        this.serializador = serializador;
    }

    public abstract void inicializarMesaCargada();
    public abstract void inicializarMesa();
    public abstract void realizarTurnoJugador();

    public Mesa obtenerMesaConcreta(){
        return mesaConcreta;
    }

    public MesaVista obtenerMesaVistaConcreta(){
        return mesaVistaConcreta;
    }

    /**
     * Carga una partida previamente guardada o inicializa una partida
     */
    public void cargarPartida(){
        boolean cargarPartida = mesaVistaConcreta.preguntarCargarPartida();
        if(cargarPartida){
            MesaEscoba mesa = (MesaEscoba)mesaConcreta;
            directorConstructor(constructorConcreto, mesa, mesaVistaConcreta.elegirArchivo());
            mesaConcreta = mesa;
            inicializarMesaCargada();
        }else{
            inicializarMesa();
            mesaConcreta.iniciarPartida();
        }
    }

    /**
     * Metodo plantilla 
     * pasa el turno 
     */
    public void pasarTurno(){
        mesaConcreta.pasarTurno();
        mesaVistaConcreta.cambiarTurnoJugador();
		mesaVistaConcreta.actualizarEtiquetaPuntajeJugador(mesaConcreta.obtenerJugadorActual().obtenerPuntaje());
    }
    
    /**
     * Metodo plantilla
     * inicializa el juego
     */
    public void iniciarJuego(){

        cargarPartida();
        mesaVistaConcreta.asignarMesa(mesaConcreta, serializador);
        mesaVistaConcreta.actualizarCartasEnMesa(mesaConcreta.obtenerCartasEnMesa());
        mesaVistaConcreta.actualizarCartasJugadorUno(mesaConcreta.obtenerPrimerJugador().obtenerCartas());
		mesaVistaConcreta.actualizarCartasJugadorDos(mesaConcreta.obtenerSegundoJugador().obtenerCartas());
        mesaVistaConcreta.actualizarEtiquetaTurnoJugador(mesaVistaConcreta.obtenerNombreJugadorActual());
        mesaVistaConcreta.deshabilitarCartasJugadores();
        mesaVistaConcreta.mostrarCartasJugadorActual();
        mesaVistaConcreta.setVisible(true);

        while(!mesaConcreta.validarTerminarPartida()){
            realizarTurnoJugador();
            pasarTurno();
        }

        mesaVistaConcreta.actualizarCartasEnMesa(mesaConcreta.retornarCartasEnMesa());
        Jugador ganador = mesaConcreta.terminarPartida();
        String mensaje="Juego finalizado\n"+mesaConcreta.obtenerPrimerJugador().obtenerNombre()+ " termina con "
        + mesaConcreta.obtenerPrimerJugador().obtenerPuntaje() +" puntos\n" + mesaConcreta.obtenerSegundoJugador().obtenerNombre()+ " termina con "
        + mesaConcreta.obtenerSegundoJugador().obtenerPuntaje() + "puntos \nEl ganador es: " + ganador.obtenerNombre();
        mesaVistaConcreta.finalizarJuego(mensaje);
        mesaVistaConcreta.setVisible(false);
    }

    /*
     * Director constructor para objetos del tipo Mesa
     */
    public static void directorConstructor(Constructor constructorMesa, MesaEscoba mesaConcretaVacia, File archivo){
        constructorMesa.iniciarMesaConcreta(mesaConcretaVacia);

        // Construir partes sobre el esqueleto de mesa 
        constructorMesa.leerArchivo(archivo);
        String atributo = constructorMesa.siguienteObjeto();
        int posicionJugador = 0;
        while(atributo != "null"){
            String tipo = constructorMesa.obtenerTipoObjeto(atributo);
            String Valor = constructorMesa.obtenerValorObjeto(atributo);
            
            switch (tipo) {
                case "Mazo":
                    constructorMesa.construirMazo(Valor);
                    break;
                
                case "CartasEnMesa":
                    constructorMesa.construirCartasEnMesa(Valor);
                    break;

                case "Jugador":
                    constructorMesa.construirJugador(Valor, posicionJugador);
                    posicionJugador += 1;
                    break;
                
                case "JugadorActual":
                    constructorMesa.construirjugadorActual(Valor);
                    break;
                case "UltimaCaptura":
                    constructorMesa.construirUltimaCaptura(Valor);
                    break;
            
                default:
                    break;
            }

            atributo = constructorMesa.siguienteObjeto();
            if(atributo.length() <= 1){
                break;
            }
        }

        // Obtener mesa resultante
        mesaConcretaVacia = constructorMesa.obtenerMesa();
    }
}