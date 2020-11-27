/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.MainView;

/**
 *
 * @author Daniel Galán
 */
public class MainController {
    
    MainView p = null;

    public MainController() {
        p = new MainView();
        p.setVisible(true);
    }
    
    
}
