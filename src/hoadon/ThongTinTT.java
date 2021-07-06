/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoadon;

import Util.MyColor;
import Util.MyConvert;
import Util.MyPrinter;
import dichvu.DichVuDAO;
import dichvu.ThueDichVu;
import khachhang.KhachHang;
import khachhang.KhachHangDAO;
import khuyenmai.KhuyenMai;
import khuyenmai.KhuyenMaiDAO;
import khuyenmai.KhuyenMaiFrame;
import phong.DanhSachPhongDAO;
import phong.PhongDAO;
import phong.ThuePhong;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * @author khanh
 */
public class ThongTinTT extends javax.swing.JFrame {

    /**
     * Creates new form ThongTinTT
     */
    private Thread threadNhan;
    private Color colorPre = new Color(255, 255, 255);
    private JButton buttonTPIsSelected = new JButton();
    private JButton buttonTDVIsSelected = new JButton();
    private PhongDAO PDAO = new PhongDAO();
    private DichVuDAO DVDAO = new DichVuDAO();
    private Thread threadGui;
    private HoaDon hoadon;
    private ArrayList<ThuePhong> listTPh;
    private ArrayList<ThuePhong> listTPIsSelected = new ArrayList<>();
    private ArrayList<ThueDichVu> listTDVIsSelected = new ArrayList<>();
    private ArrayList<ThueDichVu> listDv;
    private KhuyenMai khuyenmai;
    private KhuyenMaiDAO KMDAO = new KhuyenMaiDAO();
    private HoaDonDAO HDDAO = new HoaDonDAO();
    private KhachHang khachhang = new KhachHang();


    private Image image = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/drawable/background/background.png"));

    public ThongTinTT() {
        setIconImage(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/drawable/logo.png")));
        jPanelTTTT = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(image, 0, 0, null);
                super.paintComponent(g);


            }
        };

