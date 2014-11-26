/*
 * Copyright (C) 2014 Khasdul
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.yf.kp.design.billing;

import com.stripbandunk.jwidget.model.DefaultDoubleListModel;
import com.yf.kp.model.Angsuran;
import com.yf.kp.model.Bulanan;
import com.yf.kp.model.Kelas;
import com.yf.kp.model.Siswa;
import com.yf.kp.model.TagihanAngsuran;
import com.yf.kp.model.TagihanBulanan;
import com.yf.kp.model.TagihanTunai;
import com.yf.kp.model.Tunai;
import com.yf.kp.service.AngsuranService;
import com.yf.kp.service.BulananService;
import com.yf.kp.service.KelasService;
import com.yf.kp.service.SiswaService;
import com.yf.kp.service.TagihanAngsuranService;
import com.yf.kp.service.TagihanBulananService;
import com.yf.kp.service.TagihanTunaiService;
import com.yf.kp.service.TunaiService;
import com.yf.kp.service.impl.AngsuranServiceImpl;
import com.yf.kp.service.impl.BulananServiceImpl;
import com.yf.kp.service.impl.KelasServiceImpl;
import com.yf.kp.service.impl.SiswaServiceImpl;
import com.yf.kp.service.impl.TagihanAngsuranServiceImpl;
import com.yf.kp.service.impl.TagihanBulananServiceImpl;
import com.yf.kp.service.impl.TagihanTunaiServiceImpl;
import com.yf.kp.service.impl.TunaiServiceImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Khasdul
 */
public class FrameBiling extends javax.swing.JInternalFrame {

    private List<Siswa> listSiswa;
    private SiswaService siswaService;
    private DefaultDoubleListModel<String> dblModel;

    private void pilihKelas() {
        dblModel.removeAllSourceValues();
        dblModel.removeAllTargetValues();
        dblAngsuran.setModel(dblModel);
        cmbKelas.removeAllItems();
        KelasService kelasService = new KelasServiceImpl();
        List<Kelas> listKelas = kelasService.findAll();
        for (Kelas kelas : listKelas) {
            cmbKelas.addItem(kelas.getNama_kelas());
        }
    }

    private void inputSiswa() {
        if (tabUtama.getSelectedIndex() == 0) {
            cmbAngsuranNamaTagihan.removeAllItems();
            cmbAngsuranNamaTagihan.addItem("Pilih");
            AngsuranService angsuranService = new AngsuranServiceImpl();
            List<Angsuran> listAngsuran = angsuranService.findAll();
            for (Angsuran angsuran : listAngsuran) {
                cmbAngsuranNamaTagihan.addItem(angsuran.getNama());
            }
        } else if (tabUtama.getSelectedIndex() == 1) {
            cmbBulananNamaTagihan.removeAllItems();
            cmbBulananNamaTagihan.addItem("Pilih");
            BulananService bulananService = new BulananServiceImpl();
            List<Bulanan> listBulanan = bulananService.findAll();
            for (Bulanan bulanan : listBulanan) {
                cmbBulananNamaTagihan.addItem(bulanan.getNama());
            }
        } else if (tabUtama.getSelectedIndex() == 2) {
            cmbTunaiNamaTagihan.removeAllItems();
            cmbTunaiNamaTagihan.addItem("Pilih");
            TunaiService tunaiService = new TunaiServiceImpl();
            List<Tunai> listTunai = tunaiService.findAll();
            for (Tunai tunai : listTunai) {
                cmbTunaiNamaTagihan.addItem(tunai.getNama());
            }
        }
    }

    /**
     * Tab Angsuran
     */
    private void pilihNamaTagihanAngsuran() {
        dblModel = new DefaultDoubleListModel<>(String.class);
        if (!"Pilih".equals(cmbAngsuranNamaTagihan.getSelectedItem().toString())) {
            siswaService = new SiswaServiceImpl();
            AngsuranService angsuranService = new AngsuranServiceImpl();
            Angsuran angsuran = angsuranService.findOneByName(cmbAngsuranNamaTagihan.getSelectedItem().toString());
            txtAngsuranJumlah.setText(angsuran.getJumlah().toString());
            txtAngsuranKaliBayar.setText(angsuran.getKaliBayar().toString());
            listSiswa = siswaService.findAllByKelas(cmbKelas.getSelectedItem().toString());
            for (Siswa siswa : listSiswa) {
                dblModel.add(siswa.getNama());
            }
            dblAngsuran.setModel(dblModel);
        } else {
            txtAngsuranJumlah.setText("");
            txtAngsuranKaliBayar.setText("");
            dblModel.removeAllSourceValues();
            dblModel.removeAllTargetValues();
            dblAngsuran.setModel(dblModel);
        }
    }

