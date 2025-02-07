    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 4000;

        try (Socket socket = new Socket(host, puerto)) {
            System.out.println("Cliente conectado");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader br2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String mensaje;

            do {
                System.out.println("Introduce una operación en formato: operando1 operador operando2 (o 'salir' para terminar): ");
                mensaje = br.readLine();
                pw.println(mensaje);

                if (!mensaje.equalsIgnoreCase("salir")) {
                    String mensajeDevuelto = br2.readLine(); 
                    System.out.println("Resultado: " + mensajeDevuelto);
                }

            } while (!mensaje.equalsIgnoreCase("salir"));

            System.out.println("Cliente desconectado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
