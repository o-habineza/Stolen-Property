
package rw.rnp.entity;

import java.io.Serializable;


/**
 *
 * @author Habineza Olivier
 */

public class Identifies implements Serializable{
    
    private int identificationID;
    private String description;
    private int userID;
    private int propertyID;

    public Identifies(int identificationID, String description, int userID, int propertyID) {
        this.identificationID = identificationID;
        this.description = description;
        this.userID = userID;
        this.propertyID = propertyID;
    }

    public Identifies() {
    }

    public int getIdentificationID() {
        return identificationID;
    }

    public void setIdentificationID(int identificationID) {
        this.identificationID = identificationID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    @Override
    public String toString() {
        return "Identifies{" + "identificationID=" + identificationID + ", description=" + description + ", userID=" + userID + ", propertyID=" + propertyID + '}';
    }
     
}
