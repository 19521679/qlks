/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datphong;

import Util.MyColor;
import Util.MyConvert;
import Util.MyInstance;
import hoadon.HoaDon;
import homepage.home;
import khachhang.KhachHang;
import khachhang.KhachHangDAO;
import khachhang.KhachHangFrame;
import khuyenmai.KhuyenMai;
import khuyenmai.KhuyenMaiFrame;
import nhanvien.NhanVien;
import phong.ChonPhong;
import phong.Phong;
import phong.ThuePhong;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author khanh
 */
public class DatPhong extends javax.swing.JFrame {
    private Integer isInstace = 0;
    private KhachHang khachHang = null;
    private ArrayList<Phong> listPhong = new ArrayList<>();
    private NhanVien nhanvien = home.getNhanVien();
    private DatPhongDAO DPDAO = new DatPhongDAO();
    private Thread threadNhan;
    private KhuyenMai khuyenmai;
    private ArrayList<ThuePhong> listThuePh = new ArrayList<>();

    private HoaDon hoadon;


    /**
     * Creates new form DatPhong
     */
    public DatPhong() {
        initComponents();
        dateTuNgay.setDateFormatString("dd-MM-yyyy");
        dateDenNgay.setDateFormatString("dd-MM-yyyy");
        btnKH.setFont(new Font("Tahoma", 0, 16));
        dateTuNgay.setEnabled(false);
        dateDenNgay.setEnabled(false);
    }


    @Override
    public void dispose() {
        synchronized (threadNhan) {
            threadNhan.notify();
        }
        super.dispose();
    }

    private void reset() {

        jPanel8.removeAll();
        jPanel8.repaint();
        paintKh();
        paintPH();

        jPanel8.repaint();
        jPanel8.revalidate();
        revalidate();
        repaint();
        jPanelDP.repaint();
    }


    private void paintKh() {
        if (khachHang != null) {
            btnKH.setText("MAKH: " + khachHang.getMAKH() + "       |     Tên khách hàng: " + khachHang.getTENKH() + "       |       Loại khách hàng: " + khachHang.getLOAIKH());

        } else {
            btnKH.setText(" ");
        }

    }

