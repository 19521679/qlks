package phong;

import Util.MyConvert;
import database.Database;
import dichvu.DichVu;
import hoadon.HoaDon;
import khachhang.KhachHang;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class PhongDAO {
    Connection connection = null;

    public ArrayList<Phong> queryAllPhong() {
        ArrayList<Phong> list = new ArrayList<>();
        String sqlQuery = "SELECT DISTINCT * from PHONG Order by MAPH";
        try {
            PreparedStatement preparedStatementShow = this.connection.prepareStatement(sqlQuery);

            ResultSet rs = preparedStatementShow.executeQuery();

            while (rs.next()) {

                String maph = rs.getString("MAPH");

                String maloaiph = rs.getString("MALOAIPH");
                String tinhtrang = rs.getString("TINHTRANG");
                String ghichu = rs.getString("GHICHU");

                //Date ngayhd = rs.getDate("NGAYHD");


                list.add(new Phong(maph, maloaiph, tinhtrang, ghichu));

            }
        } catch (SQLException e) {
        }

        return list;
    }

    public ArrayList<Phong> queryPhongBaoTri() {
        ArrayList<Phong> list = new ArrayList<>();
        String sqlQuery = "SELECT DISTINCT * from PHONG WHERE TINHTRANG= 'baotri' Order by MAPH";
        try {
            PreparedStatement preparedStatementShow = this.connection.prepareStatement(sqlQuery);

            ResultSet rs = preparedStatementShow.executeQuery();

            while (rs.next()) {

                String maph = rs.getString("MAPH");

                String maloaiph = rs.getString("MALOAIPH");
                String tinhtrang = rs.getString("TINHTRANG");
                String ghichu = rs.getString("GHICHU");

                //Date ngayhd = rs.getDate("NGAYHD");


                list.add(new Phong(maph, maloaiph, tinhtrang, ghichu));

            }
        } catch (SQLException e) {
        }

        return list;
    }

    public ArrayList<Phong> queryPhongDon() {
        ArrayList<Phong> list = new ArrayList<>();
        String sqlQuery = "SELECT DISTINCT * from PHONG where MALOAIPH IN " +
                "( SELECT MALOAIPH FROM DANHSACHPHONG WHERE TENLOAIPH = 'phong don')" +
                "Order by MAPH";
        try {
            PreparedStatement preparedStatementShow = this.connection.prepareStatement(sqlQuery);
            ResultSet rs = preparedStatementShow.executeQuery();
            while (rs.next()) {
                String maph = rs.getString("MAPH");
                String maloaiph = rs.getString("MALOAIPH");
                String tinhtrang = rs.getString("TINHTRANG");
                String ghichu = rs.getString("GHICHU");
                list.add(new Phong(maph, maloaiph, tinhtrang, ghichu));

            }
        } catch (SQLException e) {
        }

        return list;
    }

    public ArrayList<Phong> queryPhongDoi() {
        ArrayList<Phong> list = new ArrayList<>();
        String sqlQuery = "SELECT DISTINCT * from PHONG where MALOAIPH IN " +
                "( SELECT MALOAIPH FROM DANHSACHPHONG WHERE TENLOAIPH = 'phong doi')" +
                "Order by MAPH";
        try {
            PreparedStatement preparedStatementShow = this.connection.prepareStatement(sqlQuery);
            ResultSet rs = preparedStatementShow.executeQuery();
            while (rs.next()) {
                String maph = rs.getString("MAPH");
                String maloaiph = rs.getString("MALOAIPH");
                String tinhtrang = rs.getString("TINHTRANG");
                String ghichu = rs.getString("GHICHU");
                list.add(new Phong(maph, maloaiph, tinhtrang, ghichu));

            }
        } catch (SQLException e) {
        }

        return list;
    }

    public ArrayList<Phong> queryPhongVIP() {
        ArrayList<Phong> list = new ArrayList<>();
        String sqlQuery = "SELECT DISTINCT * from PHONG where MALOAIPH IN " +
                "( SELECT MALOAIPH FROM DANHSACHPHONG WHERE TENLOAIPH = 'phong vip')" +
                "Order by MAPH";
        try {
            PreparedStatement preparedStatementShow = this.connection.prepareStatement(sqlQuery);
            ResultSet rs = preparedStatementShow.executeQuery();
            while (rs.next()) {
                String maph = rs.getString("MAPH");
                String maloaiph = rs.getString("MALOAIPH");
                String tinhtrang = rs.getString("TINHTRANG");
                String ghichu = rs.getString("GHICHU");
                list.add(new Phong(maph, maloaiph, tinhtrang, ghichu));

            }
        } catch (SQLException e) {
        }

        return list;
    }


    public ArrayList<Phong> queryAllPhongFullByStEd(Date startDate, Date endDate) {

        java.sql.Date date1 = new java.sql.Date(startDate.getTime());
        java.sql.Date date2 = new java.sql.Date(endDate.getTime());

        ArrayList<Phong> list = new ArrayList<>();
        String sqlQuery = "SELECT DISTINCT * FROM PHONG WHERE MAPH IN(" +
                "SELECT PHONG.MAPH from PHONG JOIN THUE_PHONG ON PHONG.MAPH=THUE_PHONG.MAPH " +
                " WHERE TO_DATE(NGBD,'dd/MM/yyyy') <= TO_DATE(?,'dd/MM/yyyy') " +
                " AND  TO_DATE(NGKT,'dd/MM/yyyy') >= TO_DATE(?,'dd/MM/yyyy') )" +
                "Order by PHONG.MAPH";
        try {
            PreparedStatement preparedStatementShow = this.connection.prepareStatement(sqlQuery);
            preparedStatementShow.setDate(1, date2);
            preparedStatementShow.setDate(2, date1);

            ResultSet rs = preparedStatementShow.executeQuery();

            while (rs.next()) {

                String maph = rs.getString("MAPH");
                String maphoaiph = rs.getString("MALOAIPH");
                String tinhtrang = rs.getString("TINHTRANG");
                String ghichu = rs.getString("GHICHU");

                //Date ngayhd = rs.getDate("NGAYHD");


                list.add(new Phong(maph, maphoaiph, tinhtrang, ghichu));

            }
        } catch (SQLException e) {
        }

        return list;
    }

    public ArrayList<Phong> queryAllPhongEmptyStEd(Date startDate, Date endDate) {
        java.sql.Date date1 = new java.sql.Date(startDate.getTime());
        java.sql.Date date2 = new java.sql.Date(endDate.getTime());

        ArrayList<Phong> list = new ArrayList<>();
        String sqlQuery = "SELECT DISTINCT * FROM PHONG WHERE MAPH NOT IN(" +
                "SELECT PHONG.MAPH from PHONG JOIN THUE_PHONG ON PHONG.MAPH=THUE_PHONG.MAPH " +
                " WHERE TO_DATE(NGBD,'dd/MM/yyyy') <= TO_DATE(?,'dd/MM/yyyy') " +
                " AND  TO_DATE(NGKT,'dd/MM/yyyy') >= TO_DATE(?,'dd/MM/yyyy') )" +
                "Order by PHONG.MAPH";
        try {
            PreparedStatement preparedStatementShow = this.connection.prepareStatement(sqlQuery);
            preparedStatementShow.setDate(1, date2);
            preparedStatementShow.setDate(2, date1);

            ResultSet rs = preparedStatementShow.executeQuery();

            while (rs.next()) {

                String maph = rs.getString("MAPH");
                String maphoaiph = rs.getString("MALOAIPH");
                String tinhtrang = rs.getString("TINHTRANG");
                String ghichu = rs.getString("GHICHU");

                //Date ngayhd = rs.getDate("NGAYHD");


                list.add(new Phong(maph, maphoaiph, tinhtrang, ghichu));

            }
        } catch (SQLException e) {
        }


        return list;
    }

    public ArrayList<ThuePhong> queryTPBySOHD(HoaDon hoadon) {
        ArrayList<ThuePhong> list = new ArrayList<>();
        String sqlQuery = "SELECT DISTINCT * FROM THUE_PHONG WHERE MAPHIEUTP IN(" +
                "SELECT MAPHIEUTP from HOADON WHERE SOHD =?) " +
                "Order by MAPH";
        try {
            PreparedStatement preparedStatementShow = this.connection.prepareStatement(sqlQuery);
            preparedStatementShow.setString(1, hoadon.getSOHD());
            ResultSet rs = preparedStatementShow.executeQuery();

            while (rs.next()) {

                String maph = rs.getString("MAPH");

                String maphieutp = rs.getString("MAPHIEUTP");
                Integer songuoithue = rs.getInt("SONGUOITHUE");
                Float phuthu = rs.getFloat("PHUTHU");
                Date ngbd = rs.getDate("NGBD");
                Date ngkt = rs.getDate("NGKT");
                String ghichu = rs.getString("GHICHU");
                Integer tien = rs.getInt("TIEN");

                //Date ngayhd = rs.getDate("NGAYHD");


                list.add(new ThuePhong(maph, maphieutp, songuoithue, phuthu, ngbd, ngkt, ghichu, tien));

            }
        } catch (SQLException e) {
        }

        return list;
    }

    public Integer querySoNgayThueByTP(ThuePhong tp) {

        String sqlQuery = "SELECT TO_DATE(NGKT,'dd/MM/yyyy')-TO_DATE(NGBD,'dd/MM/yyyy') SONGAY FROM THUE_PHONG WHERE MAPH = ?  AND MAPHIEUTP =? " +
                " Order by MAPH";
        try {
            PreparedStatement preparedStatementShow = this.connection.prepareStatement(sqlQuery);
            preparedStatementShow.setString(1, tp.getMAPH());
            preparedStatementShow.setString(2, tp.getMAPHIEUTP());
            ResultSet rs = preparedStatementShow.executeQuery();

            rs.next();
            return rs.getInt("SONGAY") + 1;

        } catch (SQLException e) {
        }

        return 0;
    }

    public void insert(Phong phong) {
        String SQL = "insert into PHONG(MALOAIPH, TINHTRANG, GHICHU) values(?,?,?)";


        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL);


            ps.setString(1, phong.getMALOAIPH());
            ps.setString(2, phong.getTINHTRANG());
            ps.setString(3, phong.getGHICHU());

            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }

    }

    public void update(Phong phong) {
        String SQL = "update PHONG set  MALOAIPH=?, TINHTRANG=?, GHICHU=? where MAPH = ?";


        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL);
            ps.setString(1, phong.getMALOAIPH());
            ps.setString(2, phong.getTINHTRANG());
            ps.setString(3, phong.getGHICHU());
            ps.setString(4, phong.getMAPH());

            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public String delete(Phong p) {

        try {
            String query = "DELETE FROM PHONG WHERE MAPH =?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, p.getMAPH());

            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            if (ex.toString().contains("ORA-02292")) return "Không thể xoá vì vi phạm khoá ngoại";
        }
        return "Thành công";
    }

    public Integer queryTongTienTPByHD(HoaDon hd) {

        String sqlQuery = "SELECT TIENTP FROM PHIEUTHUEPHONG WHERE MAPHIEUTP =? ";
        try {
            PreparedStatement preparedStatementShow = this.connection.prepareStatement(sqlQuery);
            preparedStatementShow.setString(1, hd.getMAPHIEUTP());

            ResultSet rs = preparedStatementShow.executeQuery();
            rs.next();
            return rs.getInt("TIENTP");


        } catch (SQLException e) {
        }

        return 0;
    }

    public boolean deleteTP(ArrayList<ThuePhong> list) {

        PreparedStatement ps = null;

        CallableStatement cstmt = null;
        for (ThuePhong p : list) {
            try {
                cstmt = connection.prepareCall("{CALL proc_delete_tp(?,?)}");
                cstmt.setString(1, p.getMAPH());
                cstmt.setString(2, p.getMAPHIEUTP());
                cstmt.executeQuery();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
        return true;
    }


    public PhongDAO() {
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
