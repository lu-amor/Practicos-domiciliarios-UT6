package com.example;
import java.util.HashMap;
import java.util.LinkedList;

public class TNodoTrieHashMap implements INodoTrie {

    private HashMap<Character, TNodoTrieHashMap> hijos;
    private boolean esPalabra;
    private LinkedList<Integer> paginas;

    public TNodoTrieHashMap() {
        hijos = new HashMap<>();
        esPalabra = false;
        paginas = new LinkedList<>();
    }

    @Override
    public void insertar(String palabra) {
        TNodoTrieHashMap nodo = this;
        for (char caracter : palabra.toCharArray()) {
            nodo = nodo.hijos.computeIfAbsent(caracter, k -> new TNodoTrieHashMap());
        }
        nodo.esPalabra = true;
    }

    public void insertarConPagina(String palabra, int pagina) {
        TNodoTrieHashMap nodo = this;
        for (char caracter : palabra.toCharArray()) {
            nodo = nodo.hijos.computeIfAbsent(caracter, k -> new TNodoTrieHashMap());
        }
        nodo.esPalabra = true;
        if (!nodo.paginas.contains(pagina)) {
            nodo.paginas.add(pagina);
        }
    }


    @Override
    public int buscar(String s) {
        int[] comparaciones = {0};
        TNodoTrieHashMap nodo = this.buscar(s, comparaciones);
        return nodo != null && nodo.esPalabra ? comparaciones[0] : 0;
    }

    public TNodoTrieHashMap buscar(String palabra, int[] comparaciones) {
        TNodoTrieHashMap nodo = this;
        for (char caracter : palabra.toCharArray()) {
            nodo = nodo.hijos.get(caracter);
            comparaciones[0]++;
        }
        return nodo;
    }

    @Override
    
    public void predecir(String prefijo, LinkedList<String> palabras) {
        TNodoTrieHashMap nodo = this;
        for (char caracter : prefijo.toCharArray()) {
            nodo = nodo.hijos.get(caracter);
            if (nodo == null) {
                return;
            }
        }

        if (nodo.esPalabra) {
            palabras.add(prefijo);
        }

        for (Character caracter : nodo.hijos.keySet()) {
            nodo.hijos.get(caracter).predecir(prefijo + caracter, palabras);
        }
    }

    public LinkedList<String> predecirConRetorno(String prefijo, LinkedList<String> palabras) {
        TNodoTrieHashMap nodo = this;
        for (char caracter : prefijo.toCharArray()) {
            nodo = nodo.hijos.get(caracter);
            if (nodo == null) {
                return palabras;
            }
        }

        if (nodo.esPalabra) {
            palabras.add(prefijo);
        }

        for (Character caracter : nodo.hijos.keySet()) {
            nodo.hijos.get(caracter).predecir(prefijo + caracter, palabras);
        }

        return palabras;
    }


    public LinkedList<Integer> buscarPaginas(String palabra) {
        TNodoTrieHashMap nodo = this;
        for (char caracter : palabra.toCharArray()) {
            nodo = nodo.hijos.get(caracter);
            if (nodo == null) {
                return new LinkedList<>();
            }
        }
        return nodo.paginas;
    }

    @Override
    public void imprimir() {
        imprimir("", this);
    }

    private void imprimir(String s, TNodoTrieHashMap nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                System.out.println(s);
            }
            for (Character c : nodo.hijos.keySet()) {
                imprimir(s + c, nodo.hijos.get(c));
            }
        }
    }

    public void imprimirIndice() {
        imprimirIndice("", this);
    }

    private void imprimirIndice(String s, TNodoTrieHashMap nodo) {
        if (nodo.esPalabra) {
            System.out.print(s + ": ");
            for (Integer pagina : nodo.paginas) {
                System.out.print(pagina + " ");
            }
            System.out.println();
        }
        for (Character caracter : nodo.hijos.keySet()) {
            imprimirIndice(s + caracter, nodo.hijos.get(caracter));
        }
    }
}

