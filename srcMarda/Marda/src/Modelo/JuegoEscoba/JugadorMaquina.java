package Modelo.JuegoEscoba;
import java.util.ArrayList;

import Modelo.Jugador; 
import Modelo.Carta;

public class JugadorMaquina extends Jugador{

    public JugadorMaquina(){
        super();
    }

    @Override
    public Carta elegirCarta(ArrayList<Carta> cartasEnMesa){
        Carta resultado = null;
        int suma = 0;
        boolean encontrado = false;
        ArrayList<Carta> cartas = this.obtenerCartas();
        
        if(cartasEnMesa != null){
            for(Carta naipeDeJugador:cartas){
                suma = naipeDeJugador.obtenerValor();
                ArrayList<Integer> indicesVisitados = new ArrayList<Integer>();
                encontrado = combinaciones(cartasEnMesa, suma, indicesVisitados);
                if(encontrado){
                    resultado = naipeDeJugador;
                    break;
                }
            }
        }

        if(!encontrado){
            int indice = (int)(Math.random()*cartas.size()+0);
            resultado = cartas.get(indice);
        }

        return resultado;
    }

    private boolean combinaciones(ArrayList<Carta> cartasEnMesa, int suma, ArrayList<Integer> indicesVisitados){
        boolean encontrado = false;
        if(suma == 15){
            encontrado = true;
        }else if(suma < 15){
            for(int indice = 0; indice < cartasEnMesa.size() && !encontrado; indice++){
                if(!indicesVisitados.contains(indice)){
                    Carta naipeEnMesa = cartasEnMesa.get(indice);
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
}
