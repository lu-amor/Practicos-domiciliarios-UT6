package com.example;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String nombreCompletoArchivo = "demo/src/main/java/com/example/libro.txt";
        ManejadorArchivosGenerico manager = new ManejadorArchivosGenerico();
        String[] listaLineasArchivo = manager.leerArchivo(nombreCompletoArchivo);
        HashMap<String, Integer> ocurrencias = new HashMap<String, Integer>();
        for (String linea : listaLineasArchivo) {
            String[] palabras = linea.split(" ");
            for (String palabra : palabras) {
                palabra = palabra.toLowerCase().replaceAll("\\p{Punct}$", "");
                if (ocurrencias.containsKey(palabra)) {
                    ocurrencias.put(palabra, ocurrencias.get(palabra) + 1);
                } else {
                    ocurrencias.put(palabra, 1);
                }
            }
        }
        PriorityQueue<Map.Entry<String, Integer>> cola = new PriorityQueue<>(
            new Comparator<Map.Entry<String, Integer>>() {
                public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                    return b.getValue().compareTo(a.getValue());
                }
            }
        );
        cola.addAll(ocurrencias.entrySet());
        for (int i = 0; i < 10 && !cola.isEmpty(); i++) {
            Map.Entry<String, Integer> entry = cola.poll();
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
