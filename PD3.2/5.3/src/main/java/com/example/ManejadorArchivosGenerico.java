package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

// tomado de los trabajos de clase o anteriores PD's
public class ManejadorArchivosGenerico {
    /**
     * @param nombreCompletoArchivo
     * @param listaLineasArchivo lista con las lineas del archivo
     * @throws IOException
     */
    public static void escribirArchivo(String nombreCompletoArchivo, String[] listaLineasArchivo) {
        FileWriter fw;
        try {
            fw = new FileWriter(nombreCompletoArchivo,true);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < listaLineasArchivo.length; i++){
                String lineaActual = listaLineasArchivo[i];
                bw.write(lineaActual);
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo "+nombreCompletoArchivo);
            e.printStackTrace();
        }
    }
    
    public static String[] leerArchivo(String nombreCompletoArchivo){
        FileReader fr;
        ArrayList<String> listaLineasArchivo = new ArrayList<String>();
        try {
            fr = new FileReader(nombreCompletoArchivo);
            BufferedReader br = new BufferedReader(fr);
            String lineaActual = br.readLine();
            while (lineaActual != null){
                listaLineasArchivo.add(lineaActual);
                lineaActual = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo "+nombreCompletoArchivo);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo "+nombreCompletoArchivo);
            e.printStackTrace();
        }
        System.out.println("Archivo leido satisfactoriamente");

        return listaLineasArchivo.toArray (new String [0]);
    }
    
    /**
	 * Metodo encargado de filtrar un texto, dejando solamente letras validas.-
	 * @param unaPalabra Palabra a evaluar
	 * @return Cadena de caracteres limpia
	 */
	public static String filtrarPalabra(String unaPalabra) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < unaPalabra.length(); i++) {
			char caracter = unaPalabra.charAt(i);
			if ((caracter >= 'A' && caracter <= 'Z') ||
					(caracter >= 'a' && caracter <= 'z'))
				sb.append(caracter);
		}
		
		return sb.toString();
	}
	
	/**
	    * Lee una string y devuelve las palabras que se encuentren dentro.
	    * @param strLine L�nea a procesar
	    * @return Listado de palabras procesadas
	    */
	public static String[] palabrasPorLinea(String strLine) {
        StringTokenizer st = new StringTokenizer(strLine);
        ArrayList<String> words = new ArrayList<String>();
        while(st.hasMoreTokens()){
            String temp = st.nextToken();
            String word = temp.replaceAll("�", "a");
            word = word.replaceAll("�", "e");
            word = word.replaceAll("�", "i");
            word = word.replaceAll("�", "o");
            word = word.replaceAll("�", "u");
            word = word.replaceAll("�", "n");
            word = word.replaceAll("\\W+", "");
            if (word.compareTo("") != 0){
                words.add(word);
                //System.out.println(word);
            }			    
        }
        return words.toArray(new String [0]);
    }
        
    // metodo agregado 
    /**
     * Lee el contenido de una URL y lo devuelve como una cadena de texto.
     *
     * @param url la URL a leer
     * @return el contenido de la URL como una cadena de texto
     * @throws IOException si ocurre un error al leer la URL
     */
    public static String leerURL(URL url) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}
