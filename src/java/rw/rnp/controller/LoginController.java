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
import rw.rnp.entity.Users;
import rw.rnp.model.Users_dao;

/**
 *
 * @author Habineza Olivier
 */

@ManagedBean(name = "login")
@SessionScoped
public class LoginController implements Serializable{

    private List<Users> usr = new ArrayList<>();
    private Users user = new Users();
    private boolean isLogined = false;
    
   

    public boolean isIsLogined() {
        return isLogined;
    }

    public void setIsLogined(boolean isLogined) {
        this.isLogined = isLogined;
    }

    public List<Users> getUsr() {
        return Users_dao.getUsers();
    }

    public void setUsr(List<Users> usr) {
        this.usr = usr;
    }

    public Users getUser() {

        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void verifyLogin(){
         
        if(!this.isLogined){
            
             doRedirect("Page_login.xhtml"); 
        }
    }
    
    public void doRedirect(String url){
        try{
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.getExternalContext().redirect(url);
        }catch(Exception e){
             System.out.println(e.getMessage());
        }
    }
    
    public String doLogin() {

        System.out.println("in doLogin");
        Users us = Users_dao.login(user);

        String verify = "";

        if (us != null) {
            System.out.println("in if");
            Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            sessionMap.put("usersession", us);
            System.err.println("******************************" + " " + us);

            Map<String, Object> sess = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            String status = (String) sess.get("claimingformsession");

            Map<String, Object> rSess = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            String rStatus = (String) rSess.get("reportedformsession");
            
            System.out.println(">>>>>>>>>>>>>>>>>>>>>------------" + " " + status + " " + rStatus);

            if (us.getUserRole().equalsIgnoreCase("user")) {
                this.isLogined = true;
                if ("claim".equalsIgnoreCase(status)) {
                    verify = "pClaim";
                } else if ("reported".equalsIgnoreCase(rStatus)) {
                    verify = "pReportProperty";
                } else {                    
                    verify = "pMsgNoActionChoosen";                    
                }
            } else if (us.getUserRole().equalsIgnoreCase("admin")) {
               this.isLogined = true; 
                verify = "padMenu";
            }
            return verify;
        } else {

            System.out.println("in else");
            FacesContext cont = FacesContext.getCurrentInstance();
            String msg = "Username and password incorrect";
            cont.addMessage(null, new FacesMessage(msg));
            
        }
        return"";
    }

    public String doLogout() {
        HttpSession hs = HibernateUtil.getSession();
        hs.invalidate();
        this.isLogined = false;
        return "log";
    }
    public String doLogoutToHomepage() {
        HttpSession hs = HibernateUtil.getSession();
        hs.invalidate();
        this.isLogined = false;
        return "homePage";
    }

    public void sessionFromCreatedUser() {
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        user = (Users) sessionMap.get("createusersession");
    }
    
    
}
