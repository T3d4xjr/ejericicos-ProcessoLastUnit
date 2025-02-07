/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio4;

import java.util.Random;

/**
 *
 * @author DAM1
 */
public class prueba {
    public static void main(String[] args) {
        
        String palabras="hola perro gato manzana platano";
        
        String[] palabrasSplit=palabras.split(" ");
        
        Random random =new Random();
        
        int palabra=random.nextInt(palabrasSplit.length);
        
        System.out.println("La palabra a adivinar es: "+palabrasSplit[palabra]);
    }
    
}
