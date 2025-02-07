/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author tedax
 */
public class Cliente {

    private static final String host = "localhost";
    private static final int puerto = 4000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(host, puerto); BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter pw = new PrintWriter(socket.getOutputStream(), true); Scanner scanner = new Scanner(System.in)) {

            System.out.println("Conectado al servidor.");
            String mensaje;
            boolean jugando = true;
            System.out.println("Escribe 'INICIAR' para empezar o 'SALIR' para desconectar:");
            
                mensaje = scanner.nextLine().trim().toUpperCase();
                pw.println(mensaje);
            while (jugando) {


                if (mensaje.equalsIgnoreCase("SALIR")) {
                    System.out.println("Desconectado del servidor.");
                    break;
                }
                String respuesta = br.readLine();
                if (respuesta.equals("NUMERO_ELEGIDO")) {
                    System.out.println("El juego ha comenzado. Adivina el número entre 1 y 100.");
                    boolean acierto = false;
                    while (!acierto) {
                        System.out.print("Introduce un número: ");
                        mensaje = scanner.nextLine().trim();

                        try {
                            pw.println(mensaje);
                        } catch (NumberFormatException e) {
                            System.out.println("Por favor, introduce un número válido.");
                            continue;
                        }

                        respuesta = br.readLine();
                        if (respuesta != null) {
                            System.out.println("Servidor: " + respuesta);

                            if (respuesta.equals("CORRECTO")) {
                                System.out.println("¡Has acertado!");
                                acierto = true;
                                System.out.println("¿Quieres jugar otra vez? (INICIAR/SALIR)");
                                mensaje = scanner.nextLine().trim().toUpperCase();
                                pw.println(mensaje);
                                if (!mensaje.equalsIgnoreCase("INICIAR")) {
                                    jugando = false;
                                }
                            }
                        }
                    }
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
