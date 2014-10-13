package com.yf.kp.tableModel;

import com.yf.kp.model.TabelSpp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author BlackCode
 */
public class SppTableModel extends AbstractTableModel{
    
    private List<TabelSpp> list = new ArrayList<>();
    private final String [] HEADER = {"ID","PERIODE BULAN","NOMINAL Rp.","TANGGAL AWAL BAYAR","TANGGAL AKHIR BAYAR"};
    
    public void setList(List<TabelSpp> list){
        this.list = list;
    }
    
    public void save(TabelSpp t){
        list.add(t);
        fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
    }
    
    public void update(TabelSpp t, int index){
        list.set(index, t);
        fireTableRowsUpdated(index, index);
    }
    
    public void delete(int index){
        list.remove(index);
        fireTableRowsDeleted(index, index);
    }
    
    public TabelSpp getOne(int index){
        return list.get(index);
    }
    
    @Override
    public String getColumnName(int col){
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        TabelSpp t = list.get(rowIndex);
        switch(columnIndex){
            case 0:return t.getIdSpp();
            case 1:return t.getBulan();
            case 2:return t.getNominal();
            case 3:return dateFormat.format(t.getTanggalMulaiBayar());
            case 4:return dateFormat.format(t.getTanggalAkhirBayar());
            default:return null;
        }
    }

}
