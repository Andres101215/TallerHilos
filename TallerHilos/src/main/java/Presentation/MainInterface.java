package Presentation;

import Logic.Player;
import Persistence.ReadJson;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Random;

public class MainInterface extends JFrame implements Runnable {

    JPanel principal, panel1, panel2, panel3, panel4, panel5, panel6, panelSup, panelInf;

    JLabel label1, label2, label3, label4, label5;

    int suma1, suma2, suma3, suma4, suma5, pointsformatch;
    Horario1 h1;
    Horario2 h2;
    Horario3 h3;
    Horario4 h4;
    Horario5 h5;
    Dadopanel dc1, dc2, dc3, dc4, dc5;
    JButton start;

    JButton Acerca;
    ReadJson read = new ReadJson();

    int con=0;

    ArrayList<Timer> Timers = new ArrayList<>();

    ArrayList<Player> players = new ArrayList<>();
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

        start = new JButton("START");
        Acerca = new JButton("ACERCA DE ");

        int aux[] = {1, 1};

        dc1 = new Dadopanel(aux);
        dc2 = new Dadopanel(aux);
        dc3 = new Dadopanel(aux);
        dc4 = new Dadopanel(aux);
        dc5 = new Dadopanel(aux);

        pointsformatch = 0;
        start = new JButton("START");
        Acerca = new JButton("ACERCA DE ");

