package Modelo.JuegoEscoba;

import java.util.ArrayList;

import Modelo.Regla;
import Modelo.Carta;
import Modelo.Jugador;
import Modelo.Validador;

public class ValidadorEscoba extends Validador{
    public ValidadorEscoba(){
        super();
        inicializarReglas();
    }

    @Override
    public void inicializarReglas()
    {
        ArrayList<Regla> reglas = this.obtenerReglas();
        reglas.add(new ReglaEscoba("Escoba", 1));
        reglas.add(new ReglaEscobaCantidadOros("Cantidad Oros", 1));
        reglas.add(new ReglaEscobaCantidadSietes("Cantidad Sietes", 1));
        reglas.add(new ReglaEscobaSietes("Sietes", 1));
        reglas.add(new ReglaEscobaOros("Oros", 1));
        reglas.add(new ReglaEscobaSieteOros("Siete Oros", 1));
    }

    @Override
    public void validarReglas(Jugador primerJugador, Jugador segundoJugador){
        String stringABuscar = "Cantidad";
        for (Regla regla : this.obtenerReglas()) {
            if(regla.obtenerNombre().equals("Escoba")){}
            else{
                if(regla.obtenerNombre().contains(stringABuscar)){
                    int puntuacionJugadorUno = validarReglaJugador(primerJugador, regla);
                    int puntuacionJugadorDos = validarReglaJugador(segundoJugador, regla);
                    Jugador ganador = comparacionMayor(primerJugador, segundoJugador, puntuacionJugadorUno, puntuacionJugadorDos);
                    if(ganador != null)
                    {
                        ganador.asignarPuntaje(1);
                    }
                }else{
                    primerJugador.asignarPuntaje(validarReglaJugador(primerJugador, regla));
                    segundoJugador.asignarPuntaje(validarReglaJugador(segundoJugador, regla));
                }
            }
        }
    }

    @Override
    public void validarJugada(ArrayList<Carta> cartas, Jugador jugador){
        Carta cartaDescartada = cartas.get(cartas.size()-1);
        cartas.remove(cartaDescartada);
        validarCaptura(jugador, cartaDescartada, cartas);
    }

    public int validarReglaJugador(Jugador jugador, Regla regla)
    {
        return regla.validarRegla(jugador.obtenerCartasCapturadas());
    }

    private Jugador comparacionMayor(Jugador primerJugador, Jugador segundoJugador, int puntuacionJugadorUno, int puntuacionJugadorDos)
    {
        if(puntuacionJugadorUno > puntuacionJugadorDos)
        {
            return primerJugador;
        }else{
            if(puntuacionJugadorUno < puntuacionJugadorDos){
                return segundoJugador;
            }
            return null;
        }
    }

    public ArrayList<Carta> validarCaptura(Jugador jugador, Carta carta, ArrayList<Carta> cartasEnMesa){
        boolean encontrado = false;
        ArrayList<Carta> resultado = validarEscoba(carta, cartasEnMesa);
        
        if (resultado == null){
            ArrayList<Integer> indicesVisitados = new ArrayList<Integer>();
            int suma = 0;
            suma = carta.obtenerValor();
            encontrado = combinaciones(cartasEnMesa, suma, indicesVisitados);
            if(encontrado){
                resultado = new ArrayList<Carta>();
                for(int indice:indicesVisitados){
                    resultado.add(cartasEnMesa.get(indice));
                }
                resultado.add(carta);
            }
        }
        asignarPuntajeCaptura(jugador, carta, resultado, cartasEnMesa);
        return resultado;
    }

    private boolean combinaciones(ArrayList<Carta> cartasEnMesa, int suma, ArrayList<Integer> indicesVisitados){
        boolean encontrado = false;
        if(suma == 15){
            encontrado = true;
        }else if(suma < 15){
            for(int indice = 0; indice < cartasEnMesa.size() && !encontrado; indice++){
                if(!indicesVisitados.contains(indice)){
                    Carta cartaEnMesa = cartasEnMesa.get(indice);
                    int valor = cartaEnMesa.obtenerValor();
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

    public ArrayList<Carta> validarEscoba(Carta carta, ArrayList<Carta> cartas){
        ArrayList<Carta> resultado = new ArrayList<Carta>();
        int suma = carta.obtenerValor();
        for(Carta cartasEnMesa:cartas){
            suma += cartasEnMesa.obtenerValor();
        }
        if(suma == 15){
            resultado.addAll(cartas);
            resultado.add(carta);
        }else{
            resultado = null;
        }
        return resultado;
    }

    public boolean esEscoba(ArrayList<Carta> cartasEnMesa){
        Regla escoba = this.obtenerReglas().get(0);
        if(escoba.validarRegla(cartasEnMesa) == 1){
            return true;
        }
        return false;
    }

    public void asignarPuntajeCaptura(Jugador jugador, Carta naipe, ArrayList<Carta> resultado, ArrayList<Carta> cartasEnMesa){
        if (resultado != null){
            cartasEnMesa.removeAll(resultado);
            if(esEscoba(cartasEnMesa)){
                jugador.asignarPuntaje(1);
                jugador.capturarCartas(resultado);
            } else{
                jugador.capturarCartas(resultado);
            }
        }else{
            cartasEnMesa.add(naipe);
        }
    }
}
