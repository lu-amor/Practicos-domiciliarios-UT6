package com.example;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args){
        PD3();
        PD7();
    }

    private static void PD3(){
        TTrieHashMap trie = new TTrieHashMap();

        /*String[] palabrasclave = ManejadorArchivosGenerico.leerArchivo("./src/palabras1.txt");
        for (String p : palabrasclave) {
                trie.insertar(p);
        }
        trie.imprimir(); */  
        

        // Leer y agregar las palabras del índice al trie
        String[] palabrasIndice = ManejadorArchivosGenerico.leerArchivo("src\\main\\java\\com\\example\\PalabrasIndice.txt");
        for (String palabra : palabrasIndice) {
            trie.insertar(palabra.toLowerCase());
        }

        // Indizar el libro "libro.txt"
        trie.indizarLibro("src\\main\\java\\com\\example\\libro.txt");

        // Imprimir el índice del libro
        trie.imprimirIndice();
    }

    private static void PD7(){
        TArbolTrieHashMapTelefonos trieAbonados = new TArbolTrieHashMapTelefonos();

       // CARGAR EN EL TRIE LOS TELÉFONOS Y NOMBRES A PARTIR DEL ARCHIVO ABONADOS.TXT
        String[] abonados = ManejadorArchivosGenerico.leerArchivo("src\\main\\java\\com\\example\\abonados.txt");
        for (String abonado : abonados) {
            String[] datos = abonado.split(",");
            TAbonado abonadoObj = new TAbonado(datos[1],datos[0] );
            trieAbonados.insertar(abonadoObj);
        }

        
        // LEER EL ARCHIVO DE CÓDIGOS
        String[] codigos = ManejadorArchivosGenerico.leerArchivo("src\\main\\java\\com\\example\\codigos1.txt");
        String codigoPais = null;
        String codigoArea = null;
        for (String codigo : codigos) {
            String[] datos = codigo.split(": ");
            if (datos[0].trim().equals("CODIGO PAIS")) {
                codigoPais = datos[1].trim();
            } else if (datos[0].trim().equals("CODIGO AREA")) {
                codigoArea = datos[1].trim();
            }
        }

        // VERIFICAR QUE SE HAYAN LEÍDO LOS CÓDIGOS
        if (codigoPais == null || codigoArea == null) {
            System.out.println("No se pudieron leer los códigos de país y/o área.");
            return;
        }

        // BUSCAR LOS ABONADOS EN EL TRIE
        LinkedList<TAbonado> abonadosEncontrados = trieAbonados.buscarTelefonos(codigoPais, codigoArea);

        if (abonadosEncontrados == null || abonadosEncontrados.isEmpty()) {
            System.out.println("No se encontraron abonados para el código país " + codigoPais + " y código área " + codigoArea);
            return;
        }

        // ORDENAR LOS ABONADOS ALFABÉTICAMENTE POR NOMBRE
        Collections.sort(abonadosEncontrados, (a1, a2) -> a1.getNombre().compareTo(a2.getNombre()));


        
        // CREAR EL ARCHIVO "salida.txt" CON LOS ABONADOS CORRESPONDIENTES AL PAÌS Y ÀREA
        List<String> lineasSalida = new LinkedList<>();
        // 1 POR LÌNEA
        for (TAbonado abonado : abonadosEncontrados) {
            // IMPRIMIR NOMBRE Y TELÈFONO
            lineasSalida.add(abonado.getNombre() + ": " + abonado.getTelefono());
        }

        int tamaño = lineasSalida.size();
        ManejadorArchivosGenerico.escribirArchivo("salida.txt", lineasSalida.toArray(new String[tamaño]));
        
    }
}