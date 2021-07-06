/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phong;

import Util.MyColor;
import hoadon.HoaDonDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author khanh
 */
public class ChonPhong extends javax.swing.JFrame {

    private ArrayList<Phong> listIsSelected = new ArrayList<>();
    private ArrayList<Phong> listPhongFull = new ArrayList<>();
    private ArrayList<Phong> listAllPhong = new ArrayList<>();
    private ArrayList<Phong> listPhongDon;
    private ArrayList<Phong> listPhongDoi;
    private ArrayList<Phong> listPhongVIP;
    private ArrayList<Phong> listBaoTri;
    private DanhSachPhongDAO DSPDAO = new DanhSachPhongDAO();
    private Color colorPre;
    private  boolean isSelected=false;

    /**
     * Creates new form ChonPhong
     */
    private PhongDAO PDAO = new PhongDAO();


    @Override
    public void dispose() {
        if (isSelected==false) listIsSelected.removeAll(listIsSelected);
        synchronized (threadNhan) {
            threadNhan.notify();
        }
        super.dispose();
    }

    public ChonPhong() {
        setTitle("Khách sạn Sun Shine");
        setIconImage(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/drawable/logo.png")));
        initComponents();
        dateTuNgay.setDateFormatString("dd-MM-yyyy");
        dateDenNgay.setDateFormatString("dd-MM-yyyy");

        listAllPhong = PDAO.queryAllPhong();
        listBaoTri= PDAO.queryPhongBaoTri();
        listPhongDon = PDAO.queryPhongDon();
        listPhongDoi = PDAO.queryPhongDoi();
        listPhongVIP = PDAO.queryPhongVIP();

    }


    private void reset() {
        jPanel8.removeAll();
        jPanel8.repaint();
        listIsSelected.removeAll(listIsSelected);
        paintHotelRoom();
    }


    private void paintHotelRoom() {

        //System.out.println( list.toString());
        for (Phong p : listAllPhong) {
            JButton btnPhongTemp = new javax.swing.JButton();
            ImageIcon imgTemp = new javax.swing.ImageIcon(getClass().getResource("/drawable/bed.png"));
            Integer posTemp=SwingConstants.RIGHT;


            Color colorTemp = MyColor.colorDon;
            List<Phong> matchvip = listPhongVIP.stream().filter(it -> it.getMAPH().toString().contains(p.getMAPH())).collect(Collectors.toList());
            if (!matchvip.isEmpty()) {
                colorTemp = MyColor.colorVIP;
                imgTemp = new javax.swing.ImageIcon(getClass().getResource("/drawable/background/vip.png"));
                posTemp=SwingConstants.CENTER;
            } else {
                List<Phong> matchdoi = listPhongDoi.stream().filter(it -> it.getMAPH().toString().contains(p.getMAPH())).collect(Collectors.toList());
                if (!matchdoi.isEmpty()) {
                    colorTemp = MyColor.colorDoi;
                    imgTemp = new javax.swing.ImageIcon(getClass().getResource("/drawable/doi.png"));
                }
            }
            List<Phong> matchfull = listPhongFull.stream().filter(it -> it.getMAPH().toString().contains(p.getMAPH())).collect(Collectors.toList());
            if (!matchfull.isEmpty()) {
                colorTemp = MyColor.colorFull;
                btnPhongTemp.setEnabled(false);
            }
            if (!listBaoTri.stream().filter(it -> it.getMAPH().contains(p.getMAPH())).collect(Collectors.toList()).isEmpty()) btnPhongTemp.setEnabled(false);
            btnPhongTemp.setBackground(colorTemp);
            btnPhongTemp.setFont(new java.awt.Font("Tahoma", 0, 16));
            btnPhongTemp.setIcon(imgTemp); // NOI18N
            btnPhongTemp.setText("<html>" + p.getMAPH() + "<br> Đơn giá: " + DSPDAO.queryDSPbyP(p).getDONGIA() + "</html>");
            btnPhongTemp.setHideActionText(true);

            btnPhongTemp.setHorizontalTextPosition(posTemp);
            btnPhongTemp.setPreferredSize(new java.awt.Dimension(200, 120));


            btnPhongTemp.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    String[] words = btnPhongTemp.getText().split("\\s");

                    String StrTemp = words[0];
                    String[] words2 = StrTemp.split("<html>");
                    String StrTemp2 = words2[1];
                    String[] words3 = StrTemp2.split("<br>");
                    String StrTemp3 = words3[0];
                    List<Phong> imcomes1 = listAllPhong.stream().filter(i -> i.getMAPH().equals(StrTemp3))
                            .collect(Collectors.toList());
                    List<Phong> imcomes2 = listIsSelected.stream().filter(i -> i.getMAPH().equals(StrTemp3))
                            .collect(Collectors.toList());
                    if (imcomes2.isEmpty()) {
                        btnPhongTemp.setBackground(new java.awt.Color(0, 204, 255));
                        listIsSelected.add(imcomes1.get(0));
                    } else {
                        Color preColor = null;
                        if (!matchfull.isEmpty()) {
                            preColor = MyColor.colorFull;
                            btnPhongTemp.setEnabled(false);
                        } else {
                            List<Phong> matchvip = listPhongVIP.stream().filter(it -> it.getMAPH().toString().contains(p.getMAPH())).collect(Collectors.toList());
                            if (!matchvip.isEmpty()) preColor = MyColor.colorVIP;
                            else {
                                List<Phong> matchdoi = listPhongDoi.stream().filter(it -> it.getMAPH().toString().contains(p.getMAPH())).collect(Collectors.toList());
                                if (!matchdoi.isEmpty()) preColor = MyColor.colorDoi;
                            }
                        }
                        btnPhongTemp.setBackground(preColor);
                        listIsSelected.remove(imcomes1.get(0));
                    }

                }
            });

