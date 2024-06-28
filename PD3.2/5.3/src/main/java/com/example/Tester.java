package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.*;

// hecho con ayuda de CHATGPT porque algunas funcionalidades debian de ya estar provistas en el material, pero no me permitia acceder
public class Tester {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Word Counter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton countButton = new JButton("Count Words");
        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String urlString = JOptionPane.showInputDialog("Enter URL:");
                if (urlString != null && !urlString.isEmpty()) {
                    try {
                        URL url = new URL(urlString);
                        String content = ManejadorArchivosGenerico.leerURL(url);
                        WordCount wordCount = new WordCount();
                        wordCount.addWords(content);

                        // Create a TreeMap with case-insensitive comparator
                        Map<String, Integer> sortedMap = new TreeMap<>(new CaseInsensitiveComparator());
                        sortedMap.putAll(wordCount.getMap());

                        // Display sorted content in JTextArea
                        StringBuilder sb = new StringBuilder();
                        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
                            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                        }
                        textArea.setText(sb.toString());
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Error reading URL: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid URL.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(countButton, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }
}
