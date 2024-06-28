package com.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TArbolTrieHashMap implements Serializable {
    private final TNodoTrieHashMap raiz;

    public TArbolTrieHashMap() {
        raiz = new TNodoTrieHashMap(' ');
    }
    
    public TArbolTrieHashMap insertar(String palabra) {
        TNodoTrieHashMap actual = raiz;
        HashMap<Character, TNodoTrieHashMap> hijos = actual.getHijos();
        for(char c : palabra.toCharArray()) {
            if (hijos.containsKey(c)) {
                actual = hijos.get(c);
            } else {
                actual = new TNodoTrieHashMap(c);
                hijos.put(c, actual);
            }
            hijos = actual.getHijos();
        }
        actual.setPalabra(true);
        return this;
    }

    public boolean contiene(String palabra) {
        TNodoTrieHashMap ultimoNodo = buscar(palabra);
        return ultimoNodo != null && ultimoNodo.esPalabra();
    }

    private TNodoTrieHashMap buscar(String palabra) {
        HashMap<Character, TNodoTrieHashMap> hijos = raiz.getHijos();
        TNodoTrieHashMap actual = null;
        for(char c : palabra.toCharArray()) {
            if (hijos.containsKey(c)) {
                actual = hijos.get(c);
                hijos = actual.getHijos();
            } else {
                return null;
            }
        }
        return actual;
    }

    public void eliminar(String palabra) {
        List<TNodoTrieHashMap> lista = new ArrayList<>();
        HashMap<Character, TNodoTrieHashMap> hijos = raiz.getHijos();
        for (char c : palabra.toCharArray()) {
            if (!hijos.containsKey(c)) {
                break;
            }
            TNodoTrieHashMap nodo = hijos.get(c);
            hijos = nodo.getHijos();
            lista.add(nodo);
        }
        if (lista.isEmpty() || lista.size() != palabra.length()) {
            return;
        }
        lista.get(lista.size() - 1).setPalabra(false);
        for (int i = lista.size() - 1; i >= 0; i--) {
            TNodoTrieHashMap actual = lista.get(i);
            if (actual.getHijos().isEmpty() && !actual.esPalabra()) {
                lista.get(i - 1).getHijos().remove(actual.getCaracter());
            } else if (actual.esPalabra()) {
                break;
            }
        }
    }

    public boolean contienePrefijo(String prefijo) {
        return buscar(prefijo) != null;
    }

    public List<String> predecir(String prefijo) {
        List<String> palabras = new ArrayList<>();
        TNodoTrieHashMap nodo = buscar(prefijo);
        if (nodo != null) {
            insertarPalabras(nodo, prefijo, palabras);
        }
        return palabras;
    }

    private void insertarPalabras(TNodoTrieHashMap nodo, String palabra, List<String> palabras) {
        if (nodo.esPalabra()) {
            palabras.add(palabra);
        }
        for (TNodoTrieHashMap hijo : nodo.getHijos().values()) {
            insertarPalabras(hijo, palabra + hijo.getCaracter(), palabras);
        }
    }

    public List<Integer> buscarPatrones(String texto) {
        List<Integer> posiciones = new ArrayList<>();
        for (int i = 0; i < texto.length(); i++) {
            TNodoTrieHashMap actual = raiz;
            for (int j = i; j < texto.length(); j++) {
                HashMap<Character, TNodoTrieHashMap> hijos = actual.getHijos();
                char c = texto.charAt(j);
                if (hijos.containsKey(c)) {
                    actual = hijos.get(c);
                    if (actual.esPalabra()) {
                        posiciones.add(i);
                    }
                } else {
                    break;
                }
            }
        }
        return posiciones;
    }
}
