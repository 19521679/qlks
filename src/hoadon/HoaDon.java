package hoadon;


import java.util.Date;

public class HoaDon {
    /*SOHD                 INTEGER              NOT NULL,
    MAPHIEUTP            INTEGER              NOT NULL,
    MAPHIEUTDV           INTEGER,
    MANV                 INTEGER              NOT NULL,
    MAKM                 INTEGER,
    MAKH                 INTEGER              NOT NULL,
    NGAYHD               DATE,
    THANHTIEN            NUMBER(8,2),
    CONSTRAINT PK_HOADON PRIMARY KEY (SOHD)*/
    private String SOHD;
    private String MAPHIEUTP;
    private String MAPHIEUTDV;
    private String MANV;
    private String MAKM;
    private String MAKH;
    private java.util.Date NGAYHD;
    private Integer THANHTIEN;

    @Override
    public String toString() {
        return "HoaDon{" +
                "SOHD='" + SOHD + '\'' +
                ", MAPHIEUTP='" + MAPHIEUTP + '\'' +
                ", MAPHIEUTDV='" + MAPHIEUTDV + '\'' +
                ", MANV='" + MANV + '\'' +
                ", MAKM='" + MAKM + '\'' +
                ", MAKH='" + MAKH + '\'' +
                ", NGAYHD=" + NGAYHD +
                ", THANHTIEN=" + THANHTIEN +
                '}';
    }

    public HoaDon(String SOHD, String MAPHIEUTP, String MAPHIEUTDV, String MANV, String MAKM, String MAKH, Date NGAYHD, Integer THANHTIEN) {
        this.SOHD = SOHD;
        this.MAPHIEUTP = MAPHIEUTP;
        this.MAPHIEUTDV = MAPHIEUTDV;
        this.MANV = MANV;
        this.MAKM = MAKM;
        this.MAKH = MAKH;
        this.NGAYHD = NGAYHD;
        this.THANHTIEN = THANHTIEN;
    }

    public String getSOHD() {
        return SOHD;
    }

    public void setSOHD(String SOHD) {
        this.SOHD = SOHD;
    }

    public String getMAPHIEUTP() {
        return MAPHIEUTP;
    }

    public void setMAPHIEUTP(String MAPHIEUTP) {
        this.MAPHIEUTP = MAPHIEUTP;
    }

    public String getMAPHIEUTDV() {
        return MAPHIEUTDV;
    }

    public void setMAPHIEUTDV(String MAPHIEUTDV) {
        this.MAPHIEUTDV = MAPHIEUTDV;
    }

    public String getMANV() {
        return MANV;
    }

    public void setMANV(String MANV) {
        this.MANV = MANV;
    }

    public String getMAKM() {
        return MAKM;
    }

    public void setMAKM(String MAKM) {
        this.MAKM = MAKM;
    }

    public String getMAKH() {
        return MAKH;
    }

    public void setMAKH(String MAKH) {
        this.MAKH = MAKH;
    }

    public Date getNGAYHD() {
        return NGAYHD;
    }

    public void setNGAYHD(Date NGAYHD) {
        this.NGAYHD = NGAYHD;
    }

    public Integer getTHANHTIEN() {
        return THANHTIEN;
    }

    public void setTHANHTIEN(Integer THANHTIEN) {
        this.THANHTIEN = THANHTIEN;
    }
}
