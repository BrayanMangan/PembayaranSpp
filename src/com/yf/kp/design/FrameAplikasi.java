package com.yf.kp.design;

import com.yf.kp.design.siswa.FrameSiswa;
import com.yf.kp.design.kelas.FrameKelas;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author BlackCode
 */
public class FrameAplikasi extends javax.swing.JFrame {

    private Dimension dimension;
    private Image image;
    private Object desktopPaneCustom1;

    /**
     * Creates new form FrameAplikasi
     */
    public FrameAplikasi() {
        initComponents();
        setLocationRelativeTo(null);
        //fullScreen();
        setIcon();
    }

    private void fullScreen() {
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, dimension.width, dimension.height);
    }

    private void setIcon() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/com/yf/kp/images/cash.png"));
        image = icon.getImage();
        setIconImage(image);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        customDesktopPane1 = new com.yf.kp.template.CustomDesktopPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jButtonMenuDataSiswa = new javax.swing.JButton();
        jButtonMenuKelas = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jButtonMenuJenisPembayaran = new javax.swing.JButton();
        jButtonMenuBilingPembayaran = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemTransaksiPembayaran = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Aplikasi Pembayaran");
        setMaximumSize(new java.awt.Dimension(1280, 720));
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButtonMenuDataSiswa.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButtonMenuDataSiswa.setText("DATA SISWA");
        jButtonMenuDataSiswa.setToolTipText("Login");
        jButtonMenuDataSiswa.setPreferredSize(new java.awt.Dimension(150, 40));
        jButtonMenuDataSiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMenuDataSiswaActionPerformed(evt);
            }
        });

        jButtonMenuKelas.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButtonMenuKelas.setText("KELAS");
        jButtonMenuKelas.setToolTipText("Logout");
        jButtonMenuKelas.setPreferredSize(new java.awt.Dimension(150, 40));
        jButtonMenuKelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMenuKelasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonMenuDataSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButtonMenuKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(913, 913, 913))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonMenuDataSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jButtonMenuKelas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("KESISWAAN", jPanel1);

        jButtonMenuJenisPembayaran.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButtonMenuJenisPembayaran.setText("JENIS PEMBAYARAN");
        jButtonMenuJenisPembayaran.setToolTipText("About");
        jButtonMenuJenisPembayaran.setPreferredSize(new java.awt.Dimension(150, 40));
        jButtonMenuJenisPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMenuJenisPembayaranActionPerformed(evt);
            }
        });

        jButtonMenuBilingPembayaran.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButtonMenuBilingPembayaran.setText("BILING PEMBAYARAN");
        jButtonMenuBilingPembayaran.setToolTipText("About");
        jButtonMenuBilingPembayaran.setPreferredSize(new java.awt.Dimension(150, 40));
        jButtonMenuBilingPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMenuBilingPembayaranActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonMenuJenisPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButtonMenuBilingPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(448, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonMenuJenisPembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonMenuBilingPembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("PEMBAYARAN", jPanel5);

        customDesktopPane1.add(jTabbedPane1);
        jTabbedPane1.setBounds(0, 0, 800, 100);

        jMenu1.setText("File");

        jMenuItem1.setText("Exit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Transaksi");

        jMenuItemTransaksiPembayaran.setText("Pembayaran");
        jMenuItemTransaksiPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTransaksiPembayaranActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemTransaksiPembayaran);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Laporan");

        jMenuItem2.setText("Data Siswa");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuItem3.setText("Pembayaran");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(customDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 967, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(customDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemTransaksiPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTransaksiPembayaranActionPerformed
        FrameJenisPembayaran frameJenisPembayaran = new FrameJenisPembayaran();
        frameJenisPembayaran.setBounds(0, 0, customDesktopPane1.getWidth(), customDesktopPane1.getHeight());
        customDesktopPane1.add(frameJenisPembayaran);
        frameJenisPembayaran.setVisible(true);
    }//GEN-LAST:event_jMenuItemTransaksiPembayaranActionPerformed

    private void jButtonMenuDataSiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMenuDataSiswaActionPerformed
        FrameSiswa fs = new FrameSiswa();
        fs.setBounds(0, 0, customDesktopPane1.getWidth(), customDesktopPane1.getHeight());
        customDesktopPane1.add(fs);
        fs.setVisible(true);
    }//GEN-LAST:event_jButtonMenuDataSiswaActionPerformed

    private void jButtonMenuKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMenuKelasActionPerformed
        // TODO add your handling code here:
        FrameKelas fk = new FrameKelas();
        fk.setBounds(0, 0, customDesktopPane1.getWidth(), customDesktopPane1.getHeight());
        customDesktopPane1.add(fk);
        fk.setVisible(true);
    }//GEN-LAST:event_jButtonMenuKelasActionPerformed

    private void jButtonMenuJenisPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMenuJenisPembayaranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonMenuJenisPembayaranActionPerformed

    private void jButtonMenuBilingPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMenuBilingPembayaranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonMenuBilingPembayaranActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (JOptionPane.showConfirmDialog(null, "Are You Sure to Exit?", "Exit Application", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Are You Sure to Exit?", "Exit Application", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    /**
     * @param args the command line argument
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.yf.kp.template.CustomDesktopPane customDesktopPane1;
    private javax.swing.JButton jButtonMenuBilingPembayaran;
    private javax.swing.JButton jButtonMenuDataSiswa;
    private javax.swing.JButton jButtonMenuJenisPembayaran;
    private javax.swing.JButton jButtonMenuKelas;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItemTransaksiPembayaran;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables

}
