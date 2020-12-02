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
     * Decrypts the given bytes
     * Will be used to decrypt hash
     * @param eText
     * @param publicKey
     * @return
     * @throws Exception 
     */
    public static String decrypt(byte[] eText, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return new String(cipher.doFinal(eText));
    }
    
    /**
     * Decrypts the given bytes
     * Will be used to decrypt AES key
     * @param eText
     * @param privateKey
     * @return
     * @throws Exception 
     */
    public static byte[] decrypt(byte[] eText, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(eText);
    }
    
    /**
     * Encrypts the given bytes
     * Will be used to encrypt hash
     * @param pText
     * @param privateKey
     * @return
     * @throws Exception 
     */
    public static byte[] encrypt(byte[] pText, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(pText);
    }
    
    /**
     * Encrypts the given bytes
     * Will be used to encrypt AES key
     * @param pText
     * @param publicKey
     * @return
     * @throws Exception 
     */
    public static byte[] encrypt(byte[] pText, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(pText);
    }
    
}
