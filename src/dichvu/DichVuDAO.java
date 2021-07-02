package dichvu;

import Util.MyConvert;
import database.Database;
import hoadon.HoaDon;
import phong.ThuePhong;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DichVuDAO {

    Connection connection=null;
    public ArrayList<DichVu> queryAllDichVu() {
        ArrayList<DichVu> list = new ArrayList<>();
        String sqlQuery = "SELECT * from DICHVU Order by MADV";
        try {
            PreparedStatement preparedStatementShow = this.connection.prepareStatement(sqlQuery);

            ResultSet rs = preparedStatementShow.executeQuery();

            while (rs.next()) {

                String madv = rs.getString("MADV");
                String tendv = rs.getString("TENDV");
                Integer giadv = rs.getInt("GIADV");
                DichVu dv = new DichVu(madv, tendv, giadv);
                list.add(dv);

            }
        } catch (SQLException e) {
        }
        return list;
    }

    public boolean insertDatabase(DichVu dv) {

        String query ="insert into DichVu( TENDV, GIADV) values(?,?)";
        PreparedStatement ps = null;
        try {


            ps = connection.prepareStatement(query);
            ps.setString(1, dv.getTENDV());
            ps.setInt(2, dv.getGIADV());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DichVu.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean deleteDatabase(DichVu dv) {

            try {
                String query = "DELETE FROM DICHVU WHERE MADV =?";

                PreparedStatement ps=connection.prepareStatement(query);
                ps.setString(1, dv.getMADV());

                return (ps.executeUpdate()>0);
            } catch (SQLException ex) {
                System.out.println(ex);
                Logger.getLogger(DichVu.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }

    }
    public boolean deleteTDV(ArrayList<ThueDichVu> listtdv) {

        System.out.println("xoa");
        PreparedStatement ps = null;

        CallableStatement cstmt = null;
        for (ThueDichVu p : listtdv) {
            try {
                cstmt = connection.prepareCall("{CALL proc_delete_tdv(?,?)}");
                cstmt.setString(1, p.getMADV());
                cstmt.setString(2, p.getMAPHIEUTDV());
                cstmt.executeQuery();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
        return true;

    }

    public ArrayList<ThueDichVu> queryTDVBySOHD(HoaDon hoadon)
    {
        //System.out.println("sohd: "+hoadon.toString());
        ArrayList<ThueDichVu> list = new ArrayList<>();
        String sqlQuery = "SELECT DISTINCT * from THUE_DICH_VU WHERE MAPHIEUTDV IN (" +
                "SELECT  MAPHIEUTDV FROM HOADON" +
                " WHERE HOADON.SOHD = ? )" +
                " Order by MADV";
        try {
            PreparedStatement preparedStatementShow = this.connection.prepareStatement(sqlQuery);
            preparedStatementShow.setString(1,hoadon.getSOHD());
            ResultSet rs = preparedStatementShow.executeQuery();

            while (rs.next()) {

                String madv = rs.getString("MADV");

                String maphieutdv = rs.getString("MAPHIEUTDV");
                Date ngbd=rs.getDate("NGAYBD");
                Date ngkt=rs.getDate("NGAYKT");
                String ghichu = rs.getString("GHICHU");
                Integer tien=rs.getInt("TIEN");

                //Date ngayhd = rs.getDate("NGAYHD");

                list.add(new ThueDichVu(madv,maphieutdv,ngbd,ngkt,ghichu,tien));

            }
        } catch (SQLException e) {
        }
        return list;
    }
    public DichVu queryDVByTDV(ThueDichVu thueDichVu )
    {
        DichVu dv = new DichVu();
        String sqlQuery = "SELECT DISTINCT * from DICHVU WHERE MADV =?  "+
                " Order by MADV";
        try {
            PreparedStatement preparedStatementShow = this.connection.prepareStatement(sqlQuery);
            preparedStatementShow.setString(1,thueDichVu.getMADV());
            ResultSet rs = preparedStatementShow.executeQuery();

            while (rs.next()) {

                String madv = rs.getString("MADV");
                String tendv = rs.getString("TENDV");
                Integer giadv = rs.getInt("GIADV");

                dv.setGIADV(giadv);
                dv.setTENDV(tendv);
                dv.setMADV(madv);

            }
        } catch (SQLException e) {
        }
        return dv;

    }

    public Integer soNgayThueDVbyTDV(ThueDichVu thueDichVu )
    {
        //System.out.println("sohd: "+hoadon.toString());
        ArrayList<ThueDichVu> list = new ArrayList<>();
        String sqlQuery = "SELECT NGAYKT-NGAYBD SONGAY from THUE_DICH_VU WHERE MADV =? AND MAPHIEUTDV = ? "+
                " Order by MADV";
        try {
            PreparedStatement preparedStatementShow = this.connection.prepareStatement(sqlQuery);
            preparedStatementShow.setString(1,thueDichVu.getMADV());
            preparedStatementShow.setString(2,thueDichVu.getMAPHIEUTDV());
            ResultSet rs = preparedStatementShow.executeQuery();

            while (rs.next()) {

                return rs.getInt("SONGAY")+1;

            }
        } catch (SQLException e) {
        }
        return Integer.MIN_VALUE;
    }
    public Integer queryTongTienDVByHD(HoaDon hd) {

        String sqlQuery = "SELECT TIENTDV FROM PHIEUTHUEDICHVU WHERE MAPHIEUTDV =? " ;
        try {
            PreparedStatement preparedStatementShow = this.connection.prepareStatement(sqlQuery);
            preparedStatementShow.setString(1,hd.getMAPHIEUTDV());

            ResultSet rs = preparedStatementShow.executeQuery();

            while (rs.next()) {

                return rs.getInt("TIENTDV");

            }
        } catch (SQLException e) {
        }

        return 0;
    }
    public boolean updateDatabase(DichVu dv) {
        try {

            String query = "UPDATE DICHVU SET TENDV =?, GIADV =? ";

            query+= " WHERE MADV =?";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1,dv.getTENDV());
            ps.setInt(2,dv.getGIADV());
            ps.setString(3,dv.getMADV());
            return (ps.executeUpdate() > 0);

        } catch (SQLException ex) {
            Logger.getLogger(DichVu.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ArrayList<DichVu> queryByDichVu(DichVu dv) {

        boolean preNode=false;
        ArrayList<DichVu> list=new ArrayList<>();
        String sqlQuery =
                "SELECT * from DICHVU " +
                        "where ";

        if (dv.getMADV()!=null) {
            sqlQuery += "MADV LIKE ('%'||'" + String.valueOf(dv.getMADV()) + "'||'%') ";
            preNode = true;
        }
        if (dv.getTENDV()!=null && !dv.getTENDV().isEmpty()) {
            if (preNode==true) sqlQuery+=" AND ";
            sqlQuery += " TENDV LIKE ('%'||'" + dv.getTENDV() + "'||'%') ";
            preNode = true;
        }
        if (dv.getGIADV()!=null && dv.getGIADV()!=Integer.MIN_VALUE ) {
            if (preNode==true) sqlQuery+=" AND ";
            sqlQuery += "GIADV  LIKE ('%'||'" + String.valueOf(dv.getGIADV()) + "'||'%') ";
            preNode = true;
        }
        sqlQuery+=" ORDER BY MADV";
        System.out.println("query: "+sqlQuery);
        try {
            PreparedStatement preparedStatementShow = connection.prepareStatement(sqlQuery);


            ResultSet rs = preparedStatementShow.executeQuery();

            while (rs.next()) {

                String madv = rs.getString("MADV");
                String tendv = rs.getString("TENDV");
                Integer giadv = rs.getInt("GIADV");
                DichVu dvTemp = new DichVu(madv, tendv, giadv);
                list.add(dvTemp);

            }
        } catch (SQLException e) {
        }
        return list;
    }


    public DichVuDAO() {
        setConnection();
    }

    public boolean setConnection()
    {
        this.connection= Database.getConnection();
        if (connection==null) {
            JOptionPane.showMessageDialog(null, "Can not connect to database.");
            System.exit(1);
            return false;
        }
        return true;
    }
}
