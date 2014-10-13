package com.yf.kp.tableModel;

import com.yf.kp.model.TabelBayarBiayaTambahan;
import com.yf.kp.service.TabelBiayaTambahanService;
import com.yf.kp.service.TabelSiswaService;
import com.yf.kp.utility.InjectHelper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author BlackCode
 */
public class BayarBiayaTambahanTableModel extends AbstractTableModel {

    private List<TabelBayarBiayaTambahan> list = new ArrayList<>();
    private final String[] HEADER = {"ID BAYAR", "NIS", "NAMA", "NAMA BIAYA", "TANGGAL BAYAR", "STATUS"};

    public void setList(List<TabelBayarBiayaTambahan> list) {
        this.list = list;
    }

    public void save(TabelBayarBiayaTambahan t) {
        list.add(t);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void update(TabelBayarBiayaTambahan t, int index) {
        list.set(index, t);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        list.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public TabelBayarBiayaTambahan getOne(int index) {
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
        TabelSiswaService tabelSiswaService = InjectHelper.getTabelSiswaService();
        TabelBiayaTambahanService tabelBiayaTambahanService = InjectHelper.getTabelBiayaTambahanService();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");

        TabelBayarBiayaTambahan t = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return t.getIdBayarTambahan();
            case 1:
                return t.getNisSiswa();
            case 2:
                return tabelSiswaService.findOne(t.getNisSiswa()).getNamaSiswa();
            case 3:
                return tabelBiayaTambahanService.findOne(t.getIdBiayaTambahanOnBayar()).getNamaBiayaTambahan();
            case 4:
                return dateFormat.format(t.getTanggalBayar());
            case 5:
                return t.getStatus();
            default:
                return null;
        }
    }

}
