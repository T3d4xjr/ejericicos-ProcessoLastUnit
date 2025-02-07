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
import java.util.Random;

/**
 *
 * @author tedax
 */
public class ManejadorCliente extends Thread {

    private Socket socket;
    private int numeroAleatorio;

    public ManejadorCliente(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true)) {

            String mensaje;
            boolean jugando = true;

            while (jugando && (mensaje = br.readLine()) != null) {
                
                if (mensaje.equalsIgnoreCase("SALIR")) {
                    System.out.println("Cliente desconectado");
                    jugando = false;
                    break;
                }
                if (mensaje.equalsIgnoreCase("INICIAR")) {

                    numeroAleatorio = new Random().nextInt(100) + 1;
                    System.out.println("Número generado es: " + numeroAleatorio);
                    pw.println("NUMERO_ELEGIDO");
                    boolean acierto = false;
                    while (!acierto) {
                        String mensajeNumero = br.readLine();
                        if (mensajeNumero == null) {
                            jugando = false;
                            break;
                        }
                        try {
                            int numeroUsuario = Integer.parseInt(mensajeNumero);
                            if (numeroUsuario < numeroAleatorio) {
                                pw.println("MAS_ALTO");
                            } else if (numeroUsuario > numeroAleatorio) {
                                pw.println("MAS_BAJO");
                            } else {
                                pw.println("CORRECTO");
                                acierto = true;
                                
                            }
                        } catch (NumberFormatException e) {
                            pw.println("ERROR: Ingresa un número válido.");
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
