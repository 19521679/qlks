/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoadon;

import Util.MyColor;

import java.awt.*;
import java.util.ArrayList;


import Util.MyPrinter;
import com.toedter.calendar.JDateChooser;
import Util.MyConvert;
import datphong.DatPhong;
import dichvu.DichVu;
import dichvu.DichVuDAO;
import homepage.TongQuan;
import homepage.home;
import khachhang.KhachHang;
import khachhang.KhachHangDAO;
import phong.PhongDAO;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author khanh
 */
/*SOHD                 INTEGER              NOT NULL,
   MAPHIEUTP            INTEGER              NOT NULL,
   MAPHIEUTDV           INTEGER,
   MANV                 INTEGER              NOT NULL,
   MAKM                 INTEGER,
   MAKH                 INTEGER              NOT NULL,
   NGAYHD               DATE,
   THANHTIEN            NUMBER(8,2),
   CONSTRAINT PK_HOADON PRIMARY KEY (SOHD)*/
public class HoaDonFrame extends javax.swing.JFrame {

    /**
     * Creates new form HoaDonFrame
     */
    private Thread threadGui;
    private HoaDonDAO HDDAO = new HoaDonDAO();
    private JButton buttonIsSelected = new JButton();
    private ArrayList<HoaDon> listIsSelected = new ArrayList<>();
    private ArrayList<HoaDon> listhd = new ArrayList<>();
    private ArrayList<HoaDon> listThanhToan = new ArrayList<>();
    private ArrayList<HoaDon> listChuaThanhToan = new ArrayList<>();
    private Color colorPre = MyColor.nenhoadon;
    private TongQuan tongquanframe;
    // private ArrayList<HoaDon> listKHMember =new ArrayList<>();
    // private ArrayList<HoaDon> listKHNormal =new ArrayList<>();
    // private Color colorPre=new Color(255,255,255);

