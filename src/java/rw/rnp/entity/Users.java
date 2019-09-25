
package rw.rnp.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Habineza Olivier
 */

public class Users implements Serializable{
    
    private Integer userID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Integer address;
    private Date creationDate;
    private String username;
    private String password;
    private String userRole;
    
    

    public Users(Integer userID, String firstName, String lastName, String phoneNumber, String email, Integer address, Date creationDate, String username, String password, String userRole) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.creationDate = creationDate;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }

    public Users() {
        
    }
    
    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = new Date();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "Users{" + "userID=" + userID + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", email=" + email + ", address=" + address + ", creationDate=" + creationDate + ", username=" + username + ", password=" + password + ", userRole=" + userRole + '}';
    }
      
}
