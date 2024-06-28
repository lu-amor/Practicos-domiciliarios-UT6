package com.example;

import java.util.Comparator;


// implementado manualmente
public class CaseInsensitiveComparator implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        return s1.compareToIgnoreCase(s2);
    }
}

