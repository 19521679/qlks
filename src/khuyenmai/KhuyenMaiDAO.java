package khuyenmai;

import database.Database;
import hoadon.HoaDon;
import khachhang.KhachHang;
import khuyenmai.KhuyenMai;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class KhuyenMaiDAO {
    Connection connection = null;

    public ArrayList<KhuyenMai> queryAllkm() {
        ArrayList<KhuyenMai> list = new ArrayList<>();
        String sqlQuery = "SELECT * from KhuyenMai Order by MAKM";
        try {
            PreparedStatement preparedStatementShow = this.connection.prepareStatement(sqlQuery);

            ResultSet rs = preparedStatementShow.executeQuery();

            while (rs.next()) {

                String makm = rs.getString("MAKM");
                String tenkm = rs.getString("TENKM");
                String mota = rs.getString("MOTA");
                Float tile = rs.getFloat("TILE");
                Date ngaybd = rs.getDate("NGAYBD");
                Date ngaykt = rs.getDate("NGAYKT");


                list.add(new KhuyenMai(makm, tenkm, mota, tile, ngaybd, ngaykt));

            }
        } catch (SQLException e) {
        }
        return list;
    }

    public ArrayList<KhuyenMai> queryKMThanhVien() {
        ArrayList<KhuyenMai> list = new ArrayList<>();
        String sqlQuery = "SELECT * from KhuyenMai WHERE MOTA = 'thanhvien' Order by MAKM";
        try {
            PreparedStatement preparedStatementShow = this.connection.prepareStatement(sqlQuery);

            ResultSet rs = preparedStatementShow.executeQuery();

            while (rs.next()) {

                String makm = rs.getString("MAKM");
                String tenkm = rs.getString("TENKM");
                String mota = rs.getString("MOTA");
                Float tile = rs.getFloat("TILE");
                Date ngaybd = rs.getDate("NGAYBD");
                Date ngaykt = rs.getDate("NGAYKT");


                list.add(new KhuyenMai(makm, tenkm, mota, tile, ngaybd, ngaykt));

            }
        } catch (SQLException e) {
        }
        return list;
    }

    public ArrayList<KhuyenMai> queryKMThanhVienByDate(Date startDate, Date endDate) {
        ArrayList<KhuyenMai> list = new ArrayList<>();
        String sqlQuery = "SELECT * from KhuyenMai WHERE " +
                " TO_DATE(NGAYBD,'dd/MM/yyyy') <= TO_DATE(?,'dd/MM/yyyy') AND TO_DATE(NGAYKT,'dd/MM/yyyy') >= TO_DATE(?,'dd/MM/yyyy') AND MOTA='thanhvien' Order by MAKM";
        try {
            PreparedStatement preparedStatementShow = this.connection.prepareStatement(sqlQuery);
            preparedStatementShow.setDate(1, new java.sql.Date(endDate.getTime()));
            preparedStatementShow.setDate(2, new java.sql.Date(startDate.getTime()));
            ResultSet rs = preparedStatementShow.executeQuery();

            while (rs.next()) {

                String makm = rs.getString("MAKM");
                String tenkm = rs.getString("TENKM");
                String mota = rs.getString("MOTA");
                Float tile = rs.getFloat("TILE");
                Date ngaybd = rs.getDate("NGAYBD");
                Date ngaykt = rs.getDate("NGAYKT");


                list.add(new KhuyenMai(makm, tenkm, mota, tile, ngaybd, ngaykt));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;

    }

    public ArrayList<KhuyenMai> queryKMThuongByDate(Date startDate, Date endDate) {
        ArrayList<KhuyenMai> list = new ArrayList<>();
        String sqlQuery = "SELECT * from KhuyenMai WHERE " +
                " TO_DATE(NGAYBD,'dd/MM/yyyy') <= TO_DATE(?,'dd/MM/yyyy') AND TO_DATE(NGAYKT,'dd/MM/yyyy') >= TO_DATE(?,'dd/MM/yyyy') AND MOTA!='thanhvien' Order by MAKM";
        try {
            PreparedStatement preparedStatementShow = this.connection.prepareStatement(sqlQuery);
            preparedStatementShow.setDate(1, new java.sql.Date(endDate.getTime()));
            preparedStatementShow.setDate(2, new java.sql.Date(startDate.getTime()));
            ResultSet rs = preparedStatementShow.executeQuery();

            while (rs.next()) {

                String makm = rs.getString("MAKM");
                String tenkm = rs.getString("TENKM");
                String mota = rs.getString("MOTA");
                Float tile = rs.getFloat("TILE");
                Date ngaybd = rs.getDate("NGAYBD");
                Date ngaykt = rs.getDate("NGAYKT");


                list.add(new KhuyenMai(makm, tenkm, mota, tile, ngaybd, ngaykt));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<KhuyenMai> queryKMThuong() {
        ArrayList<KhuyenMai> list = new ArrayList<>();
        String sqlQuery = "SELECT * from KhuyenMai WHERE MOTA = 'thuong' Order by MAKM";
        try {
            PreparedStatement preparedStatementShow = this.connection.prepareStatement(sqlQuery);

            ResultSet rs = preparedStatementShow.executeQuery();

            while (rs.next()) {

                String makm = rs.getString("MAKM");
                String tenkm = rs.getString("TENKM");
                String mota = rs.getString("MOTA");
                Float tile = rs.getFloat("TILE");
                Date ngaybd = rs.getDate("NGAYBD");
                Date ngaykt = rs.getDate("NGAYKT");


                list.add(new KhuyenMai(makm, tenkm, mota, tile, ngaybd, ngaykt));

            }
        } catch (SQLException e) {
        }
        return list;
    }

    public String remove(KhuyenMai km) {
        String SQL = "delete from KhuyenMai where MAKM=?";


        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL);
            ps.setString(1, km.getMAKM());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            if (throwables.toString().contains("ORA-02292"))
                return "Không thể xoá vì khuyến mãi này đang xử dụng \n (ràng buộc khoá ngoại)";
        }
        return "Thành công";


    }

    public void insert(KhuyenMai km) {
        String SQL = "insert into KhuyenMai( MAKM, TENKM, MOTA, TILE, NGAYBD,NGAYKT) values(?,?,?,?,?,?)";


        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL);

            ps.setString(1, km.getMAKM());
            ps.setString(2, km.getTENKM());
            ps.setString(3, km.getMOTA());
            ps.setFloat(4, km.getTILE());

            ps.setDate(5, new java.sql.Date(km.getNGBD().getTime()));
            ps.setDate(6, new java.sql.Date(km.getNGKT().getTime()));

            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }

    public void update(KhuyenMai km) {
        String SQL = "update KhuyenMai set   TENKM=?, MOTA=?, TILE=?, NGAYBD=?, NGAYKT=? where MAKM = ?";


        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL);


            ps.setString(1, km.getTENKM());
            ps.setString(2, km.getMOTA());
            ps.setFloat(3, km.getTILE());

            ps.setDate(4, new java.sql.Date(km.getNGBD().getTime()));
            ps.setDate(5, new java.sql.Date(km.getNGKT().getTime()));
            ps.setString(6, km.getMAKM());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean insertKMintoHD(HoaDon hd, KhuyenMai km) {
        String SQL = "update HOADON set   MAKM=? where SOHD = ?";


        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL);

            ps.setString(1, km.getMAKM());
            ps.setString(2, hd.getSOHD());


            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList<KhuyenMai> queryByKM(KhuyenMai km) {


        boolean preNode = false;
        ArrayList<KhuyenMai> list = new ArrayList<>();
        String sqlQuery =
                "SELECT * from KHUYENMAI " +
                        "where ";

        if (km.getMAKM() != null && !km.getMAKM().isEmpty()) {
            sqlQuery += "MAKM LIKE ('%'||'" + km.getMAKM() + "'||'%') ";
            preNode = true;
        }
        if (km.getTENKM() != null && !km.getTENKM().isEmpty()) {
            if (preNode == true) sqlQuery += " AND ";
            sqlQuery += " TENKM LIKE ('%'||'" + km.getTENKM() + "'||'%') ";
            preNode = true;
        }
        if (km.getMOTA() != null && !km.getMOTA().isEmpty()) {
            if (preNode == true) sqlQuery += " AND ";
            sqlQuery += " MOTA LIKE ('%'||'" + km.getMOTA() + "'||'%') ";
            preNode = true;
        }
        if (km.getTILE() != null) {
            if (preNode == true) sqlQuery += " AND ";
            sqlQuery += " TILE = " + km.getTILE() + " ";
            preNode = true;
        }
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        if (km.getNGBD() != null) {
            if (preNode == true) sqlQuery += " AND ";
            sqlQuery += "TO_DATE(NGAYBD,'dd/MM/yyyy') <= TO_DATE('" + format.format(km.getNGKT()) + "','dd/MM/yyyy') ";
            preNode = true;
        }
        if (km.getNGKT() != null) {
            if (preNode == true) sqlQuery += " AND ";
            sqlQuery += "TO_DATE(NGAKT,'dd/MM/yyyy') >= TO_DATE('" + format.format(km.getNGBD()) + "','dd/MM/yyyy') ";
            preNode = true;
        }

        sqlQuery += " ORDER BY MAKM";

        try {
            PreparedStatement preparedStatementShow = this.connection.prepareStatement(sqlQuery);

            ResultSet rs = preparedStatementShow.executeQuery();
            while (rs.next()) {
                String makm = rs.getString("MAKM");
                String tenkm = rs.getString("TENKM");
                String mota = rs.getString("MOTA");
                Float tile = rs.getFloat("TILE");
                Date ngaybd = rs.getDate("NGAYBD");
                Date ngaykt = rs.getDate("NGAYKT");
                list.add(new KhuyenMai(makm, tenkm, mota, tile, ngaybd, ngaykt));
            }
        } catch (SQLException e) {
        }
        System.out.println("list tim duoc:"+list);


        return list;
    }



    public KhuyenMaiDAO() {
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

    public KhuyenMai queryByHD(HoaDon hd) {
        KhuyenMai khuyenmai = null;
        String sqlQuery = "SELECT * from KhuyenMai WHERE MAKM =? Order by MAKM";
        try {
            PreparedStatement preparedStatementShow = this.connection.prepareStatement(sqlQuery);
            preparedStatementShow.setString(1, hd.getMAKM());
            ResultSet rs = preparedStatementShow.executeQuery();

            while (rs.next()) {

                String makm = rs.getString("MAKM");
                String tenkm = rs.getString("TENKM");
                String mota = rs.getString("MOTA");
                Float tile = rs.getFloat("TILE");
                Date ngaybd = rs.getDate("NGAYBD");
                Date ngaykt = rs.getDate("NGAYKT");


                khuyenmai = new KhuyenMai(makm, tenkm, mota, tile, ngaybd, ngaykt);

            }
        } catch (SQLException e) {
        }
        return khuyenmai;
    }
}
