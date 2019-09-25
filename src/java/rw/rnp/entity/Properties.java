
package rw.rnp.entity;

import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author Habineza Olivier
 */

public class Properties implements Serializable{
    
    private int propertyID;
    private String propertyName;
    private String propertyDescription;
    private byte[] propertyPicture;
    private Date propertyRecordedDate; 
    private String propertyStatus;
    private int categoryID;
    private int userID;
    private int sectorID;

    public Properties(int propertyID, String propertyName, String propertyDescription, byte[] propertyPicture, Date propertyRecordedDate, String propertyStatus, int categoryID, int userID, int sectorID) {
        this.propertyID = propertyID;
        this.propertyName = propertyName;
        this.propertyDescription = propertyDescription;
        this.propertyPicture = propertyPicture;
        this.propertyRecordedDate = propertyRecordedDate;
        this.propertyStatus = propertyStatus;
        this.categoryID = categoryID;
        this.userID = userID;
        this.sectorID = sectorID;
    }

    public Properties() {
    }

    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyDescription() {
        return propertyDescription;
    }

    public void setPropertyDescription(String propertyDescription) {
        this.propertyDescription = propertyDescription;
    }

    public byte[] getPropertyPicture() {
        return propertyPicture;
    }

    public void setPropertyPicture(byte[] propertyPicture) {
        this.propertyPicture = propertyPicture;
    }

    public Date getPropertyRecordedDate() {
        return propertyRecordedDate;
    }

    public void setPropertyRecordedDate(Date propertyRecordedDate) {
        this.propertyRecordedDate = propertyRecordedDate;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getPropertyStatus() {
        return propertyStatus;
    }

    public void setPropertyStatus(String propertyStatus) {
        this.propertyStatus = propertyStatus;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getSectorID() {
        return sectorID;
    }

    public void setSectorID(int sectorID) {
        this.sectorID = sectorID;
    }

    @Override
    public String toString() {
        return "Properties{" + "propertyID=" + propertyID + ", propertyName=" + propertyName + ", propertyDescription=" + propertyDescription + ", propertyPicture=" + propertyPicture + ", propertyRecordedDate=" + propertyRecordedDate + ", propertyStatus=" + propertyStatus + ", categoryID=" + categoryID + ", userID=" + userID + ", sectorID=" + sectorID + '}';
    }
    
    
}
