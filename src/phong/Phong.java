package phong;

public class Phong {
    private String MAPH;
    private String MALOAIPH;
    private String TINHTRANG;
    private String GHICHU;

    public Phong(String MAPH, String MALOAIPH, String TINHTRANG, String GHICHU) {
        this.MAPH = MAPH;
        this.MALOAIPH = MALOAIPH;
        this.TINHTRANG = TINHTRANG;
        this.GHICHU = GHICHU;
    }

    public Phong() {
    }

    @Override
    public String toString() {
        return "Phong{" +
                "MAPH='" + MAPH + '\'' +
                ", MALOAIPH='" + MALOAIPH + '\'' +
                ", TINHTRANG='" + TINHTRANG + '\'' +
                ", GHICHU='" + GHICHU + '\'' +
                '}';
    }

    public String getMAPH() {
        return MAPH;
    }

    public void setMAPH(String MAPH) {
        this.MAPH = MAPH;
    }

    public String getMALOAIPH() {
        return MALOAIPH;
    }

    public void setMALOAIPH(String MALOAIPH) {
        this.MALOAIPH = MALOAIPH;
    }

    public String getTINHTRANG() {
        return TINHTRANG;
    }

    public void setTINHTRANG(String TINHTRANG) {
        this.TINHTRANG = TINHTRANG;
    }

    public String getGHICHU() {
        return GHICHU;
    }

    public void setGHICHU(String GHICHU) {
        this.GHICHU = GHICHU;
    }
}
