package hoadon;

import Util.MyConvert;
import database.Database;
import dichvu.DichVu;
import phong.Phong;
import phong.ThuePhong;

import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/*SOHD                 INTEGER              NOT NULL,
   MAPHIEUTP            INTEGER              NOT NULL,
   MAPHIEUTDV           INTEGER,
   MANV                 INTEGER              NOT NULL,
   MAKM                 INTEGER,
   MAKH                 INTEGER              NOT NULL,
   NGAYHD               DATE,
   THANHTIEN            NUMBER(8,2),
   CONSTRAINT PK_HOADON PRIMARY KEY (SOHD)*/
public class HoaDonDAO {

    Connection connection = null;

    public ArrayList<HoaDon> queryAllHoaDon() {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sqlQuery = "SELECT * from HOADON Order by SOHD";
        try {
            PreparedStatement preparedStatementShow = this.connection.prepareStatement(sqlQuery);

            ResultSet rs = preparedStatementShow.executeQuery();

            while (rs.next()) {

                String sohd = rs.getString("SOHD");
                String maphieutp = rs.getString("MAPHIEUTP");
                String maphieutdv = rs.getString("MAPHIEUTDV");
                String manv = rs.getString("MANV");
                String makm = rs.getString("MAKM");
                String makh = rs.getString("MAKH");
                Date ngayhd = rs.getDate("NGAYHD");
                Integer thanhtien = rs.getInt("THANHTIEN");
                HoaDon hd = new HoaDon(sohd, maphieutp, maphieutdv, manv, makm, makh, ngayhd, thanhtien);
                list.add(hd);

            }
        } catch (SQLException e) {
        }
        return list;
    }


    public ArrayList<HoaDon> queryHDByNgay(java.util.Date startDate, java.util.Date endDate) {
        java.sql.Date date1 = new java.sql.Date(startDate.getTime());
        java.sql.Date date2 = new java.sql.Date(endDate.getTime());


        ArrayList<HoaDon> list = new ArrayList<>();
        String sqlQuery = "SELECT DISTINCT * FROM HOADON WHERE SOHD IN (" +
                " SELECT SOHD from HOADON JOIN THUE_PHONG ON HOADON.MAPHIEUTP=THUE_PHONG.MAPHIEUTP" +
                " WHERE TO_DATE(NGBD,'dd/MM/yyyy') <= TO_DATE(?,'dd/MM/yyyy') " +
                " AND  TO_DATE(NGKT,'dd/MM/yyyy') >= TO_DATE(?,'dd/MM/yyyy') )" +
                " Order by SOHD";
        try {
            PreparedStatement preparedStatementShow = this.connection.prepareStatement(sqlQuery);
            preparedStatementShow.setDate(1, date2);
            preparedStatementShow.setDate(2, date1);

            ResultSet rs = preparedStatementShow.executeQuery();

            while (rs.next()) {
                String sohd = rs.getString("SOHD");

                String maphieutp = rs.getString("MAPHIEUTP");
                String maphieutdv = rs.getString("MAPHIEUTDV");
                String manv = rs.getString("MANV");
                String makm = rs.getString("MAKM");
                String makh = rs.getString("MAKH");
                Date ngayhd = rs.getDate("NGAYHD");
                Integer thanhtien = rs.getInt("THANHTIEN");

                list.add(new HoaDon(sohd, maphieutp, maphieutdv, manv, makm, makh, ngayhd, thanhtien));
            }
        } catch (SQLException e) {
        }

        return list;
    }

