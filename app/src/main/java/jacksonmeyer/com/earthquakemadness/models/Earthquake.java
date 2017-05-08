package jacksonmeyer.com.earthquakemadness.models;

/**
 * Created by jacksonmeyer on 5/6/17.
 */

public class Earthquake {
    String datetime;
    Integer depth;
    Double lng;
    String src;
    String eqid;
    Double magnitude;
    Double lat;

    public Earthquake(String datetime, Integer depth, Double lng, String src, String eqid, Double magnitude, Double lat) {
        this.datetime = datetime;
        this.depth = depth;
        this.lng = lng;
        this.src = src;
        this.eqid = eqid;
        this.magnitude = magnitude;
        this.lat = lat;
    }

    public String getDatetime() {
        return datetime;
    }

    public Integer getDepth() {
        return depth;
    }

    public Double getLng() {
        return lng;
    }

    public String getSrc() {
        return src;
    }

    public String getEqid() {
        return eqid;
    }

    public Double getMagnitude() {
        return magnitude;
    }

    public Double getLat() {
        return lat;
    }

}
