package com.yf.kp.tableModel;

import com.yf.kp.model.TabelBayarSpp;
import com.yf.kp.service.TabelSppService;
import com.yf.kp.utility.InjectHelper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author BlackCode
 */
public class BayarSppTableModel extends AbstractTableModel {

    private List<TabelBayarSpp> list = new ArrayList<>();
    private final String[] HEADER = {"NIS SISWA", "PERIODE BULAN", "TANGGAL BAYAR", "STATUS"};

    public void setList(List<TabelBayarSpp> list) {
        this.list = list;
    }

    public void save(TabelBayarSpp t) {
        list.add(t);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void update(TabelBayarSpp t, int index) {
        list.set(index, t);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        list.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public TabelBayarSpp getOne(int index) {
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
        TabelSppService tabelSppService = InjectHelper.getTabelSppService();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        TabelBayarSpp t = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return t.getNisSiswa();
            case 1:
                return tabelSppService.findOne(t.getIdSppOnBayar()).getBulan();
            case 2:
                return dateFormat.format(t.getTanggalBayar());
            case 3:
                return t.getStatus();
            default:
                return null;
        }
    }

}
