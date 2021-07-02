/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dichvu;

import Util.MyConvert;
import database.Database;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author khanh
 */
public class DichVuFrame extends javax.swing.JFrame {

    /**
     * Creates new form DichVuFrame
     */

    private DichVuDAO DVDAO = null;
    private ArrayList<DichVu> listDV;

    public DichVuFrame() {

        initPreData();
        initComponents();
        initAftData();
    }

    private void initPreData() {
        DVDAO = new DichVuDAO();
    }

    private void initAftData() {


        listDV = DVDAO.queryAllDichVu();
        pushDataToTable();

    }


    //Kéo data từ trong list lên table
    public void pushDataToTable() {

        String[] ColumnName = {"MADV", "TENDV", "GIADV"};
        Object[][] Rows = new Object[listDV.size()][3];
        for (int i = 0; i < listDV.size(); i++) {
            Rows[i][0] = listDV.get(i).getMADV();
            Rows[i][1] = listDV.get(i).getTENDV();
            Rows[i][2] = listDV.get(i).getGIADV();
        }
        DefaultTableModel model = new DefaultTableModel(Rows, ColumnName);
        this.table.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelDV = new javax.swing.JPanel();
        tablePanel = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        mainPanel = new javax.swing.JPanel();
        titilePanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        informationPanel = new javax.swing.JPanel();
        txtMaDV = new javax.swing.JTextField();
        lbMaDV = new javax.swing.JLabel();
        lbTenDV = new javax.swing.JLabel();
        lbGiaDV = new javax.swing.JLabel();
        txtGiaDV = new javax.swing.JTextField();
        txtTenDV = new javax.swing.JTextField();
        choosePanel = new javax.swing.JPanel();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        addButton = new javax.swing.JButton();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        editButton = new javax.swing.JButton();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        removeButton = new javax.swing.JButton();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        resetButton = new javax.swing.JButton();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        btnSearch = new javax.swing.JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        tablePanel.setBackground(new java.awt.Color(255, 255, 255));
        tablePanel.setBorder(null);
        tablePanel.setFocusable(false);
        tablePanel.setMinimumSize(new java.awt.Dimension(23, 23));
        tablePanel.setPreferredSize(new java.awt.Dimension(452, 402));

        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "MADV", "TENDV", "GIADV"
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        tablePanel.setViewportView(table);

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setLayout(new javax.swing.BoxLayout(mainPanel, javax.swing.BoxLayout.PAGE_AXIS));

        titilePanel.setBackground(new java.awt.Color(40, 60, 82));
        titilePanel.setMaximumSize(new java.awt.Dimension(32767, 60));
        titilePanel.setMinimumSize(new java.awt.Dimension(0, 60));
        titilePanel.setPreferredSize(new java.awt.Dimension(716, 60));
        titilePanel.setLayout(new javax.swing.BoxLayout(titilePanel, javax.swing.BoxLayout.LINE_AXIS));

        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("QUẢN LÍ THÔNG TIN DỊCH VỤ");
        titleLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        titleLabel.setMaximumSize(new java.awt.Dimension(32000, 100));
        titleLabel.setMinimumSize(new java.awt.Dimension(408, 32));
        titleLabel.setPreferredSize(new java.awt.Dimension(408, 32));
        titilePanel.add(titleLabel);

        mainPanel.add(titilePanel);

        informationPanel.setBackground(new java.awt.Color(255, 255, 255));
        informationPanel.setMaximumSize(new java.awt.Dimension(32767, 300));
        informationPanel.setMinimumSize(new java.awt.Dimension(200, 280));
        informationPanel.setPreferredSize(new java.awt.Dimension(200, 300));

        txtMaDV.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMaDV.setMaximumSize(new java.awt.Dimension(2147483647, 60));
        txtMaDV.setPreferredSize(new java.awt.Dimension(7, 28));

        lbMaDV.setBackground(new java.awt.Color(255, 255, 255));
        lbMaDV.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lbMaDV.setForeground(new java.awt.Color(40, 60, 82));
        lbMaDV.setText("MADV");
        lbMaDV.setPreferredSize(new java.awt.Dimension(28, 14));

        lbTenDV.setBackground(new java.awt.Color(255, 255, 255));
        lbTenDV.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lbTenDV.setForeground(new java.awt.Color(40, 60, 82));
        lbTenDV.setText("TENDV");
        lbTenDV.setPreferredSize(new java.awt.Dimension(35, 14));

        lbGiaDV.setBackground(new java.awt.Color(255, 255, 255));
        lbGiaDV.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lbGiaDV.setForeground(new java.awt.Color(40, 60, 82));
        lbGiaDV.setText("GIADV");
        lbGiaDV.setPreferredSize(new java.awt.Dimension(38, 14));

        txtGiaDV.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtGiaDV.setMaximumSize(new java.awt.Dimension(2147483647, 60));
        txtGiaDV.setMinimumSize(new java.awt.Dimension(7, 23));
        txtGiaDV.setPreferredSize(new java.awt.Dimension(7, 23));

        txtTenDV.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTenDV.setMaximumSize(new java.awt.Dimension(2147483647, 60));
        txtTenDV.setPreferredSize(new java.awt.Dimension(7, 28));

        choosePanel.setBackground(new java.awt.Color(255, 255, 255));
        choosePanel.setLayout(new javax.swing.BoxLayout(choosePanel, javax.swing.BoxLayout.PAGE_AXIS));
        choosePanel.add(filler6);

        addButton.setBackground(new java.awt.Color(255, 255, 255));
        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanli_khach_san/drawable/add-icon-1.png"))); // NOI18N
        addButton.setBorder(null);
        addButton.setMaximumSize(new java.awt.Dimension(100, 33));
        addButton.setMinimumSize(new java.awt.Dimension(33, 33));
        addButton.setPreferredSize(new java.awt.Dimension(33, 33));
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        choosePanel.add(addButton);
        choosePanel.add(filler3);

        editButton.setBackground(new java.awt.Color(255, 255, 255));
        editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanli_khach_san/drawable/Actions-document-edit-icon-32.png"))); // NOI18N
        editButton.setBorder(null);
        editButton.setMaximumSize(new java.awt.Dimension(100, 31));
        editButton.setMinimumSize(new java.awt.Dimension(32, 32));
        editButton.setPreferredSize(new java.awt.Dimension(32, 32));
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });
        choosePanel.add(editButton);
        choosePanel.add(filler4);

        removeButton.setBackground(new java.awt.Color(255, 255, 255));
        removeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanli_khach_san/drawable/Actions-edit-delete-icon-32.png"))); // NOI18N
        removeButton.setBorder(null);
        removeButton.setMaximumSize(new java.awt.Dimension(100, 31));
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });
        choosePanel.add(removeButton);
        choosePanel.add(filler5);

        resetButton.setBackground(new java.awt.Color(255, 255, 255));
        resetButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanli_khach_san/drawable/new-icon-1.png"))); // NOI18N
        resetButton.setBorder(null);
        resetButton.setMaximumSize(new java.awt.Dimension(100, 31));
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });
        choosePanel.add(resetButton);
        choosePanel.add(filler7);

        btnSearch.setBackground(new java.awt.Color(255, 255, 255));
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanli_khach_san/drawable/search-icon-32.png"))); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout informationPanelLayout = new javax.swing.GroupLayout(informationPanel);
        informationPanel.setLayout(informationPanelLayout);
        informationPanelLayout.setHorizontalGroup(
                informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(informationPanelLayout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtGiaDV, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbMaDV, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbTenDV, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbGiaDV, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtMaDV, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTenDV, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(264, 264, 264)
                                .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(choosePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
                                .addContainerGap())
        );
        informationPanelLayout.setVerticalGroup(
                informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(informationPanelLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(informationPanelLayout.createSequentialGroup()
                                                .addComponent(lbMaDV, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtMaDV, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(13, 13, 13)
                                                .addComponent(lbTenDV, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(23, 23, 23)
                                                .addComponent(txtTenDV, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbGiaDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtGiaDV, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(informationPanelLayout.createSequentialGroup()
                                                .addComponent(btnSearch)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(choosePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.add(informationPanel);

        javax.swing.GroupLayout jPanelDVLayout = new javax.swing.GroupLayout(jPanelDV);
        jPanelDV.setLayout(jPanelDVLayout);
        jPanelDVLayout.setHorizontalGroup(
                jPanelDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelDVLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanelDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanelDVLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanelDVLayout.setVerticalGroup(
                jPanelDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDVLayout.createSequentialGroup()
                                .addContainerGap(373, Short.MAX_VALUE)
                                .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        .addGroup(jPanelDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanelDVLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(394, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanelDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanelDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        // TODO add your handling code here:

        listDV.removeAll(listDV);
        listDV = DVDAO.queryAllDichVu();
        pushDataToTable();

    }//GEN-LAST:event_resetButtonActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        // TODO add your handling code here:

        try {
            String madv = txtMaDV.getText();
            String tendv = txtTenDV.getText();
            Integer giadv = MyConvert.parseStringToInt(txtGiaDV.getText());

            DichVu dv = new DichVu(madv, tendv, giadv);
            if (txtMaDV.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "MADV không được để trống", "Lỗi", 1);
            } else {
                int thong_bao = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa " +
                        madv + " dữ liệu này không? ", "Xóa", JOptionPane.YES_NO_OPTION);

                if (thong_bao == JOptionPane.YES_OPTION) {
                    if (DVDAO.deleteDatabase(dv)) {
                        //listDV.remove(listDV.indexOf(dv));
                        listDV.removeAll(listDV);
                        listDV = DVDAO.queryAllDichVu();
                        listDV.remove(dv);

                        pushDataToTable();
                        JOptionPane.showMessageDialog(null, "Dữ liệu đã được xóa", "Xóa", 1);
                    } else {
                        JOptionPane.showMessageDialog(null, "Dữ liệu không xóa được", "Xóa", 2);
                    }
                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + "Chọn thông tin khách hàng muốn xóa.", " Thông báo.", 1);
        }

        pushDataToTable();
    }//GEN-LAST:event_removeButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:

        String madv = txtMaDV.getText();
        String tendv = txtTenDV.getText();
        Integer giadv = MyConvert.parseStringToInt(txtGiaDV.getText());

        DichVu dv = new DichVu(madv, tendv, giadv);
        ;
        if (txtMaDV.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "MADV không được để trống", "Lỗi", 1);
        } else {
            int thong_bao = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn sửa dữ liệu này không? ", "Thêm", JOptionPane.YES_NO_OPTION);
            if (DVDAO.updateDatabase(dv)) {
                    listDV.removeAll(listDV);
                    listDV = DVDAO.queryAllDichVu();
                    pushDataToTable();
                    JOptionPane.showMessageDialog(null, "Dữ liệu đã được cập nhật", "Cập nhật", 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Dữ liệu không được cập nhật", "Cập nhật", 2);
                }
            }

            pushDataToTable();

    }//GEN-LAST:event_editButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:


        String madv = txtMaDV.getText();
        String tendv = txtTenDV.getText();
        Integer giadv = MyConvert.parseStringToInt(txtGiaDV.getText());

        DichVu dv = new DichVu(madv, tendv, giadv);
        listDV.add(dv);
        if (txtMaDV.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "MADV không được để trống", "Lỗi", 1);
        } else {
            int thong_bao = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thêm dữ liệu này không? ", "Thêm", JOptionPane.YES_NO_OPTION);
            if (thong_bao == JOptionPane.YES_OPTION) {

                DVDAO.insertDatabase(dv);
                pushDataToTable();
                JOptionPane.showMessageDialog(null, "Dữ liệu đã được cập nhật", "Cập nhật", 1);
            }
        }


    }//GEN-LAST:event_addButtonActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
                int row = this.table.getSelectedRow();
                this.txtMaDV.setText(MyConvert.parseObjToString(table.getValueAt(row, 0)));
                this.txtTenDV.setText(MyConvert.parseObjToString(table.getValueAt(row, 1)));
                this.txtGiaDV.setText(MyConvert.parseObjToString(table.getValueAt(row, 2)));
    }//GEN-LAST:event_tableMouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        String madv = txtMaDV.getText();
        String tendv = txtTenDV.getText();
        Integer giadv = MyConvert.parseStringToInt(txtGiaDV.getText());

        DichVu dv = new DichVu(madv,tendv,giadv);


        listDV.removeAll(listDV);
        listDV = DVDAO.queryByDichVu(dv);
        pushDataToTable();

        if (listDV.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu cần tìm.", "Thông báo", 1);
        }


    }
    //GEN-LAST:event_btnSearchActionPerformed

    /**
     * @param args the command line arguments
     */
/*    public static void main(String args[]) {
        *//* Set the Nimbus look and feel *//*
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        *//* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         *//*
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DichVuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DichVuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DichVuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DichVuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        *//* Create and display the form *//*
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DichVuFrame().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton btnSearch;
    private javax.swing.JPanel choosePanel;
    private javax.swing.JButton editButton;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.JPanel informationPanel;
    private javax.swing.JPanel jPanelDV;
    private javax.swing.JLabel lbGiaDV;
    private javax.swing.JLabel lbMaDV;
    private javax.swing.JLabel lbTenDV;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton removeButton;
    private javax.swing.JButton resetButton;
    private javax.swing.JTable table;
    private javax.swing.JScrollPane tablePanel;
    private javax.swing.JPanel titilePanel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField txtGiaDV;
    private javax.swing.JTextField txtMaDV;
    private javax.swing.JTextField txtTenDV;
    // End of variables declaration//GEN-END:variables
}
