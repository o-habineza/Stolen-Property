package rw.rnp.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import rw.rnp.config.HibernateUtil;
import rw.rnp.controller.CategoryController;
import rw.rnp.controller.DistrictController;
import rw.rnp.controller.ProvinceController;
import rw.rnp.entity.Categories;
import rw.rnp.entity.Properties;
import rw.rnp.entity.Provinces;
import static rw.rnp.model.Categories_dao.getCategories;
import rw.rnp.model.Districts_dao;
import rw.rnp.model.Properties_dao;
import rw.rnp.model.Province_dao;
import rw.rnp.model.Sectors_dao;

/**
 *
 * @author Habineza Olivier
 */

public class Test {
     
    public static void main(String[] args) {

//    List<Properties> prptz = new ArrayList<>();
//    Properties prop = null;
//        for (Properties pro : Properties_dao.getProperties()) {
//            prop = new Properties(
//                    pro.getPropertyID(),
//                    pro.getPropertyName(),
//                    pro.getPropertyDescription(),
//                    pro.getPropertyPicture(),
//                    pro.getPropertyRecordedDate(),
//                    pro.getCategoryID());
//            prptz.add(prop);
//        }
        
       
        
//       Properties_dao pd = new Properties_dao();
       
        System.out.println(Properties_dao.getPropertyList());
        }
      }
