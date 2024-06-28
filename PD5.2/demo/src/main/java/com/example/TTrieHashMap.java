package com.example;

import java.util.LinkedList;
import java.util.StringTokenizer;

public class TTrieHashMap implements IArbolTrie {

    private TNodoTrieHashMap raiz;

    @Override
    public void insertar(String palabra) {
        if (raiz == null) {
            raiz = new TNodoTrieHashMap();
        }
        raiz.insertar(palabra);
    }

    public void insertarConPagina(String palabra, int pagina) {
        if (raiz == null) {
            raiz = new TNodoTrieHashMap();
        }
        raiz.insertarConPagina(palabra, pagina);
    }

    @Override
    public void imprimir() {
        if (raiz != null) {
            raiz.imprimir();
        }
    }

    @Override
    public int buscar(String palabra) {
        return this.raiz.buscar(palabra);
    }

    @Override
    public LinkedList<String> predecir(String prefijo) {
        LinkedList<String> palabras = new LinkedList<>();
        raiz.predecir(prefijo, palabras);
        return palabras;
    }

    public void indizarLibro(String nombreArchivo) {
        String[] lineas = ManejadorArchivosGenerico.leerArchivo(nombreArchivo);
        int numeroPagina = 1;
        int contadorLineas = 0;

        for (String linea : lineas) {
            StringTokenizer tokenizer = new StringTokenizer(linea, " ,.;:!?()[]\"\'-");
            while (tokenizer.hasMoreTokens()) {
                String palabra = tokenizer.nextToken().toLowerCase();
                insertarConPagina(palabra, numeroPagina);
            }
            contadorLineas++;
            if (contadorLineas == 50) {
                contadorLineas = 0;
                numeroPagina++;
            }
        }
    }

    public void imprimirIndice() {
        if (raiz != null) {
            raiz.imprimirIndice();
        }
    }

}
