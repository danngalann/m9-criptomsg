/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;

/**
 *
 * @author Daniel Galán
 */
public class MessageManager {
    public static Map<String, Object> sendMessage(String message, PublicKey remotePublicKey, PrivateKey privateKey){
        
        // Init final message
        Map<String, Object> finalMessage = new HashMap<>();
        
        // Make simetric key
        AES aes = new AES();
        SecretKey key = aes.getKey();
        
        // Make hash and concatenate with message
        String messageHash = SHA.makeHash(message);                
                
        try {          
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
        } catch (Exception ex) {
            Logger.getLogger(MessageManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return finalMessage;
    }
}
