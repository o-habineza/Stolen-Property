
package rw.rnp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import rw.rnp.entity.Identifies;
import rw.rnp.entity.Reviews;
import rw.rnp.entity.Users;
import rw.rnp.model.Reviews_dao;

/**
 *
 * @author Habineza Olivier
 */

@ManagedBean(name="revContrl")
@SessionScoped
public class ReviewController implements Serializable{
    
    //User to be set on view form
    private List<Reviews> rev = new ArrayList<>();
    //New instance where we do insert, update and remove
    private Reviews reviews = new Reviews();
    
    private int numberOfReviewedStolenProperty;
    private int numberOfApprovedReviewedStolenProperty;
    private int numberOfRejectedReviewedStolenProperty;    
   
    
    public Identifies selectCorrespIdentificationID(int identID){
    return Reviews_dao.getIdentifiedPropID(identID);
    }
    public Users selectCorrespUser(int userID){
    return Reviews_dao.getIdentifiedUser(userID);
    }
   
    public List<Reviews> getRev() {
        return Reviews_dao.getReviews();
    }

    public void setRev(List<Reviews> rev) {
        this.rev = rev;
    }

    public Reviews getReviews() {
        return reviews;
    }

    public void setReviews(Reviews reviews) {
        this.reviews = reviews;
    }

    public int getNumberOfReviewedStolenProperty() {
        return Reviews_dao.getNumberOfReviewedProperties();
    }

    public void setNumberOfReviewedStolenProperty(int numberOfReviewedStolenProperty) {
        this.numberOfReviewedStolenProperty = numberOfReviewedStolenProperty;
    }

    public int getNumberOfApprovedReviewedStolenProperty() {
        return Reviews_dao.getNumberOfApprovedReviewedProperties();
    }

    public void setNumberOfApprovedReviewedStolenProperty(int numberOfApprovedReviewedStolenProperty) {
        this.numberOfApprovedReviewedStolenProperty = numberOfApprovedReviewedStolenProperty;
    }

    public int getNumberOfRejectedReviewedStolenProperty() {
        return Reviews_dao.getNumberOfRejectedReviewedProperties();
    }

    public void setNumberOfRejectedReviewedStolenProperty(int numberOfRejectedReviewedStolenProperty) {
        this.numberOfRejectedReviewedStolenProperty = numberOfRejectedReviewedStolenProperty;
    }
    
    
    
    public String insert() {
        reviews.setCreationDate(new Date());
        Reviews_dao.create(reviews);       
        return "vReviewsTemp";
    }

    public String edit(Reviews rev) {
        this.reviews = rev;
        return "editReview";
    }

    public String save() {
        reviews.setCreationDate(new Date());
        Reviews_dao.edit(reviews);
        return "vReviewsTemp";
    }

    public String remove(Reviews rev) {
        Reviews_dao.remove(rev);
        return "vReviewsTemp";
    }
    
     public String approve(int identifyID) {
         System.out.println("......................................................."+identifyID);
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        Users user = (Users) sessionMap.get("usersession");
       
       reviews.setIdentificationID(identifyID);
       reviews.setReviewDate(new Date());
       reviews.setReviewStatus("Approved");
       reviews.setUserID(user.getUserID());
       Reviews_dao.create(reviews);
       
       return "vReviewsTemp";
    }
    public String reject(int identifyID) {
         Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        Users user = (Users) sessionMap.get("usersession");
       
       reviews.setIdentificationID(identifyID);
       reviews.setReviewDate(new Date());
       reviews.setReviewStatus("Rejected");
       reviews.setUserID(user.getUserID());
       Reviews_dao.create(reviews);
       
        
        return "vReviewsTemp";
    }
 
}
