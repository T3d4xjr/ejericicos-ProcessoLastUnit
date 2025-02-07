/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author tedax
 */
public class Servidor {

    public static void main(String[] args) {
        int puerto = 4000;

        try (ServerSocket srv = new ServerSocket(puerto)) {
            System.out.println("Servidor iniciado");
            Socket socket = srv.accept();

            System.out.println("Esperando al cliente");

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            String mensaje;

            do {
                mensaje = br.readLine();

                String[] mensajeSeparado = mensaje.split(" ");

                int operando1 = Integer.parseInt(mensajeSeparado[0]);
                String operador=mensajeSeparado[1];
                int operando2 = Integer.parseInt(mensajeSeparado[2]);

                if (null == operador) {
                    pw.println("No escribiste un buen formato para esta calculadora");
                } else switch (operador) {
                    case "+" -> {
                        int suma = operando1 + operando2;
                        pw.println(suma);
                    }
                    case "-" -> {
                        int resta = operando1 - operando2;
                        pw.println(resta);
                    }
                    case "*" -> {
                        int multiplicacion = operando1 * operando2;
                        pw.println(multiplicacion);
                    }
                    case "/" -> {
                        int dividir = operando1 / operando2;
                        pw.println(dividir);
                    }
                    default -> pw.println("No escribiste un buen formato para esta calculadora");
                }

            } while (!mensaje.equalsIgnoreCase("salir"));

            System.out.println("Servidor desconectado");
        } catch (IOException e) {
        }
    }
}
