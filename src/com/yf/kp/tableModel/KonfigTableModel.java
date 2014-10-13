package com.yf.kp.tableModel;

import com.yf.kp.model.TabelKonfig;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author BlackCode
 */
public class KonfigTableModel extends AbstractTableModel{
    
    private List<TabelKonfig> list = new ArrayList<>();
    private final String [] HEADER = {"ID","TAHUN AJARAN","SEMESTER"};
    
    public void setList(List<TabelKonfig> list){
        this.list = list;
    }
    
    public void save(TabelKonfig t){
        list.add(t);
        fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
    }
    
    public void update(TabelKonfig t, int index){
        list.set(index, t);
        fireTableRowsUpdated(index, index);
    }
    
    public void delete(int index){
        list.remove(index);
        fireTableRowsDeleted(index, index);
    }
    
    public TabelKonfig getOne(int index){
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
        TabelKonfig t = list.get(rowIndex);
        switch(columnIndex){
            case 0:return t.getIdKonfig();
            case 1:return t.getTahunAjaran();
            case 2:return t.getSemester();
            default:return null;
        }
    }

}
