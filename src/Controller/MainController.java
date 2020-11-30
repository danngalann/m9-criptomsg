/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Socket.ChatClient;
import View.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
