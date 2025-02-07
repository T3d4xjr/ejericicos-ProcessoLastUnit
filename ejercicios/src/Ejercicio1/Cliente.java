/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author DAM1
 */
public class Cliente {
    public static void main(String[] args) {
        String host="localhost";
        int puerto=4000;
        
        try(Socket socket=new Socket(host, puerto)) {
            System.out.println("Cliente iniciado");
            
            PrintWriter pw=new PrintWriter(socket.getOutputStream(),true);
            BufferedReader  br=new BufferedReader(new InputStreamReader(System.in));
            BufferedReader  br2=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            String mensaje;
            do {           
                System.out.println("Escribe un mensaje");
                mensaje =br.readLine();
                pw.println(mensaje);
                
                String mensajeDevuelto =br2.readLine();
                System.out.println(mensajeDevuelto);
            } while (!mensaje.equalsIgnoreCase("salir"));
            System.out.println("Cliente salio");
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
