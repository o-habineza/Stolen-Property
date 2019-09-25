package rw.rnp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import rw.rnp.entity.Districts;
import rw.rnp.model.Districts_dao;

/**
 *
 * @author Habineza Olivier
 */

@ManagedBean(name = "distContrl")
@SessionScoped
public class DistrictController implements Serializable{

    private List<Districts> districtNames = new ArrayList<>();
    private int subProvinceId;

    public void loadedDistricts() {
        //Loads the districs depending on the selected province        
        districtNames.clear();        
        districtNames = loadDistricts();
    }

    public List<Districts> loadDistricts() {        
        return Districts_dao.getDistrictsbyProvince(subProvinceId);
    }

    public List<Districts> getDistrictNames() {
        return districtNames;
    }

    public void setDistrictNames(List<Districts> districtNames) {
        this.districtNames = districtNames;
    }

    public int getSubProvinceId() {
        return subProvinceId;
    }

    public void setSubProvinceId(int subProvinceId) {
        this.subProvinceId = subProvinceId;
    }

}
