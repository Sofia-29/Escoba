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
            if(esEscoba(cartasEnMesa)){
                jugador.asignarPuntaje(1);
                jugador.capturarCartas(resultado);
                cartasEnMesa.removeAll(resultado);
            } else{
                jugador.capturarCartas(resultado);
                cartasEnMesa.removeAll(resultado);
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

    public void contabilizarPuntos(Jugador jugador){}

    public Jugador reglaCantidadCartas(){
        return null;
    }

    public Jugador reglaPaloDeOros(){
        return null;
    }

    public Jugador reglaSieteDeOros(){
        return null;
    }
    
    public Jugador reglacantidadDeSietes(){
        return null;
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
}
