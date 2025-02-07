/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author DAM1
 */
public class Servidor {
    public static void main(String[] args) {
        int puerto =4000;
        
        try (ServerSocket  srv=new ServerSocket(puerto)){
            System.out.println("Servidor iniciado");
            Socket socket=srv.accept();
            
            System.out.println("Cliente conectado");
            
            PrintWriter pw =new PrintWriter(socket.getOutputStream(),true);
            BufferedReader br =new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            String mensaje;
            do {           
                mensaje=br.readLine();
                System.out.println("Servidor: -> "+mensaje);
                pw.println("Recibido: "+mensaje.toUpperCase());
                
            } while (!mensaje.equalsIgnoreCase("salir"));
            
            System.out.println("Cliente fuera");
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
