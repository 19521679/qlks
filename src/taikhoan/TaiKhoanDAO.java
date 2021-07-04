package taikhoan;

import Util.MyConvert;
import database.Database;
import dichvu.DichVu;
import hoadon.HoaDon;
import nhanvien.NhanVien;
import taikhoan.TaiKhoan;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaiKhoanDAO {
    private static Connection connection = Database.getConnection();
    private static TaiKhoan taikhoan;
    private static NhanVien nhanvien;
    private static String PC;


    public static TaiKhoan getTaiKhoan() {
        return taikhoan;
    }


    public static boolean tuDongDangNhap(String pc) {
        TaiKhoan tk = new TaiKhoan();
        Date nght = new Date(System.currentTimeMillis());
        PC=pc;



        try {

            String query = "SELECT * FROM TAIKHOAN WHERE PC = ? order by TGDNGN";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, pc);

            ResultSet rs = ps.executeQuery();
            if (rs.next() == false) return false;
            String trangthai = rs.getString("TRANGTHAI");
            System.out.println("Trangthai: " + trangthai);

            if (trangthai.equals("dangnhap")) {

                tk.setTGDNGN(rs.getTimestamp("TGDNGN"));
                System.out.println("Landngn:" + tk.getTGDNGN());
                if (((nght.getTime() - tk.getTGDNGN().getTime()) / (60 * 1000) % 60) > 5) {
                    return false;

                } else {
                    taikhoan = new TaiKhoan();
                    taikhoan.setUSERNAME(rs.getString("USERNAME"));
                    taikhoan.setMANV(rs.getString("MANV"));
                    taikhoan.setPASSWORD(rs.getString("PASSWORD"));
                    taikhoan.setTGDNGN(rs.getDate("TGDNGN"));
                    taikhoan.setTRANGTHAI(rs.getString("TRANGTHAI"));
                    taikhoan.setPC(pc);
                    setDangNhap();
                    return true;
                }

            } else {

                return false;
            }


        } catch (SQLException ex) {
            Logger.getLogger(DichVu.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static TaiKhoan getTaikhoan() {
        return taikhoan;
    }

    public static NhanVien queryNVbyTK(TaiKhoan tk) {

        try {

            String query = "SELECT * FROM NHANVIEN WHERE MANV IN (" +
                    " SELECT MANV FROM TAIKHOAN WHERE USERNAME = ?)";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, tk.getUSERNAME());

            ResultSet rs = ps.executeQuery();

            rs.next();

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


        } catch (SQLException ex) {
            Logger.getLogger(DichVu.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;

    }


    public static boolean kiemTraDangNhap(TaiKhoan tk) {

        try {

            String query = "SELECT * FROM TAIKHOAN WHERE USERNAME =? AND PASSWORD = ?";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, tk.getUSERNAME());
            ps.setString(2, tk.getPASSWORD());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if ((rs.getString("username").equals(tk.USERNAME)) && (rs.getString("password").equals(tk.PASSWORD))) {
                    taikhoan = tk;
                    taikhoan.setMANV(rs.getString("MANV"));
                    taikhoan.setTGDNGN(rs.getDate("TGDNGN"));
                    taikhoan.setTRANGTHAI(rs.getString("TRANGTHAI"));
                    taikhoan.setPC(rs.getString("PC"));
                    setDangNhap();
                    return true;
                }
            }


        } catch (SQLException ex) {
            Logger.getLogger(DichVu.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;

    }

    private static void setDangNhap() {

        try {

            String query = "UPDATE TAIKHOAN SET TRANGTHAI = 'dangnhap', TGDNGN = ?, PC = ? ";

            query += " WHERE USERNAME =?";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setDate(1, new java.sql.Date(System.currentTimeMillis()));
            ps.setString(2, taikhoan.getPC());
            ps.setString(3, taikhoan.getUSERNAME());

            ps.executeUpdate();
            nhanvien = queryNVbyTK(taikhoan);



        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }

    public static void setDangXuat() {

        try {

            String query = "UPDATE TAIKHOAN SET TRANGTHAI = 'dangxuat', TGDNGN = ? ";

            query += " WHERE USERNAME =?";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setDate(1, new java.sql.Date(System.currentTimeMillis()));
            ps.setString(2, taikhoan.getUSERNAME());

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DichVu.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public static void setThoat() {

        try {
            String query = "UPDATE TAIKHOAN SET  TGDNGN = ? ";

            query += " WHERE USERNAME =?";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setDate(1, new java.sql.Date(System.currentTimeMillis()));
            ps.setString(2, taikhoan.getUSERNAME());

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DichVu.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public ArrayList<TaiKhoan> queryAll() {
        ArrayList<TaiKhoan> list = new ArrayList<>();

        String sqlQuery = "SELECT * from TAIKHOAN ";
        try {
            PreparedStatement preparedStatementShow = this.connection.prepareStatement(sqlQuery);
            ResultSet rs = preparedStatementShow.executeQuery();

            while (rs.next()) {

                String username = rs.getString("USERNAME");
                String manv = rs.getString("MANV");

                String password = rs.getString("PASSWORD");

                String trangthai = rs.getString("TRANGTHAI");
                Date tgdngn = rs.getDate("TGDNGN");
                String pc = rs.getString("PC");

                list.add(new TaiKhoan(username, manv, password, trangthai, tgdngn, pc));

            }
        } catch (SQLException e) {
        }


        return list;
    }

    public void insert(TaiKhoan tk) {
        String SQL = "insert into TAIKHOAN( MANV ,PASSWORD ) values(?,?)";


        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL);

            ps.setString(1, tk.getMANV());
            ps.setString(2, tk.getPASSWORD());


            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }

    public void update(TaiKhoan tk) {
        String SQL = "update TAIKHOAN set   PASSWORD=? where USERNAME = ?";


        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL);


            ps.setString(1, tk.getPASSWORD());
            ps.setString(2, tk.getUSERNAME());


            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean delete(TaiKhoan tk) {

        try {
            String query = "DELETE FROM TAIKHOAN WHERE MANV =?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, tk.getMANV());

            return (ps.executeUpdate() > 0);
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DichVu.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public ArrayList<TaiKhoan> find(TaiKhoan tk) {

        boolean preNode = false;
        ArrayList<TaiKhoan> list = new ArrayList<>();
        String sqlQuery =
                "SELECT * from TAIKHOAN " +
                        "where ";

        if (tk.getUSERNAME() != null && !tk.getUSERNAME().isEmpty()) {
            sqlQuery += "USERNAME LIKE ('%'||'" + tk.getUSERNAME() + "'||'%') ";
            preNode = true;
        }
        if (tk.getMANV() != null && !tk.getMANV().isEmpty()) {
            if (preNode == true) sqlQuery += " AND ";
            sqlQuery += " MANV LIKE ('%'||'" + tk.getMANV() + "'||'%') ";
            preNode = true;
        }
        if (tk.getPASSWORD() != null && !tk.getPASSWORD().isEmpty()) {
            if (preNode == true) sqlQuery += " AND ";
            sqlQuery += "PASSWORD  LIKE ('%'||'" + tk.getPASSWORD() + "'||'%') ";
            preNode = true;
        }
        if (tk.getTRANGTHAI() != null && !tk.getTRANGTHAI().isEmpty()) {
            if (preNode == true) sqlQuery += " AND ";
            sqlQuery += "TRANGTHAI  LIKE ('%'||'" + tk.getTRANGTHAI() + "'||'%') ";
            preNode = true;
        }
        sqlQuery += " ORDER BY USERNAME";
        System.out.println("query: " + sqlQuery);
        try {
            PreparedStatement preparedStatementShow = connection.prepareStatement(sqlQuery);


            ResultSet rs = preparedStatementShow.executeQuery();

            while (rs.next()) {

                String username = rs.getString("USERNAME");
                String manv = rs.getString("MANV");

                String password = rs.getString("PASSWORD");

                String trangthai = rs.getString("TRANGTHAI");
                Date tgdngn = rs.getDate("TGDNGN");
                String pc = rs.getString("PC");

                list.add(new TaiKhoan(username, manv, password, trangthai, tgdngn, pc));

            }
        } catch (SQLException e) {
        }
        return list;
    }

}
