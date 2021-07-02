package khachhang;

import java.util.Date;

public class KhachHang {
    public String MAKH;
    public java.lang.String TENKH;
    public String CMND;
    public java.lang.String QUOCTICH;
    public java.util.Date NGSINH;

    public String SDT;

    public java.lang.String DIACHI;

    public KhachHang() {
    }

    public java.lang.String LOAIKH;

    @Override
    public String toString() {
        return "KhachHang{" +
                "MAKH='" + MAKH + '\'' +
                ", TENKH='" + TENKH + '\'' +
                ", CMND='" + CMND + '\'' +
                ", QUOCTICH='" + QUOCTICH + '\'' +
                ", NGSINH=" + NGSINH +
                ", SDT='" + SDT + '\'' +
                ", DIACHI='" + DIACHI + '\'' +
                ", LOAIKH='" + LOAIKH + '\'' +
                '}';
    }

    public KhachHang(String MAKH, String TENKH, String CMND, String QUOCTICH, Date NGSINH, String SDT, String DIACHI, String LOAIKH) {
        this.MAKH = MAKH;
        this.TENKH = TENKH;
        this.CMND = CMND;
        this.QUOCTICH = QUOCTICH;
        this.NGSINH = NGSINH;
        this.SDT = SDT;
        this.DIACHI = DIACHI;
        this.LOAIKH = LOAIKH;
    }

    public String getMAKH() {
        return MAKH;
    }

    public void setMAKH(String MAKH) {
        this.MAKH = MAKH;
    }

    public String getTENKH() {
        return TENKH;
    }

    public void setTENKH(String TENKH) {
        this.TENKH = TENKH;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getQUOCTICH() {
        return QUOCTICH;
    }

    public void setQUOCTICH(String QUOCTICH) {
        this.QUOCTICH = QUOCTICH;
    }

    public Date getNGSINH() {
        return NGSINH;
    }

    public void setNGSINH(Date NGSINH) {
        this.NGSINH = NGSINH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDIACHI() {
        return DIACHI;
    }

    public void setDIACHI(String DIACHI) {
        this.DIACHI = DIACHI;
    }

    public String getLOAIKH() {
        return LOAIKH;
    }

    public void setLOAIKH(String LOAIKH) {
        this.LOAIKH = LOAIKH;
    }
}
