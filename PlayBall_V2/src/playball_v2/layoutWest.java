/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playball_v2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *
 * @author Dceron
 */
public class layoutWest extends JPanel implements ActionListener {

    CanvasBall canvas;
    private Box WestBox;
    private JButton start, stop;
    private JLabel points;
    private JLabel life;

    public layoutWest() {
        this.setBackground(new Color(140, 190, 178));
        initComponents();
    }

    public final void initComponents() {
        WestBox = Box.createVerticalBox();
        WestBox.setBorder(BorderFactory.createTitledBorder("Play Ball"));
        start = new JButton("Start");
        start.addActionListener(this);
        stop = new JButton("Stop");
        stop.addActionListener(this);
        points = new JLabel("Puntos: 0");
        life = new JLabel("Vidas:😜 3");
        WestBox.add(Box.createVerticalStrut(10));//Crea un espacio entre dos componentes
        WestBox.add(start);
        WestBox.add(Box.createVerticalStrut(10));//Crea un espacio entre dos componentes
        WestBox.add(stop);
        WestBox.add(Box.createVerticalStrut(10));//Crea un espacio entre dos componentes
        WestBox.add(points);
        WestBox.add(life);
        this.add(WestBox);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "Start":
                canvas.start();
                break;
            case "Stop":
                canvas.pause();
                break;
        }
    }

    public CanvasBall getCanvas() {
        return canvas;
    }

    public void setCanvas(CanvasBall canvas) {
        this.canvas = canvas;
    }

    public Box getWestBox() {
        return WestBox;
    }

    public void setWestBox(Box WestBox) {
        this.WestBox = WestBox;
    }

    public JButton getStart() {
        return start;
    }

    public void setStart(JButton start) {
        this.start = start;
    }

    public JButton getStop() {
        return stop;
    }

    public void setStop(JButton stop) {
        this.stop = stop;
    }

    public JLabel getPoints() {
        return points;
    }

    public void setPoints(JLabel points) {
        this.points = points;
    }

    public JLabel getLife() {
        return life;
    }

    public void setLife(JLabel life) {
        this.life = life;
    }

}
