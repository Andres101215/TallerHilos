package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Dadopanel  extends JPanel implements  Runnable{
    private int valor1;
    private int valor2;

    private JLabel label1;

    private JLabel label2;

    public Dadopanel(int valor1, int valor2) {
        this.valor1 = valor1;
        this.valor2 = valor2;
    }

    public void Dados(){

    }


    @Override
    public void run() {
        Dados();
    }
}
