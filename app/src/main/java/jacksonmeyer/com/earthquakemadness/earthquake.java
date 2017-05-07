package jacksonmeyer.com.earthquakemadness;

/**
 * Created by jacksonmeyer on 5/6/17.
 */

public class Earthquake {
    String datetime;
    Integer depth;
    Integer lng;
    String src;
    String eqid;
    Integer magnitude;
    Integer lat;

    public Earthquake(String datetime, Integer depth, Integer lng, String src, String eqid, Integer magnitude, Integer lat) {
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

    public Integer getLng() {
        return lng;
    }

    public String src() {
        return src;
    }

    public String getEqid() {
        return eqid;
    }

    public Integer getMagnitude() {
        return magnitude;
    }

    public Integer getLat() {
        return lat;
    }

}
