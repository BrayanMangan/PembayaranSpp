package com.yf.kp.design.transaksi;

import com.yf.kp.design.transaksi.tablemodel.TagihanAngsuranTableModel;
import com.yf.kp.design.transaksi.tablemodel.TagihanBulananTableModel;
import com.yf.kp.design.transaksi.tablemodel.TagihanTunaiTableModel;
import com.yf.kp.model.LaporanAngsuran;
import com.yf.kp.model.LaporanBulanan;
import com.yf.kp.model.LaporanTunai;
import com.yf.kp.model.TagihanAngsuran;
import com.yf.kp.model.TagihanBulanan;
import com.yf.kp.model.TagihanTunai;
import com.yf.kp.service.LaporanAngsuranService;
import com.yf.kp.service.LaporanBulananService;
import com.yf.kp.service.LaporanTunaiService;
import com.yf.kp.service.TagihanAngsuranService;
import com.yf.kp.service.TagihanBulananService;
import com.yf.kp.service.TagihanTunaiService;
import com.yf.kp.service.impl.LaporanAngsuranServiceImpl;
import com.yf.kp.service.impl.LaporanBulananServiceImpl;
import com.yf.kp.service.impl.LaporanTunaiServiceImpl;
import com.yf.kp.service.impl.TagihanAngsuranServiceImpl;
import com.yf.kp.service.impl.TagihanBulananServiceImpl;
import com.yf.kp.service.impl.TagihanTunaiServiceImpl;
import com.yf.kp.utility.TableAutoColumnWidth;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author aspire
 */
public class FrameTransaksi extends javax.swing.JInternalFrame {

    private TagihanAngsuranTableModel tagihanAngsuranTableModel;
    private TagihanTunaiTableModel tagihanTunaiTableModel;
    private TagihanBulananTableModel tagihanBulananTableModel;
    private List<TagihanBulanan> listBulanan;
    private Double bulananNominal;
    private Double total = 0.0;

    public Double getBulananNominal() {
        return bulananNominal;
    }

    public void setBulananNominal(Double bulananNominal) {
        this.bulananNominal = bulananNominal;
    }

    /**
     * Creates new form FrameTransaksi
     */
    public FrameTransaksi() {
        initComponents();
    }

