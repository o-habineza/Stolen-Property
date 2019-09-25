package rw.rnp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import rw.rnp.config.HibernateUtil;
import rw.rnp.entity.Identifies;
import rw.rnp.entity.Users;
import rw.rnp.model.Identifies_dao;

/**
 *
 * @author Habineza Olivier
 */

@ManagedBean(name = "identContrl")
@SessionScoped
public class IdentifyController implements Serializable{

    //User to be set on view form
    private List<Identifies> ident = new ArrayList<>();
    //New instance where we do insert, update and remove
    private Identifies identifies = new Identifies();
    private int numberOfIdentifiedStolenProperty;
   

    public List<Identifies> getIdent() {
        return Identifies_dao.getIdentifiedProperties();
    }

    public void setIdent(List<Identifies> ident) {
        this.ident = ident;
    }

    public Identifies getIdentifies() {
        return identifies;
    }

    public void setIdentifies(Identifies identifies) {
        this.identifies = identifies;
    }

    public int getNumberOfIdentifiedStolenProperty() {
        return Identifies_dao.getNumberOfIdentifiedProperties();
    }

    public void setNumberOfIdentifiedStolenProperty(int numberOfIdentifiedStolenProperty) {
        this.numberOfIdentifiedStolenProperty = numberOfIdentifiedStolenProperty;
    }

    
    
    public String insert() {


        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        Users user = (Users) sessionMap.get("usersession");

        String propertyID = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("propertyID");
        System.out.println("==============================================================" + " " + user.getUserID());
        identifies.setUserID(user.getUserID());
        identifies.setPropertyID(Integer.parseInt(propertyID));

        try {

            Identifies_dao.create(identifies);
           
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Your claim has been submited"));
            
            return"pMsgClaimingForm";

        } catch (Exception e) {

            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fail", "Your claim has not been submited"));
//      context.addMessage(null, new FacesMessage("Second Message", "Additional Message Detail"));

        }
        
        HttpSession hs = HibernateUtil.getSession();
        hs.invalidate();
        
       return"pMsgClaimingForm";
    }

    public String edit(Identifies ident) {
        this.identifies = ident;
        return "editClaims";
    }

    public String save() {
        Identifies_dao.edit(identifies);
        return "vClaims";
    }

    public String remove(Identifies ident) {
        Identifies_dao.remove(ident);
        return "vClaims";
    }
}
