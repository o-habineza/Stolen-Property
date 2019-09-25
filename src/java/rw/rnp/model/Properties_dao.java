package rw.rnp.model;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.primefaces.model.UploadedFile;
import rw.rnp.config.HibernateUtil;
import rw.rnp.entity.Categories;
import rw.rnp.entity.Properties;

/**
 *
 * @author Habineza Olivier
 */

public class Properties_dao {

    public static List<Properties> getProperties() {

        Session s = HibernateUtil.getSessionFactory().openSession();

        List<Properties> properties = new ArrayList<>();

        try {

            s.beginTransaction();
            properties = s.createCriteria(Properties.class).list();

            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }

        return properties;
    }

    public static List<Properties> getPropertyList() {

        Session s = HibernateUtil.getSessionFactory().openSession();

        List<Properties> properties = new ArrayList<>();

        try {

            s.beginTransaction();
            Query query = s.createQuery("from Properties where propertyStatus = 'Seized'");
            properties = query.list();

            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }

        return properties;
    }
    public static int getNumberOfSeizedProperties() {

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
            String strSql = "select * from properties where propertyStatus = 'Seized' ";
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
    public static int getNumberOfReportedProperties() {

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
            String strSql = "select * from properties where propertyStatus = 'reported' ";
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
    public static List<Properties> getReportedPropertyList() {

        Session s = HibernateUtil.getSessionFactory().openSession();

        List<Properties> properties = new ArrayList<>();

        try {

            s.beginTransaction();
            Query query = s.createQuery("from Properties where propertyStatus = 'reported'");
            properties = query.list();

            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }

        return properties;
    }

    public static Properties getIdentifiedPropertyList(int propID) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
//        Query query = s.createQuery("FROM rw.rnp.entity.Properties properties where properties.propertyID=:propertyId");
        Query query = s.createQuery("FROM rw.rnp.entity.Properties where propertyID='" + propID + "'");
//        from Properties where propertyID='" + propID + "'
//        query.setInteger("propertyId", propID);
        Object queryResult = query.uniqueResult();

        Properties property = (Properties) queryResult;
        s.getTransaction().commit();
        s.close();

        return property;
    }

    public static List<Properties> getElectronicsList() {

        Session s = HibernateUtil.getSessionFactory().openSession();

        List<Properties> properties = new ArrayList<>();

        try {

            s.beginTransaction();
            Query query = s.createQuery("from Properties where propertyStatus = 'Seized' AND categoryID = '1'");
            properties = query.list();

            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }

        return properties;
    }

    public static List<Properties> getFurnituresList() {

        Session s = HibernateUtil.getSessionFactory().openSession();

        List<Properties> properties = new ArrayList<>();

        try {

            s.beginTransaction();
            Query query = s.createQuery("from Properties where propertyStatus = 'Seized' AND categoryID = '2'");
            properties = query.list();

            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }

        return properties;
    }

    public static List<Properties> getVehiclesList() {

        Session s = HibernateUtil.getSessionFactory().openSession();

        List<Properties> properties = new ArrayList<>();

        try {

            s.beginTransaction();
            Query query = s.createQuery("from Properties where propertyStatus = 'Seized' AND categoryID = '3'");
            properties = query.list();

            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }

        return properties;
    }

    public static List<Properties> getOthersList() {

        Session s = HibernateUtil.getSessionFactory().openSession();

        List<Properties> properties = new ArrayList<>();

        try {

            s.beginTransaction();
            Query query = s.createQuery("from Properties where propertyStatus = 'Seized' AND categoryID = '7'");
            properties = query.list();

            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }

        return properties;
    }

    public static void create(Properties prpts, UploadedFile file) {

        Session s = HibernateUtil.getSessionFactory().openSession();

        try {
            byte[] bFile = new byte[(int) file.getSize()];
            FileInputStream fis = (FileInputStream) file.getInputstream();
            fis.read(bFile);
            fis.close();

            prpts.setPropertyPicture(bFile);
            s.beginTransaction();
            s.save(prpts);
            s.getTransaction().commit();
            s.close();

        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }

    }

    public static void create(Properties prpts) {

        Session s = HibernateUtil.getSessionFactory().openSession();

        try {

            prpts.setPropertyPicture(null);
            s.beginTransaction();
            s.save(prpts);
            s.getTransaction().commit();
            s.close();

        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }

    }

    public static void edit(Properties prpts, UploadedFile file) {

        Session s = HibernateUtil.getSessionFactory().openSession();

        try {
            byte[] bFile = new byte[(int) file.getSize()];
            FileInputStream fis = (FileInputStream) file.getInputstream();
            fis.read(bFile);
            fis.close();
            prpts.setPropertyPicture(bFile);
            s.beginTransaction();
            s.update(prpts);
            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }
    }
    public static void edit(Properties prpts) {

        Session s = HibernateUtil.getSessionFactory().openSession();

        try {
            s.beginTransaction();
            s.update(prpts);
            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }
    }

    public static void remove(Properties prpts) {

        Session s = HibernateUtil.getSessionFactory().openSession();

        try {
            s.beginTransaction();
            s.delete(prpts);
            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }
    }

    public static Categories getCategoryById(int catId) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query query = s.createQuery("FROM Categories categories where categories.categoryID=:categoryId");
        query.setInteger("categoryId", catId);
        Object queryResult = query.uniqueResult();

        Categories category = (Categories) queryResult;
        s.getTransaction().commit();
        s.close();

        return category;
    }
    
    public static void  main(String[]args){
        System.out.println(getReportedPropertyList());
    }
}