    private void paintPH() {

        if (listPhong != null && !listPhong.isEmpty()) {
            Integer index = 0;
            for (Phong p : listPhong) {


                JButton btnTemp = new javax.swing.JButton();
                btnTemp.setBackground(new java.awt.Color(255, 207, 134));
                btnTemp.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                btnTemp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable/bed_icon.png"))); // NOI18N

                btnTemp.setText("MAPH: " + p.getMAPH() + "  |  Số người: " + (listThuePh.size() > index ? listThuePh.get(index).getSONGUOITHUE().toString() : ""));
                btnTemp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                btnTemp.setIconTextGap(40);
                btnTemp.setMaximumSize(new java.awt.Dimension(115, 60));
                btnTemp.setMinimumSize(new java.awt.Dimension(115, 60));
                btnTemp.setPreferredSize(new java.awt.Dimension(115, 60));
                btnTemp.addActionListener(e -> {
                    String[] words = btnTemp.getText().split("\\s");
                    String StrTemp = words[1];


                    Object[] options = {"Nhập số người", "Xoá đặt phòng", "Thoát"};
                    int result = JOptionPane.showOptionDialog(this,
                            "Bạn muốn nhập số người hay xoá đặt phòng này?",
                            "Chi tiết",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[2]);

                    if (result == JOptionPane.YES_OPTION) {
                        String songuoi = JOptionPane.showInputDialog(this, "Nhập số người", "Chi tiết phòng", JOptionPane.DEFAULT_OPTION);
                        if ((!songuoi.isEmpty())) {
                            List<Phong> matches = listPhong.stream().filter(it -> it.getMAPH().toString().contains(StrTemp)).collect(Collectors.toList());
                            List<ThuePhong> phongtrongthuephong = listThuePh.stream().filter(it -> it.getMAPH().toString().contains(matches.get(0).getMAPH())).collect(Collectors.toList());
                            if (!phongtrongthuephong.isEmpty()) listThuePh.removeAll(phongtrongthuephong);
                            ThuePhong thNew = new ThuePhong();
                            thNew.setMAPH(matches.get(0).getMAPH());
                            thNew.setSONGUOITHUE(MyConvert.parseStringToInt(songuoi));
                            listThuePh.add(thNew);

                            reset();
                        }
                    } else if (result == JOptionPane.NO_OPTION) {
                        List<Phong> matches = listPhong.stream().filter(it -> it.getMAPH().toString().contains(StrTemp)).collect(Collectors.toList());

                        listPhong.removeAll(matches);

                        reset();
                    }


                });
                jPanel8.add(btnTemp);
                index++;
            }
            if (listPhong.size() < 8)
                for (int i = 0; i < 8 - listPhong.size(); i++) {
                    Button btnTemp = new Button();
                    btnTemp.setBackground(Color.WHITE);
                    btnTemp.setEnabled(false);
                    jPanel8.add(btnTemp);
                }
            jPanel8.revalidate();
        }
        pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private Image image = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/drawable/background/background.png"));
    private Image image2 = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/drawable/Sanpham.png"));

    private void initComponents() {

        jPanelDP = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);

            }
        };
        jPanel2 = new javax.swing.JPanel();
        jLabelDN = new javax.swing.JLabel();
        jLabelTN = new javax.swing.JLabel();
        dateTuNgay = new com.toedter.calendar.JDateChooser();
        dateDenNgay = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        btnKH = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel8 = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);

            }
        };
        btnKM = new javax.swing.JButton();
        btnCKH = new javax.swing.JButton();
        btnCPH = new javax.swing.JButton();
        btnSet = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanelDP.setBackground(new java.awt.Color(255, 255, 255));
        jPanelDP.setPreferredSize(new java.awt.Dimension(1230, 720));

        jPanel2.setBackground(MyColor.panel1);

        jLabelDN.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabelDN.setText("Đến ngày");

        jLabelTN.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabelTN.setText("Từ ngày");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(407, Short.MAX_VALUE)
                                .addComponent(jLabelTN, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(77, 77, 77)
                                .addComponent(jLabelDN, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(119, 119, 119))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelDN, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                        .addComponent(jLabelTN, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dateTuNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(dateDenNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable/background/poster.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 1230, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btnKH.setBackground(new java.awt.Color(255, 255, 255));
        btnKH.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnKH.setHideActionText(true);
        btnKH.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnKH.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKHActionPerformed(evt);
            }
        });

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new java.awt.GridLayout(0, 1, 2, 3));
        jScrollPane2.setViewportView(jPanel8);

        btnKM.setBackground(new java.awt.Color(255, 153, 0));
        btnKM.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnKM.setText("Khuyến mãi:");
        btnKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKMActionPerformed(evt);
            }
        });

        btnCKH.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCKH.setText("CHỌN KHÁCH HÀNG");
        btnCKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCKHActionPerformed(evt);
            }
        });

        btnCPH.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCPH.setText("CHỌN PHÒNG");
        btnCPH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCPHActionPerformed(evt);
            }
        });

        btnSet.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSet.setText("ĐẶT");
        btnSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelDPLayout = new javax.swing.GroupLayout(jPanelDP);
        jPanelDP.setLayout(jPanelDPLayout);
        jPanelDPLayout.setHorizontalGroup(
                jPanelDPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanelDPLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanelDPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelDPLayout.createSequentialGroup()
                                                .addComponent(btnKH, javax.swing.GroupLayout.PREFERRED_SIZE, 984, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)
                                                .addComponent(btnCKH))
                                        .addGroup(jPanelDPLayout.createSequentialGroup()
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 984, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)
                                                .addGroup(jPanelDPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btnCPH, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnKM, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnSet, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanelDPLayout.setVerticalGroup(
                jPanelDPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelDPLayout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addGroup(jPanelDPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnKH, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnCKH, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(11, 11, 11)
                                .addGroup(jPanelDPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanelDPLayout.createSequentialGroup()
                                                .addComponent(btnCPH, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(6, 6, 6)
                                                .addComponent(btnKM, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(196, 196, 196)
                                                .addComponent(btnSet, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanelDP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanelDP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKHActionPerformed
        // TODO add your handling code here:
        Object[] options = {"Có", "Không"};
        if (khachHang != null) {
            int result = JOptionPane.showOptionDialog(this,
                    "Bạn có muốn chọn lại khách hàng không?",
                    "Chi tiết",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);

            if (result == JOptionPane.YES_OPTION) {
                khachHang = null;
                btnKH.setText("");

                reset();
            } else if (result == JOptionPane.DEFAULT_OPTION) return;
        }
    }//GEN-LAST:event_btnKHActionPerformed

    private void btnKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKMActionPerformed
        // TODO add your handling code here:
        Object[] options = {"Chọn lại", "Xoá"};
        if (khuyenmai != null) {
            int result = JOptionPane.showOptionDialog(this,
                    "Bạn có muốn chọn lại hay xoá chọn khuyến mãi này?",
                    "Chi tiết",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (result == JOptionPane.NO_OPTION) {
                khuyenmai = null;
                btnKM.setText("");
                return;
            }
            else if (result == JOptionPane.DEFAULT_OPTION) return;
        }
        if (khachHang==null || listPhong.isEmpty())
        {
            Object[] options2 = {"Chấp thuận"};
            int result = JOptionPane.showOptionDialog(this,
                    "Bạn chưa chọn khách hàng hoặc phòng",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options2,
                    options2[0]);
            return;
        }
        KhuyenMaiFrame child = new KhuyenMaiFrame();
        child.setVisible(true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                {
                    synchronized (threadGui) {
                        // Pause
                        try { //code sau khi mở lại luồng chính
                            threadGui.wait();
                            khuyenmai = (child.getKhuyenMaiIsSelected());
                            if (khuyenmai != null) btnKM.setText(khuyenmai.getMAKM());
                        } catch (InterruptedException e) {
                        }
                    }

                }
            }

        };

        threadGui = new Thread(runnable);
        child.setThreadNhan(threadGui, khachHang, dateTuNgay.getDate(), dateDenNgay.getDate());

        //từ đây trở lên là trước khi luồng chính bị đóng
        threadGui.start();
    }//GEN-LAST:event_btnKMActionPerformed


    private void btnCPHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCPHActionPerformed
        // TODO add your handling code here
        ChonPhong cpframe = new ChonPhong();
        cpframe.setVisible(true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                {
                    synchronized (threadGui) {
                        // Pause
                        try {
                            threadGui.wait();
                            //code sau khi mở lại luồng chính

                            listPhong = cpframe.getListPhong();
                            dateTuNgay.setDate(cpframe.getNGBD());
                            dateTuNgay.updateUI();
                            dateDenNgay.setDate(cpframe.getNGKT());
                            dateTuNgay.updateUI();

                            reset();
                        } catch (InterruptedException e) {
                        }
                    }

                }
            }

        };

        threadGui = new Thread(runnable);
        cpframe.setThread(threadGui);

        //từ đây trở lên là trước khi luồng chính bị đóng
        threadGui.start();
    }//GEN-LAST:event_btnCPHActionPerformed

    private void btnSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetActionPerformed
        // TODO add your handling code here:
        if (khachHang == null || listPhong.isEmpty()) {
            thongBaoKetThuc();
            return;
        }

        String mess= DPDAO.insertKHintoPhong(khachHang, nhanvien, listPhong, listThuePh, dateTuNgay.getDate(), dateDenNgay.getDate(), khuyenmai);

            Object[] options = {"Chấp nhận"};
            int result = JOptionPane.showOptionDialog(this,
                    mess,
                    "Thông báo",
                    JOptionPane.OK_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if(result==JOptionPane.OK_OPTION)
            {
                if (mess.equals("Thành công")) dispose();
            }



    }//GEN-LAST:event_btnSetActionPerformed


    private void btnCKHActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        KhachHangFrame child = new KhachHangFrame();
        child.setVisible(true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                {
                    synchronized (threadGui) {
                        // Pause
                        try {
                            threadGui.wait();
                            //code sau khi mở lại luồng chính
                            khachHang = child.getKHIsSelected();
                            reset();
                            //    jTextField1.setText(child.getA());
                        } catch (InterruptedException e) {
                        }
                    }

                }
            }


        };

        threadGui = new Thread(runnable);
        child.setKHThread(threadGui);

        //từ đây trở lên là trước khi luồng chính bị đóng
        threadGui.start();
    }


    public Thread threadGui;

    private void thongBaoKetThuc() {
        Object[] options = {"Có", "Không"};
        int result = JOptionPane.showOptionDialog(this,
                "Bạn chưa chọn khách hàng hoặc phòng nào, bạn muốn tiếp tục đặt phòng không?",
                "Thông báo",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (result == JOptionPane.YES_OPTION) return;
        else if (result == JOptionPane.NO_OPTION) {

            dispose();

        }
    }


    public void setEdit(Thread th, HoaDon hd, NhanVien nv) {
        threadNhan = th;
        isInstace = MyInstance.IS_EDIT;
        hoadon = hd;
        nhanvien = nv;
        btnCKH.setVisible(false);
        khachHang = new KhachHangDAO().queryKHbyHD(hoadon);
        btnKM.setVisible(false);
        reset();
        paintKh();

    }

    public void setAdd(Thread th, NhanVien nv) {
        threadNhan = th;
        nhanvien = nv;
        isInstace = MyInstance.IS_ADD;
        reset();
    }


    public JPanel getPanel() {
        return jPanel1;
    }


    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DatPhong().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCKH;
    private javax.swing.JButton btnCPH;
    private javax.swing.JButton btnKH;
    private javax.swing.JButton btnKM;
    private javax.swing.JButton btnSet;
    private com.toedter.calendar.JDateChooser dateDenNgay;
    private com.toedter.calendar.JDateChooser dateTuNgay;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelDN;
    private javax.swing.JLabel jLabelTN;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelDP;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
