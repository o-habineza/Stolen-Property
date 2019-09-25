package rw.rnp.model;

import rw.rnp.config.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import rw.rnp.entity.Provinces;

/**
 *
 * @author Habineza Olivier
 */

public class Province_dao {

    public static List<Provinces> getProvinces() {

        Session s = HibernateUtil.getSessionFactory().openSession();

        List<Provinces> provinces = new ArrayList<>();

        try {

            s.beginTransaction();
            provinces = s.createCriteria(Provinces.class).list();

            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }

        return provinces;
    }
    
}
