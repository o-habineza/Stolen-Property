
package rw.rnp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import rw.rnp.entity.Sectors;
import rw.rnp.model.Sectors_dao;

/**
 *
 * @author Habineza Olivier
 */

@ManagedBean(name="sectContrl")
@SessionScoped
public class SectorController implements Serializable{
    
    private List<Sectors> sectorNames = new ArrayList<>();
    private int subDistrictId;
    
     public void loadedSectors() {
        //Loads the sectors depending on the selected distric       
        sectorNames.clear();
        sectorNames = loadSectors();
    }

    public List<Sectors> loadSectors() {        
        return Sectors_dao.getSectorsByDistrict(subDistrictId);
    }
    
    public String selectUserLocation(int idLocation) {
        return Sectors_dao.getUserLocation(idLocation);
    }
    
    
    public void setSectorNames(List<Sectors> sectorNames) {
        this.sectorNames = sectorNames;
    }

    public List<Sectors> getSectorNames() {
        return sectorNames;
    }

    public int getSubDistrictId() {
        return subDistrictId;
    }

    public void setSubDistrictId(int subDistrictId) {
        this.subDistrictId = subDistrictId;
    }
    
}
