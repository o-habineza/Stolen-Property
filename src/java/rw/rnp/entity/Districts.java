
package rw.rnp.entity;

import java.io.Serializable;


/**
 *
 * @author Habineza Olivier
 */

public class Districts implements Serializable{
    
   private Integer districtID;
   private String districtName;
   private Integer provinceID;

    public Districts(Integer districtID, String districtName, Integer provinceID) {
        this.districtID = districtID;
        this.districtName = districtName;
        this.provinceID = provinceID;
    }

    public Districts() {
    }

    public Integer getDistrictID() {
        return districtID;
    }

    public void setDistrictID(Integer districtID) {
        this.districtID = districtID;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Integer getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(Integer provinceID) {
        this.provinceID = provinceID;
    }

    @Override
    public String toString() {
        return "Districts{" + "districtID=" + districtID + ", districtName=" + districtName + ", provinceID=" + provinceID + '}';
    }
    
    
}