    private void simpanAngsuran() {
        Collection<String> values = dblModel.getValues();
        siswaService = new SiswaServiceImpl();
        TagihanAngsuranService tagihanAngsuranService = new TagihanAngsuranServiceImpl();
        List<String> listNamaSiswa = new ArrayList<>();
        for (String nama : values) {
            Siswa siswa = siswaService.findOneByName(nama);
            TagihanAngsuran tagihanAngsuran = new TagihanAngsuran();
            tagihanAngsuran.setNis(siswa.getNis());
            tagihanAngsuran.setNama(siswa.getNama());
            tagihanAngsuran.setKelas(cmbKelas.getSelectedItem().toString());
            tagihanAngsuran.setNamaTagihan(cmbAngsuranNamaTagihan.getSelectedItem().toString());
            tagihanAngsuran.setKategori("Angsuran");
            tagihanAngsuran.setJumlah(Double.valueOf(txtAngsuranJumlah.getText()));
            tagihanAngsuran.setKaliBayar(Integer.valueOf(txtAngsuranKaliBayar.getText()));
            for (String namaSiswa : values) {
                listNamaSiswa.add(namaSiswa);
            }
            tagihanAngsuranService.saveBatch(tagihanAngsuran, listNamaSiswa);
        }
        dblModel.removeAllTargetValues();
        dblAngsuran.setModel(dblModel);
    }

    /*
     * Tab Tunai
     */
    private void pilihTunaiNamaTagihan() {
        dblModel = new DefaultDoubleListModel<>(String.class);
        if (!"Pilih".equals(cmbTunaiNamaTagihan.getSelectedItem().toString())) {
            siswaService = new SiswaServiceImpl();
            TunaiService tunaiService = new TunaiServiceImpl();
            Tunai tunai = tunaiService.findOneByName(cmbTunaiNamaTagihan.getSelectedItem().toString());
            txtTunaiJumlah.setText(tunai.getJumlah().toString());
            listSiswa = siswaService.findAllByKelas(cmbKelas.getSelectedItem().toString());
            for (Siswa siswa : listSiswa) {
                dblModel.add(siswa.getNama());
            }
            dblTunai.setModel(dblModel);
        } else {
            txtTunaiJumlah.setText("");
            dblModel.removeAllSourceValues();
            dblModel.removeAllTargetValues();
            dblTunai.setModel(dblModel);
        }
    }

    private void simpanTunai() {
        Collection<String> values = dblModel.getValues();
        siswaService = new SiswaServiceImpl();
        TagihanTunaiService tagihanTunaiService = new TagihanTunaiServiceImpl();
        List<String> listNamaSiswa = new ArrayList<>();
        for (String nama : values) {
            Siswa siswa = siswaService.findOneByName(nama);
            TagihanTunai tagihanTunai = new TagihanTunai();
            tagihanTunai.setNis(siswa.getNis());
            tagihanTunai.setNama(siswa.getNama());
            tagihanTunai.setKelas(cmbKelas.getSelectedItem().toString());
            tagihanTunai.setNamaTagihan(cmbTunaiNamaTagihan.getSelectedItem().toString());
            tagihanTunai.setKategori("Tunai");
            tagihanTunai.setJumlah(Double.valueOf(txtTunaiJumlah.getText()));
            for (String namaSiswa : values) {
                listNamaSiswa.add(namaSiswa);
            }
            tagihanTunaiService.saveBatch(tagihanTunai, listNamaSiswa);
        }
        dblModel.removeAllTargetValues();
        dblTunai.setModel(dblModel);
    }

