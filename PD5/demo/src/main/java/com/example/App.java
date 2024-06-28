package com.example;

import java.util.LinkedList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ManejadorArchivosGenerico manager = new ManejadorArchivosGenerico();
        String[] lineas = ManejadorArchivosGenerico.leerArchivo("demo/src/main/java/com/example/abonados.txt");
        TArbolTrieTelefonos arbol = new TArbolTrieTelefonos();
        for (String linea : lineas) {
            String[] datos = linea.split(",");
            TAbonado abonado = new TAbonado(datos[1], datos[0]);
            arbol.insertar(abonado);
        }
        String[] lineas2 = ManejadorArchivosGenerico.leerArchivo("demo/src/main/java/com/example/codigos1.txt");
        String[] datosPais = lineas2[0].split(" ");
        String[] datosArea = lineas2[1].split(" ");
        LinkedList<TAbonado> busqueda = arbol.buscarTelefonos(datosPais[2], datosArea[2]);
        for (TAbonado abonado : busqueda) {
            System.out.println(abonado.getNombre() + " " + abonado.getTelefono());
        }
    }
}