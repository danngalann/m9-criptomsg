/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;

/**
 *
 * @author Daniel Galán
 */
public class RSA {
    
    /**
     * Makes RSA keys
     * @return
     * @throws Exception 
     */
    public static Map<String, Object> makeKeys() throws Exception {
        KeyPairGenerator keyPairGenerator =  KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey =  keyPair.getPublic();
        
        Map<String, Object> keys = new HashMap<String, Object>();
        keys.put("private", privateKey);
        keys.put("public", publicKey);
        
        return keys;
    }
    
    /**
     * Decrypts a given message
     * @param eText
     * @param publicKey
     * @return
     * @throws Exception 
     */
    public static String decrypt(String eText, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(eText)));
    }
    
    /**
     * Encrypts a given message
     * @param pText
     * @param privateKey
     * @return
     * @throws Exception 
     */
    public static String encrypt(String pText, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(pText.getBytes()));
    }
    
}
