package Modelo;

import java.util.ArrayList;

public class Validador {
    public Validador(){
    }

    public ArrayList<Naipe> validarCaptura(Jugador jugador, Naipe naipe, ArrayList<Naipe> cartasEnMesa){
        boolean encontrado = false;
        ArrayList<Naipe> resultado = validarEscoba(naipe, cartasEnMesa);
        
        if (resultado == null){
            ArrayList<Integer> indicesVisitados = new ArrayList<Integer>();
            int suma = 0;
            suma = naipe.obtenerValor();
            encontrado = combinaciones(cartasEnMesa, suma, indicesVisitados);
            if(encontrado){
                resultado = new ArrayList<Naipe>();
                for(int indice:indicesVisitados){
                    resultado.add(cartasEnMesa.get(indice));
                }
                resultado.add(naipe);
            }
        }
        asignarPuntajeCaptura(jugador, naipe, resultado, cartasEnMesa);
        return resultado;
    }

    public void asignarPuntajeCaptura(Jugador jugador, Naipe naipe, ArrayList<Naipe> resultado, ArrayList<Naipe> cartasEnMesa){
        if (resultado != null){
            cartasEnMesa.removeAll(resultado);
            if(esEscoba(cartasEnMesa)){
                jugador.asignarPuntaje(1);
                System.out.println(jugador.obtenerNombre()+ " obtuvo un punto por escoba");
                jugador.capturarCartas(resultado);
            } else{
                jugador.capturarCartas(resultado);
            }
        }else{
            cartasEnMesa.add(naipe);
        }
    }

    public boolean combinaciones(ArrayList<Naipe> cartasEnMesa, int suma, ArrayList<Integer> indicesVisitados){
        boolean encontrado = false;
        if(suma == 15){
            encontrado = true;
        }else if(suma < 15){
            for(int indice = 0; indice < cartasEnMesa.size() && !encontrado; indice++){
                if(!indicesVisitados.contains(indice)){
                    Naipe naipeEnMesa = cartasEnMesa.get(indice);
                    int valor = naipeEnMesa.obtenerValor();
                    suma += valor;
                    indicesVisitados.add(indice);
                    encontrado = combinaciones(cartasEnMesa, suma, indicesVisitados);
                    if(!encontrado){
                        suma -= valor; 
                        indicesVisitados.remove(indicesVisitados.indexOf(indice));
                    }
                }
            }
        }

        return encontrado;
    }

    public ArrayList<Naipe> validarEscoba(Naipe naipe, ArrayList<Naipe> naipes){
        ArrayList<Naipe> resultado = new ArrayList<Naipe>();
        int suma = naipe.obtenerValor();
        for(Naipe naipesEnMesa:naipes){
            suma += naipesEnMesa.obtenerValor();
        }
        if(suma == 15){
            resultado.addAll(naipes);
            resultado.add(naipe);
        }else{
            resultado = null;
        }
        return resultado;
    }

    public boolean esEscoba(ArrayList<Naipe> cartasEnMesa){
        boolean resultado = false;
        if(cartasEnMesa.isEmpty()){
            resultado = true;
        }
        return resultado;
    }

    public void contabilizarPuntos(Jugador primerJugador, Jugador segundoJugador){
        reglaCantidadCartas(primerJugador, segundoJugador);
        reglaPaloDeOros(primerJugador, segundoJugador);
        reglaSieteDeOros(primerJugador, segundoJugador);
        reglacantidadDeSietes(primerJugador, segundoJugador);
    }

    //Asignar los puntos.
    public void reglaCantidadCartas(Jugador primerJugador, Jugador segundoJugador){
        if(primerJugador.obtenerNumeroCartasCapturadas() > segundoJugador.obtenerNumeroCartasCapturadas()){
            primerJugador.asignarPuntaje(1);
            System.out.println("Reglas: Jugador 1: Cantidad de Cartas");
        }else {
            if(primerJugador.obtenerNumeroCartasCapturadas() < segundoJugador.obtenerNumeroCartasCapturadas()){
                segundoJugador.asignarPuntaje(1);
                System.out.println("Reglas: Jugador 1: Cantidad de Cartas");
            }
        }
    }

    public void reglaPaloDeOros(Jugador primerJugador, Jugador segundoJugador){
        int cantidadOrosPrimer = 0;
        int cantidadOrosSegundo = 0;
        for (Naipe naipe : primerJugador.obtenerCartasCapturadas()) {
            if(naipe.obtenerPalo() == "Oros"){
                cantidadOrosPrimer += 1;
            }
        }
        for (Naipe naipe : segundoJugador.obtenerCartasCapturadas()) {
            if(naipe.obtenerPalo() == "Oros"){
                cantidadOrosSegundo += 1;
            }
        }
        System.out.println("Reglas: Jugador 1: Palos de Oros: " + cantidadOrosPrimer);
        System.out.println("Reglas: Jugador 2: Palos de Oros: " + cantidadOrosSegundo);
        primerJugador.asignarPuntaje(cantidadOrosPrimer);
        segundoJugador.asignarPuntaje(cantidadOrosSegundo);
    }

    public void reglaSieteDeOros(Jugador primerJugador, Jugador segundoJugador){
        System.out.println("ANTES:Puntos Jugador 1: " + primerJugador.obtenerPuntaje());
        System.out.println("ANTES:Puntos Jugador 2: " + segundoJugador.obtenerPuntaje());
        boolean encontrado = false;
        for (Naipe naipe : primerJugador.obtenerCartasCapturadas()) {
            if(naipe.obtenerValor() == 7 && naipe.obtenerPalo() == "Oros"){
                primerJugador.asignarPuntaje(1);
                encontrado = true;
                System.out.println("Reglas: Jugador 1: Siete de oros: 1 punto ");
                break;
            }
        }
        if(!encontrado){
            segundoJugador.asignarPuntaje(1);
            System.out.println("Reglas: Jugador 2: Siete de oros: 1 punto ");
        }
        System.out.println("DESPUES:Puntos Jugador 1: " + primerJugador.obtenerPuntaje());
        System.out.println("DESPUES:Puntos Jugador 2: " + segundoJugador.obtenerPuntaje());
    }
    
    public void reglacantidadDeSietes(Jugador primerJugador, Jugador segundoJugador){
        int cantidadSietesPrimer = 0;
        int cantidadSietesSegundo = 0;
        for (Naipe naipe : primerJugador.obtenerCartasCapturadas()) {
            if(naipe.obtenerValor() == 7){
                cantidadSietesPrimer += 1;
            }
        }
        for (Naipe naipe : segundoJugador.obtenerCartasCapturadas()) {
            if(naipe.obtenerValor() == 7){
                cantidadSietesSegundo += 1;
            }
        }
        System.out.println("Reglas: Jugador 1: Cantidad Sietes: " + cantidadSietesPrimer);
        System.out.println("Reglas: Jugador 2: Cantidad Sietes: " + cantidadSietesSegundo);
        primerJugador.asignarPuntaje(cantidadSietesPrimer);
        segundoJugador.asignarPuntaje(cantidadSietesSegundo);
    }
}
