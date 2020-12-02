/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import com.bulenkov.darcula.DarculaLaf;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.basic.BasicLookAndFeel;

/**
 *
 * @author Daniel Galán
 */
public class MainView extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public MainView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sendBtn = new javax.swing.JButton();
        loadMessageBtn = new javax.swing.JButton();
        jScrollPaneInput = new javax.swing.JScrollPane();
        messageInput = new javax.swing.JTextArea();
        jScrollPaneChat1 = new javax.swing.JScrollPane();
        chatDisplay = new javax.swing.JTextArea();
        connectBtn = new javax.swing.JButton();
        exportPKBtn = new javax.swing.JButton();
        ipInput = new javax.swing.JTextField();
        makeKeysBtn = new javax.swing.JButton();
        importPKBtn = new javax.swing.JButton();
        exportMsgBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CryptoMsg");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        sendBtn.setText("Enviar");
        sendBtn.setToolTipText("Necesario importar clave pública ");
        sendBtn.setEnabled(false);

        loadMessageBtn.setText("Cargar mensaje");
        loadMessageBtn.setToolTipText("Necesario importar clave pública ");
        loadMessageBtn.setEnabled(false);

        messageInput.setColumns(20);
        messageInput.setLineWrap(true);
        messageInput.setRows(5);
        messageInput.setName("txtInput"); // NOI18N
        jScrollPaneInput.setViewportView(messageInput);

        chatDisplay.setEditable(false);
        chatDisplay.setColumns(20);
        chatDisplay.setLineWrap(true);
        chatDisplay.setRows(5);
        chatDisplay.setName("txtChat"); // NOI18N
        jScrollPaneChat1.setViewportView(chatDisplay);

        connectBtn.setText("Conectar");

        exportPKBtn.setText("Exportar PK");
        exportPKBtn.setToolTipText("Necesario generar claves");
        exportPKBtn.setEnabled(false);

        makeKeysBtn.setText("Generar Claves");

        importPKBtn.setText("Importar PK");
        importPKBtn.setToolTipText("Necesario generar claves");
        importPKBtn.setEnabled(false);

        exportMsgBtn.setText("Exportar");
        exportMsgBtn.setToolTipText("Necesario importar clave pública ");
        exportMsgBtn.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPaneInput, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exportMsgBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(46, 46, 46))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ipInput, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(connectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loadMessageBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(makeKeysBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(importPKBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exportPKBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addComponent(jScrollPaneChat1, javax.swing.GroupLayout.PREFERRED_SIZE, 798, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(36, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ipInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(connectBtn)
                    .addComponent(loadMessageBtn)
                    .addComponent(exportPKBtn)
                    .addComponent(makeKeysBtn)
                    .addComponent(importPKBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 349, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPaneInput)
                    .addComponent(sendBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exportMsgBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(39, 39, 39))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(63, 63, 63)
                    .addComponent(jScrollPaneChat1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(159, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainView().setVisible(true);
            }
        });
    }
    
    public void setTheme(){
        try {            
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                }                    
                System.out.println(info.getName() + ", " + info.getClassName());
            }
//            BasicLookAndFeel darcula = new DarculaLaf();
//            UIManager.setLookAndFeel(darcula);
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextArea chatDisplay;
    public javax.swing.JButton connectBtn;
    public javax.swing.JButton exportMsgBtn;
    public javax.swing.JButton exportPKBtn;
    public javax.swing.JButton importPKBtn;
    public javax.swing.JTextField ipInput;
    private javax.swing.JScrollPane jScrollPaneChat1;
    private javax.swing.JScrollPane jScrollPaneInput;
    public javax.swing.JButton loadMessageBtn;
    public javax.swing.JButton makeKeysBtn;
    public javax.swing.JTextArea messageInput;
    public javax.swing.JButton sendBtn;
    // End of variables declaration//GEN-END:variables
}
