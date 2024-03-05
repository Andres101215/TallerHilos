package Presentation;

import Logic.Player;
import Persistence.ReadJson;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MainInterface extends JFrame  {

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

    int con = 0,partidas=1;

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
        principal.add(podioPanel(read.getPlayers()));
        panel1 = PanelFinal(panel1, ImagenesInformacion(read.getPlayers().get(0).getName(), "/colombia.png",Color.RED), PanelHorario(label1, "", "",Color.RED), auxiliar(dc1, "Avanzas:", "",Color.RED));
        panel2 = PanelFinal(panel2, ImagenesInformacion(read.getPlayers().get(1).getName(), "/japon.png",Color.green), PanelHorario(label2, "", "",Color.green), auxiliar(dc2, "Avanzas:", "",Color.green));
        panel3 = PanelFinal(panel3, ImagenesInformacion(read.getPlayers().get(2).getName(), "/francia.png",Color.pink), PanelHorario(label3, "", "",Color.pink), auxiliar(dc3, "Avanzas:", "",Color.pink));
        panel4 = PanelFinal(panel4, ImagenesInformacion(read.getPlayers().get(3).getName(), "/inglaterra.png",Color.CYAN), PanelHorario(label4, "", "",Color.CYAN), auxiliar(dc4, "Avanzas:", "",Color.CYAN));
        panel5 = PanelFinal(panel5, ImagenesInformacion(read.getPlayers().get(4).getName(), "/egipto.png",Color.orange), PanelHorario(label5, "", "",Color.orange), auxiliar(dc5, "Avanzas:", "",Color.orange));
    }

    private static JPanel PanelHorario(JLabel label, String punto, String faltan,Color color) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
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

    private static JPanel ImagenesInformacion(String aux, String ruta,Color color) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        JPanel panel1 = new JPanel();
        JLabel labelimagen = new JLabel();
        labelimagen.setIcon(new ImageIcon(MainInterface.class.getResource(ruta)));

        panel1.add(labelimagen);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 1));
        JLabel label1 = new JLabel(aux);
        panel2.add(label1);

        panel.add(panel1);
        panel.add(panel2);

        panel1.setBackground(color);
        panel2.setBackground(color);

        return panel;
    }

    private  static JPanel auxiliar(Dadopanel panel, String valor, String llevas,Color color) {
        JPanel princ = new JPanel();
        princ.setLayout(new GridLayout(3, 1));
        JLabel lleva = new JLabel("Llevas :" + llevas);
        panel.setBackground(color);
        princ.setBackground(color);
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
        int randomNumber = random.nextInt(4001) + 1000;
        return randomNumber;
    }

    public void asignarPuntuacion(ArrayList<Player> aux) {
        aux.get(0).setScore(5 + aux.get(0).getScore());
        aux.get(1).setScore(4 + aux.get(1).getScore());
        aux.get(2).setScore(3 + aux.get(2).getScore());
        aux.get(3).setScore(2 + aux.get(3).getScore());
        aux.get(4).setScore(1 + aux.get(4).getScore());
    }

    public void reiniciar(){
        con=0;
        for (Player p:read.getPlayers()) {
            p.setPoints(0);
        }
        read.creararchivoJson(read.getPlayers());
        partidas--;

        for (Timer a:Timers) {
            a.start();
        }
    }

    public static JPanel podioPanel(ArrayList<Player> players) {



        for (Player p: players) {
            System.out.println(p.getName());
        }
        JPanel panel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    File imageFile = new File("src/main/resources/podio.png");
                    if (imageFile.exists()) {
                        BufferedImage image = ImageIO.read(imageFile);
                        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        panel.setLayout(new GridLayout(1, 1));

        // Solo procede si la lista 'players' no está vacía
        if (!players.isEmpty()) {
            int[] nuevoOrden = {3, 1, 0, 2, 4};

            int[] x = {30, 250, 460, 670, 830};
            int[] y = {400, 370, 280, 380, 450};
            int[] aumentoAncho = {40, 20, 30, 25, 35};
            int[] aumentoAlto = {40, 30, 20, 35, 25};

            for (int i = 0; i ==4; i++) {
                int indice = nuevoOrden[i];
                Player player = players.get(indice);
                String lugar = player.getLocation();
                try {
                    File imagenFile = new File("src/main/resources/" + lugar + ".png");
                    if (imagenFile.exists()) {
                        BufferedImage imagen = ImageIO.read(imagenFile);
                        JLabel label = new JLabel(new ImageIcon(imagen));

                        label.setBounds(x[i], y[i], imagen.getWidth() + aumentoAncho[i], imagen.getHeight() + aumentoAlto[i]);

                        panel.add(label);

                        JLabel nameLabel = new JLabel(player.getName());
                        nameLabel.setBounds(x[i] - 40, y[i] + imagen.getHeight() + 20, imagen.getWidth() + 80, 20);
                        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        nameLabel.setBackground(Color.WHITE);
                        nameLabel.setOpaque(true);
                        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
                        panel.add(nameLabel);

                        JLabel locationLabel = new JLabel(player.getLocation());
                        locationLabel.setBounds(x[i] - 40, y[i] + imagen.getHeight() + 40, imagen.getWidth() + 80, 20);
                        locationLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        locationLabel.setBackground(Color.WHITE);
                        locationLabel.setOpaque(true);
                        locationLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                        panel.add(locationLabel);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return panel;
    }

    public void Reiniciarjuego(){
        con=0;
        for (Player p:read.getPlayers()) {
            p.setPoints(0);
            p.setScore(0);
        }
        read.creararchivoJson(read.getPlayers());
        start.setEnabled(true);
    }

    public void runner() {
        pointsformatch = 200;
        start.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {

                  start.setEnabled(false);

                  Timer timer1 = new Timer(randomtime(), new ActionListener() {
                      @Override
                      public void actionPerformed(ActionEvent e) {
                          if (read.getPlayers().get(0).getPoints() >= pointsformatch) {
                              Timers.get(0).stop();
                              players.add(read.getPlayers().get(0));
                              con++;
                              if (con == 5) {
                                  asignarPuntuacion(players);
                                  read.creararchivoJson(read.getPlayers());
                                  if (partidas <= 0) {
                                      principal.removeAll();
                                      principal.add(podioPanel(players));
                                      principal.revalidate();
                                      principal.repaint();
                                      Reiniciarjuego();

                                  }else{
                                      reiniciar();
                                  }
                              }
                          } else {
                              int dados1[] = RandomPair();
                              suma1 = dados1[0] + dados1[1];
                              read.getPlayers().get(0).setPoints(read.getPlayers().get(0).getPoints() + suma1);
                              dc1 = new Dadopanel(dados1);
                              int aux = pointsformatch - read.getPlayers().get(0).getPoints();
                              aux = (aux < 0) ? 0 : aux;
                              panel1.removeAll();
                              panel1 = PanelFinal(panel1, ImagenesInformacion(read.getPlayers().get(0).getName(), "/colombia.png",Color.RED), PanelHorario(label1, read.getPlayers().get(0).getScore() + "", aux + "",Color.RED), auxiliar(dc1, "Avanzas:" + suma1, read.getPlayers().get(0).getPoints() + "",Color.RED));
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
                          if (read.getPlayers().get(1).getPoints() >= pointsformatch) {
                              Timers.get(1).stop();
                              players.add(read.getPlayers().get(1));
                              con++;
                              if (con == 5) {
                                  asignarPuntuacion(read.getPlayers());
                                  read.creararchivoJson(read.getPlayers());
                                  if (partidas <= 0) {
                                      principal.removeAll();
                                      principal.add(podioPanel(players));
                                      principal.revalidate();
                                      principal.repaint();
                                      Reiniciarjuego();
                                  }else{
                                      reiniciar();
                                  }
                              }
                          } else {
                              int dados2[] = RandomPair();
                              suma2 = dados2[0] + dados2[1];
                              read.getPlayers().get(1).setPoints(read.getPlayers().get(1).getPoints() + suma2);
                              dc2 = new Dadopanel(dados2);
                              int aux = pointsformatch - read.getPlayers().get(1).getPoints();
                              aux = (aux < 0) ? 0 : aux;
                              panel2.removeAll();
                              panel2 = PanelFinal(panel2, ImagenesInformacion(read.getPlayers().get(1).getName(), "/japon.png",Color.green), PanelHorario(label2, read.getPlayers().get(1).getScore() + "", aux + "",Color.green), auxiliar(dc2, "Avanzas:" + suma2, read.getPlayers().get(1).getPoints() + "",Color.green));
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
                          if (read.getPlayers().get(2).getPoints() >= pointsformatch) {

                              Timers.get(2).stop();
                              players.add(read.getPlayers().get(2));
                              con++;
                              if (con == 5) {
                                  asignarPuntuacion(players);
                                  read.creararchivoJson(read.getPlayers());
                                  if (partidas <= 0) {
                                      principal.removeAll();
                                      principal.add(podioPanel(players));
                                      principal.revalidate();
                                      principal.repaint();
                                      Reiniciarjuego();
                                  }else{
                                      reiniciar();
                                  }
                              }
                          } else {
                              int dados3[] = RandomPair();
                              suma3 = dados3[0] + dados3[1];
                              read.getPlayers().get(2).setPoints(read.getPlayers().get(2).getPoints() + suma3);
                              dc3 = new Dadopanel(dados3);
                              int aux = pointsformatch - read.getPlayers().get(2).getPoints();
                              aux = (aux < 0) ? 0 : aux;
                              panel3.removeAll();
                              panel3 = PanelFinal(panel3, ImagenesInformacion(read.getPlayers().get(2).getName(), "/francia.png",Color.pink), PanelHorario(label3, read.getPlayers().get(2).getScore() + "", aux + "",Color.pink), auxiliar(dc3, "Avanzas:" + suma3, read.getPlayers().get(2).getPoints() + "",Color.pink));
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
                          if (read.getPlayers().get(3).getPoints() >= pointsformatch) {
                              Timers.get(3).stop();
                              players.add(read.getPlayers().get(3));
                              con++;

                              if (con == 5) {
                                  asignarPuntuacion(players);
                                  read.creararchivoJson(read.getPlayers());
                                  if (partidas <= 0) {
                                      principal.removeAll();
                                      principal.add(podioPanel(players));
                                      principal.revalidate();
                                      principal.repaint();
                                      Reiniciarjuego();
                                  }else{
                                      reiniciar();
                                  }
                              }

                          } else {
                              int dados4[] = RandomPair();
                              suma4 = dados4[0] + dados4[1];
                              read.getPlayers().get(3).setPoints(read.getPlayers().get(3).getPoints() + suma4);
                              dc4 = new Dadopanel(dados4);
                              int aux = pointsformatch - read.getPlayers().get(3).getPoints();
                              aux = (aux < 0) ? 0 : aux;
                              panel4.removeAll();
                              panel4 = PanelFinal(panel4, ImagenesInformacion(read.getPlayers().get(3).getName(), "/inglaterra.png",Color.CYAN), PanelHorario(label4, read.getPlayers().get(3).getScore() + "", aux + "",Color.CYAN), auxiliar(dc4, "Avanzas:" + suma4, read.getPlayers().get(3).getPoints() + "",Color.CYAN));
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
                          if (read.getPlayers().get(4).getPoints() >= pointsformatch) {
                              Timers.get(4).stop();
                              players.add(read.getPlayers().get(4));
                              con++;

                              if (con == 5) {
                                  asignarPuntuacion(players);
                                  read.creararchivoJson(read.getPlayers());

                                  if (partidas <= 0) {
                                      principal.removeAll();
                                      principal=podioPanel(players);
                                      principal.revalidate();
                                      principal.repaint();
                                      Reiniciarjuego();
                                  }else{
                                      reiniciar();
                                  }
                              }
                          } else {
                              int dados5[] = RandomPair();
                              suma5 = dados5[0] + dados5[1];
                              read.getPlayers().get(4).setPoints(read.getPlayers().get(4).getPoints() + suma5);
                              dc5 = new Dadopanel(dados5);
                              int aux = pointsformatch - read.getPlayers().get(4).getPoints();
                              aux = (aux < 0) ? 0 : aux;
                              panel5.removeAll();
                              panel5 = PanelFinal(panel5, ImagenesInformacion(read.getPlayers().get(4).getName(), "/egipto.png",Color.orange), PanelHorario(label5, read.getPlayers().get(4).getScore() + "", aux + "",Color.orange), auxiliar(dc5, "Avanzas:" + suma5, read.getPlayers().get(4).getPoints() + "",Color.orange));
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
