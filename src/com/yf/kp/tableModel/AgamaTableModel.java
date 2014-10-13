package com.yf.kp.tableModel;

import com.yf.kp.model.TabelAgama;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author BlackCode
 */
public class AgamaTableModel extends AbstractTableModel {

    private List<TabelAgama> list = new ArrayList<>();
    private final String[] HEADER = {"ID AGAMA", "NAMA AGAMA"};

    public void setList(List<TabelAgama> list) {
        this.list = list;
    }

    public void save(TabelAgama t) {
        list.add(t);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void update(int index, TabelAgama t) {
        list.set(index, t);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        list.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public TabelAgama getOne(int index) {
        return list.get(index);
    }

    @Override
    public String getColumnName(int col) {
        return HEADER[col];
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return HEADER.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TabelAgama t = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return t.getIdAgama();
            case 1:
                return t.getNamaAgama();
            default:
                return null;
        }
    }

}
