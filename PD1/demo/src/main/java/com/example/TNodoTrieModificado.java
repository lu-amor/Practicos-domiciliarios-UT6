package com.example;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

public class TNodoTrieModificado implements Serializable {

    // private static final int CANT_CHR_ABECEDARIO = 26;
    //private TNodoTrie[] hijos;
    private HashMap<Character, TNodoTrieModificado> hijos;
    private boolean esPalabra;

    public TNodoTrieModificado() {
        hijos = new HashMap<>();
        //hijos = new TNodoTrie[CANT_CHR_ABECEDARIO];
        esPalabra = false;
    }

    /*public void insertar(String unaPalabra) {
        TNodoTrie nodo = this;
        for (int c = 0; c < unaPalabra.length(); c++) {
            int indice = unaPalabra.charAt(c) - 'a';
            if (nodo.hijos[indice] == null) {
                nodo.hijos[indice] = new TNodoTrie();
            }
            nodo = nodo.hijos[indice];
        }
        nodo.esPalabra = true;
    }*/

    public void insertar(String unaPalabra) {
        TNodoTrieModificado nodo = this;
        for (int c = 0; c < unaPalabra.length(); c++) {
            char ch = unaPalabra.charAt(c);
            nodo.hijos.putIfAbsent(ch, new TNodoTrieModificado());
            nodo = nodo.hijos.get(ch);
        }
        nodo.esPalabra = true;
    }

    /*private void imprimir(String s, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                System.out.println(s);
            }
            for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
                if (nodo.hijos[c] != null) {
                    imprimir(s + (char) (c + 'a'), nodo.hijos[c]);
                }
            }
        }
    }*/

    private void imprimir(String s, TNodoTrieModificado nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                System.out.println(s);
            }
            for (Character ch : nodo.hijos.keySet()) {
                imprimir(s + ch, nodo.hijos.get(ch));
            }
        }
    }

    public void imprimir() {
        imprimir("", this);
    }

    /*private void predecir(String s, String prefijo, LinkedList<String> palabras, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                palabras.add(prefijo + s);
            }
            for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
                if (nodo.hijos[c] != null) {
                    predecir(s + (char) (c + 'a'), prefijo, palabras, nodo.hijos[c]);
                }
            }
        }
    }*/

    private void predecir(String s, String prefijo, LinkedList<String> palabras, TNodoTrieModificado nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                palabras.add(prefijo + s);
            }
            for (Character ch : nodo.hijos.keySet()) {
                predecir(s + ch, prefijo, palabras, nodo.hijos.get(ch));
            }
        }
    }


    public void predecir(String prefijo, LinkedList<String> palabras) {
        TNodoTrieModificado nodo = buscarNodoTrie(prefijo);
        predecir("", prefijo, palabras, nodo);
    }

    /*private TNodoTrie buscarNodoTrie(String s) {
        TNodoTrie nodo = this;
        for (int c = 0; c < s.length(); c++) {
            int indice = s.charAt(c) - 'a';
            if (nodo.hijos[indice] == null) {
                return null;
            }
            nodo = nodo.hijos[indice];
        }
        return nodo;
    }*/

    private TNodoTrieModificado buscarNodoTrie(String s) {
        TNodoTrieModificado nodo = this;
        for (int c = 0; c < s.length(); c++) {
            char ch = s.charAt(c);
            if (!nodo.hijos.containsKey(ch)) {
                return null;
            }
            nodo = nodo.hijos.get(ch);
        }
        return nodo;
    }


    /*public int buscar(String s) {
        TNodoTrie nodo = this;
        int temp = 0;
        for (int c = 0; c < s.length(); c++) {
            int indice = s.charAt(c) - 'a';
            temp++;
            if (nodo.hijos[indice] == null) {
                return -temp;
            }
            nodo = nodo.hijos[indice];
        }
        if (nodo.esPalabra) {
            return temp;
        }
        return -temp;
    }*/

    public int buscar(String s) {
        TNodoTrieModificado nodo = this;
        int temp = 0;
        for (int c = 0; c < s.length(); c++) {
            char ch = s.charAt(c);
            temp++;
            if (!nodo.hijos.containsKey(ch)) {
                return -temp;
            }
            nodo = nodo.hijos.get(ch);
        }
        if (nodo.esPalabra) {
            return temp;
        }
        return -temp;
    }
}


