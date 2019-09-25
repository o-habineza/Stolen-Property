/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rw.rnp.report;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Habineza Olivier
 */

public class FurnitureReportedProperty extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
   try {
 
            response.setContentType("application/pdf");
            Document document = new Document(new Rectangle(700, 800));
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            Paragraph messag = new Paragraph(new Chunk("Republic of Rwanda ", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD)));
//          document.add(new Paragraph("\n\n"));
            messag.setAlignment(Element.ALIGN_LEFT);
            document.add(messag);
            Image image = Image.getInstance("C:\\Users\\Bilyone\\Documents\\NetBeansProjects\\StolenProperty\\Logo_police.png");
            document.add(image);
//          document.add(new Paragraph("\n"));

            Paragraph messg = new Paragraph(new Chunk("Rwanda National Police", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD)));
//          document.add(new Paragraph("\n\n"));
            messag.setAlignment(Element.ALIGN_LEFT);
            document.add(messg);
            LineSeparator UNDERLINE = new LineSeparator(2, 100, null, Element.ALIGN_CENTER, -2);

//          document.add(UNDERLINE);
            //============================date=============================
            Calendar cal = new GregorianCalendar();
            int days, month, year, hour, mm;
            days = cal.get(Calendar.DAY_OF_MONTH);
            month = cal.get(Calendar.MONTH) + 1;
            year = cal.get(Calendar.YEAR);
            hour = cal.get(Calendar.HOUR);
            mm = cal.get(Calendar.MINUTE);

            String dat = days + "/" + month + "/" + year + "\n " + hour + ":" + mm;
            Paragraph par1 = new Paragraph(new Chunk("Date: " + dat, FontFactory.getFont(FontFactory.HELVETICA, 10)));
            par1.setAlignment(Element.ALIGN_RIGHT);
            //=======================================================================================
            document.add(par1);

            Paragraph message = new Paragraph(new Chunk("Furniture Reported Property Report ", FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD)));
//          document.add(new Paragraph("\n\n"));
            Paragraph title = new Paragraph(new Chunk("on the fly Report", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD)));

            message.setAlignment(Element.ALIGN_CENTER);
//          title.setAlignment(Element.ALIGN_CENTER);
            document.add(message);
            document.add(new Paragraph(new Chunk("\n")));
//          document.add(title);
//          title.add(UNDERLINE);
            document.add(new Paragraph("\n"));
            //=========================message==================================
            PdfPCell pdfcell = new PdfPCell(new Paragraph("GENERAL REPORT"));
            pdfcell.setColspan(6);
            pdfcell.setHorizontalAlignment(Element.ALIGN_CENTER);

            PdfPTable tablee = new PdfPTable(4);
            tablee.setWidthPercentage(100);
            PdfPCell tablee1 = new PdfPCell();
            tablee1.addElement(new Phrase(new Chunk("NO ", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD))));
//            PdfPCell tablee2 = new PdfPCell();
//            tablee2.addElement(new Phrase(new Chunk("PICTURE", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD))));
            PdfPCell tablee3 = new PdfPCell();
            tablee3.addElement(new Phrase(new Chunk("ITEM", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD))));
            PdfPCell tablee4 = new PdfPCell();
            tablee4.addElement(new Phrase(new Chunk("DESCRIPTION", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD))));
            PdfPCell tablee5 = new PdfPCell();
            tablee5.addElement(new Phrase(new Chunk("RECORDED DATE", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD))));

            tablee.addCell(tablee1);
//            tablee.addCell(tablee2);
            tablee.addCell(tablee3);
            tablee.addCell(tablee4);
            tablee.addCell(tablee5);

            document.add(tablee);

//            InputStream pdfImage;
            int icount = 0;
            int i = 0;
            
            String propertyName = null;
            String propertyDescription = null;
            String propertyStatus = null;
            Date propertyRecordedDate = null;
            Image img = null ;
            

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/stolenproperty", "root", "prple$$@2012");

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select* from properties where propertyStatus = 'reported' AND categoryID = '2'");
            while (rs.next()) {

//                Blob blob = rs.getBlob("propertyPicture");
//
//                byte[] bytearray = blob.getBytes(1, (int) blob.length());
//
//                img = Image.getInstance(bytearray);
//
//                img.scaleAbsolute(50, 50);
               
                propertyName = rs.getString("propertyName");
                propertyDescription = rs.getString("propertyDescription");
                propertyRecordedDate = rs.getDate("propertyRecordedDate");
 
                PdfPTable table = new PdfPTable(4);

                table.setWidthPercentage(100);
                PdfPCell table1 = new PdfPCell();
                table1.addElement(new Phrase(new Chunk("" + (++icount) + "", FontFactory.getFont(FontFactory.HELVETICA, 8))));
//                PdfPCell table2 = new PdfPCell();
//                table2.addElement(img);
                PdfPCell table3 = new PdfPCell();
                table3.addElement(new Phrase(new Chunk("" + propertyName + "", FontFactory.getFont(FontFactory.HELVETICA, 8))));
                PdfPCell table4 = new PdfPCell();
                table4.addElement(new Phrase(new Chunk("" + propertyDescription + "", FontFactory.getFont(FontFactory.HELVETICA, 8))));
                PdfPCell table5 = new PdfPCell();
                table5.addElement(new Phrase(new Chunk("" + propertyRecordedDate + "", FontFactory.getFont(FontFactory.HELVETICA, 8))));

                table.addCell(table1);
//                table.addCell(table2);
                table.addCell(table3);
                table.addCell(table4);
                table.addCell(table5);

                document.add(table);

                  System.out.println(""+icount+" "+propertyName+" "+propertyDescription+" "+propertyStatus);
                  
            }

            document.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
