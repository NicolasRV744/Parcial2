
package com.mycompany.casino;
import com.murcia.utils.Input;

public class Juego {

    private Mazo mazo; //mazo de cartas
    private Jugador jugador; //Jugador principal
    private Jugador dealer; //Jugador digital
    private Input input; // Método principal del flujo del juego

    public void iniciar() {

     
    String nombre = Input.nextLine("Ingrese su nombre: ");

    jugador = new Jugador(nombre);
    dealer = new Jugador("Dealer");
    
    boolean seguirJugando = true;//es si el jugador quiere seguir jugando
    while (seguirJugando) {
    
    // Se crea y baraja un nuevo mazo
    mazo = new Mazo();
    mazo.barajar();

    int rondas = 3;
    
    
    //se ejecutan 3 rondas obligatoriamente para empezar 
    for (int i = 1; i <= rondas; i++) {
        System.out.println("\n=======================");
        System.out.println("      RONDA " + i);
        System.out.println("=======================");
        jugarRonda();
    }
    
    // Limpia buffer (evita errores de entrada)
     Input.nextLine("");
     
     String opcion;
     
     while (true) {
     
     //se le pregunta al jugador si quiere otras 3 rondas mas o no 
       System.out.println("\n==============================");
       System.out.println("Quieres jugar otras 3 rondas?");
       opcion = Input.nextLine("(si/no): ");
       System.out.println("==============================\n");
          
       if (opcion.equalsIgnoreCase("si") || opcion.equalsIgnoreCase("no")) { 
        break; // entrada válida y salir del ciclo
        } else {
        System.out.println("Opcion invalida, intenta nuevamente.");
    }
}

        if (opcion.equalsIgnoreCase("no")) {
            seguirJugando = false;
            System.out.println("Gracias por jugar");
        }
    }
    
}
    //ronda completa del juego 
    private void jugarRonda() {

        jugador.limpiarMano();
        dealer.limpiarMano();

        
        // Se crea y baraja un nuevo mazo para la ronda
        mazo = new Mazo();
        mazo.barajar();

        // cartas iniciales, se le reparten dos cartas a cada uno 
        jugador.recibirCarta(mazo.robarCarta());
        jugador.recibirCarta(mazo.robarCarta());

        dealer.recibirCarta(mazo.robarCarta());
        dealer.recibirCarta(mazo.robarCarta());

        turnoJugador();
        turnoDealer();
        mostrarGanador();
    }

    private void turnoJugador() {

        boolean seguir = true;

        while (seguir) {

            int puntos = jugador.calcularPuntos();

            System.out.println("\n" + jugador.getNombre() + " tienes " + puntos + " puntos");

            
            
            // Si se pasa de 21, pierde automaticamente ; la principal regla del blackjack

            if (puntos > 21) {
                System.out.println("Te pasaste!");
                break;
            }
            
            //opciones
            System.out.println("1. Plantarse");
            System.out.println("2. Pedir carta");

            int opcion = Input.nextInt("Elige: ");
            if (opcion == 2) {
                jugador.recibirCarta(mazo.robarCarta());
            } else if (opcion == 1) {
                seguir = false;
                System.out.println("Te plantaste con: " + puntos);
            } else {
                System.out.println("Opcion invalida");
            }
        }
    }
    
    //reglas del dealer 
    private void turnoDealer() {
        
        // El dealer pide cartas hasta tener al menos 17 puntos
        while (dealer.calcularPuntos() < 17) {
            dealer.recibirCarta(mazo.robarCarta());
        }

        int puntosDealer = dealer.calcularPuntos();
        
        //resultado de lo que le salio al dealer en las cartas que saco 
       if (puntosDealer > 21) {
       System.out.println("Dealer se paso con " + puntosDealer);
       } else {
       System.out.println("Dealer se planta con " + puntosDealer);
}
    }
     // Determina el ganador de la ronda
    private void mostrarGanador() {

        int p1 = jugador.calcularPuntos();
        int p2 = dealer.calcularPuntos();
        
        System.out.println("\n----- RESULTADO -----");
        System.out.println("Jugador: " + p1);
        System.out.println("Dealer: " + p2);

        // Reglas del Blackjack
        if (p1 > 21) {
            System.out.println("Gana el Dealer");
        } else if (p2 > 21 || p1 > p2) {
            System.out.println("Gana el Jugador");
        } else if (p1 < p2) {
            System.out.println("Gana el Dealer");
        } else {
            System.out.println("Empate");
        }
    }
}