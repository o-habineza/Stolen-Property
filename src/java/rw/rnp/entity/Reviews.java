
package rw.rnp.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Habineza Olivier
 */

public class Reviews implements Serializable{
    
    private int reviewID;
    private Date reviewDate;
    private String reviewStatus;
    private int userID;
    private int identificationID;

    public Reviews(int reviewID, Date reviewDate, String reviewStatus, int userID, int identificationID) {
        this.reviewID = reviewID;
        this.reviewDate = reviewDate;
        this.reviewStatus = reviewStatus;
        this.userID = userID;
        this.identificationID = identificationID;
    }

    public Reviews() {
    }
    
    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getIdentificationID() {
        return identificationID;
    }

    public void setIdentificationID(int identificationID) {
        this.identificationID = identificationID;
    }

    @Override
    public String toString() {
        return "Reviews{" + "reviewID=" + reviewID + ", reviewDate=" + reviewDate + ", reviewStatus=" + reviewStatus + ", userID=" + userID + ", identificationID=" + identificationID + '}';
    }

    public void setCreationDate(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
