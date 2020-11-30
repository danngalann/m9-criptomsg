/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Repository.KeyIO;
import Repository.RSA;
import Socket.ChatClient;
import View.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Daniel Galán
 */
public class MainController {
    
    MainView p;
    boolean connected = false;
    ChatClient chatClient;

    public MainController() {
        p = new MainView();
        initListeners();
        p.setDarkMode();
        p.setVisible(true);
    }
    
    private void initListeners(){
        // Socket connection
        p.connectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!connected){
                    String ip = p.ipInput.getText();

                    if(!ip.isEmpty()){
                        chatClient = new ChatClient(ip, 3000);
                        connected = chatClient.execute();

                        if(connected){
                            onConnect();
                        } else {
                            showMessageDialog(p, "No se ha podido conectar");
                        }
                    } else {
                        showMessageDialog(p, "La IP no puede estar en blanco.");
                    }
                } else {
                    chatClient.disconnect();
                    onDisconnect();
                }
            }
        });
        
        // Send Message
        p.sendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = p.messageInput.getText();
                
                if(message.isEmpty()){
                    showMessageDialog(p, "El mensaje no puede estar en blanco");
                } else {
                    // TODO: Send message to MessageManager
                }
            }
        });
        
        // Load message
        p.loadFileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                
                int returnValue = fileChooser.showOpenDialog(p);
                
                if(returnValue == JFileChooser.APPROVE_OPTION){
                    File selectedFile = fileChooser.getSelectedFile();
                    // TODO: Send file to MessageManager
                    System.out.println(selectedFile.getAbsolutePath());
                }
            }
        });
        
        // Make keys
        p.makeKeysBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Map<String, Object> keys = RSA.makeKeys();
                    KeyIO.serializePrivate((PrivateKey) keys.get("private"));
                    KeyIO.serializePublic((PublicKey) keys.get("public"));
                    showMessageDialog(p, "Claves generadas");
                } catch (Exception ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    showMessageDialog(p, "No se han podido generar las claves.");
                }
            }
        });
    }
    
    private void onConnect(){
        p.ipInput.setEnabled(false);
        p.connectBtn.setText("Desconectar");
    }
    
    private void onDisconnect(){
        p.ipInput.setEnabled(true);
        p.connectBtn.setText("Conectar");
    }
    
    
}
