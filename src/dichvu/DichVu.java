package dichvu;

public class DichVu {
/*MADV                 INTEGER              NOT NULL,
   TENDV                VARCHAR2(40),
   GIADV                INTEGER,
   CONSTRAINT PK_DICHVU PRIMARY KEY (MADV)*/
    private String MADV;
    private String TENDV;
    private Integer GIADV;

    public DichVu() {
    }

    public DichVu(String MADV, String TENDV) {
        this.MADV = MADV;
        this.TENDV = TENDV;
    }

    public DichVu(String MADV, String TENDV, Integer GIADV) {
        this.MADV = MADV;
        this.TENDV = TENDV;
        this.GIADV = GIADV;
    }

    @Override
    public String toString() {
        return "DichVu{" +
                "MADV='" + MADV + '\'' +
                ", TENDV='" + TENDV + '\'' +
                ", GIADV=" + GIADV +
                '}';
    }

    public String getMADV() {
        return MADV;
    }

    public void setMADV(String MADV) {
        this.MADV = MADV;
    }

    public String getTENDV() {
        return TENDV;
    }

    public void setTENDV(String TENDV) {
        this.TENDV = TENDV;
    }

    public Integer getGIADV() {
        return GIADV;
    }

    public void setGIADV(Integer GIADV) {
        this.GIADV = GIADV;
    }
}
