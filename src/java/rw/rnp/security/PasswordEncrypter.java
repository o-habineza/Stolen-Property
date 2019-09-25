/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rw.rnp.security;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Habineza Olivier
 */


public class PasswordEncrypter {


    public static String securePassword(String passwordToHash) {
        String generatedPassword = null;
        try {
            
            // Create MessageDigest instance for SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            //Add password bytes to digest
            String saltKey = "itdc@SYS=2";
            byte[] salt = saltKey.getBytes();
            md.update(salt);
                     
            //Get the hash's bytes 
            byte[] bytes = md.digest(passwordToHash.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
          System.out.println(e.toString());  
        }
        return generatedPassword;
    }


 
}
