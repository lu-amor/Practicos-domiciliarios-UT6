package com.example;

import java.util.HashMap;
import java.util.LinkedList;

public class MedicionPredecirHashMap extends Medible {
    private HashMap<String, String> hashMap;

    public MedicionPredecirHashMap(HashMap<String, String> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public void ejecutar(Object... params) {
        int repeticion = (int) params[0];
        String prefijo = (String) params[1];
        for (int i = 0; i < repeticion; i++) {
            LinkedList<String> coincidencias = new LinkedList<>();

            for (String clave : hashMap.keySet()) {
                if (clave.startsWith(prefijo)) {
                    coincidencias.add(clave);
                }
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.hashMap;
    }
}