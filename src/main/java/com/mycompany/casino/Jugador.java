package com.mycompany.casino;

import com.murcia.utils.ColaEnlazada;

public class Jugador {
     
    // Nombre del jugador
    private String nombre;
    
    // Cola que representa la mano de cartas del jugador
    private ColaEnlazada mano;
    
    // Constructor: inicializa el nombre y la mano vacia
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new ColaEnlazada();
    }
    
    // Metodo para recibir una carta y agregarla a la mano
    public void recibirCarta(Carta carta) {
        mano.encolar(carta);
        System.out.println(nombre + " recibe: " + carta);
    }
     
    // Metodo que calcula los puntos totales de la mano
    public int calcularPuntos() {

        int total = 0;// acumulador los puntos 
        int ases = 0; // contador de ases 

        ColaEnlazada copia = new ColaEnlazada();
        Carta c;

        // recorrer la mano
        while ((c = (Carta) mano.desencolar()) != null) {

            // Obtiene el valor numerico de la carta
            int valor = c.obtenerValorNumerico();

            if (valor == 11) {
                ases++;
            }

            total += valor;

            copia.encolar(c);
        }

         // devolver cartas a la mano
        while ((c = (Carta) copia.desencolar()) != null) {
            mano.encolar(c);
        }
        
        
        // Ajuste dinamico de los As:
        // Si el total supera 21, convertimos As de 11 a 1 (restando 10)
        while (total > 21 && ases > 0) {
            total -= 10; // convierte un As de 11 → 1
            ases--;
        }

        return total;
    }
    
    // Reinicia la mano del jugador (para una nueva ronda)
    public void limpiarMano() {
        mano = new ColaEnlazada();
    }
    
    // Devuelve el nombre del jugador
    public String getNombre() {
        return nombre;
    }
}