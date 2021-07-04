/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanvien;

import Util.MyColor;
import Util.MyConvert;
import dichvu.DichVu;
import dichvu.DichVuDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Util.MyColor;

/**
 * @author khanh
 */
public class NhanVienFrame extends javax.swing.JFrame {
    private NhanVienDAO NVDAO = new NhanVienDAO();
    public Thread threadToTTNV;
    private NhanVien isSelected;
    ArrayList<NhanVien> listNV = new ArrayList<>();

    /**
     * Creates new form NhanVienFrame
     */
    public NhanVienFrame() {
        initComponents();
        dataChange();
    }

    private void dataChange() {
        listNV = NVDAO.queryAllNV();
        reset();
    }

    private void reset() {
        isSelected = null;
        DefaultTableModel model1 = (DefaultTableModel) jTable2.getModel();
        int rows = model1.getRowCount();
        for (int i = rows - 1; i >= 0; i--) {
            model1.removeRow(i);
        }


        String[] ColumnName = {"MANV", "MAQL", "HOTEN", "GIOITINH", "DCHI", "SODT", "NGSINH", "NGVL", "LUONG"};
        Object[][] Rows = new Object[listNV.size()][9];
        for (int i = 0; i < listNV.size(); i++) {
            Rows[i][0] = listNV.get(i).getMANV();
            Rows[i][1] = listNV.get(i).getMAQL();
            Rows[i][2] = listNV.get(i).getHOTEN();
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Rows[i][3] = listNV.get(i).getGIOITINH();
            Rows[i][4] = listNV.get(i).getDCHI();
            Rows[i][5] = listNV.get(i).getSODT();
            Rows[i][6] = format.format(listNV.get(i).getNGSINH());
            Rows[i][7] = format.format(listNV.get(i).getNGVL());
            Rows[i][8] = MyConvert.parseIntToString(listNV.get(i).getLUONG());
        }
        DefaultTableModel model = new DefaultTableModel(Rows, ColumnName);
        jTable2.setModel(model);

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
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabelAVT = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnTk = new javax.swing.JButton();
        txt1 = new javax.swing.JTextField();
        tablePanel1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        btnT = new javax.swing.JButton();
        btnX = new javax.swing.JButton();
        btnS = new javax.swing.JButton();
        btnS1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelDV.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1483, 100));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poster/Nhanvien.png"))); // NOI18N

        jLabelAVT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/taikhoan/avatar/NV.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelAVT)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabelAVT)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 56, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 204, 204));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("Nhập mã nhân viên");

        btnTk.setBackground(MyColor.button);
        btnTk.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnTk.setText("Tìm kiếm");
        btnTk.setToolTipText("");
        btnTk.setActionCommand("");
        btnTk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTkActionPerformed(evt);
            }
        });

        txt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(btnTk, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTk)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt1))
                .addContainerGap())
        );

        tablePanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTable2.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MANV", "MAQL", "HOTEN", "GIOITINH", "DCHI", "NGSINH", "SODT", "NGVL", "LUONG"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable2.setGridColor(new java.awt.Color(255, 255, 255));
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        tablePanel1.setViewportView(jTable2);

        btnT.setBackground(new java.awt.Color(255, 204, 204));
        btnT.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnT.setText("Thêm");
        btnT.setActionCommand("");
        btnT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTActionPerformed(evt);
            }
        });

        btnX.setBackground(new java.awt.Color(255, 204, 204));
        btnX.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnX.setText("Xoá");
        btnX.setActionCommand("");
        btnX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXActionPerformed(evt);
            }
        });

        btnS.setBackground(new java.awt.Color(255, 204, 204));
        btnS.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnS.setText("Sửa");
        btnS.setActionCommand("");
        btnS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSActionPerformed(evt);
            }
        });

        btnS1.setBackground(new java.awt.Color(255, 204, 204));
        btnS1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnS1.setText("Tìm kiếm nâng cao");
        btnS1.setActionCommand("");
        btnS1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnS1ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable/background/nhanvienpanel.png"))); // NOI18N

        javax.swing.GroupLayout jPanelDVLayout = new javax.swing.GroupLayout(jPanelDV);
        jPanelDV.setLayout(jPanelDVLayout);
        jPanelDVLayout.setHorizontalGroup(
            jPanelDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDVLayout.createSequentialGroup()
                .addComponent(tablePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1032, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnT, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnX, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnS, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnS1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelDVLayout.createSequentialGroup()
                .addGroup(jPanelDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelDVLayout.createSequentialGroup()
                        .addGroup(jPanelDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1234, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1234, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelDVLayout.setVerticalGroup(
            jPanelDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDVLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDVLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnT, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnX, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnS, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnS1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDVLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tablePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelDV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTkActionPerformed
        // TODO add your handling code here:
        if (txt1.getText().isBlank()) dataChange();
        else {
            NhanVien temp = new NhanVien();
            temp.setMANV(txt1.getText());
            listNV = NVDAO.queryByNV(temp);
            reset();
        }
    }//GEN-LAST:event_btnTkActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:

        try {
            int row = this.jTable2.getSelectedRow();
            isSelected = new NhanVien();
            isSelected.setMANV(MyConvert.parseObjToString(jTable2.getValueAt(row, 0)));
            isSelected.setMAQL(MyConvert.parseObjToString(jTable2.getValueAt(row, 1)));
            isSelected.setHOTEN(MyConvert.parseObjToString(jTable2.getValueAt(row, 2)));

            isSelected.setGIOITINH(MyConvert.parseObjToString(jTable2.getValueAt(row, 3)));
            isSelected.setDCHI(MyConvert.parseObjToString(jTable2.getValueAt(row, 4)));
            isSelected.setSODT(MyConvert.parseObjToString(jTable2.getValueAt(row, 5)));
            isSelected.setNGSINH(new SimpleDateFormat("dd-MM-yyyy").parse(jTable2.getValueAt(row, 6).toString()));
            isSelected.setNGVL(new SimpleDateFormat("dd-MM-yyyy").parse(jTable2.getValueAt(row, 7).toString()));

            isSelected.setLUONG(MyConvert.parseObjToInt(jTable2.getValueAt(row, 8)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jTable2MouseClicked

    private void btnTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTActionPerformed
        // TODO add your handling code here:
        ThongTinNV child = new ThongTinNV();
        child.setVisible(true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                {
                    synchronized (threadToTTNV) {
                        // Pause
                        try { //code sau khi mở lại luồng chính
                            threadToTTNV.wait();

                            dataChange();
                        } catch (InterruptedException e) {
                        }
                    }

                }
            }

        };

        threadToTTNV = new Thread(runnable);
        child.setThem(threadToTTNV);

        //từ đây trở lên là trước khi luồng chính bị đóng
        threadToTTNV.start();
    }//GEN-LAST:event_btnTActionPerformed

    private void btnXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXActionPerformed
        // TODO add your handling code here:
        if (isSelected == null)
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn nhân viên nào", "Thông tin", JOptionPane.INFORMATION_MESSAGE);
        else {
            Object[] options = {"Có", "Không"};
            int result = JOptionPane.showOptionDialog(this,
                    "Bạn có chắc muốn xoá nhân viên này?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);

            if (result == JOptionPane.YES_OPTION) {
                String mess = NVDAO.deleteDatabase(isSelected);
                Object[] options2 = {"Chấp nhận"};
                int result2 = JOptionPane.showOptionDialog(this,
                        mess,
                        "Thông báo",
                        JOptionPane.OK_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options2,
                        options2[0]);
                if (mess.contains("Thành công")) {
                    dataChange();
                }

            }
        }//GEN-LAST:event_btnXActionPerformed
    }

    private void btnSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSActionPerformed
        // TODO add your handling code here:
        ThongTinNV child = new ThongTinNV();
        child.setVisible(true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                {
                    synchronized (threadToTTNV) {
                        // Pause
                        try { //code sau khi mở lại luồng chính
                            threadToTTNV.wait();

                            dataChange();
                        } catch (InterruptedException e) {
                        }
                    }

                }
            }

        };

        threadToTTNV = new Thread(runnable);
        child.setSua(threadToTTNV, isSelected);

        //từ đây trở lên là trước khi luồng chính bị đóng
        threadToTTNV.start();
    }//GEN-LAST:event_btnSActionPerformed

    private void btnS1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnS1ActionPerformed
        // TODO add your handling code here:
        ThongTinNV child = new ThongTinNV();
        child.setVisible(true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                {
                    synchronized (threadToTTNV) {
                        // Pause
                        try { //code sau khi mở lại luồng chính
                            threadToTTNV.wait();
                            listNV=child.getListNV();

                            reset();
                        } catch (InterruptedException e) {
                        }
                    }

                }
            }

        };

        threadToTTNV = new Thread(runnable);
        child.setTimKiemNC(threadToTTNV);
        //từ đây trở lên là trước khi luồng chính bị đóng
        threadToTTNV.start();
    }//GEN-LAST:event_btnS1ActionPerformed

    private void txt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt1ActionPerformed

    public JPanel getPanel() {
        return jPanelDV;
    }

    /**
     * @param args the command line arguments
     */
    /*   public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(NhanVienFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanVienFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanVienFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanVienFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        *//* Create and display the form *//*
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NhanVienFrame().setVisible(true);
            }
        });
    }*/
    public static void main(String[] args) {
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnS;
    private javax.swing.JButton btnS1;
    private javax.swing.JButton btnT;
    private javax.swing.JButton btnTk;
    private javax.swing.JButton btnX;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelAVT;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelDV;
    private javax.swing.JTable jTable2;
    private javax.swing.JScrollPane tablePanel1;
    private javax.swing.JTextField txt1;
    // End of variables declaration//GEN-END:variables
}
