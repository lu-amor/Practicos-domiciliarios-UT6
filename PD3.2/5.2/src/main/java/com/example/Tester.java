package com.example;

import javax.swing.*;

// solicitada a CHATGPT porque era un programa dado en la propuesta, pero no me dejaba acceder al mismo
public class Tester {
    public static void main(String[] args) {
        // Crear un modelo de combo box con una lista vacía inicialmente
        ArrayListComboBoxModel model = new ArrayListComboBoxModel(new java.util.ArrayList<>());

        // Crear un combo box con el modelo creado
        JComboBox<Object> comboBox = new JComboBox<>(model);

        // Crear la ventana principal
        JFrame frame = new JFrame("ArrayListComboBoxModel Tester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Agregar el combo box al contenido del frame
        frame.getContentPane().add(comboBox);

        // Mostrar la ventana
        frame.pack();
        frame.setVisible(true);

        // Agregar elementos al combo box usando los argumentos de la línea de comandos
        if (args.length > 0){
            for (String arg : args) {
                model.addElement(arg);
            }
        } else {
            SwingUtilities.invokeLater(() -> main (new String[]{"Jim", "Joe", "Mary"})); // en caso de que no le sean pasado los argumentos por la consola
        }
    

        // Remover el primer elemento si hay al menos un elemento en el combo box
        if (model.getSize() > 0) {
            model.removeElementAt(0);
        }
    }
}

