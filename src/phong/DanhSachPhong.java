package phong;

public class DanhSachPhong {
    private String MALOAIPH;
    private String TENLOAIPH;
    private Integer SOLUONGPH;
    private Integer DONGIA;
    private Integer SONGUOI;
    private String GHICHU;

    public DanhSachPhong() {
    }

    @Override
    public String toString() {
        return "DanhSachPhong{" +
                "MALOAIPH='" + MALOAIPH + '\'' +
                ", TENLOAIPH='" + TENLOAIPH + '\'' +
                ", SOLUONGPH=" + SOLUONGPH +
                ", DONGIA=" + DONGIA +
                ", SONGUOI=" + SONGUOI +
                ", GHICHU='" + GHICHU + '\'' +
                '}';
    }

    public DanhSachPhong(String MALOAIPH, String TENLOAIPH, Integer SOLUONGPH, Integer DONGIA, Integer SONGUOI, String GHICHU) {
        this.MALOAIPH = MALOAIPH;
        this.TENLOAIPH = TENLOAIPH;
        this.SOLUONGPH = SOLUONGPH;
        this.DONGIA = DONGIA;
        this.SONGUOI = SONGUOI;
        this.GHICHU = GHICHU;
    }

    public String getMALOAIPH() {
        return MALOAIPH;
    }

    public void setMALOAIPH(String MALOAIPH) {
        this.MALOAIPH = MALOAIPH;
    }

    public String getTENLOAIPH() {
        return TENLOAIPH;
    }

    public void setTENLOAIPH(String TENLOAIPH) {
        this.TENLOAIPH = TENLOAIPH;
    }

    public Integer getSOLUONGPH() {
        return SOLUONGPH;
    }

    public void setSOLUONGPH(Integer SOLUONGPH) {
        this.SOLUONGPH = SOLUONGPH;
    }

    public Integer getDONGIA() {
        return DONGIA;
    }

    public void setDONGIA(Integer DONGIA) {
        this.DONGIA = DONGIA;
    }

    public Integer getSONGUOI() {
        return SONGUOI;
    }

    public void setSONGUOI(Integer SONGUOI) {
        this.SONGUOI = SONGUOI;
    }

    public String getGHICHU() {
        return GHICHU;
    }

    public void setGHICHU(String GHICHU) {
        this.GHICHU = GHICHU;
    }
}
