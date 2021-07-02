package khuyenmai;

import java.util.Date;

public class KhuyenMai {
    String MAKM;
    String TENKM;
    String MOTA;
    Float TILE;
    Date NGBD;
    Date NGKT;

    @Override
    public String toString() {
        return "KhuyenMai{" +
                "MAKM='" + MAKM + '\'' +
                ", TENKM='" + TENKM + '\'' +
                ", MOTA='" + MOTA + '\'' +
                ", TILE=" + TILE +
                ", NGBD=" + NGBD +
                ", NGKT=" + NGKT +
                '}';
    }

    public String getMAKM() {
        return MAKM;
    }

    public void setMAKM(String MAKM) {
        this.MAKM = MAKM;
    }

    public String getTENKM() {
        return TENKM;
    }

    public void setTENKM(String TENKM) {
        this.TENKM = TENKM;
    }

    public String getMOTA() {
        return MOTA;
    }

    public void setMOTA(String MOTA) {
        this.MOTA = MOTA;
    }

    public Float getTILE() {
        return TILE;
    }

    public void setTILE(Float TILE) {
        this.TILE = TILE;
    }

    public Date getNGBD() {
        return NGBD;
    }

    public void setNGBD(Date NGBD) {
        this.NGBD = NGBD;
    }

    public Date getNGKT() {
        return NGKT;
    }

    public void setNGKT(Date NGKT) {
        this.NGKT = NGKT;
    }

    public KhuyenMai() {
    }

    public KhuyenMai(String MAKM, String TENKM, String MOTA, Float TILE, Date NGBD, Date NGKT) {
        this.MAKM = MAKM;
        this.TENKM = TENKM;
        this.MOTA = MOTA;
        this.TILE = TILE;
        this.NGBD = NGBD;
        this.NGKT = NGKT;
    }
}