    private void pilihBulananNamaTagihan() {
        dblModel = new DefaultDoubleListModel<>(String.class);
        if (!"Pilih".equals(cmbBulananNamaTagihan.getSelectedItem().toString())) {
            siswaService = new SiswaServiceImpl();
            listSiswa = siswaService.findAllByKelas(cmbKelas.getSelectedItem().toString());
            for (Siswa siswa : listSiswa) {
                dblModel.add(siswa.getNama());
            }
            dblBulanan.setModel(dblModel);
        } else {
            dblModel.removeAllSourceValues();
            dblModel.removeAllTargetValues();
            dblBulanan.setModel(dblModel);
        }
    }

    private void simpanBulanan() {
        Collection<String> values = dblModel.getValues();
        siswaService = new SiswaServiceImpl();
        TagihanBulananService tagihanBulananService = new TagihanBulananServiceImpl();
        BulananService bulananService = new BulananServiceImpl();
        Bulanan bulanan = bulananService.findOneByName(cmbBulananNamaTagihan.getSelectedItem().toString());
        List<String> listNamaSiswa = new ArrayList<>();
        for (String nama : values) {
            Siswa siswa = siswaService.findOneByName(nama);
            TagihanBulanan tagihanBulanan = new TagihanBulanan();
            tagihanBulanan.setNis(siswa.getNis());
            tagihanBulanan.setNama(siswa.getNama());
            tagihanBulanan.setKelas(cmbKelas.getSelectedItem().toString());
            tagihanBulanan.setNamaTagihan(cmbBulananNamaTagihan.getSelectedItem().toString());
            tagihanBulanan.setKategori("Bulanan");
            tagihanBulanan.setJumlah(bulanan.getJumlah());
            tagihanBulanan.setJanuari(!bulanan.isJanuari());
            tagihanBulanan.setFebruari(!bulanan.isFebruari());
            tagihanBulanan.setMaret(!bulanan.isMaret());
            tagihanBulanan.setApril(!bulanan.isApril());
            tagihanBulanan.setMei(!bulanan.isMei());
            tagihanBulanan.setJuni(!bulanan.isJuni());
            tagihanBulanan.setJuli(!bulanan.isJuli());
            tagihanBulanan.setAgustus(!bulanan.isAgustus());
            tagihanBulanan.setSeptember(!bulanan.isSeptember());
            tagihanBulanan.setOktober(!bulanan.isOktober());
            tagihanBulanan.setNovember(!bulanan.isNovember());
            tagihanBulanan.setDesember(!bulanan.isDesember());
            for (String namaSiswa : values) {
                listNamaSiswa.add(namaSiswa);
            }
            tagihanBulananService.saveBatch(tagihanBulanan, listNamaSiswa);
        }
        dblModel.removeAllTargetValues();
        dblBulanan.setModel(dblModel);
    }

