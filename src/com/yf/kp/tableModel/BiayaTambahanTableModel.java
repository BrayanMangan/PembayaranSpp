package com.yf.kp.tableModel;

import com.yf.kp.model.TabelBiayaTambahan;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author BlackCode
 */
public class BiayaTambahanTableModel extends AbstractTableModel {

    private List<TabelBiayaTambahan> list = new ArrayList<>();
    private final String[] HEADER = {"ID BIAYA TAMBAHAN", "NAMA BIAYA TAMBAHAN", "NOMINAL Rp.", "TANGGAL AWAL BAYAR", "TANGGAL AKHIR BAYAR"};

    public void setList(List<TabelBiayaTambahan> list) {
        this.list = list;
    }

    public void save(TabelBiayaTambahan t) {
        list.add(t);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void update(TabelBiayaTambahan t, int index) {
        list.set(index, t);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        list.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public TabelBiayaTambahan getOne(int index) {
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
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
        TabelBiayaTambahan t = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return t.getIdBiayaTambahan();
            case 1:
                return t.getNamaBiayaTambahan();
            case 2:
                return t.getNominal();
            case 3:
                return format.format(t.getTanggalAwalBayar());
            case 4:
                return format.format(t.getTanggalAkhirBayar());
            default:
                return null;
        }
    }

}
