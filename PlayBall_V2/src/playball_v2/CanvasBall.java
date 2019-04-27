/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playball_v2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Dceron
 */
public class CanvasBall extends Canvas implements Runnable, MouseListener, KeyListener {

    layoutWest westInCanvas;
    Graphics2D buffer;
    private Image imageOff, backgroundImage;
    //  private Dimension dimensionOff;
    private Dimension dim;
    private Thread th;
    private Ellipse2D.Float ellipse;
    private ArrayList<Ellipse2D.Float> ListBalls;
    private int width, heigh, x, y;
    private int number, points, lives, clicks, clicksOn, figuresQuantity;
    private boolean pausePlay, canPlay;
    private URL url;

    public CanvasBall() {
        ListBalls = new ArrayList();
        figuresQuantity = 0;
        System.out.println("" + this.getWidth());
        x = new java.util.Random().nextInt((int) (400));
        y = new java.util.Random().nextInt((int) (500));
        System.out.println("" + x);
        //y =(int) (Math.random() * 400 + 100) - 100;
        ellipse = createEllipse(x, y, 100, 100);
        ListBalls.add(ellipse);
        width = 100;
        heigh = 100;
        number = 2000;
        points = 0;
        lives = 3;
        canPlay = true;
        clicks = clicksOn = 1;
        pausePlay = true;
        url = getClass().getResource("/Images/BackgroundImage5.jpg");
        backgroundImage = new ImageIcon(url).getImage();
        addMouseListener(this);
        addKeyListener(this);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        repaint();
        //g2.setColor(new Color(193, 99, 66));
        g2.setColor(new Color(252, 67, 73));
        for (int i = 0; i < ListBalls.size(); i++) {
            Shape get = ListBalls.get(i);
            g2.fill(get);
        }

    }

    @Override
    public void update(Graphics g) {
        dim = getSize();

        // dimensionOff = dim;
        imageOff = createImage(dim.width, dim.height);
        buffer = (Graphics2D) imageOff.getGraphics();//se convierte la imagen en Graphics 2D y lo guardamos en el buffer, se va a pintar con el buffer.

        buffer.setColor(getBackground());
        paint(buffer);

        g.drawImage(imageOff, 0, 0, this);
    }

    private Ellipse2D.Float createEllipse(int x1, int y1, int x2, int y2) {
        figuresQuantity += 1;
        return new Ellipse2D.Float(x1, y1, x2, y2);//Math.min retorna el mas pequeño de los dos
    }

    public void start() {

        if (th == null) {
            th = new Thread(this);
            th.start();
            canPlay = true;

        } else {
            th.resume();
            canPlay = true;
            pausePlay = true;
        }
    }

    public void stop() {
        th.stop();
//        pausePlay = false;
//       th.suspend();
        canPlay = false;
    }

    public void pause() {
        th.suspend();
        canPlay = false;
    }

    @Override
    public void run() {
        while (pausePlay) {

            for (int i = 0; i < ListBalls.size(); i++) {
                Ellipse2D.Float c = ListBalls.get(i);
                c.x = new java.util.Random().nextInt((int) (this.getWidth() - c.width));
                c.y = new java.util.Random().nextInt((int) (this.getHeight() - c.height));
//                c.x = (int) (Math.random() * (this.getWidth() - c.width));
//              y = (int) (Math.random() * 400 + 100) - 100;

            }
            repaint();
            try {

                Thread.sleep(number);
            } catch (InterruptedException ex) {
                System.out.println("Error en " + ex.toString());
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (th != null) {
            if (canPlay) {
                pause();
                clicks++;
                for (int i = 0; i < ListBalls.size(); i++) {
                    Ellipse2D.Float c = ListBalls.get(i);
                    if (c.contains(me.getPoint().x, me.getPoint().y)) {

                        if (c.width > 50) {
                            c.width -= 10;
                            c.height -= 10;
                            points += 10;
                            westInCanvas.getPoints().setText("Puntos " + points);
                        } else {
                            ellipse = createEllipse(x, y, 100, 100);
                            ListBalls.add(ellipse);
                            number -= 100;
                            points += 10;
                            westInCanvas.getPoints().setText("Puntos" + points);
                        }
                        clicksOn++;
                    } else {

                    }
                }
                if (clicks != clicksOn) {
                    System.out.println("perdio");
                    if (lives > 1) {
                        lives -= 1;
                        westInCanvas.getLife().setText("Vidas 👾" + lives);
                        for (int i = 0; i < ListBalls.size(); i++) {
                            Ellipse2D.Float c = ListBalls.get(i);
                            if (c.width < 100) {
                                c.width += 10;
                                c.height += 10;
                            }
                        }
                        clicks = clicksOn = 1;
                    } else {
                        //figuresQuantity=ListBalls.size();
                        if (JOptionPane.showConfirmDialog(null, "Puntaje: " + points + "\nCantidad de figuras: " + figuresQuantity + "\n¿Desea jugar de nuevo?", "Game Over", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                            pause();
                            ListBalls.clear();
                             figuresQuantity=0;
                            ellipse = createEllipse(x, y, 100, 100);
                            ListBalls.add(ellipse);
                            lives = 3;
                            points = 0;
                            westInCanvas.getPoints().setText("Puntos" + points);
                            westInCanvas.getLife().setText("Vidas 👾" + lives);
                            clicks = clicksOn = 1;
                            number = 2000;
                           
                        } else {
                            System.exit(0);
                        }

                    }
                }
                repaint();
                start();
            } else {
                System.out.println("Estado del hilo pausado");
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
            start();
        }
        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            pause();
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
}
