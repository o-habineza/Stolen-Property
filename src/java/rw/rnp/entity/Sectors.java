
package rw.rnp.entity;

import java.io.Serializable;

/**
 *
 * @author Habineza Olivier
 */

public class Sectors implements Serializable{
    
    private int sectorID;
    private String sectorName;
    private int districtID;

    public Sectors(int sectorID, String sectorName, int districtID) {
        this.sectorID = sectorID;
        this.sectorName = sectorName;
        this.districtID = districtID;
    }

    public Sectors() {
    }
    
    public int getSectorID() {
        return sectorID;
    }

    public void setSectorID(int sectorID) {
        this.sectorID = sectorID;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public int getDistrictID() {
        return districtID;
    }

    public void setDistrictID(int districtID) {
        this.districtID = districtID;
    }

    @Override
    public String toString() {
        return "Sectors{" + "sectorID=" + sectorID + ", sectorName=" + sectorName + ", districtID=" + districtID + '}';
    }
   
}
