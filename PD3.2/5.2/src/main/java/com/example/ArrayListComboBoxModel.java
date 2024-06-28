package com.example;

import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.MutableComboBoxModel;

public class ArrayListComboBoxModel extends AbstractListModel<Object> implements MutableComboBoxModel<Object> {
    
    private List<Object> model;
    private Object selectedItem;

    public ArrayListComboBoxModel(List<Object> listExample) {
        model = listExample;
        if (!model.isEmpty()) {
            selectedItem = model.get(0); // Set the first item as the selected item initially
        }
    }

    @Override
    public void addElement(Object item) {
        model.add(item);
        fireIntervalAdded(this, model.size() - 1, model.size() - 1); // Notifica que se ha añadido un elemento
    }

    @Override
    public void insertElementAt(Object item, int index) {
        model.add(index, item);
        fireIntervalAdded(this, index, index); // Notifica que se ha insertado un elemento en el índice especificado
    }

    @Override
    public void removeElement(Object obj) {
        int index = model.indexOf(obj);
        if (index != -1) {
            model.remove(index);
            fireIntervalRemoved(this, index, index); // Notifica que se ha eliminado un elemento en el índice especificado
        }
    }

    @Override
    public void removeElementAt(int index) {
        model.remove(index);
        fireIntervalRemoved(this, index, index); // Notifica que se ha eliminado un elemento en el índice especificado
    }

    @Override
    public Object getSelectedItem() {
        return selectedItem;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        if ((selectedItem != null && !selectedItem.equals(anItem)) ||
            (selectedItem == null && anItem != null)) {
            selectedItem = anItem;
            fireContentsChanged(this, -1, -1); // Notifica que el elemento seleccionado ha cambiado
        }
    }

    @Override
    public Object getElementAt(int position) {
        return model.get(position);
    }

    @Override
    public int getSize() {
        return model.size();
    }
}
