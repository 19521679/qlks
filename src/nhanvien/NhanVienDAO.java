package nhanvien;

import database.Database;
import dichvu.DichVu;
import hoadon.HoaDon;
import khachhang.KhachHang;


import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NhanVienDAO {

    Connection connection = null;

    public ArrayList<NhanVien> queryAllNV() {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sqlQuery = "SELECT * from NHANVIEN Order by MANV";
        try {
            PreparedStatement preparedStatementShow = this.connection.prepareStatement(sqlQuery);

            ResultSet rs = preparedStatementShow.executeQuery();

            while (rs.next()) {

                String manv = rs.getString("MANV");
                String maql = rs.getString("MAQL");

                String tennv = rs.getString("HOTEN");

                String gioitinh = rs.getString("GIOITINH");
                String dc = rs.getString("DCHI");
                String sdt = rs.getString("SODT");
                Date ngsinh = rs.getDate("NGSINH");
                Date ngvl = rs.getDate("NGVL");
                Integer luong = rs.getInt("LUONG");
                NhanVien nv = new NhanVien(manv, maql, tennv, gioitinh, dc, sdt, ngsinh, ngvl, luong);
                list.add(nv);

            }
        } catch (SQLException e) {
        }


        return list;
    }

    public NhanVien queryNVbyHD(HoaDon hoadon) {

        String sqlQuery = "SELECT * from NHANVIEN WHERE MANV =?";
        try {
            PreparedStatement preparedStatementShow = this.connection.prepareStatement(sqlQuery);
            preparedStatementShow.setString(1, hoadon.getMANV());
            ResultSet rs = preparedStatementShow.executeQuery();

            while (rs.next()) {

                String manv = rs.getString("MANV");
                String maql = rs.getString("MAQL");

                String tennv = rs.getString("HOTEN");

                String gioitinh = rs.getString("GIOITINH");
                String dc = rs.getString("DCHI");
                String sdt = rs.getString("SODT");

                Date ngsinh = rs.getDate("NGSINH");
                Date ngvl = rs.getDate("NGVL");
                Integer luong = rs.getInt("LUONG");
                return new NhanVien(manv, maql, tennv, gioitinh, dc, sdt, ngsinh, ngvl, luong);

            }
        } catch (SQLException e) {
        }


        return new NhanVien();
    }

    public void insert(NhanVien nv) {
        String SQL = "insert into NHANVIEN( HOTEN, MAQL ,GIOITINH , DCHI, SODT,NGSINH, NGVL, LUONG) values(?,?,?,?,?,?,?,?)";


        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL);

            ps.setString(1, nv.getHOTEN());
            ps.setString(2, nv.getMAQL());
            ps.setString(3, nv.getGIOITINH());
            ps.setString(4, nv.getDCHI());
            ps.setString(5, nv.getSODT());

            ps.setDate(6, new java.sql.Date(nv.getNGSINH().getTime()));
            ps.setDate(7, new java.sql.Date(nv.getNGVL().getTime()));
            ps.setInt(8, nv.getLUONG());

            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }

    public void update(NhanVien nv) {
        String SQL = "update NHANVIEN set  HOTEN=?, MAQL=?, GIOITINH=?,  DCHI=?, SDT=?, NGSINH=?, NGVL=?, LUONG=? where MANV = ?";


        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL);


            ps.setString(1, nv.getMAQL());
            ps.setString(2, nv.getGIOITINH());
            ps.setString(3, nv.getDCHI());
            ps.setString(4, nv.getSODT());

            ps.setDate(5, new java.sql.Date(nv.getNGSINH().getTime()));
            ps.setDate(6, new java.sql.Date(nv.getNGVL().getTime()));
            ps.setInt(7, nv.getLUONG());
            ps.setString(8, nv.getHOTEN());

            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String deleteDatabase(NhanVien nv) {

        try {
            String query = "DELETE FROM NHANVIEN WHERE MANV =?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, nv.getMANV());

            ps.executeUpdate();
        } catch (SQLException ex) {
            ex
                    .printStackTrace();
            if (ex.toString().contains("ORA-02292"))
                return "Không thể xoá vì nhân viên đang chứa liên kết \n (Ràng buộc khoá ngoại)";
        }
        return "Thành công";

    }


    public ArrayList<NhanVien> queryByNV(NhanVien nv) {

        boolean preNode = false;
        ArrayList<NhanVien> list = new ArrayList<>();
        String sqlQuery =
                "SELECT * from NHANVIEN " +
                        "where ";

        if (nv.getMANV() != null && !nv.getMANV().isEmpty()) {
            sqlQuery += "MANV LIKE ('%'||'" + nv.getMANV() + "'||'%') ";
            preNode = true;
        }
        if (nv.getMAQL() != null && !nv.getMAQL().isEmpty()) {
            if (preNode == true) sqlQuery += " AND ";
            sqlQuery += " MAQL LIKE ('%'||'" + nv.getMAQL() + "'||'%') ";
            preNode = true;
        }
        if (nv.getHOTEN() != null && !nv.getHOTEN() .isEmpty()) {
            if (preNode == true) sqlQuery += " AND ";
            sqlQuery += " HOTEN LIKE ('%'||'" + nv.getHOTEN() + "'||'%') ";
            preNode = true;
        }
        if (nv.getGIOITINH() != null && !nv.getGIOITINH().isEmpty()) {
            if (preNode == true) sqlQuery += " AND ";
            sqlQuery += " GIOITINH LIKE ('%'||'" + nv.getGIOITINH() + "'||'%') ";
            preNode = true;
        }
        if (nv.getDCHI() != null && !nv.getDCHI().isEmpty()) {
            if (preNode == true) sqlQuery += " AND ";
            sqlQuery += " DCHI LIKE ('%'||'" + nv.getDCHI() + "'||'%') ";
            preNode = true;
        }

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        if (nv.getNGSINH() != null) {
            if (preNode == true) sqlQuery += " AND ";
            sqlQuery += " NGSINH = TO_DATE('" + format.format(nv.getNGSINH()) + "','dd/MM/yyyy') ";
            preNode = true;
        }
        if (nv.getNGVL() != null) {
            if (preNode == true) sqlQuery += " AND ";
            sqlQuery += " NGVL = TO_DATE('" + format.format(nv.getNGVL()) + "','dd/MM/yyyy') ";
            preNode = true;
        }
        if (nv.getSODT() != null && !nv.getSODT().isEmpty()) {
            if (preNode == true) sqlQuery += " AND ";
            sqlQuery += " SODT LIKE ('%'||'" + nv.getSODT() + "'||'%') ";
            preNode = true;
        }
        if (nv.getLUONG()!=null) {
            if (preNode == true) sqlQuery += " AND ";
            sqlQuery += " LUONG = " + nv.getLUONG() + " ";
            preNode = true;
        }

        sqlQuery += " ORDER BY MANV";
        try {
            PreparedStatement preparedStatementShow = this.connection.prepareStatement(sqlQuery);
            ResultSet rs = preparedStatementShow.executeQuery();

            while (rs.next()) {

                String manv = rs.getString("MANV");
                String maql = rs.getString("MAQL");
                String tennv = rs.getString("HOTEN");
                String gioitinh = rs.getString("GIOITINH");
                String dc = rs.getString("DCHI");
                String sdt = rs.getString("SODT");
                Date ngsinh = rs.getDate("NGSINH");
                Date ngvl = rs.getDate("NGVL");
                Integer luong = rs.getInt("LUONG");
                list.add(new NhanVien(manv, maql, tennv, gioitinh, dc, sdt, ngsinh, ngvl, luong));

            }
        } catch (SQLException e) {
        }
        return list;

    }


    public NhanVienDAO() {
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
