package com.yf.kp.tableModel;

import com.yf.kp.model.TabelSiswa;
import com.yf.kp.service.TabelAgamaService;
import com.yf.kp.service.TabelKelasService;
import com.yf.kp.utility.InjectHelper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author BlackCode
 */
public class SiswaTableModel extends AbstractTableModel{
    
    private List<TabelSiswa> list = new ArrayList<>();
    private final String [] HEADER = {"NIS","NAMA","JENIS KELAMIN","ALAMAT","NO TELPON","AGAMA","TEMPAT LAHIR","TANGGAL LAHIR","NAMA ORANG TUA","PEKERJAAN ORANG TUA","NO TELPON ORANG TUA","KELAS","STATUS"};
    
    public void setList(List<TabelSiswa> list){
        this.list = list;
    }
    
    public void save(TabelSiswa t){
        list.add(t);
        fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
    }
    
    public void update(TabelSiswa t, int index){
        list.set(index, t);
        fireTableRowsUpdated(index, index);
    }
    
    public void delete(int index){
        list.remove(index);
        fireTableRowsDeleted(index, index);
    }
    
    public TabelSiswa getOne(int index){
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
        TabelAgamaService tabelAgamaService = InjectHelper.getTabelAgamaService();
        TabelKelasService tabelKelasService = InjectHelper.getTabelKelasService();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        TabelSiswa t = list.get(rowIndex);
        switch(columnIndex){
            case 0:return t.getNis();
            case 1:return t.getNamaSiswa();
            case 2:return t.getJenisKelamin();
            case 3:return t.getAlamat();
            case 4:return t.getNoTelpon();
            case 5:return tabelAgamaService.findOne(t.getIdAgamaOnSiswa()).getNamaAgama();
            case 6:return t.getTempatLahir();
            case 7:return dateFormat.format(t.getTanggalLahir());
            case 8:return t.getNamaOrtu();
            case 9:return t.getPekerjaanOrtu();
            case 10:return t.getNoTelponOrtu();
            case 11:return tabelKelasService.findOne(t.getIdKelasSiswa()).getNamaKelas();
            case 12:return t.getStatus();
            default:return null;
        }
    }

}
