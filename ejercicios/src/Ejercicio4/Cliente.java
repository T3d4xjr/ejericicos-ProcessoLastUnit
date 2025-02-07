/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author DAM1
 */
public class Cliente {

    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 4000;
        try (Socket socket = new Socket(host, puerto)) {
            System.out.println("Cliente iniciado");

            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            BufferedReader br2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String letra;
            boolean continuar = true;

            while (continuar) {
                String mensajeRecibido = br2.readLine();
                System.out.println(mensajeRecibido);

                System.out.println("Introduce una letra:");
                letra = br.readLine();

                while (letra.length() != 1) {
                    System.out.println("Debes introducir solo una letra: ");
                    letra = br.readLine();
                }

                pw.println(letra);

                mensajeRecibido = br2.readLine();
                System.out.println(mensajeRecibido);

                if (mensajeRecibido.contains("¿Quieres jugar otra vez?")) {
                    System.out.println("¿Quieres jugar otra vez? (sí/no)");
                    String respuesta = br.readLine();
                    if (respuesta.equalsIgnoreCase("no")) {
                        continuar = false;
                    }
                }
            }
            socket.close();

        } catch (Exception e) {
        }

    }
}
