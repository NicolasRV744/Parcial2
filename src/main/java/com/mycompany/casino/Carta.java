
package com.mycompany.casino;
public class Carta {

    
    private String palo; // Representa el palo de la carta (Corazones, Picas, etc.)
    private String valor; // Representa el valor de la carta (A, 2, 3... J, Q, K)

// Constructor: inicializa el palo y el valor de la carta
public Carta(String palo, String valor) {
    this.palo = palo;
    this.valor = valor;
    
    } 

    // Metodo que devuelve el valor numerico de la carta
    public int obtenerValorNumerico() {
        
        // Las figuras valen 10
        if (valor.equals("J") || valor.equals("Q") || valor.equals("K"))
            return 10;
        // El As inicialmente vale 11 (luego se ajusta en Jugador)
        if (valor.equals("As"))
            return 11;
        
        // Para cartas numéricas (2-10), se convierte el String a entero
        return Integer.parseInt(valor);
    }
     
    // Metodo para mostrar la carta
    @Override
    public String toString() {
        return valor + " de " + palo;
    }
}
   
   

