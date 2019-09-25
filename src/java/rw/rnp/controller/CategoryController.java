
package rw.rnp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import rw.rnp.entity.Categories;
import rw.rnp.model.Categories_dao;

/**
 *
 * @author Habineza Olivier
 */

@ManagedBean(name="catContrl")
@SessionScoped
public class CategoryController implements Serializable{
    
    private List<String> categoryDescription = new ArrayList<>();
    private Categories categories = new Categories();
    
    
    

    public List<Categories> getCategoryDescription() {                
    
        return Categories_dao.getCategories();
    }

    public void setCategoryDescription(List<String> categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }
    
       public String insert() {
       Categories_dao.create(categories);       
        return "vCategoryTemp";
    }

    public String edit(Categories cat) {
        this.categories = cat;
        return "editCategoryTemp";
    }

    public String save() {
        Categories_dao.edit(categories);
        return "vCategoryTemp";
    }

    public String remove(Categories cat) {
        Categories_dao.remove(cat);
        return "vCategoryTemp";
    }
    
}
