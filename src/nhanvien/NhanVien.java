package nhanvien;

import java.util.Date;

public class NhanVien {
    private String MANV;
    private String MAQL;
    private String HOTEN;
    private String GIOITINH;
    private String DCHI;
    private String SODT;
    private Date NGSINH;
    private Date NGVL;
    private Integer LUONG;

    public String getMANV() {
        return MANV;
    }

    public void setMANV(String MANV) {
        this.MANV = MANV;
    }

    public String getMAQL() {
        return MAQL;
    }

    public NhanVien(String MANV) {
        this.MANV = MANV;
    }

    public NhanVien() {
    }

    public void setMAQL(String MAQL) {
        this.MAQL = MAQL;
    }

    public String getHOTEN() {
        return HOTEN;
    }

    public void setHOTEN(String HOTEN) {
        this.HOTEN = HOTEN;
    }

    public String getGIOITINH() {
        return GIOITINH;
    }

    public void setGIOITINH(String GIOITINH) {
        this.GIOITINH = GIOITINH;
    }

    public String getDCHI() {
        return DCHI;
    }

    public void setDCHI(String DCHI) {
        this.DCHI = DCHI;
    }

    public String getSODT() {
        return SODT;
    }

    public void setSODT(String SODT) {
        this.SODT = SODT;
    }

    public Date getNGSINH() {
        return NGSINH;
    }

    public void setNGSINH(Date NGSINH) {
        this.NGSINH = NGSINH;
    }

    public Date getNGVL() {
        return NGVL;
    }

    public void setNGVL(Date NGVL) {
        this.NGVL = NGVL;
    }

    public Integer getLUONG() {
        return LUONG;
    }

    public void setLUONG(Integer LUONG) {
        this.LUONG = LUONG;
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "MANV='" + MANV + '\'' +
                ", MAQL='" + MAQL + '\'' +
                ", HOTEN='" + HOTEN + '\'' +
                ", GIOITINH='" + GIOITINH + '\'' +
                ", DCHI='" + DCHI + '\'' +
                ", SODT='" + SODT + '\'' +
                ", NGSINH=" + NGSINH +
                ", NGVL=" + NGVL +
                ", LUONG=" + LUONG +
                '}';
    }

    public NhanVien(String MANV, String MAQL, String HOTEN, String GIOITINH, String DCHI, String SODT, Date NGSINH, Date NGVL, Integer LUONG) {
        this.MANV = MANV;
        this.MAQL = MAQL;
        this.HOTEN = HOTEN;
        this.GIOITINH = GIOITINH;
        this.DCHI = DCHI;
        this.SODT = SODT;
        this.NGSINH = NGSINH;
        this.NGVL = NGVL;
        this.LUONG = LUONG;
    }
}
