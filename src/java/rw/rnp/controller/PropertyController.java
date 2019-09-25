package rw.rnp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;
import rw.rnp.entity.Categories;
import rw.rnp.entity.Properties;
import rw.rnp.entity.Users;
import rw.rnp.model.Properties_dao;

/**
 *
 * @author Habineza Olivier
 */

@ManagedBean(name = "prptsContrl")
@SessionScoped
public class PropertyController implements Serializable{

    private List<Properties> prptz = new ArrayList<>();
    private List<Properties> reprptz = new ArrayList<>();
    private Properties prpts = new Properties();
    private UploadedFile file;
    private int catId;
    private String displayCategory;
    private int numberOfSeizedStolenProperty;
    private int numberOfReportedStolenProperty;

   
    public Categories selectCorrespondingCategory() {
        return Properties_dao.getCategoryById(catId);
    }

    public Properties selectCorrespProp(int id) {
        return Properties_dao.getIdentifiedPropertyList(id);
    }

    public String electronics() {
        setDisplayCategory("Electronics");

        return "homePage";
    }

    public String furnitures() {
        setDisplayCategory("Furnitures");

        return "homePage";
    }

    public String vehicles() {
        setDisplayCategory("Vehicles");

        return "homePage";
    }

    public String others() {
        setDisplayCategory("Others");

        return "homePage";
    }

    public String allProperties() {
        setDisplayCategory("All");
        return "homePage";
    }

    public List<Properties> getPrptz() {

        if (displayCategory == "Electronics") {
            setPrptz(Properties_dao.getElectronicsList());

        } else if (displayCategory == "Furnitures") {
            setPrptz(Properties_dao.getFurnituresList());

        } else if (displayCategory == "Vehicles") {
            setPrptz(Properties_dao.getVehiclesList());

        } else if (displayCategory == "Others") {
            setPrptz(Properties_dao.getOthersList());

        } else {
            setPrptz(Properties_dao.getPropertyList());

        }
        return this.prptz;
    }

    public void setPrptz(List<Properties> prptz) {
        this.prptz = prptz;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Properties getPrpts() {
        return prpts;
    }

    public void setPrpts(Properties prpts) {

        this.prpts = prpts;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getDisplayCategory() {
        return displayCategory;
    }

    public void setDisplayCategory(String displayCategory) {
        this.displayCategory = displayCategory;
    }

    public List<Properties> getReprptz() {
        return Properties_dao.getReportedPropertyList();

    }

    public void setReprptz(List<Properties> reprptz) {
        this.reprptz = reprptz;
    }

    public int getNumberOfSeizedStolenProperty() {
        return Properties_dao.getNumberOfSeizedProperties();
    }

    public void setNumberOfSeizedStolenProperty(int numberOfSeizedStolenProperty) {
        this.numberOfSeizedStolenProperty = numberOfSeizedStolenProperty;
    }

    public int getNumberOfReportedStolenProperty() {
        return Properties_dao.getNumberOfReportedProperties();
    }

    public void setNumberOfReportedStolenProperty(int numberOfReportedStolenProperty) {
        this.numberOfReportedStolenProperty = numberOfReportedStolenProperty;
    }
    
    

    public String insert() {

        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        Users user = (Users) sessionMap.get("usersession");

        String propertyStatus = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("propertyStatus");

        prpts.setUserID(user.getUserID());
        prpts.setSectorID(user.getAddress());
        prpts.setPropertyRecordedDate(new Date());
        prpts.setPropertyStatus(propertyStatus);

        try {
//            if (file.getSize() != 0) {
//                System.out.println("***************Create with picture***********");
//                Properties_dao.create(prpts, file);
//
//            } else {
//                System.out.println("***************Create without picture***********");
//                Properties_dao.create(prpts);
//
//            }
//            FacesContext context = FacesContext.getCurrentInstance();
//            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "New Property Added Successfully"));

//            switch (propertyStatus) {
//            case "reported":
//                System.out.println("---------------------------" + propertyStatus);
//                return "pMsgReportProperty";
//            case "Seized":
//                return "vPropertyTemp";
//        }
            
            if("reported".equalsIgnoreCase(propertyStatus)){
                 if (file.getSize() != 0) {
                System.out.println("***************Create with picture*****In reported******");
                Properties_dao.create(prpts, file);

            } else {
                System.out.println("***************Create without picture*******In reported****");
                Properties_dao.create(prpts);

            }
                return "pMsgReportProperty";
            }else if("Seized".equalsIgnoreCase(propertyStatus)){
                 if (file.getSize() != 0) {
                System.out.println("***************Create with picture********In seized***");
                Properties_dao.create(prpts, file);

            } else {
                System.out.println("***************Create without picture******In seized*****");
                Properties_dao.create(prpts);

            }
                return "vPropertyTemp";
            }else{
                System.out.println("**It should not reach here***");
               return "";
            }
            
        } catch (Exception e) {
            System.out.println(e);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fail", "New property Failed to be saved"));
    //      context.addMessage(null, new FacesMessage("Second Message", "Additional Message Detail"));
            return"";
        }
       
    }

    public String edit(Properties prptz) {
        
        this.prpts = prptz;
        return "editPropertyTemp"; 
    }
    public String editReported(Properties prptz) {
        
        this.prpts = prptz;
        return "vREpProperty"; 
    }

    public String save() {
        if (file.getSize() != 0) {
        prpts.setPropertyRecordedDate(new Date());
        Properties_dao.edit(prpts, file);
        return "vPropertyTemp";
        }else{
        prpts.setPropertyRecordedDate(new Date());
        Properties_dao.edit(prpts);
        return "vPropertyTemp";
        }
        
    }
    public String saveReported() {
        if (file.getSize() != 0) {
        prpts.setPropertyRecordedDate(new Date());
        Properties_dao.edit(prpts, file);
        return "vREpProperty";
        }else{
        prpts.setPropertyRecordedDate(new Date());
        Properties_dao.edit(prpts);
        return "vREpProperty";
        }
        
    }

    public String remove(Properties prptz) {
        Properties_dao.remove(prptz);
        return "vPropertyTemp";
    }
    public String removeReported(Properties prptz) {
        Properties_dao.remove(prptz);
        return "vREpProperty";
    }

    public void destroyWorld() {
        addMessage("System Error", "Please try again later.");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    //Menu
    public void savem() {
        addMessage("Success", "Data saved");
    }

    public void update() {
        addMessage("Success", "Data updated");
    }

    public void delete() {
        addMessage("Success", "Data deleted");
    }

    public void addMessagem(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String callClaimForm() {
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("prptsession", prpts);

        Map<String, Object> sMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sMap.put("categorysession", Properties_dao.getCategoryById(prpts.getCategoryID()));

        Map<String, Object> sessMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        String status = "claim";
        sessMap.put("claimingformsession", status);

        System.out.println(prpts);

        return "log";
    }

    public String getCategDescription() {

        return "";
    }
}
