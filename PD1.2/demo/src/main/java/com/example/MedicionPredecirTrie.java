package com.example;

public class MedicionPredecirTrie extends Medible {
    private TArbolTrie trie;

    public MedicionPredecirTrie(TArbolTrie trie) {
        this.trie = trie;
    }

    @Override
    public void ejecutar(Object... params) {
        int repeticion = (int) params[0];
        String prefijo = (String) params[1];
        for (int i = 0; i < repeticion; i++) {
            trie.predecir(prefijo);
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.trie;
    }
}