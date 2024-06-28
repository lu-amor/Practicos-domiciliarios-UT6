package com.example;

public class MedicionPredecirTrieModificado extends Medible {
    private TArbolTrieModificado trie;

    public MedicionPredecirTrieModificado(TArbolTrieModificado trie) {
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