    private void pilihDataTableAngsuran() {
        if (jTabbedPane1.getSelectedIndex() == 0) {
            btnAngsuranSimpan.setEnabled(false);
            tblAngsuran.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (tblAngsuran.getSelectedRow() != -1) {
                        cmbAngsuranJumlah.removeAllItems();
                        int index = tblAngsuran.getSelectedRow();
                        String namaTagihan = (String) tagihanAngsuranTableModel.getValueAt(index, 1);
                        Double jumlah = (Double) tagihanAngsuranTableModel.getValueAt(index, 2);
                        Integer kaliBayar = (Integer) tagihanAngsuranTableModel.getValueAt(index, 3);
                        txtAngsuranNamaPembayaran.setText(namaTagihan);
                        txtAngsuranNominal.setText(jumlah.toString());
                        for (int i = 1; i <= kaliBayar; i++) {
                            cmbAngsuranJumlah.addItem(i);
                        }
                        txtAngsuranTotalBayar.setText("");
                    }
                }
            });
        }
    }

    private void cariNisAngsuran() {
        if (txtAngsuranNis.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nis Siswa Masih Kosong");
        } else {
            tagihanAngsuranTableModel = new TagihanAngsuranTableModel();
            String nis = txtAngsuranNis.getText();
            TagihanAngsuranService service = new TagihanAngsuranServiceImpl();
            List<TagihanAngsuran> list = service.findAllByNis(nis);
            for (TagihanAngsuran tagihanAngsuran : list) {
                txtAngsuranNama.setText(tagihanAngsuran.getNama());
                txtAngsuranNis.setText(tagihanAngsuran.getNis());
                txtAngsuranKelas.setText(tagihanAngsuran.getKelas());
            }
            tagihanAngsuranTableModel.setList(list);
            tblAngsuran.setModel(tagihanAngsuranTableModel);
            TableAutoColumnWidth tableAutoColumnWidth = new TableAutoColumnWidth(tblAngsuran);
            pilihDataTableAngsuran();
        }
    }

    private void cariNamaAngsuran() {
        if (txtAngsuranNama.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama Siswa Masih Kosong");
        } else {
            tagihanAngsuranTableModel = new TagihanAngsuranTableModel();
            String nama = txtAngsuranNama.getText();
            TagihanAngsuranService service = new TagihanAngsuranServiceImpl();
            List<TagihanAngsuran> list = service.findAllByNama(nama);
            for (TagihanAngsuran tagihanAngsuran : list) {
                txtAngsuranNis.setText(tagihanAngsuran.getNis());
                txtAngsuranNama.setText(tagihanAngsuran.getNama());
                txtAngsuranKelas.setText(tagihanAngsuran.getKelas());
            }
            tagihanAngsuranTableModel.setList(list);
            tblAngsuran.setModel(tagihanAngsuranTableModel);
            TableAutoColumnWidth tableAutoColumnWidth = new TableAutoColumnWidth(tblAngsuran);
            pilihDataTableAngsuran();
        }
    }

    private void tampilkanJumlahAngsuran() {
        String nominal = txtAngsuranNominal.getText();
        String kaliBayarS = cmbAngsuranJumlah.getSelectedItem().toString();
        Double totalBayar = Double.valueOf(nominal) * Integer.valueOf(kaliBayarS);
        txtAngsuranTotalBayar.setText(totalBayar.toString());
    }

    private void lakukanPembayaranAngsuran() {
        if (!txtAngsuranBayar.getText().trim().isEmpty()) {
            Double totalBayar = Double.valueOf(txtAngsuranTotalBayar.getText());
            Double jumlahUang = Double.valueOf(txtAngsuranBayar.getText());
            Double kembalian = jumlahUang - totalBayar;
            lblAngsuranKembalian.setText(kembalian.toString());
            btnAngsuranSimpan.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Silahkan Input Banyaknya Uang untuk dibayarkan");
        }
    }

    private void simpanPembayaranAngsuran() {
        TagihanAngsuranService tagihanService = new TagihanAngsuranServiceImpl();
        LaporanAngsuranService laporanService = new LaporanAngsuranServiceImpl();

        LaporanAngsuran laporan = new LaporanAngsuran();
        laporan.setNis(txtAngsuranNis.getText());
        laporan.setNama(txtAngsuranNama.getText());
        laporan.setNamaTagihan(txtAngsuranNamaPembayaran.getText());
        laporan.setNominal(Double.valueOf(txtAngsuranNominal.getText()));
        laporan.setTotalBayar(Double.valueOf(txtAngsuranTotalBayar.getText()));
        laporan.setKaliBayar(cmbAngsuranJumlah.getSelectedItem().toString());
        laporan.setAngsuranBayar(Double.valueOf(txtAngsuranBayar.getText()));
        laporan.setKembalian(Double.valueOf(lblAngsuranKembalian.getText()));
        laporanService.save(laporan);

        TagihanAngsuran tagihan = tagihanService.findOneByNisAndNamaTagihan(txtAngsuranNis.getText(), txtAngsuranNamaPembayaran.getText());
        TagihanAngsuran updated = new TagihanAngsuran();
        updated.setId(tagihan.getId());
        updated.setJumlah(Double.valueOf(txtAngsuranNominal.getText()));
        updated.setKaliBayar(tagihan.getKaliBayar() - Integer.valueOf(cmbAngsuranJumlah.getSelectedItem().toString()));
        updated.setKategori("Angsuran");
        updated.setKelas(txtAngsuranKelas.getText());
        updated.setNama(txtAngsuranNama.getText());
        updated.setNamaTagihan(txtAngsuranNamaPembayaran.getText());
        updated.setNis(txtAngsuranNis.getText());
        tagihanService.update(updated);
        cariNisAngsuran();
        txtAngsuranBayar.setText("");
        lblAngsuranKembalian.setText("0");
    }

    private void batalAngsuran() {
        txtAngsuranNis.setText("");
        txtAngsuranNama.setText("");
        txtAngsuranKelas.setText("");
        txtAngsuranNamaPembayaran.setText("");
        txtAngsuranNominal.setText("");
        txtAngsuranTotalBayar.setText("");
        txtAngsuranBayar.setText("");
        lblAngsuranKembalian.setText("0");
    }

    private void pilihDataTableTunai() {
        if (jTabbedPane1.getSelectedIndex() == 2) {
            btnTunaiSimpan.setEnabled(false);
            tblTunai.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (tblTunai.getSelectedRow() != -1) {
                        int index = tblTunai.getSelectedRow();
                        String namaTagihan = (String) tagihanTunaiTableModel.getValueAt(index, 1);
                        Double jumlah = (Double) tagihanTunaiTableModel.getValueAt(index, 2);
                        txtTunaiNamaTagihan.setText(namaTagihan);
                        txtTunaiJumlah.setText(jumlah.toString());
                    }
                }
            });
        }
    }

    private void cariNisTunai() {
        if (txtTunaiCariNis.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nis Siswa Masih Kosong");
        } else {
            tagihanTunaiTableModel = new TagihanTunaiTableModel();
            String nis = txtTunaiCariNis.getText();
            TagihanTunaiService service = new TagihanTunaiServiceImpl();
            List<TagihanTunai> list = service.findAllByNis(nis);
            for (TagihanTunai tagihanTunai : list) {
                txtTunaiCariNama.setText(tagihanTunai.getNama());
                txtTunaiCariNis.setText(tagihanTunai.getNis());
                txtTunaiKelas.setText(tagihanTunai.getKelas());
            }
            tagihanTunaiTableModel.setList(list);
            tblTunai.setModel(tagihanTunaiTableModel);
            TableAutoColumnWidth tableAutoColumnWidth = new TableAutoColumnWidth(tblTunai);
            pilihDataTableTunai();
        }
    }

    private void cariNamaTunai() {
        if (txtTunaiCariNama.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama Siswa Masih Kosong");
        } else {
            tagihanTunaiTableModel = new TagihanTunaiTableModel();
            String nama = txtTunaiCariNama.getText();
            TagihanTunaiService service = new TagihanTunaiServiceImpl();
            List<TagihanTunai> list = service.findAllByNama(nama);
            for (TagihanTunai tagihanTunai : list) {
                txtTunaiCariNis.setText(tagihanTunai.getNis());
                txtTunaiCariNama.setText(tagihanTunai.getNama());
                txtTunaiKelas.setText(tagihanTunai.getKelas());
            }
            tagihanTunaiTableModel.setList(list);
            tblTunai.setModel(tagihanTunaiTableModel);
            TableAutoColumnWidth tableAutoColumnWidth = new TableAutoColumnWidth(tblTunai);
            pilihDataTableTunai();
        }
    }

    private void lakukanPembayaranTunai() {
        if (!txtTunaiBayar.getText().trim().isEmpty()) {
            Double totalBayar = Double.valueOf(txtTunaiJumlah.getText());
            Double jumlahUang = Double.valueOf(txtTunaiBayar.getText());
            Double kembalian = jumlahUang - totalBayar;
            lblTunaiKembalian.setText(kembalian.toString());
            btnTunaiSimpan.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Silahkan Input Banyaknya Uang untuk dibayarkan");
        }
    }

    private void simpanPembayaranTunai() {
        TagihanTunaiService tunaiService = new TagihanTunaiServiceImpl();
        LaporanTunaiService laporanService = new LaporanTunaiServiceImpl();

        LaporanTunai laporan = new LaporanTunai();
        laporan.setNis(txtTunaiCariNis.getText());
        laporan.setNama(txtTunaiCariNama.getText());
        laporan.setNamaTagihan(txtTunaiNamaTagihan.getText());
        laporan.setNominal(Double.valueOf(txtTunaiJumlah.getText()));
        laporan.setTotalBayar(Double.valueOf(txtTunaiBayar.getText()));
        laporan.setKembalian(Double.valueOf(lblTunaiKembalian.getText()));
        laporanService.save(laporan);

        TagihanTunai tunai = tunaiService.findOneByNisAndNamaTagihan(txtTunaiCariNis.getText(), txtTunaiNamaTagihan.getText());
        this.setTitle(tunai.getId().toString());
        tunaiService.delete(tunai.getId());
        cariNisTunai();
        txtTunaiBayar.setText("");
        lblTunaiKembalian.setText("0");
    }

    private void disableChkBoxBulanan(Boolean januari, Boolean februari, Boolean maret,
            Boolean april, Boolean mei, Boolean juni, Boolean juli, Boolean agustus, Boolean september,
            Boolean oktober, Boolean november, Boolean desember) {
        if (januari) {
            chkJanuari.setEnabled(false);
            if (!chkJanuari.isEnabled()) {
                chkJanuari.setSelected(false);
            }
        }
        if (februari) {
            chkFebruari.setEnabled(false);
            if (!chkFebruari.isEnabled()) {
                chkFebruari.setSelected(false);
            }
        }
        if (maret) {
            chkMaret.setEnabled(false);
            if (!chkMaret.isEnabled()) {
                chkMaret.setSelected(false);
            }
        }
        if (april) {
            chkApril.setEnabled(false);
            if (!chkApril.isEnabled()) {
                chkApril.setSelected(false);
            }
        }
        if (mei) {
            chkMei.setEnabled(false);
            if (!chkMei.isEnabled()) {
                chkMei.setSelected(false);
            }
        }
        if (juni) {
            chkJuni.setEnabled(false);
            if (!chkJuni.isEnabled()) {
                chkJuni.setSelected(false);
            }
        }
        if (juli) {
            chkJuli.setEnabled(false);
            if (!chkJuli.isEnabled()) {
                chkJuli.setSelected(false);
            }
        }
        if (agustus) {
            chkAgustus.setEnabled(false);
            if (!chkAgustus.isEnabled()) {
                chkAgustus.setSelected(false);
            }
        }
        if (september) {
            chkSeptember.setEnabled(false);
            if (!chkSeptember.isEnabled()) {
                chkSeptember.setSelected(false);
            }
        }
        if (oktober) {
            chkOktober.setEnabled(false);
            if (!chkOktober.isEnabled()) {
                chkOktober.setSelected(false);
            }
        }
        if (november) {
            chkNovember.setEnabled(false);
            if (!chkNovember.isEnabled()) {
                chkNovember.setSelected(false);
            }
        }
        if (desember) {
            chkDesember.setEnabled(false);
            if (!chkDesember.isEnabled()) {
                chkDesember.setSelected(false);
            }
        }
    }

    private void pilihDataTableBulanan() {
        if (jTabbedPane1.getSelectedIndex() == 1) {
            btnBulananSimpan.setEnabled(false);
            tblBulanan.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (tblBulanan.getSelectedRow() != -1) {
                        int index = tblBulanan.convertRowIndexToModel(tblBulanan.getSelectedRow());
                        TagihanBulanan tagihanBulanan = listBulanan.get(index);
                        txtBulananNamaTagihan.setText(tagihanBulanan.getNamaTagihan());
                        chkJanuari.setSelected(tagihanBulanan.isJanuari());
                        chkFebruari.setSelected(tagihanBulanan.isFebruari());
                        chkMaret.setSelected(tagihanBulanan.isMaret());
                        chkApril.setSelected(tagihanBulanan.isApril());
                        chkMei.setSelected(tagihanBulanan.isMei());
                        chkJuni.setSelected(tagihanBulanan.isJuni());
                        chkJuli.setSelected(tagihanBulanan.isJuli());
                        chkAgustus.setSelected(tagihanBulanan.isAgustus());
                        chkSeptember.setSelected(tagihanBulanan.isSeptember());
                        chkOktober.setSelected(tagihanBulanan.isOktober());
                        chkNovember.setSelected(tagihanBulanan.isNovember());
                        chkDesember.setSelected(tagihanBulanan.isDesember());
                        setBulananNominal(tagihanBulanan.getJumlah());
                        disableChkBoxBulanan(tagihanBulanan.isJanuari(), tagihanBulanan.isFebruari(), tagihanBulanan.isMaret(), tagihanBulanan.isApril(),
                                tagihanBulanan.isMei(), tagihanBulanan.isJuni(), tagihanBulanan.isJuli(), tagihanBulanan.isAgustus(), tagihanBulanan.isSeptember(),
                                tagihanBulanan.isOktober(), tagihanBulanan.isNovember(), tagihanBulanan.isDesember());
                    }
                }
            });
        }
    }

    private void cariNisBulanan() {
        if (txtBulananCariNis.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nis Siswa Masih Kosong");
        } else {
            tagihanBulananTableModel = new TagihanBulananTableModel();
            String nis = txtBulananCariNis.getText();
            TagihanBulananService service = new TagihanBulananServiceImpl();
            listBulanan = service.findAllByNis(nis);
            for (TagihanBulanan tagihanBulanan : listBulanan) {
                txtBulananCariNama.setText(tagihanBulanan.getNama());
                txtBulananCariNis.setText(tagihanBulanan.getNis());
                txtBulananKelas.setText(tagihanBulanan.getKelas());
            }
            tagihanBulananTableModel.setList(listBulanan);
            tblBulanan.setModel(tagihanBulananTableModel);
            TableAutoColumnWidth tableAutoColumnWidth = new TableAutoColumnWidth(tblBulanan);
            pilihDataTableBulanan();
        }
    }

    private void cariNamaBulanan() {
        if (txtBulananCariNama.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama Siswa Masih Kosong");
        } else {
            TagihanBulananService service = new TagihanBulananServiceImpl();
            tagihanBulananTableModel = new TagihanBulananTableModel();
            String nama = txtBulananCariNama.getText();
            listBulanan = service.findAllByNama(nama);
            for (TagihanBulanan tagihanBulanan : listBulanan) {
                txtBulananCariNama.setText(tagihanBulanan.getNama());
                txtBulananCariNis.setText(tagihanBulanan.getNis());
                txtBulananKelas.setText(tagihanBulanan.getKelas());
            }
            tagihanBulananTableModel.setList(listBulanan);
            tblBulanan.setModel(tagihanBulananTableModel);
            TableAutoColumnWidth tableAutoColumnWidth = new TableAutoColumnWidth(tblBulanan);
            pilihDataTableBulanan();
        }
    }

    private void simpanPembayaranBulanan() {

        TagihanBulananService bulananService = new TagihanBulananServiceImpl();
        LaporanBulananService laporanService = new LaporanBulananServiceImpl();

        LaporanBulanan laporan = new LaporanBulanan();
        laporan.setNis(txtBulananCariNis.getText());
        laporan.setNama(txtBulananCariNama.getText());
        laporan.setNamaTagihan(txtBulananNamaTagihan.getText());
        laporan.setNominal(Double.valueOf(txtBulananTotalBayar.getText()));
        laporan.setTotalBayar(Double.valueOf(txtBulananBayar.getText()));
        laporan.setKembalian(Double.valueOf(lblBulananKembalian.getText()));
        laporan.setJanuari(chkJanuari.isSelected());
        laporan.setFebruari(chkFebruari.isSelected());
        laporan.setMaret(chkMaret.isSelected());
        laporan.setApril(chkApril.isSelected());
        laporan.setMei(chkMei.isSelected());
        laporan.setJuni(chkJuni.isSelected());
        laporan.setJuli(chkJuni.isSelected());
        laporan.setAgustus(chkAgustus.isSelected());
        laporan.setSeptember(chkSeptember.isSelected());
        laporan.setOktober(chkOktober.isSelected());
        laporan.setNovember(chkNovember.isSelected());
        laporan.setDesember(chkDesember.isSelected());
        laporanService.save(laporan);

        TagihanBulanan bulanan = bulananService.findOneByNisAndNamaTagihan(txtBulananCariNis.getText(), txtBulananNamaTagihan.getText());
        TagihanBulanan updated = new TagihanBulanan();
        updated.setId(bulanan.getId());
        updated.setNis(txtBulananCariNis.getText());
        updated.setNama(txtBulananCariNama.getText());
        updated.setNamaTagihan(txtBulananNamaTagihan.getText());
        updated.setJumlah(Double.valueOf(txtBulananTotalBayar.getText()));
        updated.setJanuari(chkJanuari.isSelected());
        updated.setFebruari(chkFebruari.isSelected());
        updated.setMaret(chkMaret.isSelected());
        updated.setApril(chkApril.isSelected());
        updated.setMei(chkMei.isSelected());
        updated.setJuni(chkJuni.isSelected());
        updated.setJuli(chkJuni.isSelected());
        updated.setAgustus(chkAgustus.isSelected());
        updated.setSeptember(chkSeptember.isSelected());
        updated.setOktober(chkOktober.isSelected());
        updated.setNovember(chkNovember.isSelected());
        updated.setDesember(chkDesember.isSelected());
        bulananService.update(updated);
        cariNisBulanan();
        txtBulananBayar.setText("");
        lblBulananKembalian.setText("0");
    }

    private void lakukanPembayaranBulanan() {
        if (!txtBulananBayar.getText().trim().isEmpty()) {
            Double totalBayar = Double.valueOf(txtBulananTotalBayar.getText());
            Double jumlahUang = Double.valueOf(txtBulananBayar.getText());
            Double kembalian = jumlahUang - totalBayar;
            lblBulananKembalian.setText(kembalian.toString());
            btnBulananSimpan.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Silahkan Input Banyaknya Uang untuk dibayarkan");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        txtAngsuranNamaPembayaran = new javax.swing.JTextField();
        txtAngsuranNominal = new javax.swing.JTextField();
        cmbAngsuranJumlah = new javax.swing.JComboBox();
        txtAngsuranTotalBayar = new javax.swing.JTextField();
        txtAngsuranBayar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblAngsuranKembalian = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnAngsuranSimpan = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtAngsuranNis = new javax.swing.JTextField();
        txtAngsuranNama = new javax.swing.JTextField();
        txtAngsuranKelas = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblAngsuran = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtBulananCariNis = new javax.swing.JTextField();
        txtBulananCariNama = new javax.swing.JTextField();
        txtBulananKelas = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBulanan = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        txtBulananTotalBayar = new javax.swing.JTextField();
        txtBulananBayar = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        chkJanuari = new javax.swing.JCheckBox();
        chkFebruari = new javax.swing.JCheckBox();
        chkMaret = new javax.swing.JCheckBox();
        chkApril = new javax.swing.JCheckBox();
        chkMei = new javax.swing.JCheckBox();
        chkJuni = new javax.swing.JCheckBox();
        chkJuli = new javax.swing.JCheckBox();
        chkAgustus = new javax.swing.JCheckBox();
        chkSeptember = new javax.swing.JCheckBox();
        chkOktober = new javax.swing.JCheckBox();
        chkNovember = new javax.swing.JCheckBox();
        chkDesember = new javax.swing.JCheckBox();
        lblBulananKembalian = new javax.swing.JLabel();
        btnBulananSimpan = new javax.swing.JButton();
        txtBulananNamaTagihan = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jButton15 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtTunaiCariNis = new javax.swing.JTextField();
        txtTunaiCariNama = new javax.swing.JTextField();
        txtTunaiKelas = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTunai = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        txtTunaiJumlah = new javax.swing.JTextField();
        txtTunaiBayar = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnTunaiSimpan = new javax.swing.JButton();
        txtTunaiNamaTagihan = new javax.swing.JTextField();
        lblTunaiKembalian = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1024, 550));

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.setPreferredSize(new java.awt.Dimension(420, 380));

        txtAngsuranNamaPembayaran.setEnabled(false);
        txtAngsuranNamaPembayaran.setPreferredSize(new java.awt.Dimension(150, 30));

        txtAngsuranNominal.setEnabled(false);
        txtAngsuranNominal.setPreferredSize(new java.awt.Dimension(150, 30));

        cmbAngsuranJumlah.setPreferredSize(new java.awt.Dimension(40, 30));

        txtAngsuranTotalBayar.setEnabled(false);
        txtAngsuranTotalBayar.setPreferredSize(new java.awt.Dimension(90, 30));

        txtAngsuranBayar.setPreferredSize(new java.awt.Dimension(90, 30));

        jLabel4.setText("Jumlah yang harus dibayar");

        jLabel5.setText("Jumlah pembayaran");

        jButton3.setText("Bayar");
        jButton3.setPreferredSize(new java.awt.Dimension(50, 30));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel6.setText("Kembali :");

        jLabel21.setText("Nama pembayaran");

        lblAngsuranKembalian.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblAngsuranKembalian.setText("0");

        jButton1.setText("Show");
        jButton1.setPreferredSize(new java.awt.Dimension(50, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnAngsuranSimpan.setText("Simpan");
        btnAngsuranSimpan.setEnabled(false);
        btnAngsuranSimpan.setPreferredSize(new java.awt.Dimension(50, 30));
        btnAngsuranSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAngsuranSimpanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                                        .addGap(132, 132, 132))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(90, 90, 90))
                                    .addComponent(txtAngsuranTotalBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtAngsuranBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblAngsuranKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtAngsuranNamaPembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAngsuranNominal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbAngsuranJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAngsuranSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addGap(46, 46, 46)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAngsuranNamaPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAngsuranNominal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbAngsuranJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAngsuranTotalBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAngsuranBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAngsuranKembalian))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(btnAngsuranSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton10.setText("Batal");
        jButton10.setPreferredSize(new java.awt.Dimension(60, 30));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("Keluar");
        jButton11.setPreferredSize(new java.awt.Dimension(60, 30));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(372, Short.MAX_VALUE)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel12.setPreferredSize(new java.awt.Dimension(420, 380));

        jLabel22.setText("Nis");

        jLabel23.setText("Nama");

        jLabel24.setText("Kelas");

        txtAngsuranKelas.setEnabled(false);

        jButton12.setText("Cari");
        jButton12.setPreferredSize(new java.awt.Dimension(40, 30));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setText("Cari");
        jButton13.setPreferredSize(new java.awt.Dimension(40, 30));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jScrollPane4.setViewportView(tblAngsuran);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtAngsuranNama, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                                    .addComponent(txtAngsuranNis))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(txtAngsuranKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(257, Short.MAX_VALUE))))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtAngsuranNis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtAngsuranNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtAngsuranKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Transaksi (Angsuran)", jPanel2);

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.setPreferredSize(new java.awt.Dimension(420, 380));

        jLabel7.setText("Nis");

        jLabel8.setText("Nama");

        jLabel9.setText("Kelas");

        jButton4.setText("Cari");
        jButton4.setPreferredSize(new java.awt.Dimension(40, 30));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Cari");
        jButton5.setPreferredSize(new java.awt.Dimension(40, 30));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(tblBulanan);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBulananCariNama)
                            .addComponent(txtBulananCariNis)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(txtBulananKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 89, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtBulananCariNis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtBulananCariNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtBulananKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.setPreferredSize(new java.awt.Dimension(420, 380));

        txtBulananTotalBayar.setEnabled(false);
        txtBulananTotalBayar.setPreferredSize(new java.awt.Dimension(90, 30));

        txtBulananBayar.setPreferredSize(new java.awt.Dimension(90, 30));

        jLabel10.setText("Jumlah yang harus dibayar");

        jLabel11.setText("Jumlah pembayaran");

        jButton6.setText("Bayar");
        jButton6.setPreferredSize(new java.awt.Dimension(50, 30));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel12.setText("Kembali :");

        jLabel13.setText("Nama pembayaran");

        chkJanuari.setText("Januari");
        chkJanuari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkJanuariActionPerformed(evt);
            }
        });

        chkFebruari.setText("Februari");
        chkFebruari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkFebruariActionPerformed(evt);
            }
        });

        chkMaret.setText("Maret");
        chkMaret.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMaretActionPerformed(evt);
            }
        });

        chkApril.setText("April");
        chkApril.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAprilActionPerformed(evt);
            }
        });

        chkMei.setText("Mei");
        chkMei.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMeiActionPerformed(evt);
            }
        });

        chkJuni.setText("Juni");
        chkJuni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkJuniActionPerformed(evt);
            }
        });

        chkJuli.setText("Juli");
        chkJuli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkJuliActionPerformed(evt);
            }
        });

        chkAgustus.setText("Agustus");
        chkAgustus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAgustusActionPerformed(evt);
            }
        });

        chkSeptember.setText("September");
        chkSeptember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSeptemberActionPerformed(evt);
            }
        });

        chkOktober.setText("Oktober");
        chkOktober.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkOktoberActionPerformed(evt);
            }
        });

        chkNovember.setText("November");
        chkNovember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkNovemberActionPerformed(evt);
            }
        });

        chkDesember.setText("Desember");
        chkDesember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDesemberActionPerformed(evt);
            }
        });

        lblBulananKembalian.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblBulananKembalian.setText("0");

        btnBulananSimpan.setText("Simpan");
        btnBulananSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBulananSimpanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBulananNamaTagihan, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                        .addGap(217, 217, 217))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtBulananTotalBayar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtBulananBayar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(lblBulananKembalian)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(89, 89, 89)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chkJanuari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chkFebruari, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                            .addComponent(chkMaret, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chkMei, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chkJuni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chkApril, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chkJuli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chkAgustus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chkSeptember, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(chkOktober, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chkNovember, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chkDesember, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(79, 79, 79))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBulananSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtBulananNamaTagihan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkJanuari)
                    .addComponent(chkJuli))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkFebruari)
                    .addComponent(chkAgustus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkMaret)
                    .addComponent(chkSeptember))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkApril)
                    .addComponent(chkOktober))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkMei)
                    .addComponent(chkNovember))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkJuni)
                    .addComponent(chkDesember))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBulananTotalBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBulananBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lblBulananKembalian))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBulananSimpan)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jButton15.setText("Keluar");
        jButton15.setPreferredSize(new java.awt.Dimension(60, 30));
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton14.setText("Batal");
        jButton14.setPreferredSize(new java.awt.Dimension(60, 30));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(171, 171, 171)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                        .addGap(10, 10, 10)))
                .addGap(15, 15, 15))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Transaksi (Bulanan)", jPanel4);

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.setPreferredSize(new java.awt.Dimension(420, 380));

        jLabel14.setText("Nis");

        jLabel15.setText("Nama");

        jLabel16.setText("Kelas");

        txtTunaiKelas.setEnabled(false);

        jButton7.setText("Cari");
        jButton7.setPreferredSize(new java.awt.Dimension(40, 30));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Cari");
        jButton8.setPreferredSize(new java.awt.Dimension(40, 30));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(tblTunai);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTunaiCariNis, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                            .addComponent(txtTunaiCariNama)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(txtTunaiKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 102, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtTunaiCariNis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtTunaiCariNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtTunaiKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.setPreferredSize(new java.awt.Dimension(420, 380));

        txtTunaiJumlah.setEnabled(false);
        txtTunaiJumlah.setPreferredSize(new java.awt.Dimension(90, 30));

        txtTunaiBayar.setPreferredSize(new java.awt.Dimension(90, 30));

        jLabel17.setText("Jumlah yang harus dibayar");

        jLabel18.setText("Jumlah pembayaran");

        jButton9.setText("Bayar");
        jButton9.setPreferredSize(new java.awt.Dimension(50, 30));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel19.setText("Kembali :");

        jLabel20.setText("Nama pembayaran");

        btnTunaiSimpan.setText("Simpan");
        btnTunaiSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTunaiSimpanActionPerformed(evt);
            }
        });

        txtTunaiNamaTagihan.setEnabled(false);
        txtTunaiNamaTagihan.setPreferredSize(new java.awt.Dimension(10, 30));

        lblTunaiKembalian.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTunaiKembalian.setText("0");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(323, 323, 323))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTunaiSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                        .addGap(195, 195, 195))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(153, 153, 153))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTunaiNamaTagihan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTunaiJumlah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTunaiBayar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(52, 52, 52)
                                .addComponent(lblTunaiKembalian)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTunaiNamaTagihan, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(txtTunaiJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTunaiBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(lblTunaiKembalian))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(btnTunaiSimpan)
                .addContainerGap())
        );

        jButton16.setText("Batal");
        jButton16.setPreferredSize(new java.awt.Dimension(60, 30));
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setText("Keluar");
        jButton17.setPreferredSize(new java.awt.Dimension(60, 30));
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Transaksi (Tunai)", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addGap(13, 13, 13))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        batalAngsuran();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        cariNisAngsuran();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        cariNamaAngsuran();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        tampilkanJumlahAngsuran();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        lakukanPembayaranAngsuran();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnAngsuranSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAngsuranSimpanActionPerformed
        simpanPembayaranAngsuran();
    }//GEN-LAST:event_btnAngsuranSimpanActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        cariNisTunai();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        cariNamaTunai();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        lakukanPembayaranTunai();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void btnTunaiSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTunaiSimpanActionPerformed
        simpanPembayaranTunai();
    }//GEN-LAST:event_btnTunaiSimpanActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        cariNisBulanan();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        cariNamaBulanan();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void chkJanuariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkJanuariActionPerformed
        Double nominal = getBulananNominal();
        if (chkJanuari.isSelected()) {
            total += nominal;
            txtBulananTotalBayar.setText(total.toString());
        } else {
            total -= nominal;
            txtBulananTotalBayar.setText(total.toString());
        }
    }//GEN-LAST:event_chkJanuariActionPerformed

    private void chkFebruariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkFebruariActionPerformed
        // TODO add your handling code here:
        Double nominal = getBulananNominal();
        if (chkFebruari.isSelected()) {
            total += nominal;
            txtBulananTotalBayar.setText(total.toString());
        } else {
            total -= nominal;
            txtBulananTotalBayar.setText(total.toString());
        }
    }//GEN-LAST:event_chkFebruariActionPerformed

    private void chkMaretActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMaretActionPerformed
        Double nominal = getBulananNominal();
        if (chkMaret.isSelected()) {
            total += nominal;
            txtBulananTotalBayar.setText(total.toString());
        } else {
            total -= nominal;
            txtBulananTotalBayar.setText(total.toString());
        }
    }//GEN-LAST:event_chkMaretActionPerformed

    private void chkAprilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAprilActionPerformed
        Double nominal = getBulananNominal();
        if (chkApril.isSelected()) {
            total += nominal;
            txtBulananTotalBayar.setText(total.toString());
        } else {
            total -= nominal;
            txtBulananTotalBayar.setText(total.toString());
        }
    }//GEN-LAST:event_chkAprilActionPerformed

    private void chkMeiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMeiActionPerformed
        Double nominal = getBulananNominal();
        if (chkMei.isSelected()) {
            total += nominal;
            txtBulananTotalBayar.setText(total.toString());
        } else {
            total -= nominal;
            txtBulananTotalBayar.setText(total.toString());
        }
    }//GEN-LAST:event_chkMeiActionPerformed

    private void chkJuniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkJuniActionPerformed
        Double nominal = getBulananNominal();
        if (chkJuni.isSelected()) {
            total += nominal;
            txtBulananTotalBayar.setText(total.toString());
        } else {
            total -= nominal;
            txtBulananTotalBayar.setText(total.toString());
        }
    }//GEN-LAST:event_chkJuniActionPerformed

    private void chkJuliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkJuliActionPerformed
        Double nominal = getBulananNominal();
        if (chkJuli.isSelected()) {
            total += nominal;
            txtBulananTotalBayar.setText(total.toString());
        } else {
            total -= nominal;
            txtBulananTotalBayar.setText(total.toString());
        }
    }//GEN-LAST:event_chkJuliActionPerformed

    private void chkAgustusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAgustusActionPerformed
        // TODO add your handling code here:
        Double nominal = getBulananNominal();
        if (chkAgustus.isSelected()) {
            total += nominal;
            txtBulananTotalBayar.setText(total.toString());
        } else {
            total -= nominal;
            txtBulananTotalBayar.setText(total.toString());
        }
    }//GEN-LAST:event_chkAgustusActionPerformed

    private void chkSeptemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSeptemberActionPerformed
        // TODO add your handling code here:
        Double nominal = getBulananNominal();
        if (chkSeptember.isSelected()) {
            total += nominal;
            txtBulananTotalBayar.setText(total.toString());
        } else {
            total -= nominal;
            txtBulananTotalBayar.setText(total.toString());
        }
    }//GEN-LAST:event_chkSeptemberActionPerformed

    private void chkOktoberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkOktoberActionPerformed
        // TODO add your handling code here:
        Double nominal = getBulananNominal();
        if (chkOktober.isSelected()) {
            total += nominal;
            txtBulananTotalBayar.setText(total.toString());
        } else {
            total -= nominal;
            txtBulananTotalBayar.setText(total.toString());
        }
    }//GEN-LAST:event_chkOktoberActionPerformed

    private void chkNovemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkNovemberActionPerformed
        // TODO add your handling code here:
        Double nominal = getBulananNominal();
        if (chkNovember.isSelected()) {
            total += nominal;
            txtBulananTotalBayar.setText(total.toString());
        } else {
            total -= nominal;
            txtBulananTotalBayar.setText(total.toString());
        }
    }//GEN-LAST:event_chkNovemberActionPerformed

    private void chkDesemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDesemberActionPerformed
        // TODO add your handling code here:
        Double nominal = getBulananNominal();
        if (chkDesember.isSelected()) {
            total += nominal;
            txtBulananTotalBayar.setText(total.toString());
        } else {
            total -= nominal;
            txtBulananTotalBayar.setText(total.toString());
        }
    }//GEN-LAST:event_chkDesemberActionPerformed

    private void btnBulananSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBulananSimpanActionPerformed
        simpanPembayaranBulanan();
    }//GEN-LAST:event_btnBulananSimpanActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        lakukanPembayaranBulanan();
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAngsuranSimpan;
    private javax.swing.JButton btnBulananSimpan;
    private javax.swing.JButton btnTunaiSimpan;
    private javax.swing.JCheckBox chkAgustus;
    private javax.swing.JCheckBox chkApril;
    private javax.swing.JCheckBox chkDesember;
    private javax.swing.JCheckBox chkFebruari;
    private javax.swing.JCheckBox chkJanuari;
    private javax.swing.JCheckBox chkJuli;
    private javax.swing.JCheckBox chkJuni;
    private javax.swing.JCheckBox chkMaret;
    private javax.swing.JCheckBox chkMei;
    private javax.swing.JCheckBox chkNovember;
    private javax.swing.JCheckBox chkOktober;
    private javax.swing.JCheckBox chkSeptember;
    private javax.swing.JComboBox cmbAngsuranJumlah;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAngsuranKembalian;
    private javax.swing.JLabel lblBulananKembalian;
    private javax.swing.JLabel lblTunaiKembalian;
    private javax.swing.JTable tblAngsuran;
    private javax.swing.JTable tblBulanan;
    private javax.swing.JTable tblTunai;
    private javax.swing.JTextField txtAngsuranBayar;
    private javax.swing.JTextField txtAngsuranKelas;
    private javax.swing.JTextField txtAngsuranNama;
    private javax.swing.JTextField txtAngsuranNamaPembayaran;
    private javax.swing.JTextField txtAngsuranNis;
    private javax.swing.JTextField txtAngsuranNominal;
    private javax.swing.JTextField txtAngsuranTotalBayar;
    private javax.swing.JTextField txtBulananBayar;
    private javax.swing.JTextField txtBulananCariNama;
    private javax.swing.JTextField txtBulananCariNis;
    private javax.swing.JTextField txtBulananKelas;
    private javax.swing.JTextField txtBulananNamaTagihan;
    private javax.swing.JTextField txtBulananTotalBayar;
    private javax.swing.JTextField txtTunaiBayar;
    private javax.swing.JTextField txtTunaiCariNama;
    private javax.swing.JTextField txtTunaiCariNis;
    private javax.swing.JTextField txtTunaiJumlah;
    private javax.swing.JTextField txtTunaiKelas;
    private javax.swing.JTextField txtTunaiNamaTagihan;
    // End of variables declaration//GEN-END:variables
}
