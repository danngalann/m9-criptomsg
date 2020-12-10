/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Repository.FileManager;
import Repository.KeyIO;
import Repository.MessageManager;
import Repository.RSA;
import Socket.ChatClient;
import View.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Daniel Galán
 */
public class MainController {
    
    MainView p;
    boolean connected = false;
    ChatClient chatClient;
    Map<String, Object> rsaKeys;
    PublicKey remotePublicKey;
    FileFilter keyFilter;
    FileFilter messageFilter;
    Map<String, Object> lastMessage = new HashMap<>();

    /**
     * Main Controllers constructor
     */
    public MainController() {
        initFilters();
        
        p = new MainView();
        initListeners();
        p.setTheme();
        p.setVisible(true);
    }
    
    /**
     * Initializes file filters
     */
    private void initFilters(){        
        // Key
        keyFilter = new FileFilter() {
            
            @Override
            public String getDescription() {
                return "Key Files (*.key)";
            }
            
            @Override
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".key");
            }
        };
        
        // Key
        messageFilter = new FileFilter() {
            
            @Override
            public String getDescription() {
                return "Message Files (*.msg)";
            }
            
            @Override
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".msg");
            }
        };
    }
    
    /**
     * Initializes view event listeners
     */
    private void initListeners(){
        // Socket connection
        p.connectBtn.addActionListener((ActionEvent e) -> {
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
        });
        
        // Send Message
        p.sendBtn.addActionListener((ActionEvent e) -> {
            String message = p.messageInput.getText();
            
            if(message.isEmpty()){
                showMessageDialog(p, "El mensaje no puede estar en blanco");
            } else {
                try {
                    p.chatDisplay.setText(message);
                    lastMessage = MessageManager.sendMessage(message, remotePublicKey, (PrivateKey) rsaKeys.get("private"));
                } catch (Exception ex) {
                    showMessageDialog(p, "Error al generar el mensaje encriptad");
                }
            }
        });
        
        // Load message
        p.loadMessageBtn.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            
            fileChooser.setDialogTitle("Carga un mensaje de un compañero");
            fileChooser.setFileFilter(messageFilter);
            
            int returnValue = fileChooser.showOpenDialog(p);
            
            if(returnValue == JFileChooser.APPROVE_OPTION){
                // Load message file
                File selectedFile = fileChooser.getSelectedFile();
                Map<String, Object> messageData = null;
                try {
                    messageData = FileManager.readMessage(selectedFile.getAbsolutePath());
                } catch (Exception ex) {
                    showMessageDialog(p, "Error al cargar el mensaje encriptado");
                }
                
                // Decrypt message
                String plainMessage = "";
                try {
                    plainMessage = MessageManager.receiveMessage(messageData, remotePublicKey, (PrivateKey) rsaKeys.get("private"));
                } catch (Exception ex) {
                    showMessageDialog(p, "Error al desencriptar el mensaje");
                }
                
                // Print message to chat display
                p.chatDisplay.setText(plainMessage);
            }
        });
        
        // Export message
        p.exportMsgBtn.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            
            fileChooser.setDialogTitle("Exporta el último mensaje");
            fileChooser.setFileFilter(messageFilter);
            
            int returnValue = fileChooser.showOpenDialog(p);
            
            if(returnValue == JFileChooser.APPROVE_OPTION){
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    FileManager.saveMessage(lastMessage, selectedFile.getAbsolutePath());
                } catch (Exception ex) {
                    showMessageDialog(p, "Error al exportar el mensaje encriptado");
                }
            }
        });
        
        // Make keys
        p.makeKeysBtn.addActionListener((ActionEvent e) -> {
            try {
                rsaKeys = RSA.makeKeys(); // Private keys will be stored on volatile memory, not on disk
                showMessageDialog(p, "Claves generadas");
                
                // Enable key export/import buttons
                p.exportPKBtn.setEnabled(true);
                p.importPKBtn.setEnabled(true);
                
                // Delete tooltips
                p.exportMsgBtn.setToolTipText(null);
                p.importPKBtn.setToolTipText(null);
            } catch (Exception ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                showMessageDialog(p, "No se han podido generar las claves.");
            }
        });
        
        // Export public key
        p.exportPKBtn.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            
            fileChooser.setDialogTitle("Exporta tu clave pública");
            fileChooser.setFileFilter(keyFilter);
            
            int returnValue = fileChooser.showSaveDialog(p);
            
            if(returnValue == JFileChooser.APPROVE_OPTION){
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    KeyIO.serializePublic((PublicKey) rsaKeys.get("public"), selectedFile.getAbsolutePath());
                } catch (Exception ex) {
                    showMessageDialog(p, "Error al exportar la clave");
                }
                showMessageDialog(p, "Clave exportada");
            }
        });
        
        // Import remote public key
        p.importPKBtn.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            
            fileChooser.setDialogTitle("Importa la clave publica de tu compañero");
            fileChooser.setFileFilter(keyFilter);
            
            int returnValue = fileChooser.showOpenDialog(p);
            
            if(returnValue == JFileChooser.APPROVE_OPTION){
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    remotePublicKey = KeyIO.loadPublic(selectedFile.getAbsolutePath());
                } catch (Exception ex) {
                    showMessageDialog(p, "Error al importar la clave");
                }
                System.out.println(remotePublicKey.getAlgorithm());
                showMessageDialog(p, "Clave importada");
                
                // Enable send and export buttons
                p.sendBtn.setEnabled(true);
                p.exportMsgBtn.setEnabled(true);
                p.loadMessageBtn.setEnabled(true);
                
                // Delete tooltip text
                p.sendBtn.setToolTipText(null);
                p.exportMsgBtn.setToolTipText(null);
                p.loadMessageBtn.setToolTipText(null);
            }
        });
    }
    
    /**
     * Disables IP input
     */
    private void onConnect(){
        p.ipInput.setEnabled(false);
        p.connectBtn.setText("Desconectar");
    }
    
    /**
     * Enables IP input
     */
    private void onDisconnect(){
        p.ipInput.setEnabled(true);
        p.connectBtn.setText("Conectar");
    }
    
    
}
