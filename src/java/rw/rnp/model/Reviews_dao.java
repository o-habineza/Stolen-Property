
package rw.rnp.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import rw.rnp.config.HibernateUtil;
import rw.rnp.controller.UserController;
import rw.rnp.entity.Identifies;
import rw.rnp.entity.Reviews;
import rw.rnp.entity.Users;

/**
 *
 * @author Habineza Olivier
 */

public class Reviews_dao {
    
      public static List<Reviews> getReviews() {

        Session s = HibernateUtil.getSessionFactory().openSession();
        
        List<Reviews> usr = new ArrayList<>();

        try {

            s.beginTransaction();
            usr = s.createCriteria(Reviews.class).list();
            s.getTransaction().commit();
            s.close();

        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }

        return usr;

    }
      public static Identifies getIdentifiedPropID(int identID) {

        Session s = HibernateUtil.getSessionFactory().openSession();
        
            s.beginTransaction();
            Query query = s.createQuery("FROM rw.rnp.entity.Identifies where identificationID='" + identID + "'");
            Object queryResult = query.uniqueResult();

            Identifies revi = (Identifies) queryResult;
            s.getTransaction().commit();
            s.close();
 
        return revi;

    }
      public static Users getIdentifiedUser(int userID) {

        Session s = HibernateUtil.getSessionFactory().openSession();
        
            s.beginTransaction();
            Query query = s.createQuery("FROM rw.rnp.entity.Users where userID='" + userID + "'");
            Object queryResult = query.uniqueResult();

            Users users = (Users) queryResult;
            
            s.getTransaction().commit();
            s.close();
 
        return users;

    }
      
      public static int getNumberOfReviewedProperties() {

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
            String strSql = "select * from reviews";
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
      public static int getNumberOfApprovedReviewedProperties() {

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
            String strSql = "select * from reviews where reviewStatus = 'Approved'";
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
      public static int getNumberOfRejectedReviewedProperties() {

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
            String strSql = "select * from reviews where reviewStatus = 'Rejected'";
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

    public static void create(Reviews rev) {

        Session s = HibernateUtil.getSessionFactory().openSession();

        try {
            s.beginTransaction();
            s.save(rev);
            s.getTransaction().commit();
            s.close();
           
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        } 
    }

    public static void edit(Reviews rev) {

        Session s = HibernateUtil.getSessionFactory().openSession();

        try {
            s.beginTransaction();
            s.update(rev);
            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }
    }

    public static void remove(Reviews rev) {

        Session s = HibernateUtil.getSessionFactory().openSession();

        try {
            s.beginTransaction();
            s.delete(rev);
            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }
    }
      public static void main(String[]args){
          
          System.out.println(getIdentifiedUser(53).getFirstName()+" "+getIdentifiedUser(53).getLastName());
      }

   
}