        Acerca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AcercaDeVentana ventanaInformacion = new AcercaDeVentana();
                ventanaInformacion.setVisible(true);
            }
        });
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

        Dimension nuevoTamanio = new Dimension(100, 200);
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

    private void principal() {

        panel1 = PanelFinal(panel1, ImagenesInformacion(read.getPlayers().get(0).getName(), "/colombia.png"), PanelHorario(label1, "", ""), auxiliar(dc1, "Avanzas:", ""));
        panel2 = PanelFinal(panel2, ImagenesInformacion(read.getPlayers().get(1).getName(), "/japon.png"), PanelHorario(label2, "", ""), auxiliar(dc2, "Avanzas:", ""));
        panel3 = PanelFinal(panel3, ImagenesInformacion(read.getPlayers().get(2).getName(), "/francia.png"), PanelHorario(label3, "", ""), auxiliar(dc3, "Avanzas:", ""));
        panel4 = PanelFinal(panel4, ImagenesInformacion(read.getPlayers().get(3).getName(), "/inglaterra.png"), PanelHorario(label4, "", ""), auxiliar(dc4, "Avanzas:", ""));
        panel5 = PanelFinal(panel5, ImagenesInformacion(read.getPlayers().get(4).getName(), "/egipto.png"), PanelHorario(label5, "", ""), auxiliar(dc5, "Avanzas:", ""));
    }

    private static JPanel PanelHorario(JLabel label, String punto, String faltan) {
        JPanel panel = new JPanel();
        JLabel puntos = new JLabel("Puntos: " + punto);
        JLabel falta = new JLabel("Te faltan: " + faltan);
        panel.setLayout(new GridLayout(3, 1));
        panel.add(label);
        panel.add(puntos);
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

    private JPanel ImagenesInformacion(String aux, String ruta) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        JPanel panel1 = new JPanel();
        JLabel labelimagen = new JLabel();
        labelimagen.setIcon(new ImageIcon(getClass().getResource(ruta)));
        panel1.add(labelimagen);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 1));
        JLabel label1 = new JLabel(aux);
        panel2.add(label1);

        panel.add(panel1);
        panel.add(panel2);

        return panel;
    }

    private JPanel auxiliar(Dadopanel panel, String valor, String llevas) {
        JPanel princ = new JPanel();
        princ.setLayout(new GridLayout(3, 1));
        JLabel lleva = new JLabel("Llevas :" + llevas);
        princ.add(panel);
        princ.add(new JLabel(valor));
        princ.add(lleva);
        return princ;
    }

    public int[] RandomPair() {
        Random random = new Random();
        int valor[] = new int[2];

        valor[0] = random.nextInt(6) + 1;
        valor[1] = random.nextInt(6) + 1;

        return valor;
    }

    public synchronized int randomtime() {
        Random random = new Random();
        int randomNumber = random.nextInt(7001) + 3000;
        return randomNumber;
    }
    public void asignarPuntuacion(ArrayList<Player> aux){
        for (Player p:aux) {
            System.out.println(p.getName());
        }
        aux.get(0).setScore(5+aux.get(0).getScore());
        aux.get(1).setScore(4+aux.get(1).getScore());
        aux.get(2).setScore(3+aux.get(2).getScore());
        aux.get(3).setScore(2+aux.get(3).getScore());
        aux.get(4).setScore(1+aux.get(4).getScore());
    }
    @Override
    public void run() {
        pointsformatch = 200;
        start.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {

                start.setEnabled(false);

                Timer timer1 = new Timer(randomtime(), new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (read.getPlayers().get(0).getPoints() > pointsformatch) {
                            Timers.get(0).stop();
                            players.add(read.getPlayers().get(0));
                            con++;
                            if(con==5){
                                asignarPuntuacion(players);
                                read.creararchivoJson(read.getPlayers());
                            }

                        } else {
                            int dados1[] = RandomPair();
                            suma1 = dados1[0] + dados1[1];
                            read.getPlayers().get(0).setPoints(read.getPlayers().get(0).getPoints() + suma1);
                            dc1 = new Dadopanel(dados1);
                            int aux = pointsformatch - read.getPlayers().get(0).getPoints();
                            aux = (aux < 0) ? 0 : aux;
                            panel1.removeAll();
                            panel1 = PanelFinal(panel1, ImagenesInformacion(read.getPlayers().get(0).getName(), "/colombia.png"), PanelHorario(label1, read.getPlayers().get(0).getScore() + "", aux + ""), auxiliar(dc1, "Avanzas:" + suma1, read.getPlayers().get(0).getPoints() + ""));
                            panel1.revalidate();
                            panel1.repaint();
                        }
                    }
                });
                Timers.add(timer1);
                timer1.start();

                Timer timer2 = new Timer(randomtime(), new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (read.getPlayers().get(1).getPoints() > pointsformatch) {
                            Timers.get(1).stop();
                            players.add(read.getPlayers().get(1));
                            con++;
                            if(con==5){
                                asignarPuntuacion(players);
                                read.creararchivoJson(read.getPlayers());
                            }

                        } else {
                            int dados2[] = RandomPair();
                            suma2 = dados2[0] + dados2[1];
                            read.getPlayers().get(1).setPoints(read.getPlayers().get(1).getPoints() + suma2);
                            dc2 = new Dadopanel(dados2);
                            int aux = pointsformatch - read.getPlayers().get(1).getPoints();
                            aux = (aux < 0) ? 0 : aux;
                            panel2.removeAll();
                            panel2 = PanelFinal(panel2, ImagenesInformacion(read.getPlayers().get(1).getName(), "/japon.png"), PanelHorario(label2, read.getPlayers().get(1).getScore() + "", aux + ""), auxiliar(dc2, "Avanzas:" + suma2, read.getPlayers().get(1).getPoints() + ""));
                            panel2.revalidate();
                            panel2.repaint();
                        }
                    }
                });
                Timers.add(timer2);
                timer2.start();

                Timer timer3 = new Timer(randomtime(), new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (read.getPlayers().get(2).getPoints() > pointsformatch) {

                            Timers.get(2).stop();
                            players.add(read.getPlayers().get(2));
                            con++;
                            if(con==5){
                                asignarPuntuacion(players);
                                read.creararchivoJson(read.getPlayers());
                            }
                        } else {
                            int dados3[] = RandomPair();
                            suma3 = dados3[0] + dados3[1];
                            read.getPlayers().get(2).setPoints(read.getPlayers().get(2).getPoints() + suma3);
                            dc3 = new Dadopanel(dados3);
                            int aux = pointsformatch - read.getPlayers().get(2).getPoints();
                            aux = (aux < 0) ? 0 : aux;
                            panel3.removeAll();
                            panel3 = PanelFinal(panel3, ImagenesInformacion(read.getPlayers().get(2).getName(), "/francia.png"), PanelHorario(label3, read.getPlayers().get(2).getScore() + "", aux + ""), auxiliar(dc3, "Avanzas:" + suma3, read.getPlayers().get(2).getPoints() + ""));
                            panel3.revalidate();
                            panel3.repaint();
                        }
                    }
                });
                Timers.add(timer3);
                timer3.start();

                Timer timer4 = new Timer(randomtime(), new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (read.getPlayers().get(3).getPoints() > pointsformatch) {
                            Timers.get(3).stop();
                            players.add(read.getPlayers().get(3));
                            con++;

                            if(con==5){
                                asignarPuntuacion(players);
                                read.creararchivoJson(read.getPlayers());
                            }

                        } else {
                            int dados4[] = RandomPair();
                            suma4 = dados4[0] + dados4[1];
                            read.getPlayers().get(3).setPoints(read.getPlayers().get(3).getPoints() + suma4);
                            dc4 = new Dadopanel(dados4);
                            int aux = pointsformatch - read.getPlayers().get(3).getPoints();
                            aux = (aux < 0) ? 0 : aux;
                            panel4.removeAll();
                            panel4 = PanelFinal(panel4, ImagenesInformacion(read.getPlayers().get(3).getName(), "/inglaterra.png"), PanelHorario(label4, read.getPlayers().get(3).getScore() + "", aux + ""), auxiliar(dc4, "Avanzas:" + suma4, read.getPlayers().get(3).getPoints() + ""));
                            panel4.revalidate();
                            panel4.repaint();
                        }
                    }
                });
                Timers.add(timer4);
                timer4.start();
                Timer timer5 = new Timer(randomtime(), new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (read.getPlayers().get(4).getPoints() > pointsformatch) {
                            Timers.get(4).stop();
                            players.add(read.getPlayers().get(4));
                            con++;

                            if(con==5){
                                asignarPuntuacion(players);
                                read.creararchivoJson(read.getPlayers());
                            }

                        } else {
                            int dados5[] = RandomPair();
                            suma5 = dados5[0] + dados5[1];
                            read.getPlayers().get(4).setPoints(read.getPlayers().get(4).getPoints() + suma5);
                            dc5 = new Dadopanel(dados5);
                            int aux = pointsformatch - read.getPlayers().get(4).getPoints();
                            aux = (aux < 0) ? 0 : aux;
                            panel5.removeAll();
                            panel5 = PanelFinal(panel5, ImagenesInformacion(read.getPlayers().get(4).getName(), "/egipto.png"), PanelHorario(label5, read.getPlayers().get(4).getScore() + "", aux + ""), auxiliar(dc5, "Avanzas:" + suma5, read.getPlayers().get(4).getPoints() + ""));
                            panel5.revalidate();
                            panel5.repaint();
                        }
                    }
                });
                Timers.add(timer5);
                timer5.start();

            });
        });
    }
}
