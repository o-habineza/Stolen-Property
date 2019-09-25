package rw.rnp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import rw.rnp.entity.Provinces;
import rw.rnp.model.Province_dao;

/**
 *
 * @author Habineza Olivier
 */

@ManagedBean(name = "provContrl")
@SessionScoped
public class ProvinceController implements Serializable{


    private List<String> provinceNames = new ArrayList<>();

    
    public List<Provinces> getAllProvinces() {
        provinceNames.clear();        
        return Province_dao.getProvinces();
    }

    public void setProvinceNames(List<String> provinceNames) {
        this.provinceNames = provinceNames;
    }
    

}
