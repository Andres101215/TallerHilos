package Presentation;

import javax.swing.*;
import java.awt.*;

public class Dadopanel extends JPanel {
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
        Dados();
        this.add(label1);
        this.add(label2);

    }

    public void Dados() {

        this.label1.setIcon(new ImageIcon(getClass().getResource("/Dice"+valor1+".png")
        ));
        this.label2.setIcon(new ImageIcon(getClass().getResource("/Dice"+valor2+".png")
        ));
    }
}

