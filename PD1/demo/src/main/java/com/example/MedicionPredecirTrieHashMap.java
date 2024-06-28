package com.example;

public class MedicionPredecirTrieHashMap extends Medible{

    private TArbolTrieHashMap trie;

    public MedicionPredecirTrieHashMap(TArbolTrieHashMap trie2) {
        this.trie = trie2;
    }
    
    @Override
    public void ejecutar(Object... params) {
        int repeticion = (int) params[0];
        String[] palabras = (String[]) params[1];
        for(int i = 0; i < repeticion; i++){
            for(String palabra : palabras){
                trie.predecir(palabra);
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.trie;
    }
}
