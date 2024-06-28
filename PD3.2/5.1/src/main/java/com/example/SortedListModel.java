package com.example;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.AbstractListModel;

// hecho por mi misma dado que no figuraba el provisto en la tarea

public class SortedListModel extends AbstractListModel{

    private SortedSet<Object> model;

    public SortedListModel(){
        this.model = new TreeSet<>();
    }

    @Override
    public int getSize() {
        return model.size();
    }

    @Override
    public Object getElementAt(int position) {
        return model.toArray()[position];
    }

    public void add(Object element){
        model.add(element);
    }

    public void addAll(Object[] elements){
        for (Object element : elements){
            model.add(element);
        }
        //model.addAll(elements); // no funciona dado que el metodo no recibe un array sino un tipo Collections
    }
    
    public void clear(){
        model.clear();
    }

    public boolean contains(Object element){
        return model.contains(element);
    }

    public Object firstElement(){
        return model.first();
    }

    public java.util.Iterator<Object> iterator(){
        return model.iterator(); 
    }

    public Object lastElement(){
        return model.last();
    }

    public boolean remove(Object element){
        return model.remove(element);
    }

}
