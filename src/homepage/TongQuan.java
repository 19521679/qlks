/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homepage;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.border.Border;

import Util.MyColor;
import Util.MyConvert;
import Util.MyScrollPanel;
import datphong.DatPhong;
import hoadon.HoaDonFrame;
import hoadon.HoaDonFrame;
import khachhang.KhachHang;
import khachhang.ThongTinKhachHang;
import phong.*;

/**
 * @author khanh
 */
public class TongQuan extends javax.swing.JFrame {

    /**
     * Creates new form TrangChu
     */
    private ArrayList<Phong> listAllPhong = new ArrayList<>();
    private ArrayList<Phong> listIsSelected = new ArrayList<>();
    private ArrayList<Phong> listPhongFull = new ArrayList<>();
    private ArrayList<Phong> listPhongDon;
    private ArrayList<Phong> listPhongDoi;
    private ArrayList<Phong> listPhongVIP;
    //private ArrayList<Phong> listPhongAll = new ArrayList<>();
    private HoaDonFrame hoadonframe;
    private DanhSachPhongDAO DSPDAO = new DanhSachPhongDAO();
    private ArrayList<Phong> listBaoTri = new ArrayList<>();
    private Thread threadPoster;
    Integer isSelected = 1;
    ArrayList<JCheckBox> listJCheckbox = new ArrayList<>();
    /**
     * Creates new form ChonPhong
     */
    private PhongDAO PDAO = new PhongDAO();
    private Thread threadGui;

