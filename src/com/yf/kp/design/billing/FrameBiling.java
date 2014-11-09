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
import com.yf.kp.model.TagihanSiswa;
import com.yf.kp.model.Tunai;
import com.yf.kp.service.AngsuranService;
import com.yf.kp.service.BulananService;
import com.yf.kp.service.KelasService;
import com.yf.kp.service.SiswaService;
import com.yf.kp.service.TagihanService;
import com.yf.kp.service.TunaiService;
import com.yf.kp.service.impl.AngsuranServiceImpl;
import com.yf.kp.service.impl.BulananServiceImpl;
import com.yf.kp.service.impl.KelasServiceImpl;
import com.yf.kp.service.impl.SiswaServiceImpl;
import com.yf.kp.service.impl.TagihanServiceImpl;
import com.yf.kp.service.impl.TunaiServiceImpl;
import java.awt.event.ItemEvent;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Khasdul
 */
public class FrameBiling extends javax.swing.JInternalFrame {

    private List<Angsuran> angsuranList;
    private List<Bulanan> bulananList;
    private List<Tunai> tunaiList;
    private List<Kelas> kelasList;
    private List<Siswa> siswaList;
    private KelasService kelasService;
    private DefaultDoubleListModel<String> model;

    /**
     * Creates new form FrameBiling
     */
    public FrameBiling() {
        initComponents();
    }

    private void dataKelas() {
        if (!"Pilih . .".equals(cmbKelas.getSelectedItem().toString())) {
            disableComboBox(true);
        } else {
            disableComboBox(false);
        }
    }

    private void pilihKelas() {
        kelasService = new KelasServiceImpl();
        kelasList = kelasService.findAll();
        cmbKelas.addItem("Pilih . .");
        for (Kelas kelas : kelasList) {
            cmbKelas.addItem(kelas.getNama_kelas());
        }

        if (siswaList != null) {
            siswaList.clear();
            model.removeAllSourceValues();
            model.removeAllTargetValues();
        }
    }

    private void pilihPembayaran() {
        SiswaService siswaService = new SiswaServiceImpl();

        if (null != cmbJenisPembayaran.getSelectedItem().toString()) {
            switch (cmbJenisPembayaran.getSelectedItem().toString()) {
                case "Angsuran":
                    cmbPembayaran.removeAllItems();
                    AngsuranService angsuranService = new AngsuranServiceImpl();
                    angsuranList = angsuranService.findAll();
                    for (Angsuran angsuran : angsuranList) {
                        cmbPembayaran.addItem(angsuran.getNama());
                    }

                    if (siswaList != null) {
                        siswaList.clear();
                        model.removeAllSourceValues();
                        model.removeAllTargetValues();
                    }

                    siswaList = siswaService.findAllByKelasAndAngsuran(cmbKelas.getSelectedItem().toString(), false);
                    model = new DefaultDoubleListModel<>(String.class);
                    for (Siswa siswa : siswaList) {
                        model.add(siswa.getNama());
                    }
                    dblListSiswa.setModel(model);
                    break;
                case "Bulanan":

                    cmbPembayaran.removeAllItems();
                    BulananService bulananService = new BulananServiceImpl();
                    bulananList = bulananService.findAll();
                    for (Bulanan bulanan : bulananList) {
                        cmbPembayaran.addItem(bulanan.getNama());
                    }

                    if (siswaList != null) {
                        siswaList.clear();
                        model.removeAllSourceValues();
                        model.removeAllTargetValues();
                    }

                    siswaList = siswaService.findAllByKelasAndBulanan(cmbKelas.getSelectedItem().toString(), false);
                    model = new DefaultDoubleListModel<>(String.class);
                    for (Siswa siswa : siswaList) {
                        model.add(siswa.getNama());
                    }
                    dblListSiswa.setModel(model);
                    break;
                case "Tunai":

                    cmbPembayaran.removeAllItems();
                    TunaiService tunaiService = new TunaiServiceImpl();
                    tunaiList = tunaiService.findAll();
                    for (Tunai tunai : tunaiList) {
                        cmbPembayaran.addItem(tunai.getNama());
                    }

                    if (siswaList != null) {
                        siswaList.clear();
                        model.removeAllSourceValues();
                        model.removeAllTargetValues();
                    }

                    siswaList = siswaService.findAllByKelasAndTunai(cmbKelas.getSelectedItem().toString(), false);
                    model = new DefaultDoubleListModel<>(String.class);
                    for (Siswa siswa : siswaList) {
                        model.add(siswa.getNama());
                    }
                    dblListSiswa.setModel(model);
                    break;
            }
        }
    }

