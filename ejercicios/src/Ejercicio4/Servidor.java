package Ejercicio4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Servidor {

    public static void main(String[] args) {
        int puerto = 4000;

        try (ServerSocket srv = new ServerSocket(puerto)) {
            System.out.println("Servidor Iniciado");

            while (true) { 
                try (Socket socket = srv.accept()) {
                    System.out.println("Cliente conectado");
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                    String palabras = "hola perro gato manzana platano";
                    String[] palabrasSplit = palabras.split(" ");
                    Random random = new Random();
                    int palabra = random.nextInt(palabrasSplit.length);
                    String palabraAdivina = palabrasSplit[palabra];
                    String palabraCodificada = "";
                    for (int i = 0; i < palabraAdivina.length(); i++) {
                        palabraCodificada += "_ ";
                    }   System.out.println("La palabra a adivinar es: " + palabraAdivina);
                    String letra;
                    boolean juegoTerminado = false;
                    while (!juegoTerminado) {
                        pw.println("Palabra codificada: " + palabraCodificada);
                        pw.println("Introduce una letra:");
                        
                        letra = br.readLine();
                        
                        String nuevaPalabraCodificada = "";
                        boolean acierto = false;
                        
                        for (int i = 0; i < palabraAdivina.length(); i++) {
                            if (palabraAdivina.charAt(i) == letra.charAt(0)) {
                                nuevaPalabraCodificada += letra + " ";
                                acierto = true;
                            } else {
                                nuevaPalabraCodificada += palabraCodificada.charAt(i * 2) + " ";
                            }
                        }
                        
                        palabraCodificada = nuevaPalabraCodificada.trim();
                        
                        if (!palabraCodificada.contains("_")) {
                            pw.println("¡Has adivinado la palabra! La palabra es: " + palabraCodificada);
                            pw.println("¿Quieres jugar otra vez? (sí/no)");
                            if (br.readLine().equalsIgnoreCase("no")) {
                                juegoTerminado = true;
                            } else {
                                // Volver a empezar con otra palabra
                                palabraAdivina = palabrasSplit[random.nextInt(palabrasSplit.length)];
                                palabraCodificada = "_ ".repeat(palabraAdivina.length()).trim();
                            }
                        }
                    }
                    // Cerrar la conexión con el cliente
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
