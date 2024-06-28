package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContadorPalabras {
    public static void main(String[] args) {
        Map<String, Integer> frecuencias = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("demo\\src\\libro.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] palabras = linea.split("\\s+"); // Dividir la línea en palabras
                for (String palabra : palabras) {
                    palabra = palabra.toLowerCase().replaceAll("[^a-zA-Záéíóúüñ]", ""); // Eliminar caracteres no alfabéticos
                    //palabra = palabra.toLowerCase().replaceAll("['?¡¿!-_:.;@#$%&/(){}[]<>\\|~`*+=^]", ""); // Eliminar caracteres no alfabéticos
                    if (!palabra.isEmpty()) {
                        frecuencias.put(palabra, frecuencias.getOrDefault(palabra, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Imprimir las frecuencias
        frecuencias.forEach((palabra, frecuencia) -> System.out.println(palabra + ": " + frecuencia));

        // Convertir el mapa a una lista de entradas para ordenarlas
        List<Map.Entry<String, Integer>> listaFrecuencias = new ArrayList<>(frecuencias.entrySet());

        // Ordenar la lista de entradas por valor (cantidad de ocurrencias) en orden descendente (de mayor a menor frecuencia)
        listaFrecuencias.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        // Tomar solo las primeras 10 palabras más frecuentes
        List<Map.Entry<String, Integer>> primeras10Frecuentes = listaFrecuencias.subList(0, Math.min(10, listaFrecuencias.size()));
        // usamos el Math.min por la lista es menor a 10 elementos.

        // Imprimir las palabras más frecuentes en la consola
        System.out.println("Las 10 palabras más frecuentes son:");
        for (Map.Entry<String, Integer> entry : primeras10Frecuentes) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
