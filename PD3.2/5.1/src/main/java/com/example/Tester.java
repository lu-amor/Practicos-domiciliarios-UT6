package com.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;


// busqueda para hacerlo dado que era una clase provista en la tarea
public class Tester extends JFrame {
    private SortedListModel model;
    private JList<Object> list;
    private JTextField inputField;
    private JButton addButton, removeButton, printButton;

    public Tester(String[] initialValues) {
        super("SortedListModel Tester");

        model = new SortedListModel();
        for (String value : initialValues) {
            model.add(value);
        }

        list = new JList<>(model);
        inputField = new JTextField(20);
        addButton = new JButton("Add");
        removeButton = new JButton("Remove");
        printButton = new JButton("Print");

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String value = inputField.getText().trim();
                if (!value.isEmpty()) {
                    model.add(value);
                    inputField.setText("");
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String value = inputField.getText().trim();
                if (!value.isEmpty()) {
                    model.remove(value);
                    inputField.setText("");
                }
            }
        });

        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                printAction();
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(inputField);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);
        inputPanel.add(printButton);

        getContentPane().add(inputPanel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(list), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }

    private void printAction() {
        Iterator<Object> iterator = model.iterator();
        System.out.println("List contents:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            SwingUtilities.invokeLater(() -> new Tester(args));
        } else {
            SwingUtilities.invokeLater(() -> new Tester(new String[]{"One", "Two", "Three", "Four", "Five"})); // en caso de que no le sean pasado los argumentos por la consola
        }
    }
}


