    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio2;

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
        try (Socket socket =new Socket(host, puerto);){
            System.out.println("Cliente conectado");
            //Primer reader lee el mensaje que escribe al usuario
            BufferedReader  br=new BufferedReader(new InputStreamReader(System.in));
            //printwriter para mandarle al servidor ese mensaje
            PrintWriter pw=new PrintWriter(socket.getOutputStream(),true);
            //Segundo reader mensaje que devuelve el servidor al cliente como respuesta.
            BufferedReader  br2=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            String mensaje;
            do {                
                System.out.println("Introduce un numero");
           
                mensaje=br.readLine();
                pw.println(mensaje);
                
                String mensajeDevuelto=br2.readLine();
                System.out.println(mensajeDevuelto);
                
            } while (mensaje.equals("Acertaste el numero"));
            
            System.out.println("Cliente desconectado");
            
        } catch (IOException e) {
        }
        
    }
}
