package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Dadopanel  extends JPanel implements  Runnable{
    private int faceValue;
    private Random random;
    public synchronized int getFaceValue() {
        return faceValue;
    }
    public Dadopanel() {
        faceValue = 1;
        random = new Random();
        setPreferredSize(new Dimension(18, 18));
    }

    public synchronized void roll() {
       faceValue = random.nextInt(6) + 1;
        repaint();
    }

    @Override
    protected synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawDice(g, getWidth() / 2, getHeight() / 2);
    }

    private synchronized void drawDice(Graphics g, int x, int y) {
        int size = 75;
        int dotSize = 20;

        g.drawRect(x - size / 2, y - size / 2, size, size);

        switch (faceValue) {
            case 1:
                drawDot(g, x, y);
                break;
            case 2:
                drawDot(g, x - size / 4, y - size / 4);
                drawDot(g, x + size / 4, y + size / 4);
                break;
            case 3:
                drawDot(g, x - size / 4, y - size / 4);
                drawDot(g, x, y);
                drawDot(g, x + size / 4, y + size / 4);
                break;
            case 4:
                drawDot(g, x - size / 4, y - size / 4);
                drawDot(g, x + size / 4, y - size / 4);
                drawDot(g, x - size / 4, y + size / 4);
                drawDot(g, x + size / 4, y + size / 4);
                break;
            case 5:
                drawDot(g, x - size / 4, y - size / 4);
                drawDot(g, x + size / 4, y - size / 4);
                drawDot(g, x, y);
                drawDot(g, x - size / 4, y + size / 4);
                drawDot(g, x + size / 4, y + size / 4);
                break;
            case 6:
                drawDot(g, x - size / 4, y - size / 4);
                drawDot(g, x + size / 4, y - size / 4);
                drawDot(g, x - size / 4, y);
                drawDot(g, x + size / 4, y);
                drawDot(g, x - size / 4, y + size / 4);
                drawDot(g, x + size / 4, y + size / 4);
                break;
        }
    }

    private synchronized void drawDot(Graphics g, int x, int y) {
        g.fillOval(x - 10, y - 10, 18, 18);
    }

    @Override
    public void run() {


    }
}
