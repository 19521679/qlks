package taikhoan;

import java.util.Date;

public class TaiKhoan {
    String USERNAME;
    String MANV;
    String PASSWORD;
    String TRANGTHAI;
    Date TGDNGN;
    String PC;

    public String getUSERNAME() {
        return USERNAME;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" +
                "USERNAME='" + USERNAME + '\'' +
                ", MANV='" + MANV + '\'' +
                ", PASSWORD='" + PASSWORD + '\'' +
                ", TRANGTHAI='" + TRANGTHAI + '\'' +
                ", TGDNGN=" + TGDNGN +
                ", PC='" + PC + '\'' +
                '}';
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getMANV() {
        return MANV;
    }

    public void setMANV(String MANV) {
        this.MANV = MANV;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getTRANGTHAI() {
        return TRANGTHAI;
    }

    public void setTRANGTHAI(String TRANGTHAI) {
        this.TRANGTHAI = TRANGTHAI;
    }

    public Date getTGDNGN() {
        return TGDNGN;
    }

    public void setTGDNGN(Date TGDNGN) {
        this.TGDNGN = TGDNGN;
    }

    public String getPC() {
        return PC;
    }

    public void setPC(String PC) {
        this.PC = PC;
    }

    public TaiKhoan() {
    }

    public TaiKhoan(String USERNAME, String MANV, String PASSWORD, String TRANGTHAI, Date TGDNGN, String PC) {
        this.USERNAME = USERNAME;
        this.MANV = MANV;
        this.PASSWORD = PASSWORD;
        this.TRANGTHAI = TRANGTHAI;
        this.TGDNGN = TGDNGN;
        this.PC = PC;
    }
}