            jPanel8.add(btnPhongTemp);
        }
        if (listAllPhong.size() < 16)
            for (int i = 0; i < 16 - listAllPhong.size(); i++) {
                Button btnTemp = new Button();
                btnTemp.setBackground(Color.WHITE);
                btnTemp.setEnabled(false);
                jPanel8.add(btnTemp);
            }
        jPanel8.revalidate();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelCP = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelTN = new javax.swing.JLabel();
        dateTuNgay = new com.toedter.calendar.JDateChooser();
        jLabelDN = new javax.swing.JLabel();
        dateDenNgay = new com.toedter.calendar.JDateChooser();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        btnSetDate = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnVIP = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnDon = new javax.swing.JButton();
        btnFull = new javax.swing.JButton();
        btnDoi = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel8 = new javax.swing.JPanel();
        btnOKE = new javax.swing.JButton();
        jLabelAVT = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanelCP.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCP.setPreferredSize(new java.awt.Dimension(1230, 720));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1483, 100));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poster/Chonphong.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabelTN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelTN.setText("Từ ngày");

        jLabelDN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelDN.setText("Đến ngày");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(412, 412, 412)
                .addComponent(jLabelTN, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(dateTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelDN, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(dateDenNgay, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(dateTuNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelDN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 204, 102));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Tình trạng phòng");

        btnSetDate.setBackground(MyColor.button);
        btnSetDate.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnSetDate.setText("Chọn ngày");
        btnSetDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetDateActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setText("Phòng đôi");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText("Phòng đơn");

        btnVIP.setBackground(new java.awt.Color(255, 200, 0));
        btnVIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVIPActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("Đã đặt");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel10.setText("Phòng VIP");

        btnDon.setBackground(new java.awt.Color(102, 255, 102));
        btnDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDonActionPerformed(evt);
            }
        });

        btnFull.setBackground(new java.awt.Color(255, 51, 51));
        btnFull.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFullActionPerformed(evt);
            }
        });

        btnDoi.setBackground(new java.awt.Color(255, 153, 153));
        btnDoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel7)
                .addGap(97, 97, 97)
                .addComponent(btnFull)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDoi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnVIP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(151, 151, 151)
                .addComponent(btnSetDate)
                .addGap(37, 37, 37))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnFull, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDoi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVIP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSetDate)))
                .addContainerGap())
        );

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new java.awt.GridLayout(0, 4));
        jScrollPane2.setViewportView(jPanel8);

        btnOKE.setBackground(new java.awt.Color(255, 204, 204));
        btnOKE.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnOKE.setText("Chọn");
        btnOKE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKEActionPerformed(evt);
            }
        });

        jLabelAVT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/taikhoan/avatar/NV.png"))); // NOI18N

        javax.swing.GroupLayout jPanelCPLayout = new javax.swing.GroupLayout(jPanelCP);
        jPanelCP.setLayout(jPanelCPLayout);
        jPanelCPLayout.setHorizontalGroup(
            jPanelCPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCPLayout.createSequentialGroup()
                .addGroup(jPanelCPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelCPLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1056, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOKE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1231, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelCPLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelAVT)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelCPLayout.setVerticalGroup(
            jPanelCPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCPLayout.createSequentialGroup()
                .addGroup(jPanelCPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAVT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanelCPLayout.createSequentialGroup()
                        .addGap(0, 429, Short.MAX_VALUE)
                        .addComponent(btnOKE, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOKEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKEActionPerformed
        // TODO add your handling code here:
        //CODE trước trong luồng phụ
        isSelected=true;
        dispose();
    }//GEN-LAST:event_btnOKEActionPerformed

    private Integer valuebtn = 0;

    private void btnFullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFullActionPerformed
        // TODO add your handling code here:
        if (dateTuNgay.getDate()==null && dateDenNgay.getDate()==null) return;
        if (valuebtn !=1) {
            listAllPhong = listPhongFull;
            valuebtn = 1;
        } else {
            valuebtn = 0;
        }
        reset();
        listAllPhong = PDAO.queryAllPhong();
    }//GEN-LAST:event_btnFullActionPerformed

    private void btnDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDonActionPerformed
        // TODO add your handling code here:
        if (dateTuNgay.getDate()==null && dateDenNgay.getDate()==null) return;
        if (valuebtn !=2) {
            listAllPhong = listPhongDon;
            valuebtn = 2;
        } else {
            valuebtn = 0;
        }
        reset();
        listAllPhong = PDAO.queryAllPhong();
    }//GEN-LAST:event_btnDonActionPerformed


    private void btnDoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiActionPerformed
        // TODO add your handling code here:
        if (dateTuNgay.getDate()==null && dateDenNgay.getDate()==null) return;
        if (valuebtn !=3) {
            listAllPhong = listPhongDoi;
            valuebtn = 3;
        } else {
            valuebtn = 0;
        }
        reset();
        listAllPhong = PDAO.queryAllPhong();
    }//GEN-LAST:event_btnDoiActionPerformed


    private void btnVIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVIPActionPerformed
        // TODO add your handling code here:
        if (dateTuNgay.getDate()==null && dateDenNgay.getDate()==null) return;
        if (valuebtn !=4) {
            listAllPhong = listPhongVIP;
            valuebtn = 4;
        } else {
            valuebtn = 0;
        }
        reset();
        listAllPhong = PDAO.queryAllPhong();
    }//GEN-LAST:event_btnVIPActionPerformed
    private void btnSetDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetDateActionPerformed
        // TODO add your handling code here:
        listAllPhong = PDAO.queryAllPhong();
        listPhongFull = PDAO.queryAllPhongFullByStEd(dateTuNgay.getDate(), dateDenNgay.getDate());
        reset();
        valuebtn=0;
    }//GEN-LAST:event_btnSetDateActionPerformed

    public Thread threadNhan;

    public ArrayList<Phong> getListPhong() {

        return listIsSelected;
    }

    public Date getNGBD() {
        return dateTuNgay.getDate();
    }

    public Date getNGKT() {
        return dateDenNgay.getDate();
    }

    public void setThread(Thread t) {

        threadNhan = t;
    }

    public JPanel getPanel() {
        return jPanelCP;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChonPhong().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDoi;
    private javax.swing.JButton btnDon;
    private javax.swing.JButton btnFull;
    private javax.swing.JButton btnOKE;
    private javax.swing.JButton btnSetDate;
    private javax.swing.JButton btnVIP;
    private com.toedter.calendar.JDateChooser dateDenNgay;
    private com.toedter.calendar.JDateChooser dateTuNgay;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAVT;
    private javax.swing.JLabel jLabelDN;
    private javax.swing.JLabel jLabelTN;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelCP;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
