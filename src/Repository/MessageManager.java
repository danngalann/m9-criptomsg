/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Exceptions.MessageIntegrityCompromised;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Daniel Galán
 */
public class MessageManager {
    public static Map<String, Object> sendMessage(String message, PublicKey remotePublicKey, PrivateKey privateKey) throws Exception {
        
        // Init final message
        Map<String, Object> finalMessage = new HashMap<>();
        
        // Make simetric key
        AES aes = new AES();
        SecretKey key = aes.getKey();
        
        // Make hash and concatenate with message
        String messageHash = SHA.makeHash(message);             
        // Encrypt hash with your private key
        byte[] encryptedHash = RSA.encrypt(messageHash.getBytes(), privateKey);

        // Encript simetric key with remote public key
        byte[] encryptedSimetricKey = RSA.encrypt(key.getEncoded(), remotePublicKey);

        // Encrypt message
        byte[] encryptedMessage = aes.encrypt(message.getBytes());

        // Make final message with encripted message and encripted simetric key            
        finalMessage.put("hash", encryptedHash);
        finalMessage.put("message", encryptedMessage);
        finalMessage.put("key", encryptedSimetricKey);
        
        return finalMessage;
    }
    
    public static String receiveMessage(Map<String, Object> encryptedData, PublicKey remotePublicKey, PrivateKey privateKey) throws Exception {
        
        String plainMessage = "";
        // Extract everything
        byte[] encryptedHash = (byte[]) encryptedData.get("hash");
        byte[] encriptedSimetricKey = (byte[]) encryptedData.get("key");
        byte[] encryptedMessage = (byte[]) encryptedData.get("message");
        
        // Decrypt hash with remote public key
        String hash = RSA.decrypt(encryptedHash, remotePublicKey);

        // Decrypt simetric key with private key
        byte[] encodedSimetricKey = RSA.decrypt(encriptedSimetricKey, privateKey);

        // Make AES key from raw bytes
        SecretKey simetricKey = new SecretKeySpec(encodedSimetricKey, 0, encodedSimetricKey.length, "AES");

        // Decrypt message with AES key
        String message = AES.decrypt(encryptedMessage, simetricKey);
        
        // Check message integrity
        if(!isValidHash(hash, message)){
            throw new MessageIntegrityCompromised("El mensaje está corrupto");
        }

        // Store decrypted message on return variable
        plainMessage = message;
        
        return plainMessage;
    }
    
    static private boolean isValidHash(String hash, String text){
        String localHash = SHA.makeHash(text);
        
        return hash.equals(localHash);
    }
}
