package Presentation;

import Persistence.ReadJson;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MainInterface extends JFrame implements Runnable {

    JPanel principal, panel1, panel2, panel3, panel4, panel5, panel6, panelSup, panelInf;

    JLabel label1, label2, label3, label4, label5;

    int suma1, suma2, suma3, suma4, suma5;
    Horario1 h1;
    Horario2 h2;
    Horario3 h3;
    Horario4 h4;
    Horario5 h5;
    Dadopanel dc1, dc2, dc3, dc4, dc5, dc6, dc7, dc8, dc9, dc10;
    Button start;

    Button Acerca;

    ReadJson read = new ReadJson();

    public MainInterface() {
        setTitle("Juego");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        begin();

        setVisible(true);
    }

    public void begin() {
        beginComponents();
        addComponents();
        principal();
    }

    private void beginComponents() {
        principal = new JPanel();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();
        panel6 = new JPanel();
        panelInf = new JPanel();
        panelSup = new JPanel();

        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();


        h1 = new Horario1(label1);
        h2 = new Horario2(label2);
        h3 = new Horario3(label3);
        h4 = new Horario4(label4);
        h5 = new Horario5(label5);


        Thread hilo1 = new Thread(h1);
        Thread hilo2 = new Thread(h2);
        Thread hilo3 = new Thread(h3);
        Thread hilo4 = new Thread(h4);
        Thread hilo5 = new Thread(h5);

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();

        start = new Button("START");
        Acerca = new Button("ACERCA DE ");
    }


    private void addComponents() {
        //principal.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Border bordeGrueso = BorderFactory.createLineBorder(Color.red, 3);
        Border bordeGrueso1 = BorderFactory.createLineBorder(Color.green, 3);
        Border bordeGrueso2 = BorderFactory.createLineBorder(Color.pink, 3);
        Border bordeGrueso3 = BorderFactory.createLineBorder(Color.blue, 3);
        Border bordeGrueso4 = BorderFactory.createLineBorder(Color.orange, 3);
        Border bordeGrueso5 = BorderFactory.createLineBorder(Color.black, 3);
        panel1.setBorder(bordeGrueso);
        panel2.setBorder(bordeGrueso1);
        panel3.setBorder(bordeGrueso2);
        panel4.setBorder(bordeGrueso3);
        panel5.setBorder(bordeGrueso4);
        panel6.setBorder(bordeGrueso5);

        Dimension nuevoTamanio = new Dimension(100, 150);
        panel1.setPreferredSize(nuevoTamanio);
        panel2.setPreferredSize(nuevoTamanio);
        panel3.setPreferredSize(nuevoTamanio);
        panel4.setPreferredSize(nuevoTamanio);
        panel5.setPreferredSize(nuevoTamanio);
        panel6.setPreferredSize(nuevoTamanio);


        panel6.setLayout(new GridLayout(2, 1));
        panel6.add(start);
        panel6.add(Acerca);

        panelInf.setLayout(new GridLayout(1, 3));
        panelInf.add(panel4);
        panelInf.add(panel5);
        panelInf.add(panel6);
        panelSup.setLayout(new GridLayout(1, 3));
        panelSup.add(panel1);
        panelSup.add(panel2);
        panelSup.add(panel3);

        this.add(panelSup, BorderLayout.NORTH);
        this.add(principal, BorderLayout.CENTER);
        this.add(panelInf, BorderLayout.SOUTH);
    }

    private void principal(){

        panel1 = PanelFinal(panel1, ImagenesInformacion(read.getPlayers().get(0).getName()), PanelHorario(label1), auxiliar("Avanzas:"));
        panel2 = PanelFinal(panel2, ImagenesInformacion(read.getPlayers().get(1).getName()), PanelHorario(label2), auxiliar("Avanzas:"));
        panel3 = PanelFinal(panel3, ImagenesInformacion(read.getPlayers().get(2).getName()), PanelHorario(label3), auxiliar("Avanzas:"));
        panel4 = PanelFinal(panel4, ImagenesInformacion(read.getPlayers().get(3).getName()), PanelHorario(label4), auxiliar("Avanzas:"));
        panel5 = PanelFinal(panel5, ImagenesInformacion(read.getPlayers().get(4).getName()), PanelHorario(label5),auxiliar("Avanzas:"));
    }

    public synchronized JPanel dados(Dadopanel dcz, Dadopanel dcx,String valor) {
        JPanel principal = new JPanel();
        principal.setLayout(new GridLayout(2, 1));
        Random random = new Random();
        int randomNumber = random.nextInt(9001) + 1000;
        JPanel op = DiceAnimation(dcz, randomNumber);
        JPanel op1 = DiceAnimation(dcx, randomNumber);
        JPanel aux2 = new JPanel();
        aux2.setLayout(new GridLayout(1, 2));
        aux2.add(op);
        aux2.add(op1);
        principal.add(aux2);
        principal.add(new JLabel(valor));
        return principal;
    }

    public synchronized JPanel DiceAnimation(Dadopanel dado,int a) {
        Timer timer = new Timer(a, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        timer.start();
        return dado;
    }
    private static JPanel PanelHorario(JLabel label) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(label);
        JLabel lleva = new JLabel("Llevas :");
        JLabel falta = new JLabel("Te faltan :");
        panel.add(lleva);
        panel.add(falta);
        return panel;
    }

    private static JPanel PanelFinal(JPanel panel, JPanel aux1, JPanel aux2, JPanel aux3) {
        panel.setLayout(new GridLayout(1, 3));
        panel.add(aux1);
        panel.add(aux2);
        panel.add(aux3);
        return panel;
    }
    private  JPanel ImagenesInformacion(String aux){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        JPanel panel1 = new JPanel();
        ImageIcon imagen = new ImageIcon("");
        JLabel labelImagen = new JLabel(imagen);
        panel1.add(labelImagen);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 1));
        JLabel label1 = new JLabel(aux);
        panel2.add(label1);

        panel.add(panel1);
        panel.add(panel2);

        return  panel;
    }
    private JPanel auxiliar(String valor){
        JPanel princ = new JPanel();
        princ.setLayout(new GridLayout(2, 1));
        JPanel op = new Dadopanel(RandomPair());
        JPanel op1 = new Dadopanel(RandomPair());
        JPanel aux2 = new JPanel();
        aux2.setLayout(new GridLayout(1, 2));
        aux2.add(op);
        aux2.add(op1);
        princ.add(aux2);
        princ.add(new JLabel(valor));
        return princ;
    }
    public static int[] RandomPair() {
        Random random = new Random();

        int dado1 = random.nextInt(6) + 1;
        int dado2 = random.nextInt(6) + 1;

        return new int[]{dado1, dado2};
    }


    @Override
    public void run() {
        start.addActionListener((e) -> {

            start.setEnabled(false);

            panel1.removeAll();
            panel2.removeAll();
            panel3.removeAll();
            panel4.removeAll();
            panel5.removeAll();


            panel1 = PanelFinal(panel1, ImagenesInformacion(read.getPlayers().get(0).getName()), PanelHorario(label1),dados(dc1,dc2,"Avanzas:") );
            panel2 = PanelFinal(panel2, ImagenesInformacion(read.getPlayers().get(1).getName()), PanelHorario(label2), dados(dc3, dc4,"Avanzas:"));
            panel3 = PanelFinal(panel3, ImagenesInformacion(read.getPlayers().get(2).getName()), PanelHorario(label3), dados(dc5, dc6,"Avanzas:"));
            panel4 = PanelFinal(panel4, ImagenesInformacion(read.getPlayers().get(3).getName()), PanelHorario(label4), dados(dc7, dc8,"Avanzas:"));
            panel5 = PanelFinal(panel5, ImagenesInformacion(read.getPlayers().get(4).getName()), PanelHorario(label5), dados(dc9, dc10,"Avanzas:"));


            panel1.revalidate();
            panel2.revalidate();
            panel3.revalidate();
            panel4.revalidate();
            panel5.revalidate();

            panel1.repaint();
            panel2.repaint();
            panel3.repaint();
            panel4.repaint();
            panel5.repaint();
        });
    }
}
