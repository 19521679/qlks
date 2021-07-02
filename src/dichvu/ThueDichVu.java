package dichvu;


import java.util.Date;

public class ThueDichVu {


    private String MADV;
    private String MAPHIEUTDV;
    private Date NGAYBD;
    private Date NGAYKT;
    private String GHICHU;

    @Override
    public String toString() {
        return "ThueDichVu{" +
                "MADV='" + MADV + '\'' +
                ", MAPHIEUTDV='" + MAPHIEUTDV + '\'' +
                ", NGAYBD=" + NGAYBD +
                ", NGAYKT=" + NGAYKT +
                ", GHICHU='" + GHICHU + '\'' +
                ", TIEN=" + TIEN +
                '}';
    }

    public String getMADV() {
        return MADV;
    }

    public void setMADV(String MADV) {
        this.MADV = MADV;
    }

    public String getMAPHIEUTDV() {
        return MAPHIEUTDV;
    }

    public void setMAPHIEUTDV(String MAPHIEUTDV) {
        this.MAPHIEUTDV = MAPHIEUTDV;
    }

    public Date getNGAYBD() {
        return NGAYBD;
    }

    public void setNGAYBD(Date NGAYBD) {
        this.NGAYBD = NGAYBD;
    }

    public Date getNGAYKT() {
        return NGAYKT;
    }

    public void setNGAYKT(Date NGAYKT) {
        this.NGAYKT = NGAYKT;
    }

    public String getGHICHU() {
        return GHICHU;
    }

    public void setGHICHU(String GHICHU) {
        this.GHICHU = GHICHU;
    }

    public Integer getTIEN() {
        return TIEN;
    }

    public void setTIEN(Integer TIEN) {
        this.TIEN = TIEN;
    }

    public ThueDichVu() {
    }

    public ThueDichVu(String MADV, String MAPHIEUTDV, Date NGAYBD, Date NGAYKT, String GHICHU, Integer TIEN) {
        this.MADV = MADV;
        this.MAPHIEUTDV = MAPHIEUTDV;
        this.NGAYBD = NGAYBD;
        this.NGAYKT = NGAYKT;
        this.GHICHU = GHICHU;
        this.TIEN = TIEN;
    }

    private Integer TIEN;

}
