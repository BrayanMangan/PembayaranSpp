package com.yf.kp.tableModel;

import com.yf.kp.model.TabelWaliKelas;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author BlackCode
 */
public class WaliKelasTableModel extends AbstractTableModel {
    
    private List<TabelWaliKelas> list = new ArrayList<>();
    private final String [] HEADER = {"NIP","NAMA WALI KELAS"};
    
    public void setList(List<TabelWaliKelas> list){
        this.list = list;
    }
    
    public void save(TabelWaliKelas t){
        list.add(t);
        fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
    }
    
    public void update(TabelWaliKelas t, int index){
        list.set(index, t);
        fireTableRowsUpdated(index, index);
    }
    
    public void delete(int index){
        list.remove(index);
        fireTableRowsDeleted(index, index);
    }
    
    public TabelWaliKelas getOne(int index){
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
        TabelWaliKelas t = list.get(rowIndex);
        switch(columnIndex){
            case 0:return t.getNip();
            case 1:return t.getNamaWaliKelas();
            default:return null;
        }
    }

}
