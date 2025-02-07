/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author tedax
 */
public class Servidor {
    private static final int PUERTO = 4000;
    
    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado");
            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado ");
                new ManejadorCliente(cliente).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

