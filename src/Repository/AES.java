/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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
        SecureRandom rnd = new SecureRandom();
        rnd.nextBytes(IV);
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
    public String decrypt(byte[] encryptedText) throws Exception {
        // Get cipher
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        
        // Make SecretKeySpec
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
        
        // Make IVParameterSpec
        IvParameterSpec ivSpec = new IvParameterSpec(IV);
        
        // Init cipher
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        
        // Encrypt
        byte[] text = cipher.doFinal(encryptedText);
        
        return new String(text);
    }
}
