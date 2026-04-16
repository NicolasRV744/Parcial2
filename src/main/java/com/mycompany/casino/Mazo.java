package com.mycompany.casino;

import com.murcia.utils.ColaEnlazada;
import java.util.Random;

public class Mazo {
    
    // Cola que almacena todas las cartas del mazo
    private ColaEnlazada cartas;
    
    
    // Constructor: crea la cola e inicializa el mazo
    public Mazo() {
        cartas = new ColaEnlazada();
        crearMazo();
    }
     
    
    // Metodo que crea las 52 cartas del mazo
    private void crearMazo() {

        String[] palos = {"Corazones", "Diamantes", "Treboles", "Picas"};
        String[] valores = {
            "As","2","3","4","5","6","7","8","9","10",
            "J","Q","K"
        };
        

        // Se combinan palos y valores para formar el mazo completo
        for (String palo : palos) {
            for (String valor : valores) {
                cartas.add(new Carta(palo, valor));
            }
        }
    }
    
     // Metodo para robar (sacar) la carta
    public Carta robarCarta() {
        return (Carta) cartas.desencolar(); 
    }
    
    //Metodo que hace que se baraje el mazo 
      public void barajar() { 
    Random rand = new Random();

    int n = 52; //total de cartas 
    
    // Se realizan multiples movimientos para mezclar el mazo
    for (int i = 0; i < n * 2; i++) {
        
        Carta carta1 = (Carta) cartas.desencolar();
        
        //Se elige una posicion aleatoria 
        int pasos = rand.nextInt(n);

        // Se mueven cartas dentro de la cola
        for (int j = 0; j < pasos; j++) {
            Carta c = (Carta) cartas.desencolar();
            cartas.encolar(c);
        }
        
        // Hace que la carta se inserte en una nueva posicion eso genera una forma aleatoria
        cartas.encolar(carta1);
    }

    System.out.println("Mazo barajado");
 }
}