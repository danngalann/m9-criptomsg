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
    FileFilter binaryFilter;
    FileFilter keyFilter;

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
        binaryFilter = new FileFilter() {
            
            @Override
            public String getDescription() {
                return "Binary Files (*.bin)";
            }
            
            @Override
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".bin");
            }
        };
        
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
                // TODO: Send message to MessageManager
            }
        });
        
        // Load message
        p.loadFileBtn.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            
            fileChooser.setDialogTitle("Carga un mensaje de un compañero");
            fileChooser.setFileFilter(binaryFilter);
            
            int returnValue = fileChooser.showOpenDialog(p);
            
            if(returnValue == JFileChooser.APPROVE_OPTION){
                File selectedFile = fileChooser.getSelectedFile();
                // TODO: Send file to MessageManager
                System.out.println(selectedFile.getAbsolutePath());
            }
        });
        
        // Make keys
        p.makeKeysBtn.addActionListener((ActionEvent e) -> {
            try {
                rsaKeys = RSA.makeKeys(); // Private keys will be stored on volatile memory, not on disk
                showMessageDialog(p, "Claves generadas");
                p.exportPKBtn.setEnabled(true);
                p.importPKBtn.setEnabled(true);
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
                KeyIO.serializePublic((PublicKey) rsaKeys.get("public"), selectedFile.getAbsolutePath());
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
                remotePublicKey = KeyIO.loadPublic(selectedFile.getAbsolutePath());
                System.out.println(remotePublicKey.getAlgorithm());
                showMessageDialog(p, "Clave importada");
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
