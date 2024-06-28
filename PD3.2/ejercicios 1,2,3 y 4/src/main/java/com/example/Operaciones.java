package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import javax.swing.SwingUtilities;

public class Operaciones {
    // EJERCICIO 1
    public static void eliminarValoresNulosEnMap(){
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", null);
        map.put("key3", "value3");
        map.put("key4", null);

        // Eliminar todas las entradas con valores null (linea que los elimina)
        map.values().removeIf(Objects::isNull);

        System.out.println(map); // Imprimirá: {key1=value1, key3=value3
    }
    
    // EJERCICIO 2
    public static Map<String, String> invertirMapa(Map<String, String> original) throws IllegalArgumentException {
        Map<String, String> invertido = new HashMap<>();

        for (Map.Entry<String, String> entry : original.entrySet()) {
            String clave = entry.getKey();
            String valor = entry.getValue();

            if (invertido.containsKey(valor)) {
                throw new IllegalArgumentException("Valor duplicado encontrado: " + valor);
            }

            invertido.put(valor, clave);
        }

        return invertido;
    }

    public static void crearMapaParaInvertir(){
        try {
            Map<String, String> original = new HashMap<>();
            original.put("a", "1");
            original.put("b", "2");
            original.put("c", "3");

            Map<String, String> invertido = invertirMapa(original);
            System.out.println(invertido); // Imprimirá: {1=a, 2=b, 3=c}

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

    }

    // EJERCICIO 3
    public static void OrdenarCadenas(){
        Scanner scanner = new Scanner(System.in);
        List<String> cadenas = new ArrayList<>();
        
        System.out.println("Introduce cadenas de caracteres (para terminar introduce una cadena vacía):");
        String entrada;
        
        while (!(entrada = scanner.nextLine()).isEmpty()) {
            cadenas.add(entrada);
        }
        
        // Ordenar las cadenas primero por longitud y luego lexicográficamente
        Collections.sort(cadenas, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if (s1.length() != s2.length()) {
                    return Integer.compare(s1.length(), s2.length());
                } else {
                    return s1.compareTo(s2);
                }
            }
        });
        
        // Imprimir las cadenas ordenadas
        for (String cadena : cadenas) {
            System.out.println(cadena);
        }
        
        scanner.close();
    }

    // EJERCICIO 4
        public static void ejercicio4 (String[] args){

        if (args.length != 2) {
            //System.out.println("Uso: java RandomLinesPrinter <nombre_archivo> <num_lineas>");
            SwingUtilities.invokeLater(() -> ejercicio4( new String[]{"src/main/java/com/example/libro.txt", "10"})); // por si no le ingresamos los parametros en la consola
            return;
        }

        String nombreArchivo = args[0];
        int numLineasAImprimir;

        try {
            numLineasAImprimir = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("El segundo parámetro debe ser un número entero.");
            return;
        }

        File archivo = new File(nombreArchivo);
        if (!archivo.exists() || !archivo.isFile()) {
            System.out.println("El archivo especificado no existe o no es un archivo válido.");
            return;
        }

        // Calcular el tamaño promedio de una línea
        int tamañoPromedioLinea = Operaciones.calcularTamañoPromedioLinea(nombreArchivo);

        // Calcular el número estimado de líneas
        long tamañoArchivo = archivo.length();
        int numLineasEstimadas = (int) (tamañoArchivo / tamañoPromedioLinea);

        List<String> lineas = new ArrayList<>(numLineasEstimadas);

        // Leer el archivo y almacenar las líneas en la lista
        try (BufferedReader br = Files.newBufferedReader(Paths.get(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        // Verificar que el número de líneas a imprimir no exceda el número de líneas en el archivo
        if (numLineasAImprimir > lineas.size()) {
            System.out.println("El número de líneas a imprimir excede el número de líneas en el archivo.");
            return;
        }

        // Imprimir líneas aleatorias
        Collections.shuffle(lineas);
        for (int i = 0; i < numLineasAImprimir; i++) {
            System.out.println(lineas.get(i));
        }
    }

    private static int calcularTamañoPromedioLinea(String nombreArchivo){
        int tamañoTotal = 0;
        int numLineas = 0;
        int numLineasParaCalcular = 100; // Leer 100 líneas para calcular el promedio.
        try (BufferedReader br = Files.newBufferedReader(Paths.get(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null && numLineas < numLineasParaCalcular) {
                tamañoTotal += linea.length();
                numLineas++;
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return 0;
        }
        return tamañoTotal / numLineas;
    }
}
