
package rw.rnp.entity;

import java.io.Serializable;


/**
 *
 * @author Habineza Olivier
 */


public class Categories implements Serializable{
    
    private int categoryID;
    private String categoryDescription;

    public Categories(int categoryID, String categoryDescription) {
        this.categoryID = categoryID;
        this.categoryDescription = categoryDescription;
    }

    public Categories() {
    }
    

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    @Override
    public String toString() {
        return "Categories{" + "categoryID=" + categoryID + ", categoryDescription=" + categoryDescription + '}';
    }
    
    
    
}