    public TongQuan() {
        jPanel1 = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);

            }
        };
        initComponents();
        listJCheckbox.add(jCheckBox1);
        listJCheckbox.add(jCheckBox2);
        listJCheckbox.add(jCheckBox3);
        listJCheckbox.add(jCheckBox4);
        jCheckBox1.setSelected(true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                {
                    synchronized (threadPoster) {
                        // Pause
                        try { //code sau khi mở lại luồng chính
                            while (true) {
                                Thread.sleep(5000);
                                isSelected = isSelected == 4 ? 1 : isSelected + 1;
                                for (int i = 0; i < 4; i++) {
                                    if (i == isSelected - 1) listJCheckbox.get(i).setSelected(true);
                                    else listJCheckbox.get(i).setSelected(false);
                                }
                                if (isSelected == 1) {
                                    for (int i = 37; i >= 0; i--) {
                                        Double toado = 1.0 * i / 50;

                                        MyScrollPanel.scroll(jScrollPane4, 0.0, toado);
                                        Thread.sleep(10);
                                    }
                                } else
                                    for (int i = 0; i < 50; i++) {
                                        Double toado = 1.0 * (isSelected - 2) / 4 + 1.0 * (i + 1) * 0.005;
                                        MyScrollPanel.scroll(jScrollPane4, 0.0, toado);
                                        Thread.sleep(10);
                                    }
                            }
                        } catch (InterruptedException e) {
                        }
                    }

                }
            }

        };

        threadPoster = new Thread(runnable);
        //từ đây trở lên là trước khi luồng chính bị đóng
        threadPoster.start();


        if (home.getNhanVien().getMAQL() == null) btnNC.setVisible(true);

        dateTuNgay.setDate(new Date(System.currentTimeMillis()));
        dateDenNgay.setDate(new Date(System.currentTimeMillis()));

        listPhongDon = PDAO.queryPhongDon();
        listPhongDoi = PDAO.queryPhongDoi();
        listPhongVIP = PDAO.queryPhongVIP();
        listBaoTri = PDAO.queryPhongBaoTri();
        dataChange();
    }

    public void dataChange() {
        listAllPhong = PDAO.queryAllPhong();
        listPhongFull = PDAO.queryAllPhongFullByStEd(dateDenNgay.getDate(), dateDenNgay.getDate());
        reset();
    }

    private void notifyDateChange() {
        hoadonframe.dataChange();
        dataChange();
    }

    public void setHDFtoTQF(HoaDonFrame frame) {
        hoadonframe = frame;
    }


    private void reset() {
        jPanel8.removeAll();
        jPanel8.repaint();
        paintHotelRoom();
    }

    private void paintHotelRoom() {

        listIsSelected.removeAll(listIsSelected);
        for (Phong p : listAllPhong) {
            JButton btnPhongTemp = new javax.swing.JButton();
            ImageIcon imgTemp = new javax.swing.ImageIcon(getClass().getResource("/drawable/bed.png"));
            Integer posTemp = SwingConstants.RIGHT;


            Color colorTemp = MyColor.colorDon;
            List<Phong> matchvip = listPhongVIP.stream().filter(it -> it.getMAPH().toString().contains(p.getMAPH())).collect(Collectors.toList());
            if (!matchvip.isEmpty()) {
                colorTemp = MyColor.colorVIP;
                imgTemp = new javax.swing.ImageIcon(getClass().getResource("/drawable/background/vip.png"));
                posTemp = SwingConstants.CENTER;
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
            }
            if (!listBaoTri.stream().filter(it -> it.getMAPH().contains(p.getMAPH())).collect(Collectors.toList()).isEmpty())
                btnPhongTemp.setEnabled(false);
            btnPhongTemp.setBackground(colorTemp);
            btnPhongTemp.setFont(new java.awt.Font("Tahoma", 0, 16));
            btnPhongTemp.setIcon(imgTemp); // NOI18N
            btnPhongTemp.setText("<html>" + p.getMAPH() + "<br> Đơn giá: " + DSPDAO.queryDSPbyP(p).getDONGIA() + "</html>");
            btnPhongTemp.setHideActionText(true);

            btnPhongTemp.setHorizontalTextPosition(posTemp);
            btnPhongTemp.setPreferredSize(new java.awt.Dimension(200, 120));

            /*btnPhongTemp.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    ThongTinPhongAdapter child = new ThongTinPhongAdapter();
                    child.setAdapter(p);
                    child.setVisible(true);

                }
            });*/

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
        jPanel8.repaint();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private Image image = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/drawable/background/background.png"));

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel3 = new javax.swing.JPanel();
        jLabelTN = new javax.swing.JLabel();
        jLabelDN = new javax.swing.JLabel();
        dateTuNgay = new com.toedter.calendar.JDateChooser();
        dateDenNgay = new com.toedter.calendar.JDateChooser();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnSetDate = new javax.swing.JButton();
        btnFull = new javax.swing.JButton();
        btnDoi = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnDon = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnVIP = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel8 = new javax.swing.JPanel();
        btnDP = new javax.swing.JButton();
        btnNC = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1230, 720));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabelTN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelTN.setText("Từ ngày");

        jLabelDN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelDN.setText("Đến ngày");

        dateTuNgay.setDateFormatString("yyyy-MM-dd");
        dateTuNgay.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        dateDenNgay.setDateFormatString("yyyy-MM-dd");
        dateDenNgay.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jCheckBox4.setBackground(new java.awt.Color(255, 204, 204));
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });

        jCheckBox3.setBackground(new java.awt.Color(255, 204, 204));
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jCheckBox2.setBackground(new java.awt.Color(255, 204, 204));
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jCheckBox1.setBackground(new java.awt.Color(255, 204, 204));
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jCheckBox1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelTN, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelDN, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jCheckBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jCheckBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabelDN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabelTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(dateTuNgay, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                                .addComponent(dateDenNgay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Phòng VIP");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Tình trạng phòng");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("Đã đặt");

        btnSetDate.setBackground(MyColor.button);
        btnSetDate.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnSetDate.setText("Chọn ngày");
        btnSetDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetDateActionPerformed(evt);
            }
        });

        btnFull.setBackground(new java.awt.Color(0, 0, 255));
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

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Phòng đôi");

        btnDon.setBackground(new java.awt.Color(102, 255, 102));
        btnDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Phòng đơn");

        btnVIP.setBackground(new java.awt.Color(255, 200, 0));
        btnVIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVIPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel2)
                                .addGap(40, 40, 40)
                                .addComponent(btnFull)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnDon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDoi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(btnVIP)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(199, 199, 199)
                                .addComponent(btnSetDate, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnFull, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(btnSetDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel1))
                                        .addComponent(btnDoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnVIP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new java.awt.GridLayout(0, 4));
        jScrollPane2.setViewportView(jPanel8);

        btnDP.setBackground(MyColor.button);
        btnDP.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnDP.setText("Đặt phòng");
        btnDP.setActionCommand("Đạt phòng");
        btnDP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDPActionPerformed(evt);
            }
        });

        btnNC.setBackground(MyColor.button);
        btnNC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnNC.setText("Nâng cao");
        btnNC.setActionCommand("");
        btnNC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNCActionPerformed(evt);
            }
        });

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poster/poster.png"))); // NOI18N
        jScrollPane4.setViewportView(jLabel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1056, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnNC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnDP, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                                .addContainerGap())
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1230, Short.MAX_VALUE)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btnDP, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnNC, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(299, Short.MAX_VALUE))
                                        .addComponent(jScrollPane2)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSetDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetDateActionPerformed
        // TODO add your handling code here:

        listAllPhong = PDAO.queryAllPhong();
        listPhongFull = PDAO.queryAllPhongFullByStEd(dateTuNgay.getDate(), dateDenNgay.getDate());
        valuebtn = 0;
        dataChange();
    }//GEN-LAST:event_btnSetDateActionPerformed

    public Thread threadXPD;

    public Thread threadDP;

    private void btnDPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDPActionPerformed
        // TODO add your handling code here:

        DatPhong child = new DatPhong();
        child.setVisible(true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                {
                    synchronized (threadDP) {
                        // Pause
                        try { //code sau khi mở lại luồng chính
                            threadDP.wait();
                            notifyDateChange();
                        } catch (InterruptedException e) {
                        }
                    }

                }
            }


        };

        threadDP = new Thread(runnable);
        child.setAdd(threadDP, home.getNhanVien());

        //từ đây trở lên là trước khi luồng chính bị đóng
        threadDP.start();
        child.setVisible(true);

        // new CHILD().setVisible(true);

    }//GEN-LAST:event_btnDPActionPerformed

    private void btnNCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNCActionPerformed
        //
        DanhSachPhongFrame child = new DanhSachPhongFrame();
        child.setVisible(true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                {
                    synchronized (threadGui) {
                        // Pause
                        try { //code sau khi mở lại luồng chính
                            threadGui.wait();

                            reset();
                        } catch (InterruptedException e) {
                        }
                    }

                }
            }


        };

        threadGui = new Thread(runnable);
        child.setThread(threadGui);

        //từ đây trở lên là trước khi luồng chính bị đóng
        threadGui.start();
    }//GEN-LAST:event_btnNCActionPerformed

    private Integer valuebtn = 0;

    private void btnFullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFullActionPerformed
        // TODO add your handling code here:
        if (valuebtn != 1) {
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
        if (valuebtn != 2) {
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
        if (valuebtn != 3) {
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
        if (valuebtn != 4) {
            listAllPhong = listPhongVIP;
            valuebtn = 4;
        } else {
            valuebtn = 0;
        }
        reset();
        listAllPhong = PDAO.queryAllPhong();
    }//GEN-LAST:event_btnVIPActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        // TODO add your handling code here:
        isSelected = 4;
        jCheckBox1.setSelected(false);
        jCheckBox2.setSelected(false);
        jCheckBox3.setSelected(false);
        jCheckBox4.setSelected(true);
        MyScrollPanel.scroll(jScrollPane4, 0.0, 1.0 * (isSelected - 1) / 4);
    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        // TODO add your handling code here:
        isSelected = 3;
        jCheckBox1.setSelected(false);
        jCheckBox2.setSelected(false);
        jCheckBox3.setSelected(true);
        jCheckBox4.setSelected(false);
        MyScrollPanel.scroll(jScrollPane4, 0.0, 1.0 * (isSelected - 1) / 4);
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
        isSelected = 2;
        jCheckBox1.setSelected(false);
        jCheckBox2.setSelected(true);
        jCheckBox3.setSelected(false);
        jCheckBox4.setSelected(false);
        MyScrollPanel.scroll(jScrollPane4, 0.0, 1.0 * (isSelected - 1) / 4);
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        isSelected = 1;
        jCheckBox1.setSelected(true);
        jCheckBox2.setSelected(false);
        jCheckBox3.setSelected(false);
        jCheckBox4.setSelected(false);
        MyScrollPanel.scroll(jScrollPane4, 0.0, 1.0 * (isSelected - 1) / 4);
    }//GEN-LAST:event_jCheckBox1ActionPerformed


    public JPanel getPanel() {
        return jPanel1;
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TongQuan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDP;
    private javax.swing.JButton btnDoi;
    private javax.swing.JButton btnDon;
    private javax.swing.JButton btnFull;
    private javax.swing.JButton btnNC;
    private javax.swing.JButton btnSetDate;
    private javax.swing.JButton btnVIP;
    private com.toedter.calendar.JDateChooser dateDenNgay;
    private com.toedter.calendar.JDateChooser dateTuNgay;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelDN;
    private javax.swing.JLabel jLabelTN;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
}
