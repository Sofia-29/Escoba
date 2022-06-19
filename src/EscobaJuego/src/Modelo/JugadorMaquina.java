package Modelo;
import java.util.ArrayList;

public class JugadorMaquina extends Jugador{

    public JugadorMaquina(String nombre){
        super(nombre);
    }

    @Override
    public Naipe descartarCarta(ArrayList<Naipe> cartasEnMesa){
        Naipe resultado = null;
        int suma = 0;
        boolean encontrado = false;
        ArrayList<Naipe> cartas = this.obtenerCartas();
        for(Naipe naipeDeJugador:cartas){
            suma = naipeDeJugador.obtenerValor();
            ArrayList<Integer> indicesVisitados = new ArrayList<Integer>();
            encontrado = combinaciones(cartasEnMesa, suma, indicesVisitados);
            if(encontrado){
            resultado = naipeDeJugador;
            break;
            }
        }

        if(!encontrado){
            int indice = (int)(Math.random()*cartas.size()+0);
            resultado = cartas.get(indice);
        }

        return resultado;
    }

    private boolean combinaciones(ArrayList<Naipe> cartasEnMesa, int suma, ArrayList<Integer> indicesVisitados){
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
}
