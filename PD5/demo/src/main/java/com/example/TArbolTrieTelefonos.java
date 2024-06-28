package com.example;

import java.util.LinkedList;

public class TArbolTrieTelefonos implements IArbolTrieTelefonos {
    private TNodoTrieTelefonos raiz;

    public TArbolTrieTelefonos() {
        raiz = new TNodoTrieTelefonos(' ');
    }

    @Override
    public LinkedList<TAbonado> buscarTelefonos(String pais, String area) {
        LinkedList<TAbonado> abonados = new LinkedList<>();
        raiz.buscarTelefonos(pais + area, abonados);
        return abonados;
    }

    @Override
    public void insertar(TAbonado unAbonado) {
        raiz.insertar(unAbonado);
    }
}
