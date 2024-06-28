package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

// implementado manualmente

public class WordCount {
    private Map<String, Integer> wordMap;

    public WordCount() {
        wordMap = new HashMap<>();
    }

    public Map<String, Integer> getMap(){
        return wordMap;
    }
    public void addWords(String text) {
        StringTokenizer tokenizer = new StringTokenizer(text);
        while (tokenizer.hasMoreTokens()) {
            String word = tokenizer.nextToken().toLowerCase(); // Convertir a minúsculas para contar sin distinción de mayúsculas y minúsculas
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1); // Incrementar la cuenta de la palabra
        }
    }

    public String convertMap() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    public String convertMapSorted() {
        // Crear un TreeMap utilizando CaseInsensitiveComparator para ordenar
        TreeMap<String, Integer> sortedMap = new TreeMap<>(new CaseInsensitiveComparator());
        sortedMap.putAll(wordMap); // Copiar las entradas del mapa original al TreeMap ordenado

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    public void clear() {
        wordMap.clear();
    }
}

