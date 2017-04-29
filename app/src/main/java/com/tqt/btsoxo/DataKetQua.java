package com.tqt.btsoxo;

/**
 * Created by Quyen Hua on 4/28/2017.
 */

public class DataKetQua {

    private String level;
    private String giatri;

    public DataKetQua(String level, String giatri) {
        this.level = level;
        this.giatri = giatri;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getGiatri() {
        return giatri;
    }

    public void setGiatri(String giatri) {
        this.giatri = giatri;
    }
}
