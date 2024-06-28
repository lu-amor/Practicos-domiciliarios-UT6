package com.example;

import java.util.HashMap;

public class App{

    private static TArbolTrieHashMap trie;
    private static final int REPETICIONES = 20;
    public static void main( String[] args )
    {
        ManejadorArchivosGenerico manager = new ManejadorArchivosGenerico();
        String[] lineas = manager.leerArchivo("demo/src/main/java/com/example/listado-general_desordenado.txt");
        trie = new TArbolTrieHashMap();
        TArbolTrie trie2 = new TArbolTrie();
        for (String linea : lineas) {
            trie.insertar(linea);
            trie2.insertar(linea);
        }

        String[] prefijosBuscar = new String[1];
        prefijosBuscar[0] = "cas";

        Medible[] predecibles = new Medible[2];
        int j = 0;
        predecibles[j++] = new MedicionPredecirTrie(trie2);
        predecibles[j++] = new MedicionPredecirTrieHashMap(trie);
        Medicion mi2;
    j = 0;
        Object[] params2 = {REPETICIONES, prefijosBuscar};
        String[] lineas2 = new String[3];
        lineas2[j++] = "algoritmo, tiempo, memoria";
        for (Medible m2: predecibles){
            mi2 = m2.medir(params2);
            mi2.print();
            lineas2[j++] = mi2.getTexto()+", " + mi2.getTiempoEjecucion().toString() + ", " + mi2.getMemoria().toString();
        }
        
        ManejadorArchivosGenerico.escribirArchivo("demo/src/main/java/com/example/salida.txt", lineas2);
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