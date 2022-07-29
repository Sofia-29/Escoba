package Vista;


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
}
