package rw.rnp.controller;

import java.sql.*;
import java.io.*;
import javax.faces.bean.ManagedBean;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Habineza Olivier
 */

@ManagedBean(name="d")
public class DisplayImage extends HttpServlet implements Serializable{

    private static final long serialVersionUID = 4593558495041379082L;

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        Statement stmt = null;
        Connection con = null;
        
        ResultSet rs;
        InputStream sImage;
        try {

            String id = request.getParameter("Image_id");
            System.out.println("inside servlet–>" + id);

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/stolenproperty", "root", "prple$$@2012");

            stmt = con.createStatement();
            String strSql = "select propertyPicture from properties where propertyID='" + id + "' AND propertyStatus = 'Seized' ";
            rs = stmt.executeQuery(strSql);
            if (rs.next()) {

                byte[] bytearray = new byte[1048576];
                int size = 0;
                sImage = rs.getBinaryStream(1);
                response.reset();
                response.setContentType("image/jpg");
                while ((size = sImage.read(bytearray)) != -1) {
                    response.getOutputStream().
                            write(bytearray, 0, size);
                }
               
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