    /**
     * Creates new form FrameBiling
     */
    public FrameBiling() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabUtama = new javax.swing.JTabbedPane();
        panelAngsuran = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cmbAngsuranNamaTagihan = new javax.swing.JComboBox();
        dblAngsuran = new com.stripbandunk.jwidget.JDoubleList();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtAngsuranJumlah = new javax.swing.JTextField();
        txtAngsuranKaliBayar = new javax.swing.JTextField();
        panelBulanan = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cmbBulananNamaTagihan = new javax.swing.JComboBox();
        dblBulanan = new com.stripbandunk.jwidget.JDoubleList();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        panelTunai = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cmbTunaiNamaTagihan = new javax.swing.JComboBox();
        dblTunai = new com.stripbandunk.jwidget.JDoubleList();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        txtTunaiJumlah = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cmbKelas = new javax.swing.JComboBox();

        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        tabUtama.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabUtamaStateChanged(evt);
            }
        });

        jLabel2.setText("Pilih Nama Tagihan");

        cmbAngsuranNamaTagihan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbAngsuranNamaTagihanItemStateChanged(evt);
            }
        });

        jButton1.setText("Keluar");
        jButton1.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Simpan");
        jButton2.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtAngsuranJumlah.setEnabled(false);

        txtAngsuranKaliBayar.setEnabled(false);

        javax.swing.GroupLayout panelAngsuranLayout = new javax.swing.GroupLayout(panelAngsuran);
        panelAngsuran.setLayout(panelAngsuranLayout);
        panelAngsuranLayout.setHorizontalGroup(
            panelAngsuranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAngsuranLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAngsuranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dblAngsuran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelAngsuranLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbAngsuranNamaTagihan, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAngsuranJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtAngsuranKaliBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 65, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAngsuranLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelAngsuranLayout.setVerticalGroup(
            panelAngsuranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAngsuranLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAngsuranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbAngsuranNamaTagihan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAngsuranJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAngsuranKaliBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dblAngsuran, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelAngsuranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tabUtama.addTab("Angsuran", panelAngsuran);

        jLabel4.setText("Pilih Nama Tagihan");

        cmbBulananNamaTagihan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbBulananNamaTagihanItemStateChanged(evt);
            }
        });

        jButton3.setText("Keluar");
        jButton3.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Simpan");
        jButton4.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBulananLayout = new javax.swing.GroupLayout(panelBulanan);
        panelBulanan.setLayout(panelBulananLayout);
        panelBulananLayout.setHorizontalGroup(
            panelBulananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBulananLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBulananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dblBulanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelBulananLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbBulananNamaTagihan, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 350, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBulananLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelBulananLayout.setVerticalGroup(
            panelBulananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBulananLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBulananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbBulananNamaTagihan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dblBulanan, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBulananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tabUtama.addTab("Bulanan", panelBulanan);

        jLabel6.setText("Pilih Nama Tagihan");

        cmbTunaiNamaTagihan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTunaiNamaTagihanItemStateChanged(evt);
            }
        });

        jButton5.setText("Keluar");
        jButton5.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Simpan");
        jButton6.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        txtTunaiJumlah.setEnabled(false);

        javax.swing.GroupLayout panelTunaiLayout = new javax.swing.GroupLayout(panelTunai);
        panelTunai.setLayout(panelTunaiLayout);
        panelTunaiLayout.setHorizontalGroup(
            panelTunaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTunaiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTunaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dblTunai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelTunaiLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbTunaiNamaTagihan, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTunaiJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 181, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTunaiLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelTunaiLayout.setVerticalGroup(
            panelTunaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTunaiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTunaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbTunaiNamaTagihan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTunaiJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dblTunai, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTunaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tabUtama.addTab("Tunai", panelTunai);

        jLabel1.setText("Pilih Kelas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabUtama)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(12, 12, 12)
                .addComponent(cmbKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabUtama, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        pilihKelas();
    }//GEN-LAST:event_formInternalFrameOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tabUtamaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabUtamaStateChanged
        inputSiswa();
    }//GEN-LAST:event_tabUtamaStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void cmbAngsuranNamaTagihanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbAngsuranNamaTagihanItemStateChanged
        pilihNamaTagihanAngsuran();
    }//GEN-LAST:event_cmbAngsuranNamaTagihanItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        simpanAngsuran();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cmbTunaiNamaTagihanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTunaiNamaTagihanItemStateChanged
        pilihTunaiNamaTagihan();
    }//GEN-LAST:event_cmbTunaiNamaTagihanItemStateChanged

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        simpanTunai();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void cmbBulananNamaTagihanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbBulananNamaTagihanItemStateChanged
        pilihBulananNamaTagihan();
    }//GEN-LAST:event_cmbBulananNamaTagihanItemStateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        simpanBulanan();
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbAngsuranNamaTagihan;
    private javax.swing.JComboBox cmbBulananNamaTagihan;
    private javax.swing.JComboBox cmbKelas;
    private javax.swing.JComboBox cmbTunaiNamaTagihan;
    private com.stripbandunk.jwidget.JDoubleList dblAngsuran;
    private com.stripbandunk.jwidget.JDoubleList dblBulanan;
    private com.stripbandunk.jwidget.JDoubleList dblTunai;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel panelAngsuran;
    private javax.swing.JPanel panelBulanan;
    private javax.swing.JPanel panelTunai;
    private javax.swing.JTabbedPane tabUtama;
    private javax.swing.JTextField txtAngsuranJumlah;
    private javax.swing.JTextField txtAngsuranKaliBayar;
    private javax.swing.JTextField txtTunaiJumlah;
    // End of variables declaration//GEN-END:variables

}
