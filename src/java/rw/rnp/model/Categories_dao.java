
package rw.rnp.model;

import rw.rnp.config.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import rw.rnp.entity.Categories;


/**
 *
 * @author Habineza Olivier
 */

public class Categories_dao {
    
    public static List<Categories> getCategories(){
         Session s = HibernateUtil.getSessionFactory().openSession();
        
        List<Categories> categories = new ArrayList<>();

        try {

            s.beginTransaction();
            categories = s.createCriteria(Categories.class).list();
            
            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
            
        }

        return categories;
    
    }
     public static void create(Categories catg) {

        Session s = HibernateUtil.getSessionFactory().openSession();

        try {
            s.beginTransaction();
            s.save(catg);
            s.getTransaction().commit();
            s.close();
           
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        } 
    }

    public static void edit(Categories catg) {

        Session s = HibernateUtil.getSessionFactory().openSession();

        try {
            s.beginTransaction();
            s.update(catg);
            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }
    }

    public static void remove(Categories catg) {

        Session s = HibernateUtil.getSessionFactory().openSession();

        try {
            s.beginTransaction();
            s.delete(catg);
            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }
    }
}