    /**
     * Creates new form HoaDonFrame
     */
    public HoaDonFrame() {
        jPanelHD = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);

            }
        };
        initComponents();
        btnTT.setEnabled(false);
        dateTN.setDateFormatString("dd-MM-yyyy");
        dateTN.setDate(new Date(System.currentTimeMillis()));
        dateDN.setDateFormatString("dd-MM-yyyy");
        dateDN.setDate(new Date(System.currentTimeMillis()));

        dataChange();
    }

    public JPanel getPanel() {
        return jPanelHD;
    }

    public void setTQFtoHDF(TongQuan frame) {
        tongquanframe = frame;
    }

    public void dataChange() {

        listhd = HDDAO.queryHDByNgay(dateTN.getDate(), dateDN.getDate());
        System.out.println("hoadon:"+listhd);
        listThanhToan = HDDAO.queryHDDTTByNgay(dateTN.getDate(), dateDN.getDate());
        listChuaThanhToan = HDDAO.queryHDCTTByNgay(dateTN.getDate(), dateDN.getDate());
        reset();
    }

    private void notifyDateChange() {
        tongquanframe.dataChange();
        dataChange();
    }

    private void reset() {
        listIsSelected.remove(listIsSelected);
        jPanel8.removeAll();
        jPanel8.repaint();
        painthd();

    }

    private Image image = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/drawable/background/background.png"));


    private void painthd() {

        for (HoaDon k : listhd) {

            Image image1 = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/drawable/background/background.png"));

            ImageIcon imgTemp = new javax.swing.ImageIcon(getClass().getResource("/drawable/hoadon (2).png"));
            Color colorTemp;

            JButton btnTemp = new javax.swing.JButton();

            btnTemp.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
            java.util.List<HoaDon> match = listThanhToan.stream().filter(it -> it.getSOHD().contains(k.getSOHD())).collect(Collectors.toList());
            if (!match.isEmpty()) {
                colorTemp = MyColor.dathanhtoan;
            } else colorTemp = MyColor.chuathanhtoan;
            btnTemp.setBackground(colorTemp);
            btnTemp.setIcon(imgTemp); // NOI18N
            String space = "                              ";
            String noidung = k.getSOHD() + "    |  " + k.getMAKH() + "           |  " + new KhachHangDAO().queryKHbyHD(k).getTENKH() + space.substring(new KhachHangDAO().queryKHbyHD(k).getTENKH().length()) +
                    "               " + MyConvert.parseIntToString(new PhongDAO().queryTPBySOHD(k).size()) +
                    "                |                  " + MyConvert.parseIntToString(new DichVuDAO().queryTDVBySOHD(k).size()) +
                    "       ";

            btnTemp.setText(noidung);
            btnTemp.setHorizontalAlignment(SwingConstants.LEFT);
            btnTemp.setHorizontalTextPosition(SwingConstants.RIGHT);
            btnTemp.setIconTextGap(40);
            btnTemp.setMaximumSize(new java.awt.Dimension(115, 60));
            btnTemp.setMinimumSize(new java.awt.Dimension(115, 60));
            btnTemp.setPreferredSize(new java.awt.Dimension(115, 60));
            btnTemp.addActionListener(e -> {
                buttonIsSelected.setBackground(colorPre);
                colorPre = btnTemp.getBackground();
                String[] words = btnTemp.getText().split("\\s");
                String StrTemp = words[0];
                listIsSelected.removeAll(listIsSelected);

                List<HoaDon> matches = listhd.stream().filter(it -> it.getSOHD().toString().contains(StrTemp)).collect(Collectors.toList());

                listIsSelected.add(matches.get(0));
                List<HoaDon> matches1 = listChuaThanhToan.stream().filter(it -> it.getSOHD().toString().contains(matches.get(0).getSOHD())).collect(Collectors.toList());
                if (!matches1.isEmpty()) btnTT.setEnabled(true);
                else btnTT.setEnabled(false);
                buttonIsSelected = btnTemp;
                buttonIsSelected.setBackground(new java.awt.Color(0, 204, 255));
            });
            jPanel8.add(btnTemp);
        }
        if (listhd.size() < 8)
            for (int i = 0; i < 8 - listhd.size(); i++) {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabelAVT = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelDN = new javax.swing.JLabel();
        jLabelTN = new javax.swing.JLabel();
        dateTN = new com.toedter.calendar.JDateChooser();
        dateDN = new com.toedter.calendar.JDateChooser();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnCN = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnDP = new javax.swing.JButton();
        btnX = new javax.swing.JButton();
        btnCNC = new javax.swing.JButton();
        btnTT = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel8 = new javax.swing.JPanel();
        btnIHD = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanelHD.setBackground(new java.awt.Color(255, 255, 255));
        jPanelHD.setPreferredSize(new java.awt.Dimension(1230, 720));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poster/Hoadon.png"))); // NOI18N

        jLabelAVT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/taikhoan/avatar/NV.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelAVT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelAVT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1230, 56));
        jPanel2.setRequestFocusEnabled(false);

        jLabelDN.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabelDN.setText("Đến ngày");

        jLabelTN.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabelTN.setText("Từ ngày");

        dateTN.setDateFormatString("yyyy-MM-dd");
        dateTN.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        dateDN.setDateFormatString("yyyy-MM-dd");
        dateDN.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jPanel5.setBackground(MyColor.dathanhtoan);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("Đã thanh toán");

        jPanel7.setBackground(MyColor.chuathanhtoan);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 31, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Chưa thanh toán");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(jLabelTN, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dateTN, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelDN, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateDN, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dateDN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                            .addComponent(jLabelDN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelTN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateTN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel3)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(51, 204, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("SOHD");

        btnCN.setBackground(MyColor.button);
        btnCN.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnCN.setText("Chọn ngày");
        btnCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCNActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Mã khách hàng");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Tên khách hàng");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Số dịch vụ đã đặt");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Số phòng đã đặt");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Số ngày");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(btnCN, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnDP.setBackground(MyColor.button);
        btnDP.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnDP.setText("Đặt phòng");
        btnDP.setActionCommand("Đạt phòng");
        btnDP.setMaximumSize(new java.awt.Dimension(120, 30));
        btnDP.setMinimumSize(new java.awt.Dimension(120, 30));
        btnDP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDPActionPerformed(evt);
            }
        });

        btnX.setBackground(MyColor.button);
        btnX.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnX.setText("Xoá");
        btnX.setActionCommand("");
        btnX.setMaximumSize(new java.awt.Dimension(120, 30));
        btnX.setMinimumSize(new java.awt.Dimension(120, 30));
        btnX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXActionPerformed(evt);
            }
        });

        btnCNC.setBackground(MyColor.button);
        btnCNC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCNC.setText("Chọn nâng cao");
        btnCNC.setMaximumSize(new java.awt.Dimension(120, 30));
        btnCNC.setMinimumSize(new java.awt.Dimension(120, 30));
        btnCNC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCNCActionPerformed(evt);
            }
        });

        btnTT.setBackground(new java.awt.Color(255, 204, 204));
        btnTT.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnTT.setText("Thanh toán");
        btnTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTTActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new java.awt.GridLayout(0, 1, 2, 3));
        jScrollPane1.setViewportView(jPanel8);

        btnIHD.setBackground(MyColor.button);
        btnIHD.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnIHD.setText("In hoá đơn");
        btnIHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelHDLayout = new javax.swing.GroupLayout(jPanelHD);
        jPanelHD.setLayout(jPanelHDLayout);
        jPanelHDLayout.setHorizontalGroup(
            jPanelHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHDLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1056, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCNC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnX, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnIHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanelHDLayout.createSequentialGroup()
                .addGroup(jPanelHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelHDLayout.setVerticalGroup(
            jPanelHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHDLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelHDLayout.createSequentialGroup()
                        .addComponent(btnDP, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnX, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCNC, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(141, 141, 141)
                        .addComponent(btnIHD, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTT, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(142, 142, 142))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCNActionPerformed
        // TODO add your handling code here:
        listhd = HDDAO.queryHDByNgay(dateTN.getDate(), dateDN.getDate());
        listThanhToan = HDDAO.queryHDDTTByNgay(dateTN.getDate(), dateDN.getDate());
        listChuaThanhToan = HDDAO.queryHDCTTByNgay(dateTN.getDate(), dateDN.getDate());
        notifyDateChange();
    }//GEN-LAST:event_btnCNActionPerformed

    private void btnDPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDPActionPerformed
        // TODO add your handling code here:
        DatPhong child = new DatPhong();
        child.setVisible(true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                {
                    synchronized (threadGui) {
                        // Pause
                        try { //code sau khi mở lại luồng chính
                            threadGui.wait();

                            notifyDateChange();
                        } catch (InterruptedException e) {
                        }
                    }

                }
            }

        };

        threadGui = new Thread(runnable);
        child.setAdd(threadGui, home.getNhanVien());

        //từ đây trở lên là trước khi luồng chính bị đóng
        threadGui.start();

        // new CHILD().setVisible(true);
    }//GEN-LAST:event_btnDPActionPerformed

    private void btnXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXActionPerformed
        // TODO add your handling code here:
        if (listIsSelected.isEmpty())

            JOptionPane.showMessageDialog(null, "Bạn chưa chọn hoá đơn nào", "Thông tin", JOptionPane.INFORMATION_MESSAGE);
        else {
            Object[] options = {"Có", "Không"};
            int result = JOptionPane.showOptionDialog(this,
                    "Bạn có chắc muốn xoá hoá đơn này",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);

            if (result == JOptionPane.YES_OPTION) {
                HDDAO.deleteDatabase(listIsSelected.get(0));
                listIsSelected.removeAll(listIsSelected);
                notifyDateChange();
            }
        }
    }//GEN-LAST:event_btnXActionPerformed

    private void btnCNCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCNCActionPerformed
        // TODO add your handling code here:
        if (listIsSelected.isEmpty())

            JOptionPane.showMessageDialog(null, "Bạn chưa chọn hoá đơn nào", "Thông tin", JOptionPane.INFORMATION_MESSAGE);
        else {
            ThongTinHD child = new ThongTinHD();
            child.setVisible(true);
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    {
                        synchronized (threadGui) {
                            // Pause
                            try { //code sau khi mở lại luồng chính
                                threadGui.wait();

                                notifyDateChange();
                            } catch (InterruptedException e) {
                            }
                        }

                    }
                }

            };

            threadGui = new Thread(runnable);

            child.setTheadTTHD(threadGui, listIsSelected.get(0));

            //từ đây trở lên là trước khi luồng chính bị đóng
            threadGui.start();
        }
    }//GEN-LAST:event_btnCNCActionPerformed

    private void btnTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTTActionPerformed
        // TODO add your handling code here:
        if (listIsSelected.isEmpty())

            JOptionPane.showMessageDialog(null, "Bạn chưa chọn hoá đơn nào", "Thông tin", JOptionPane.INFORMATION_MESSAGE);
        else {
            ThongTinTT child = new ThongTinTT();
            child.setVisible(true);
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    {
                        synchronized (threadGui) {
                            // Pause
                            try { //code sau khi mở lại luồng chính
                                threadGui.wait();

                                notifyDateChange();
                            } catch (InterruptedException e) {
                            }
                        }

                    }
                }

            };

            threadGui = new Thread(runnable);

            child.setTheadTTTT(threadGui, listIsSelected.get(0));

            //từ đây trở lên là trước khi luồng chính bị đóng
            threadGui.start();
        }
    }//GEN-LAST:event_btnTTActionPerformed

    private void btnIHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIHDActionPerformed
        // TODO add your handling code here:

        //
        if (listIsSelected.isEmpty()) {

            JOptionPane.showMessageDialog(null, "Bạn chưa chọn hoá đơn nào", "Thông tin", JOptionPane.INFORMATION_MESSAGE);
        } else {
            new MyPrinter().printMyContent(listIsSelected.get(0));
        }
    }//GEN-LAST:event_btnIHDActionPerformed


    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HoaDonFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCN;
    private javax.swing.JButton btnCNC;
    private javax.swing.JButton btnDP;
    private javax.swing.JButton btnIHD;
    private javax.swing.JButton btnTT;
    private javax.swing.JButton btnX;
    private com.toedter.calendar.JDateChooser dateDN;
    private com.toedter.calendar.JDateChooser dateTN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAVT;
    private javax.swing.JLabel jLabelDN;
    private javax.swing.JLabel jLabelTN;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelHD;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
