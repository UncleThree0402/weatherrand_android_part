package models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "earthquake")
public class Earthquake {

    @PrimaryKey
    private long earthquakeId;

    @ColumnInfo(name = "report_colour")
    private String reportColour;
    @ColumnInfo(name = "report_content")
    private String reportContent;
    @ColumnInfo(name = "origin_time")
    private String originTime;
    @ColumnInfo(name = "depth")
    private Double depth;
    @ColumnInfo(name = "magnitude")
    private Double magnitude;

    public Earthquake(long earthquakeId, String reportColour, String reportContent, String originTime, Double depth, Double magnitude) {
        this.earthquakeId = earthquakeId;
        this.reportColour = reportColour;
        this.reportContent = reportContent;
        this.originTime = originTime;
        this.depth = depth;
        this.magnitude = magnitude;
    }

    @Ignore
    public Earthquake() {
    }

    public long getEarthquakeId() {
        return earthquakeId;
    }

    public void setEarthquakeId(long earthquakeId) {
        this.earthquakeId = earthquakeId;
    }

    public String getReportColour() {
        return reportColour;
    }

    public void setReportColour(String reportColour) {
        this.reportColour = reportColour;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public String getOriginTime() {
        return originTime;
    }

    public void setOriginTime(String originTime) {
        this.originTime = originTime;
    }

    public Double getDepth() {
        return depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public Double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Double magnitude) {
        this.magnitude = magnitude;
    }
}
