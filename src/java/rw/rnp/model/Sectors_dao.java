
package rw.rnp.model;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import rw.rnp.config.HibernateUtil;
import rw.rnp.entity.Sectors;

/**
 *
 * @author Habineza Olivier
 */

public class Sectors_dao {
    
     public static List<Sectors> getSectors() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Sectors> sectors = new ArrayList<>();
        try {
            s.beginTransaction();
            sectors = s.createCriteria(Sectors.class).list();
            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }
        return sectors;
    }
     
     public static String getUserLocation(int address) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
//        Query query = s.createQuery("FROM rw.rnp.entity.Properties properties where properties.propertyID=:propertyId");
        Query query = s.createQuery("select sectorName from Sectors where sectorID='" + address + "'");
//        from Properties where propertyID='" + propID + "'
//        query.setInteger("propertyId", propID);
        Object queryResult = query.uniqueResult();

        String sect = (String) queryResult;
        s.getTransaction().commit();
        s.close();

        return sect;
    }
     
    public static List<Sectors> getSectorsByDistrict(int district) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Sectors> sectors = s.createQuery("FROM Sectors where districtID=:districtId").setInteger("districtId", district).list();        
        s.close();
        return sectors;
    }
    
    public static void main(String []args){
        System.out.println(getUserLocation(107));
    }
}
