/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playball_v2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Dceron
 */
public class windowsBall extends JFrame{
    
    Container containerWindow;
    layoutWest west;
    CanvasBall canvasWindow;

    public windowsBall(String title) {
        super(title);
        this.setBounds(0, 0, 1300, 750);
        this.setBackground(new Color(96, 111, 140));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        west = new layoutWest();
        canvasWindow = new CanvasBall();
        container();
    }

    public final void container() {
         containerWindow = this.getContentPane();
        containerWindow.setLayout(new BorderLayout(3, 10));
        
        canvasWindow.westInCanvas=this.west;
        west.canvas=this.canvasWindow;
        containerWindow.add(west,BorderLayout.WEST);
        containerWindow.add(canvasWindow,BorderLayout.CENTER);
        
    }
    
}
