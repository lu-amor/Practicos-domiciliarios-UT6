package com.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int[] clavesInsertar = ManejadorArchivosGenerico.leerClaves("demo\\src\\main\\java\\com\\example\\claves_insertar.txt");
        int[] clavesBuscar = ManejadorArchivosGenerico.leerClaves("demo\\src\\main\\java\\com\\example\\claves_buscar.txt");

        double[] factoresCarga = {0.7, 0.75, 0.8, 0.85, 0.9, 0.91, 0.92, 0.93, 0.94, 0.95, 0.96, 0.97, 0.98, 0.99};

        for (double factorCarga : factoresCarga) {
            int tamañoTabla = (int) (clavesInsertar.length / factorCarga);
            TablaHash tablaHash = new TablaHash(tamañoTabla);

            ArrayList<Integer> comparacionesInsercion = new ArrayList<>();
            ArrayList<Integer> comparacionesExitosas = new ArrayList<>();
            ArrayList<Integer> comparacionesInfructuosas = new ArrayList<>();

            for (int clave : clavesInsertar) {
                int comparaciones = tablaHash.insertar(clave);
                comparacionesInsercion.add(comparaciones);
            }

            for (int clave : clavesBuscar) {
                int comparaciones = tablaHash.buscar(clave);
                if (tablaHash.buscar(clave) == 0) {
                    comparacionesInfructuosas.add(comparaciones);
                } else {
                    comparacionesExitosas.add(comparaciones);
                }
            }

            double promedioInsercion = comparacionesInsercion.stream().mapToInt(Integer::intValue).average().orElse(0);
            double promedioExitosas = comparacionesExitosas.stream().mapToInt(Integer::intValue).average().orElse(0);
            double promedioInfructuosas = comparacionesInfructuosas.stream().mapToInt(Integer::intValue).average().orElse(0);

            System.out.printf("Factor de carga: %.2f\n", factorCarga);
            System.out.printf("Promedio de comparaciones para inserciones: %.2f\n", promedioInsercion);
            System.out.printf("Promedio de comparaciones para búsquedas exitosas: %.2f\n", promedioExitosas);
            System.out.printf("Promedio de comparaciones para búsquedas infructuosas: %.2f\n\n", promedioInfructuosas);
            
            /*
             * System.out.printf es similar a System.out.println, 
             * pero con la capacidad adicional de formatear la salida.
             * Permite especificar un formato detallado para la salida de texto.
             * 
             * Explicación del formato %.2f:
             * % es el carácter de inicio de una especificación de formato.
             * .2f es una especificación de formato para un número de coma flotante (float o double) con dos decimales.
             * . indica que se especificará el número de decimales a mostrar.
             * 2 significa que se mostrarán dos cifras decimales.
             * f significa que se está formateando un número de coma flotante
             */
        }
    }
}