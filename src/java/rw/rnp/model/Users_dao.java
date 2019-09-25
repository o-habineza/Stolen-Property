package rw.rnp.model;

import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import rw.rnp.config.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import rw.rnp.entity.Sectors;
import rw.rnp.entity.Users;
import rw.rnp.security.PasswordEncrypter;

/**
 *
 * @author Habineza Olivier
 */

public class Users_dao {

    public static List<Users> getUsers() {

        Session s = HibernateUtil.getSessionFactory().openSession();

        List<Users> usr = new ArrayList<>();

        try {

            s.beginTransaction();
            usr = s.createCriteria(Users.class).list();
            s.getTransaction().commit();
            s.close();

        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }

        return usr;

    }

//    public static List<Users> getUser() {
//
//        Session s = HibernateUtil.getSessionFactory().openSession();
//
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stolenproperty", "root", "prple$$@2012");            
//            PreparedStatement ps = con.prepareStatement("select * from Users");
//            ResultSet rs = ps.executeQuery();
//            
//            List<Users> usrs = new ArrayList<>();
//            
//            ArrayList<Users> usr = new ArrayList<Users>();
//
//            while (rs.next()) {
//                Users b = new Users();
//                b.userID = rs.getInt(1);
//                b.firstName = rs.getString(2);
//                b.lastName = rs.getString(3);
//                b.phoneNumber = rs.getInt(4);
//                b.email = rs.getString(5);
//                b.address = rs.getString(6);
//                usr.add(b);
//            }
//            con.close();
//
//
//        } catch (Exception e) {
//            out.println(e);
//        }
//
//    }

    public static Users getSpecificUser(int userID) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
//        Query query = s.createQuery("FROM rw.rnp.entity.Properties properties where properties.propertyID=:propertyId");
        Query query = s.createQuery("FROM rw.rnp.entity.Users where userID='" + userID + "'");
//        from Properties where propertyID='" + propID + "'
//        query.setInteger("propertyId", propID);
        Object queryResult = query.uniqueResult();

        Users user = (Users) queryResult;
        s.getTransaction().commit();
        s.close();

        return user;
    }
    public static Sectors getSpecificUserAddress(int sectorID) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
//        Query query = s.createQuery("FROM rw.rnp.entity.Properties properties where properties.propertyID=:propertyId");
        Query query = s.createQuery("FROM rw.rnp.entity.Sectors where sectorID='" + sectorID + "'");
//        from Properties where propertyID='" + propID + "'
//        query.setInteger("propertyId", propID);
        Object queryResult = query.uniqueResult();

        Sectors sectror = (Sectors) queryResult;
        s.getTransaction().commit();
        s.close();

        return sectror;
    }
    

    public static void create(Users usr) {

        Session s = HibernateUtil.getSessionFactory().openSession();

        try {

            String genPassword = PasswordEncrypter.securePassword(usr.getPassword());
            usr.setPassword(genPassword);

            s.beginTransaction();
            s.save(usr);
            s.getTransaction().commit();
            s.flush();

            Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            sessionMap.put("usersession", usr);
            
            s.close();

        } catch (Exception e) {
            System.out.println("From Dao"+e);
            s.getTransaction().rollback();
        }
    }

    public static void edit(Users usr) {

        Session s = HibernateUtil.getSessionFactory().openSession();

        try {

            String genPassword = PasswordEncrypter.securePassword(usr.getPassword());
            usr.setPassword(genPassword);

            s.beginTransaction();
            s.update(usr);
            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }
    }

    public static void remove(Users usr) {

        Session s = HibernateUtil.getSessionFactory().openSession();

        try {
            s.beginTransaction();
            s.delete(usr);
            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }
    }

    public static Users login(Users user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String queryString = "select users from Users users where users.username=:username and users.password=:password";

        Query query = session.createQuery(queryString);
        query.setString("username", user.getUsername());
        query.setString("password", PasswordEncrypter.securePassword(user.getPassword()));

        Object queryResult = query.uniqueResult();
        Users us = (Users) queryResult;
        session.getTransaction().commit();
        session.close();
        return us;
    }

    public static void main(String[] args) {
//        System.out.println(getSpecificUserAddress(107));
    }
}
