package com.example;

import java.util.LinkedList;

public class TArbolTrieHashMapTelefonos implements IArbolTrieTelefonos {

    private TNodoTrieHashMapTelefonos raiz;

    @Override
    public LinkedList<TAbonado> buscarTelefonos(String codigoPais, String codigoArea) {
        LinkedList<TAbonado> abonados = new LinkedList<>();
        if (raiz != null) {
            raiz.buscarTelefonos(codigoPais + codigoArea, abonados);
        }
        return abonados;
    }

    @Override
    public void insertar(TAbonado unAbonado) {
        if (raiz == null) {
            raiz = new TNodoTrieHashMapTelefonos();
        }
        raiz.insertar(unAbonado);
    }
}

