package models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "air_pollution")
public class AirPollution {

    @PrimaryKey
    private int air_pollution_id;

    @ColumnInfo(name = "aqi")
    private String aqi;
    @ColumnInfo(name = "co")
    private String co;
    @ColumnInfo(name = "no")
    private String no;
    @ColumnInfo(name = "no_2")
    private String noTwo;
    @ColumnInfo(name = "o_3")
    private String oThree;
    @ColumnInfo(name = "so_2")
    private String soTwo;
    @ColumnInfo(name = "pm_2_5")
    private String pmTwoPFive;
    @ColumnInfo(name = "pm_10")
    private String pmTen;
    @ColumnInfo(name = "nh_3")
    private String nhThree;

    public AirPollution(int air_pollution_id, String aqi, String co, String no, String noTwo, String oThree, String soTwo, String pmTwoPFive, String pmTen, String nhThree) {
        this.air_pollution_id = air_pollution_id;
        this.aqi = aqi;
        this.co = co;
        this.no = no;
        this.noTwo = noTwo;
        this.oThree = oThree;
        this.soTwo = soTwo;
        this.pmTwoPFive = pmTwoPFive;
        this.pmTen = pmTen;
        this.nhThree = nhThree;
    }

    @Ignore
    public AirPollution() {
    }


    public int getAir_pollution_id() {
        return air_pollution_id;
    }

    public void setAir_pollution_id(int air_pollution_id) {
        this.air_pollution_id = air_pollution_id;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getNoTwo() {
        return noTwo;
    }

    public void setNoTwo(String noTwo) {
        this.noTwo = noTwo;
    }

    public String getOThree() {
        return oThree;
    }

    public void setOThree(String oThree) {
        this.oThree = oThree;
    }

    public String getSoTwo() {
        return soTwo;
    }

    public void setSoTwo(String soTwo) {
        this.soTwo = soTwo;
    }

    public String getPmTwoPFive() {
        return pmTwoPFive;
    }

    public void setPmTwoPFive(String pmTwoPFive) {
        this.pmTwoPFive = pmTwoPFive;
    }

    public String getPmTen() {
        return pmTen;
    }

    public void setPmTen(String pmTen) {
        this.pmTen = pmTen;
    }

    public String getNhThree() {
        return nhThree;
    }

    public void setNhThree(String nhThree) {
        this.nhThree = nhThree;
    }
}
