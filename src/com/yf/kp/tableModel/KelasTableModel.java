package com.yf.kp.tableModel;

import com.yf.kp.model.TabelKelas;
import com.yf.kp.service.TabelWaliKelasService;
import com.yf.kp.utility.InjectHelper;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author BlackCode
 */
public class KelasTableModel extends AbstractTableModel {

    private List<TabelKelas> list = new ArrayList<>();
    private final String[] HEADER = {"ID KELAS", "NIP WALI KELAS", "NAMA WALI KELAS", "NAMA KELAS"};

    public void setList(List<TabelKelas> list) {
        this.list = list;
    }

    public void save(TabelKelas t) {
        list.add(t);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void update(TabelKelas t, int index) {
        list.set(index, t);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        list.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public TabelKelas getOne(int index) {
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
        TabelWaliKelasService tabelWaliKelasService = InjectHelper.getTabelWaliKelasService();
        TabelKelas t = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return t.getIdKelas();
            case 1:
                return t.getNipWaliKelas();
            case 2: 
                return t.getNipWaliKelas();
            case 3:
                return tabelWaliKelasService.findOne(t.getNipWaliKelas()).getNamaWaliKelas();
            default:
                return null;
        }
    }

}
