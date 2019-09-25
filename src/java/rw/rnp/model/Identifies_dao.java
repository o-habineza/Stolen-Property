package rw.rnp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import rw.rnp.config.HibernateUtil;
import rw.rnp.entity.Identifies;


/**
 *
 * @author Habineza Olivier
 */

public class Identifies_dao {

//    public static List<Identifies> getIdentifiedProperties() {
//
//        Session s = HibernateUtil.getSessionFactory().openSession();
//        
//        List<Identifies> usr = new ArrayList<>();
//
//        try {
//
//            s.beginTransaction();
//            usr = s.createCriteria(Identifies.class).list();
//            s.getTransaction().commit();
//            s.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            s.getTransaction().rollback();
//        }
//
//        return usr;
//
//    }
    public static List<Identifies> getIdentifiedProperties() {

        Session s = HibernateUtil.getSessionFactory().openSession();

        List<Identifies> identify = new ArrayList<>();

        try {

            s.beginTransaction();
            Query query = s.createQuery("from Identifies ident where not exists (from Reviews rev where rev.identificationID =ident.identificationID)");
            identify = query.list();

            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }

        return identify;
    }
    public static int getNumberOfIdentifiedProperties() {

      Statement stmt = null;
        Connection con = null;
        
        ResultSet rs;
        int sum =0;
        int i = 0;
        
        try {


            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/stolenproperty", "root", "prple$$@2012");

            stmt = con.createStatement();
            String strSql = "select * from identifies";
            rs = stmt.executeQuery(strSql);
            while(rs.next()) {
                i++;
            }
             sum =+i;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sum;
    }

//    public static Identifies getIdentifiedProperty(int identId) {
//
//        Session s = HibernateUtil.getSessionFactory().openSession();
//        
//        Identifies idt = new Identifies();
//
//        try {
//            s.beginTransaction();
//            Query query = s.createQuery("from Identifies ident where ident.identificationID=:identId");
//            query.setInteger("identId", identId);
//            idt = (Identifies) query.uniqueResult();
//            s.getTransaction().commit();
//            s.flush();
//            s.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            s.getTransaction().rollback();
//        }
//        return idt;
//    }
    public static void create(Identifies identifies) {

        Session s = HibernateUtil.getSessionFactory().openSession();

        try {
            s.beginTransaction();
            s.save(identifies);
            s.getTransaction().commit();
            s.flush();
            s.close();

        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }
    }

    public static void edit(Identifies identifies) {

        Session s = HibernateUtil.getSessionFactory().openSession();

        try {
            s.beginTransaction();
            s.update(identifies);
            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }
    }

    public static void remove(Identifies identifies) {

        Session s = HibernateUtil.getSessionFactory().openSession();

        try {
            s.beginTransaction();
            s.delete(identifies);
            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }
    }
    public static void main(String[] args) {
        System.out.println(getIdentifiedProperties());
    }
}
