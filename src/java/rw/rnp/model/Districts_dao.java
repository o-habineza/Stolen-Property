package rw.rnp.model;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import rw.rnp.config.HibernateUtil;
import rw.rnp.entity.Districts;

/**
 *
 * @author Habineza Olivier
 */

public class Districts_dao {

    public static List<Districts> getDistricts() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Districts> districts = new ArrayList<>();
        try {
            s.beginTransaction();
            districts = s.createCriteria(Districts.class).list();
            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }
        return districts;
    }
    public static List<Districts> getDistrictsbyProvince(int province) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Districts> districts = s.createQuery("FROM Districts where provinceID=:provinceId").setInteger("provinceId", province).list();        
        s.close();
        return districts;
    }
    
}
