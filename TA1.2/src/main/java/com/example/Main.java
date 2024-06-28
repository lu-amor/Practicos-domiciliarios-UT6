package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jechague
 */

public class Main {

    public static void main(String[] args) {

         // Crear una tabla de tipo THash e insertar las claves del archivo "claves_insertar.txt"

        int tamañoTabla = 220; // Tamaño de la tabla
        TablaHash tablaHash = new TablaHash(tamañoTabla);
        ArrayList<Integer> comparacionesExitosas = new ArrayList<>();
        ArrayList<Integer> comparacionesInfructuosas = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("src\\main\\java\\com\\example\\claves_insertar.txt"));
            String linea;
            while ((linea = br.readLine()) != null) {
                int clave = Integer.parseInt(linea.trim());
                tablaHash.insertar(clave);
            }
            br.close();
            System.out.println("Claves insertadas.");

            // Buscar en la tabla creada anteriormente las claves indicadas en el archivo "claves_buscar.txt"
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src\\main\\java\\com\\example\\claves_buscar.txt"));
            String linea2;

            while ((linea2 = bufferedReader.readLine()) != null) {
                int claveBuscada = Integer.parseInt(linea2.trim());
                int comparaciones = tablaHash.buscar(claveBuscada);
                if (comparaciones != -1) {
                    comparacionesExitosas.add(comparaciones);
                    System.out.println("Clave " + claveBuscada + " encontrada. Comparaciones: " + comparaciones);
                } else {
                    comparacionesInfructuosas.add(comparaciones);
                    System.out.println("Clave " + claveBuscada + " no encontrada. Comparaciones: " + comparaciones);
                }
            }
            bufferedReader.close();

            double promedioExitosas = comparacionesExitosas.stream().mapToInt(Integer::intValue).average().orElse(0);
            double promedioInfructuosas = comparacionesInfructuosas.stream().mapToInt(Integer::intValue).average().orElse(0);

            System.out.println("Promedio de comparaciones para búsquedas exitosas: " + promedioExitosas);
            System.out.println("Promedio de comparaciones para búsquedas infructuosas: " + promedioInfructuosas);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

