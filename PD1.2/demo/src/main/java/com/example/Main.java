package com.example;

import java.util.HashMap;

public class Main {
    private static final int REPETICIONES = 100;
    public static void main(String[] args) {
        medicionPredecir();
    }
    public static void medicionPredecir() {
        TArbolTrie trie = new TArbolTrie();
        TArbolTrieModificado trieModificado = new TArbolTrieModificado();
        HashMap hashMap = new HashMap();
        String[] palabrasclave = ManejadorArchivosGenerico
                .leerArchivo("demo\\src\\listado-general_desordenado.txt");

        String prefijo = "ab";

        for (String p : palabrasclave) {
            // insertar la palabra p en el trie
            trie.insertar(p);
            // insertar la palabra p en el trie con el hashmap
            trieModificado.insertar(p);
            // insertar la palabra p en el hashMap
            hashMap.put(p, p);
        }
        // PREDECIR

        Medible[] medibles = new Medible[3];
        int i = 0;
        medibles[i++] = new MedicionPredecirTrieModificado(trieModificado);
        medibles[i++] = new MedicionPredecirTrie(trie);
        medibles[i++] = new MedicionPredecirHashMap(hashMap);
        Medicion mi;
        i = 0;
        Object[] params = { REPETICIONES, prefijo };
        String[] lineas = new String[4];
        lineas[i++] = "algoritmo,tiempo,memoria";
        for (Medible m : medibles) {
            mi = m.medir(params);
            mi.print();
            lineas[i++] = mi.getTexto() + "," + mi.getTiempoEjecucion().toString() + ","
                    + mi.getMemoria().toString();
        }

        ManejadorArchivosGenerico.escribirArchivo("demo\\src\\salida.scv",
                lineas);
    }
}