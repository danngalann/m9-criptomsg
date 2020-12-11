
import Controller.MainController;
import Repository.AES;
import Repository.KeyIO;
import Repository.MessageManager;
import Repository.RSA;
import Repository.SHA;
import Socket.ChatClient;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel Galán
 */
public class CriptoMsg {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Made with love by Marc Sedano and Daniel Galan
        new MainController();
    }
    
}
