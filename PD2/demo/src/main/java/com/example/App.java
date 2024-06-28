package com.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        
        THash tabla = new THash(223, 0.99);
        ManejadorArchivosGenerico manager = new ManejadorArchivosGenerico();
        String nombreArchivoInsertar = "demo/src/main/java/com/example/claves_insertar.txt";
        String[] clavesInsertar = manager.leerArchivo(nombreArchivoInsertar);
        int compInsertar = 0;
        int promedioInsertar = 0;
        for (int i = 0; i < clavesInsertar.length; i++) {
            int clave = Integer.parseInt(clavesInsertar[i]);
            int comp = tabla.insertar(clave);
            compInsertar += comp;
            promedioInsertar++;
        }
        
        String nombreArchivoBuscar = "demo/src/main/java/com/example/claves_buscar.txt";
        String[] clavesBuscar = manager.leerArchivo(nombreArchivoBuscar);
        int compExitosas = 0;
        int contExitosas = 0;
        int compNoExitosas = 0;
        int contNoExitosas = 0;
        int promedioBuscar = 0;
        for (int i = 0; i < clavesBuscar.length; i++) {
            int clave = Integer.parseInt(clavesBuscar[i]);
            int comparaciones = tabla.buscar(clave);
            boolean encontrado = comparaciones > 0;
            comparaciones = Math.abs(comparaciones);
            if (encontrado) {
                compExitosas += comparaciones;
                contExitosas++;
            } else {
                compNoExitosas += comparaciones;
                contNoExitosas++;
            }
            promedioBuscar++;
            System.out.println("Clave: " + clave + " Comparaciones: " + comparaciones + " Encontrado: " + encontrado);
        }

        System.out.println("Comparaciones inserción: " + compInsertar + " Promedio: " + compInsertar/promedioInsertar);
        System.out.println("Comparaciones exitosas: " + compExitosas + " Promedio: " + compExitosas/contExitosas);
        System.out.println("Comparaciones no exitosas: " + Math.abs(compNoExitosas) + " Promedio: " + compNoExitosas/contNoExitosas);
        System.out.println("Promedio búsqueda: " + promedioBuscar/clavesBuscar.length);
    }
}
