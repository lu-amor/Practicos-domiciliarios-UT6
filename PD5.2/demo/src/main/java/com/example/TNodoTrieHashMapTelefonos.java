package com.example;


import java.util.HashMap;
import java.util.LinkedList;

public class TNodoTrieHashMapTelefonos implements INodoTrieTelefonos {

    private HashMap<Character, TNodoTrieHashMapTelefonos> hijos;
    private boolean esNumeroDeTelefono;
    private LinkedList<TAbonado> abonados;

    public TNodoTrieHashMapTelefonos() {
        hijos = new HashMap<>();
        esNumeroDeTelefono = false;
        abonados = new LinkedList<>();
    }

    @Override
    public void insertar(TAbonado unAbonado) {
        TNodoTrieHashMapTelefonos nodo = this;
        for (char caracter : unAbonado.getTelefono().toCharArray()) {
            nodo = nodo.hijos.computeIfAbsent(caracter, k -> new TNodoTrieHashMapTelefonos());
        }
        nodo.esNumeroDeTelefono = true;
        nodo.abonados.add(unAbonado);
    }

    @Override
    public void buscarTelefonos(String primerosDigitos, LinkedList<TAbonado> abonados) {
        TNodoTrieHashMapTelefonos nodo = this;
        for (char caracter : primerosDigitos.toCharArray()) {
            nodo = nodo.hijos.get(caracter);
            if (nodo == null) {
                return;
            }
        }
        agregarAbonados(nodo, abonados);
    }

    private void agregarAbonados(TNodoTrieHashMapTelefonos nodo, LinkedList<TAbonado> abonados) {
        if (nodo.esNumeroDeTelefono) {
            abonados.addAll(nodo.abonados);
        }
        for (Character caracter : nodo.hijos.keySet()) {
            agregarAbonados(nodo.hijos.get(caracter), abonados);
        }
    }
}

