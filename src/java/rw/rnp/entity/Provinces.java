
package rw.rnp.entity;

import java.io.Serializable;

/**
 *
 * @author Habineza Olivier
 */

public class Provinces implements Serializable{
    
    private Integer provinceID;
    private String provinceName;

    public Provinces(Integer provinceID, String provinceName) {
        this.provinceID = provinceID;
        this.provinceName = provinceName;
    }

    public Provinces() {
    }

    public Integer getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(Integer provinceID) {
        this.provinceID = provinceID;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    @Override
    public String toString() {
        return "Provinces{" + "provinceID=" + provinceID + ", provinceName=" + provinceName + '}';
    }
    
    
}
