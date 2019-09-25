package rw.rnp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import rw.rnp.entity.Sectors;
import rw.rnp.entity.Users;
import rw.rnp.model.Users_dao;

/**
 *
 * @author Habineza Olivier
 */

@ManagedBean(name = "usrContrl")
@SessionScoped
public class UserController implements Serializable {

    //User to be set on view form
    private List<Users> usr = new ArrayList<>();
    //New instance where we do insert, update and remove
    private Users users = new Users();
    
    @PostConstruct
    public void init() {
    this.users = new Users();
  
    }

    public Users selectSpecificUser(int id) {
        return Users_dao.getSpecificUser(id);
    }

    public Sectors selectSpecificUserAddress(int sectorID) {
        return Users_dao.getSpecificUserAddress(sectorID);
    }

    public List<Users> getUsr() {
        return Users_dao.getUsers();
    }

    public void setUsr(List<Users> usr) {
        this.usr = usr;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String insert() {
        String status = "";
        String rStatus = "";
      
        users.setCreationDate(new Date());
        users.setUserRole("user");
       
        try {

            FacesContext context = FacesContext.getCurrentInstance();
 
            Map<String, Object> sess = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            status = (String) sess.get("claimingformsession");

            Map<String, Object> rSess = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            rStatus = (String) rSess.get("reportedformsession");
            
                Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
                Users sz  = (Users) sessionMap.get("usersession");
            
           System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + " " + status + " " + rStatus);
         
//            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "You have been registered"));

            
             if ("claim".equalsIgnoreCase(status)) {
                 Users_dao.create(users);
                 
                return "pClaim";
                
            } else if ("reported".equalsIgnoreCase(rStatus)) {
                Users_dao.create(users);
               
                return "pReportProperty";
            }else if (!sz.getFirstName().isEmpty()){
                System.out.println(">>>>"+sz.getFirstName());
                Users_dao.create(users);
                return "vUsersTemp";
            }else{
                return "pMsgNoActionChoosen";
            }
                

        } catch (Exception e) {

            FacesContext text = FacesContext.getCurrentInstance();
            System.out.println(e);
            String msg = "You couldn't register";
            text.addMessage(null, new FacesMessage(msg));
//            , You should go back to the home page and choose either to claim or report missing property before"
            return "pMsgNoActionChoosen";            
        }

    }

    public String edit(Users usr) {
        this.users = usr;
        return "editUsersTemp";
    }

    public String editPassword(Users usr) {
        this.users = usr;
        return "changePasswd";
    }

    public String save() {
        users.setCreationDate(new Date());
        users.setUserRole("user");
        Users_dao.edit(users);
        return "vUsersTemp";
    }

    public String remove(Users usr) {
        System.out.println(users);
        Users_dao.remove(usr);
        return "vUsersTemp";
    }

    public String redirect() {
        Map<String, Object> sessMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        String status = "reported";
        sessMap.put("reportedformsession", status);

        return "log";
    }

}
