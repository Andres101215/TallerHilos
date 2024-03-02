package Presentation;

import javax.swing.*;
import java.awt.*;

public class Dadopanel extends JPanel implements Runnable {
    private int valor1;
    private int valor2;

    private JLabel label1;
    private JLabel label2;

    public Dadopanel(int valorDados[]) {
        this.valor1 = valorDados[0];
        this.valor2 = valorDados[1];
        this.setLayout(new GridLayout(1,2));
        label1 = new JLabel();
        label2 = new JLabel();
        this.add(label1);
        this.add(label2);
        Dados();
    }

    public void Dados() {
        ImageIcon icon1 = new ImageIcon(getClass().getClassLoader().getResource("src/main/resources/Dice"+valor1+".png"));
        label1.setIcon(icon1);
        ImageIcon icon2 = new ImageIcon(getClass().getClassLoader().getResource("src/main/resources/Dice"+valor2+".png"));
        label2.setIcon(icon2);
    }

    @Override
    public void run() {
        Dados();
    }
}