    public ArrayList<HoaDon> queryHDDTTByNgay(java.util.Date startDate, java.util.Date endDate) {
        java.sql.Date date1 = new java.sql.Date(startDate.getTime());
        java.sql.Date date2 = new java.sql.Date(endDate.getTime());


        ArrayList<HoaDon> list = new ArrayList<>();
        String sqlQuery = "SELECT DISTINCT * FROM HOADON WHERE SOHD IN (" +
                " SELECT SOHD from HOADON JOIN THUE_PHONG ON HOADON.MAPHIEUTP=THUE_PHONG.MAPHIEUTP" +
                " WHERE NGAYHD IS NOT NULL AND TO_DATE(NGBD,'dd/MM/yyyy') <= TO_DATE(?,'dd/MM/yyyy') " +
                " AND  TO_DATE(NGKT,'dd/MM/yyyy') >= TO_DATE(?,'dd/MM/yyyy') )" +
                " Order by SOHD";
        try {
            PreparedStatement preparedStatementShow = this.connection.prepareStatement(sqlQuery);
            preparedStatementShow.setDate(1, date2);
            preparedStatementShow.setDate(2, date1);

            ResultSet rs = preparedStatementShow.executeQuery();

            while (rs.next()) {
                String sohd = rs.getString("SOHD");

                String maphieutp = rs.getString("MAPHIEUTP");
                String maphieutdv = rs.getString("MAPHIEUTDV");
                String manv = rs.getString("MANV");
                String makm = rs.getString("MAKM");
                String makh = rs.getString("MAKH");
                Date ngayhd = rs.getDate("NGAYHD");
                Integer thanhtien = rs.getInt("THANHTIEN");

                list.add(new HoaDon(sohd, maphieutp, maphieutdv, manv, makm, makh, ngayhd, thanhtien));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public ArrayList<HoaDon> queryHDCTTByNgay(java.util.Date startDate, java.util.Date endDate) {
        java.sql.Date date1 = new java.sql.Date(startDate.getTime());
        java.sql.Date date2 = new java.sql.Date(endDate.getTime());


        ArrayList<HoaDon> list = new ArrayList<>();
        String sqlQuery = "SELECT DISTINCT * FROM HOADON WHERE SOHD IN (" +
                " SELECT SOHD from HOADON JOIN THUE_PHONG ON HOADON.MAPHIEUTP=THUE_PHONG.MAPHIEUTP" +
                " WHERE NGAYHD IS NULL AND TO_DATE(NGBD,'dd/MM/yyyy') <= TO_DATE(?,'dd/MM/yyyy') " +
                " AND  TO_DATE(NGKT,'dd/MM/yyyy') >= TO_DATE(?,'dd/MM/yyyy') )" +
                " Order by SOHD";
        try {
            PreparedStatement preparedStatementShow = this.connection.prepareStatement(sqlQuery);
            preparedStatementShow.setDate(1, date2);
            preparedStatementShow.setDate(2, date1);

            ResultSet rs = preparedStatementShow.executeQuery();

            while (rs.next()) {
                String sohd = rs.getString("SOHD");

                String maphieutp = rs.getString("MAPHIEUTP");
                String maphieutdv = rs.getString("MAPHIEUTDV");
                String manv = rs.getString("MANV");
                String makm = rs.getString("MAKM");
                String makh = rs.getString("MAKH");
                Date ngayhd = rs.getDate("NGAYHD");
                Integer thanhtien = rs.getInt("THANHTIEN");

                list.add(new HoaDon(sohd, maphieutp, maphieutdv, manv, makm, makh, ngayhd, thanhtien));
            }
        } catch (SQLException e) {
        }

        return list;
    }

    public boolean insertDatabase(HoaDon hd) {
        String query = "";
        String preQuery = "insert into HOADON(SOHD";
        String folQuery = " values(?";


        PreparedStatement ps = null;
        try {

            if (hd.getMAPHIEUTP() != null) {
                preQuery += ", MAPHIEUTP";
                folQuery += ", " + hd.getMAPHIEUTP();
            }
            if (hd.getMAPHIEUTDV() != null) {
                preQuery += ", MAPHIEUTDV";
                folQuery += ", " + hd.getMAPHIEUTDV();
            }
            if (hd.getMANV() != null) {
                preQuery += ", MANV";
                folQuery += ", " + hd.getMANV();
            }
            if (hd.getMAKM() != null) {
                preQuery += ", MAKM";
                folQuery += ", " + hd.getMAKM();
            }
            if (hd.getMAKH() != null) {
                preQuery += ", MAKH";
                folQuery += ", " + hd.getMAKH();
            }
            if (hd.getNGAYHD() != null) {
                preQuery += ", NGAYHD";
                folQuery += ", " + "TO_DATE(?,'YYYY/MM/DD')";
            }
            if (hd.getTHANHTIEN() != null) {
                preQuery += ", THANHTIEN";
                folQuery += ", " + hd.getTHANHTIEN();
            }
            preQuery += ")";
            folQuery += ")";
            query += preQuery + folQuery;
            ps = connection.prepareStatement(query);
            ps.setString(1, hd.getSOHD());
            if (hd.getNGAYHD() != null) {
                ps.setString(2, hd.getNGAYHD().toString());
            }


            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DichVu.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean deleteDatabase(HoaDon hd) {
        CallableStatement cstmt = null;
        try {
            cstmt = connection.prepareCall("{CALL PROC_DELETE_HOADON(?)}");


            cstmt.setString(1, hd.getSOHD());

            cstmt.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;


        }
        return true;

    }

    public boolean deleteKM(HoaDon hd) {
        PreparedStatement ps = null;
        try {
            String queryPTP = "UPDATE HOADON SET MAKM = ''  WHERE SOHD =?";
            ps = connection.prepareStatement(queryPTP);

            ps.setString(1, hd.getSOHD());

            ps.executeUpdate();
            return true;

        } catch (SQLException throwables) {


            return true;

        }
    }


    public boolean updateDatabase(HoaDon hd) {
        return insertDatabase(hd);


    }

    public String thanhToan(HoaDon hd) {
        CallableStatement cstmt = null;
        try {
            cstmt = connection.prepareCall("{CALL PROC_PAY_HOADON(?)}");


            cstmt.setString(1, hd.getSOHD());

            cstmt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();

            String[] words1 = throwables.toString().split("ORA-20001:");
            String StrTemp1 = words1[1];
            String[] words2 = StrTemp1.split("ORA");
            String StrTemp2 = words2[0];
            return StrTemp2;
        }

        return "Thành công";


    }

    public ArrayList<HoaDon> queryByHoaDon(HoaDon hd) {

        boolean preNode = false;
        ArrayList<HoaDon> list = new ArrayList<>();

        String sqlQuery =
                "SELECT * from HOADON " +
                        "where ";

        if (hd.getSOHD() != null) {
            sqlQuery += "SOHD = " + hd.getSOHD() + " ";
            preNode = true;
        }
        if (hd.getMAPHIEUTP() != null) {
            if (preNode == true) sqlQuery += " AND ";
            sqlQuery += " MAPHIEUTP = " + hd.getMAPHIEUTP() + " ";
            preNode = true;
        }
        if (hd.getMAPHIEUTDV() != null) {
            if (preNode == true) sqlQuery += " AND ";
            sqlQuery += " MAPHIEUTDV = " + hd.getMAPHIEUTDV() + " ";
            preNode = true;
        }
        if (hd.getMANV() != null) {
            if (preNode == true) sqlQuery += " AND ";
            sqlQuery += " MANV = " + hd.getMANV() + " ";
            preNode = true;
        }

        if (hd.getMAKM() != null) {
            if (preNode == true) sqlQuery += " AND ";
            sqlQuery += " MAKM = " + hd.getMAKM() + " ";
            preNode = true;
        }
        if (hd.getMAKH() != null) {
            if (preNode == true) sqlQuery += " AND ";
            sqlQuery += " MAKH = " + hd.getMAKH() + " ";
            preNode = true;
        }
        if (hd.getNGAYHD() != null) {
            if (preNode == true) sqlQuery += " AND ";
            sqlQuery += " NGAHD = " + "TO_DATE(" + hd.getNGAYHD() + ",'YYYY/MM/DD') ";
            preNode = true;
        }
        if (hd.getTHANHTIEN() != null) {
            if (preNode == true) sqlQuery += " AND ";
            sqlQuery += " THANHTIEN = " + hd.getTHANHTIEN() + " ";
            preNode = true;
        }

        sqlQuery += " ORDER BY SOHD";
        try {
            PreparedStatement preparedStatementShow = connection.prepareStatement(sqlQuery);


            ResultSet rs = preparedStatementShow.executeQuery();

            while (rs.next()) {

                String sohd = rs.getString("SOHD");
                String maphieutp = rs.getString("MAPHIEUTP");
                String maphieutdv = rs.getString("MAPHIEUTDV");
                String manv = rs.getString("MANV");
                String makm = rs.getString("MAKM");
                String makh = rs.getString("MAKH");
                java.util.Date ngayhd = rs.getDate("NGAYHD");
                Integer thanhtien = rs.getInt("THANHTIEN");

                HoaDon hdTemp = new HoaDon(sohd, maphieutp, maphieutdv, manv, makm, makh, ngayhd, thanhtien);
                ;
                list.add(hdTemp);

            }
        } catch (SQLException e) {
        }
        return list;
    }


    public HoaDonDAO() {
        setConnection();
    }

    public boolean setConnection() {
        this.connection = Database.getConnection();
        if (connection == null) {
            JOptionPane.showMessageDialog(null, "Can not connect to database.");
            System.exit(1);
            return false;
        }
        return true;
    }
}
