package com.yf.kp.tableModel;

import com.yf.kp.model.TabelPengguna;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author BlackCode
 */
public class PenggunaTableModel extends AbstractTableModel {

    private List<TabelPengguna> list = new ArrayList<>();
    private final String[] HEADER = {"ID", "NAMA", "USERNAME", "PASSWORD"};

    public void setList(List<TabelPengguna> list) {
        this.list = list;
    }

    public void save(TabelPengguna t) {
        list.add(t);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void update(TabelPengguna t, int index) {
        list.set(index, t);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        list.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public TabelPengguna getOne(int index) {
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
        TabelPengguna t = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return t.getId();
            case 1:
                return t.getNamaPengguna();
            case 2:
                return t.getUsername();
            case 3:
                return t.getPassword();
            default:
                return null;
        }
    }

}
