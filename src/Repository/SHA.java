/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Marc
 */
public class SHA {
    
    public String makeHash(String text){
        MessageDigest md = null;
        String hash = "";
        
        try{
            md = MessageDigest.getInstance("SHA-256");
            md.update(text.getBytes());
            byte[] msgDigestSha = md.digest();
            StringBuffer strBuffer = new StringBuffer();
            
            for(byte b : msgDigestSha) {        
		strBuffer.append(String.format("%02x", b));
            }
            
            hash = strBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return hash;
    }
}