    public void disableComboBox(Boolean expression) {
        cmbPembayaran.setEnabled(expression);
        cmbJenisPembayaran.setEnabled(expression);
    }

    private void simpanTarget() {
        Collection<String> values = model.getValues();
        SiswaService siswaService = new SiswaServiceImpl();
        TagihanService tagihanService = new TagihanServiceImpl();
        for (String nama : values) {
            Siswa siswa = siswaService.findOneByName(nama);
            this.setTitle(siswa.getId().toString());
            TagihanSiswa tagihanSiswa = new TagihanSiswa();
            tagihanSiswa.setNama(siswa.getNama());
            tagihanSiswa.setNis(siswa.getNis());
            tagihanSiswa.setKelas(siswa.getKelas());
            tagihanSiswa.setTagihan(cmbPembayaran.getSelectedItem().toString());
            siswa.setId(Long.valueOf(this.getTitle()));
            if (cmbJenisPembayaran.getSelectedItem().toString() != null) {
                switch (cmbJenisPembayaran.getSelectedItem().toString()) {
                    case "Angsuran":
                        siswa.setAngsuran(true);
                        break;
                    case "Bulanan":
                        siswa.setBulanan(true);
                        break;
                    case "Tunai":
                        siswa.setTunai(true);
                        break;
                }
            }
            siswaService.update(siswa);
            tagihanService.save(tagihanSiswa);
        }
        model.removeAllTargetValues();
        dblListSiswa.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dblListSiswa = new com.stripbandunk.jwidget.JDoubleList();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cmbJenisPembayaran = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cmbKelas = new javax.swing.JComboBox();
        cmbPembayaran = new javax.swing.JComboBox();

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

        dblListSiswa.setButtonAddAllText("Tambah semua >>");
        dblListSiswa.setButtonAddText("Tambah >");
        dblListSiswa.setButtonRemoveAllText("<< Hapus semua ");
        dblListSiswa.setButtonRemoveText("< Hapus");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yf/kp/images/folder_delete.gif"))); // NOI18N
        jButton1.setText("Kosongkan");
        jButton1.setPreferredSize(new java.awt.Dimension(120, 30));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yf/kp/images/icon_accept.gif"))); // NOI18N
        jButton2.setText("Simpan");
        jButton2.setPreferredSize(new java.awt.Dimension(120, 30));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yf/kp/images/action_stop.gif"))); // NOI18N
        jButton3.setText("Keluar");
        jButton3.setPreferredSize(new java.awt.Dimension(120, 30));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setText("Jenis Pembayaran");

        cmbJenisPembayaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih . .", "Angsuran", "Bulanan", "Tunai" }));
        cmbJenisPembayaran.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbJenisPembayaranItemStateChanged(evt);
            }
        });

        jLabel2.setText("Tentukan siswa berdasarkan kelas");

        cmbKelas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbKelasItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dblListSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmbJenisPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(cmbJenisPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(dblListSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        disableComboBox(false);
        pilihKelas();
    }//GEN-LAST:event_formInternalFrameOpened

    private void cmbJenisPembayaranItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJenisPembayaranItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            pilihPembayaran();
        }
    }//GEN-LAST:event_cmbJenisPembayaranItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        simpanTarget();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cmbKelasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbKelasItemStateChanged
        dataKelas();
    }//GEN-LAST:event_cmbKelasItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbJenisPembayaran;
    private javax.swing.JComboBox cmbKelas;
    private javax.swing.JComboBox cmbPembayaran;
    private com.stripbandunk.jwidget.JDoubleList dblListSiswa;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables

}
