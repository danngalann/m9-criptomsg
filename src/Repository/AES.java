/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Daniel Galán
 */
public class AES {
    private SecretKey key;
    private byte[] IV;

    public AES() {
        try {
            makeKey();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AES.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Makes a key on runtime
     * @throws NoSuchAlgorithmException 
     */
    public void makeKey() throws NoSuchAlgorithmException{
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        
        // Make key
        key = keyGenerator.generateKey();
        
        // Make IV
        IV = new byte[16];
    }
    
    public SecretKey getKey(){
        return key;
    }

    /**
     * Encrypts text
     * @param text byte array
     * @return
     * @throws Exception 
     */
    public byte[] encrypt (byte[] text) throws Exception {
        // Get cipher
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        
        // Make SecretKeySpec
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
        
        // Make IVParameterSpec
        IvParameterSpec ivSpec = new IvParameterSpec(IV);
        
        // Init cipher
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        
        // Encrypt
        byte[] encryptedText = cipher.doFinal(text);
        
        return encryptedText;
    }
    
    /**
     * Decrypts text
     * @param encryptedText byte array
     * @return
     * @throws Exception 
     */
    public static String decrypt(byte[] encryptedText, SecretKey simetricKey) throws Exception {
        // Get cipher
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        
        // Make IVParameterSpec
        IvParameterSpec ivSpec = new IvParameterSpec(new byte[16]);
        
        // Init cipher
        cipher.init(Cipher.DECRYPT_MODE, simetricKey, ivSpec);
        
        // Encrypt
        byte[] text = cipher.doFinal(encryptedText);
        
        return new String(text);
    }
}
