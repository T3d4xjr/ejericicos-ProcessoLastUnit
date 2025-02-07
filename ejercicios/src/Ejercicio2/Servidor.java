/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.Random;
public class Servidor {
    public static void main(String[] args) {
        int puerto=4000;
        
        try(ServerSocket srv=new ServerSocket(puerto)) {
            System.out.println("Servidor iniciado");
            Socket socket=srv.accept();
            System.out.println("Cliente conectado");
           
            //Leer el mensaje que nos escribe el cliente
            BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            //Escribir una resspuesta del mensaje que nos envio
            PrintWriter pw =new PrintWriter(socket.getOutputStream(),true);
            
            Random random =new Random();
            int numeroAleatorio=random.nextInt(1, 101);
            System.out.println("El numero a acertar es "+numeroAleatorio);
            boolean acierto=false;
            
            String mensaje;
            
            do { 
                mensaje=br.readLine();
                int numeroUsuario=Integer.parseInt(mensaje);
                
                if(numeroUsuario <numeroAleatorio){
                    pw.println("El numero es mas alto");
                }else if(numeroUsuario>numeroAleatorio){
                    pw.println("El numero es mas bajo");
                }else{
                    pw.println("Acertaste el numero");
                    acierto=true;
                }
            } while (!acierto);
            
            System.out.println("Servidor desconectando.");
        } catch (Exception e) {
        }
    }
}