        initComponents();


    }

    @Override
    public void dispose() {
        synchronized (threadNhan) {
            threadNhan.notify();
        }
        super.dispose();
    }

    private void resetP() {
        jPanelP.removeAll();
        jPanelP.repaint();
        listTPIsSelected.removeAll(listTPIsSelected);
        paintp();

    }

    private void resetDV() {

        jPanelDV.removeAll();
        jPanelDV.repaint();
        listTDVIsSelected.removeAll(listTPIsSelected);
        paintdv();
    }

    private void paintKM() {
        if (khuyenmai != null) btnKM.setText("Khuyến mãi: " + khuyenmai.getMAKM());
    }


    private void paintp() {
        txtSP.setText(MyConvert.parseIntToString(listTPh.size()));
        for (ThuePhong p : listTPh) {
            JButton btnTemp = new javax.swing.JButton();
            btnTemp.setBackground(new java.awt.Color(255, 245, 245));
            btnTemp.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
            btnTemp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable/thuephong.png"))); // NOI18N
            btnTemp.setText("MAPH: " + p.getMAPH() + "|Số người thuê: " + p.getSONGUOITHUE() + "  | Đơn giá: " + (new DanhSachPhongDAO().queryDSPbyTP(p)).getDONGIA() + "   | Số ngày thuê:  " + PDAO.querySoNgayThueByTP(p) + "  | Tiền phòng: " + p.getTIEN() + "  Phụ thu:  " + p.getPHUTHU());
            btnTemp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            btnTemp.setIconTextGap(40);
            btnTemp.setMaximumSize(new java.awt.Dimension(115, 60));
            btnTemp.setMinimumSize(new java.awt.Dimension(115, 60));
            btnTemp.setPreferredSize(new java.awt.Dimension(115, 60));

            btnTemp.addActionListener(e -> {
                buttonTPIsSelected.setBackground(colorPre);
                colorPre = new Color(255, 245, 245);
                String[] words = btnTemp.getText().split("\\s");
                String StrTemp = words[1];

                buttonTPIsSelected = btnTemp;
                buttonTPIsSelected.setBackground(new java.awt.Color(0, 204, 255));
            });
            jPanelP.add(btnTemp);
        }
        if (listTPh.size() < 4)
            for (int i = 0; i < 4 - listTPh.size(); i++) {
                Button btnTemp = new Button();
                btnTemp.setBackground(Color.WHITE);
                btnTemp.setEnabled(false);
                jPanelP.add(btnTemp);
            }
        jPanelP.revalidate();
    }

    private void paintdv() {
        txtSDV.setText(MyConvert.parseIntToString(listDv.size()));
        for (ThueDichVu p : listDv) {
            JButton btnTemp = new javax.swing.JButton();
            btnTemp.setBackground(new java.awt.Color(255, 245, 245));
            btnTemp.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
            btnTemp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable/dichvu.png"))); // NOI18N
            String space = "                              ";
            btnTemp.setText("MADV: " + p.getMADV() + "| Tên dịch vụ: " + DVDAO.queryDVByTDV(p).getTENDV() +space.substring(DVDAO.queryDVByTDV(p).getTENDV().length())+ "  | Đơn giá: " + DVDAO.queryDVByTDV(p).getGIADV() + "   | Số ngày thuê:  " + DVDAO.soNgayThueDVbyTDV(p) + "  | Tiền dịch vụ: " + p.getTIEN());
            btnTemp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            btnTemp.setIconTextGap(40);
            btnTemp.setMaximumSize(new java.awt.Dimension(115, 60));
            btnTemp.setMinimumSize(new java.awt.Dimension(115, 60));
            btnTemp.setPreferredSize(new java.awt.Dimension(115, 60));

            btnTemp.addActionListener(e -> {
                buttonTDVIsSelected.setBackground(colorPre);
                colorPre = new Color(255, 245, 245);
                String[] words = btnTemp.getText().split("\\s");
                String StrTemp = words[1];

                buttonTDVIsSelected = btnTemp;
                buttonTDVIsSelected.setBackground(new java.awt.Color(0, 204, 255));
            });
            jPanelDV.add(btnTemp);
        }
        if (listDv.size() < 4)
            for (int i = 0; i < 4 - listDv.size(); i++) {
                Button btnTemp = new Button();
                btnTemp.setBackground(Color.WHITE);
                btnTemp.setEnabled(false);
                jPanelDV.add(btnTemp);
            }
        jPanelDV.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelTTTT = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabelAVT = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtSHD = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMAKH = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTKH = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtSP = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtSDV = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanelP = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanelDV = new javax.swing.JPanel();
        btnKM = new javax.swing.JButton();
        btnTT = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbTP = new javax.swing.JLabel();
        lnDV = new javax.swing.JLabel();
        lbTT = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lbTT2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanelTTTT.setBackground(new java.awt.Color(255, 255, 255));
        jPanelTTTT.setPreferredSize(new java.awt.Dimension(1280, 720));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1483, 100));

        jLabelAVT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/taikhoan/avatar/NV.png"))); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poster/Thongtinhoadon.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelAVT)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabelAVT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("SOHD:");

        txtSHD.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("MAKH:");

        txtMAKH.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("Tên khách hàng:");

        txtTKH.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText("Số phòng đặt:");

        txtSP.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setText("Số dịch vụ");

        txtSDV.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSHD, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMAKH, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTKH, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSP, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSDV, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtSHD, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtMAKH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTKH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtSDV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanelP.setBackground(new java.awt.Color(255, 255, 255));
        jPanelP.setLayout(new java.awt.GridLayout(0, 1, 2, 3));
        jScrollPane2.setViewportView(jPanelP);

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanelDV.setBackground(new java.awt.Color(255, 255, 255));
        jPanelDV.setLayout(new java.awt.GridLayout(0, 1, 2, 3));
        jScrollPane3.setViewportView(jPanelDV);

        btnKM.setBackground(new java.awt.Color(255, 153, 0));
        btnKM.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnKM.setText("Khuyến mãi:");
        btnKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKMActionPerformed(evt);
            }
        });

        btnTT.setBackground(new java.awt.Color(255, 204, 204));
        btnTT.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnTT.setText("Hoàn tất");
        btnTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTTActionPerformed(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel10.setText("Tổng tiền phòng và dịch vụ:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel11.setText("Tiền phòng");

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel13.setText("Tiền dịch vụ");

        lbTP.setBackground(new java.awt.Color(255, 255, 255));
        lbTP.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        lnDV.setBackground(new java.awt.Color(255, 255, 255));
        lnDV.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        lbTT.setBackground(new java.awt.Color(255, 255, 255));
        lbTT.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel15.setText("Thành tiền ");

        lbTT2.setBackground(new java.awt.Color(255, 255, 255));
        lbTT2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanelTTTTLayout = new javax.swing.GroupLayout(jPanelTTTT);
        jPanelTTTT.setLayout(jPanelTTTTLayout);
        jPanelTTTTLayout.setHorizontalGroup(
                jPanelTTTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelTTTTLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanelTTTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
                                        .addComponent(jScrollPane3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelTTTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelTTTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(btnKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnTT, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel10)
                                                .addComponent(jLabel13)
                                                .addComponent(jLabel11)
                                                .addComponent(lbTP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lnDV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(lbTT, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel15)
                                        .addComponent(lbTT2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1280, Short.MAX_VALUE)
        );
        jPanelTTTTLayout.setVerticalGroup(
                jPanelTTTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelTTTTLayout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelTTTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelTTTTLayout.createSequentialGroup()
                                                .addComponent(btnKM, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(84, 84, 84)
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbTP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lnDV, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbTT, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbTT2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnTT, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(41, 41, 41))
                                        .addGroup(jPanelTTTTLayout.createSequentialGroup()
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanelTTTT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanelTTTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTTActionPerformed
        // TODO add your handling code here:
        Object[] options = {"Có", "Không"};
        int result = JOptionPane.showOptionDialog(this,
                "Bạn chắc chắn muốn thanh toán ?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (result == JOptionPane.YES_OPTION) {

            String mess = HDDAO.thanhToan(hoadon);
            Object[] options2 = {"Chấp nhận"};
            int result2 = JOptionPane.showOptionDialog(this,
                    mess,
                    "Thông báo",
                    JOptionPane.OK_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options2,
                    options2[0]);
            if (result2 == JOptionPane.OK_OPTION) {
                if (mess.equals("Thành công")) dispose();
            }
        }
    }//GEN-LAST:event_btnTTActionPerformed

    private void btnKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKMActionPerformed
        // TODO add your handling code here:
        Object[] options = {"Chọn lại", "Xoá","Thoát"};
        if (khuyenmai != null) {
            int result = JOptionPane.showOptionDialog(this,
                    "Bạn có muốn chọn lại hay xoá chọn khuyến mãi này?",
                    "Chi tiết",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options, options[2]);
            if (result==JOptionPane.CANCEL_OPTION) return;

            if (result == JOptionPane.NO_OPTION) {
                Object[] options2 = {"Xoá", "Không"};
                int result2 = JOptionPane.showOptionDialog(this,
                        "Bạn có chắc chắn muốn xoá khuyến mãi này?",
                        "Chi tiết",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options2,
                        options2[0]);
                if (result2 == JOptionPane.YES_OPTION) {

                    HDDAO.deleteKM(hoadon);
                    khuyenmai = null;
                    btnKM.setText("");
                    return;
                }
            }
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
                            if (khuyenmai != null) {
                                btnKM.setText(khuyenmai.getMAKM());
                                lbTT2.setText(MyConvert.parseFloatToString((MyConvert.parseStringToInt(lbTP.getText()) + MyConvert.parseStringToInt(lnDV.getText())) * (1 - khuyenmai.getTILE())));
                                KMDAO.insertKMintoHD(hoadon, khuyenmai);
                                paintKM();
                            }
                        } catch (InterruptedException e) {
                        }
                    }

                }
            }

        };

        threadGui = new Thread(runnable);
        Date startDate = new Date(listTPh
                .stream()
                .mapToLong(v -> v.getNGBD().getTime())
                .min().orElseThrow(NoSuchElementException::new));

        Date endDate = new Date(listTPh
                .stream()
                .mapToLong(v -> v.getNGKT().getTime())
                .max().orElseThrow(NoSuchElementException::new));
        child.setThreadNhan(threadGui, khachhang, startDate, endDate);

        //từ đây trở lên là trước khi luồng chính bị đóng
        threadGui.start();
    }//GEN-LAST:event_btnKMActionPerformed


    public void setTheadTTTT(Thread th, HoaDon hd) {
        threadNhan = th;
        hoadon = hd;
        if (hoadon.getNGAYHD()!=null)
        {
            btnKM.setEnabled(false);
            btnTT.setVisible(false);
        }
        listTPh = PDAO.queryTPBySOHD(hoadon);
        listDv = DVDAO.queryTDVBySOHD(hoadon);
        Date date = new Date(System.currentTimeMillis());

        khuyenmai = KMDAO.queryByHD(hoadon);
        txtSHD.setText(hd.getSOHD());
        khachhang = new KhachHangDAO().queryKHbyHD(hoadon);

        lbTP.setText(MyConvert.parseObjToString(PDAO.queryTongTienTPByHD(hoadon)));
        lnDV.setText(MyConvert.parseObjToString(DVDAO.queryTongTienDVByHD(hoadon)));
        lbTT.setText(MyConvert.parseIntToString(MyConvert.parseStringToInt(lbTP.getText()) + MyConvert.parseStringToInt(lnDV.getText())));
        txtTKH.setText((new KhachHangDAO().queryKHbyHD(hd)).getTENKH());

        if (khuyenmai == null) lbTT2.setText(lbTT.getText());
        else
            lbTT2.setText(MyConvert.parseFloatToString((MyConvert.parseStringToInt(lbTP.getText()) + MyConvert.parseStringToInt(lnDV.getText())) * (1 - khuyenmai.getTILE())));
        resetP();
        resetDV();
        paintKM();
    }

    public static void main(String[] args) {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKM;
    private javax.swing.JButton btnTT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAVT;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelDV;
    private javax.swing.JPanel jPanelP;
    private javax.swing.JPanel jPanelTTTT;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbTP;
    private javax.swing.JLabel lbTT;
    private javax.swing.JLabel lbTT2;
    private javax.swing.JLabel lnDV;
    private javax.swing.JLabel txtMAKH;
    private javax.swing.JLabel txtSDV;
    private javax.swing.JLabel txtSHD;
    private javax.swing.JLabel txtSP;
    private javax.swing.JLabel txtTKH;
    // End of variables declaration//GEN-END:variables
}
