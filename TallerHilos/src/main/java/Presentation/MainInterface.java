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
import java.util.Collections;
import java.util.Comparator;
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

    JTextField numberOfGamesField;
    ArrayList<Player> players = new ArrayList<>();

    private JSpinner numberOfGamesSpinner;
    int numberOfGames;

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
        principal.setLayout(new GridLayout(1, 1));
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

        numberOfGamesField= new JTextField();
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
        //principal.add(PanelBienvenida());
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
        for (Timer a:Timers) {
            a.start();
        }
    }

    public JPanel PanelBienvenida() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60)); // Márgenes amplios en todos los lados del panel

        ImageIcon welcomeImage = new ImageIcon("src/main/resources/bienvenido.png");
        JLabel welcomeImageLabel = new JLabel(welcomeImage);
        panel.add(welcomeImageLabel);

        JPanel numberPanel = new JPanel();
        numberPanel.setLayout(new BorderLayout());
        numberPanel.setBackground(new Color(254, 196, 38));

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new GridLayout(1, 2));

        JLabel instructionLabel = new JLabel("<html><div style='text-align:center;'><span style='font-size:14px;'>Bienvenido!<br>Seleccione el número de partidas:</span></div></html>");
        innerPanel.add(instructionLabel);

        numberOfGamesSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        JComponent editor = numberOfGamesSpinner.getEditor();
        JFormattedTextField textField = ((JSpinner.DefaultEditor) editor).getTextField();
        textField.setColumns(3);
        textField.setFont(new Font("Arial", Font.BOLD, 140));
        innerPanel.add(numberOfGamesSpinner);


        numberPanel.add(innerPanel, BorderLayout.CENTER);


        JButton confirmButton = new JButton("Confirmar");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberOfGames = (int) numberOfGamesSpinner.getValue();
                JOptionPane.showMessageDialog(panel, "Número de partidas seleccionado: " + numberOfGames);
            }
        });
        numberPanel.add(confirmButton, BorderLayout.SOUTH);


        panel.add(numberPanel);

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

    public static JPanel podioPanelPartida(ArrayList<Player> lugares) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int panelWidth = getWidth();
                int panelHeight = getHeight();

                int marginLeft = 300; // Aumentado
                int marginRight = 300; // Aumentado
                int usableWidth = panelWidth - marginLeft - marginRight;

                try {
                    BufferedImage backgroundImage = ImageIO.read(new File("src/main/resources/metapartida.png"));
                    Image scaledImage = backgroundImage.getScaledInstance(usableWidth, panelHeight, Image.SCALE_SMOOTH);
                    g.drawImage(scaledImage, marginLeft, 0, null); // Ajuste de posición
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int rectCount = lugares.size();
                int rectHeight = 100;
                int rectWidth = usableWidth / rectCount;
                int rectY = panelHeight - rectHeight;

                for (int i = 0; i < rectCount; i++) {
                    Player currentPlayer = lugares.get(i);
                    int rectX = marginLeft + i * rectWidth;
                    g.setColor(Color.WHITE);
                    g.fillRect(rectX, rectY, rectWidth, rectHeight);
                    g.setColor(Color.BLACK);
                    g.drawRect(rectX, rectY, rectWidth, rectHeight);

                    int boxWidth = rectWidth; // Ancho del cuadro de texto
                    int boxHeight = rectHeight / 3; // Altura del cuadro de texto
                    int x = rectX;
                    int y = rectY - rectHeight / 3; // Mover el cuadro de texto arriba del rectángulo
                    g.setColor(Color.WHITE);
                    g.fillRect(x, y, boxWidth, boxHeight);

                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial", Font.BOLD, 12)); // Tamaño del texto ajustado
                    String playerScore = String.valueOf(currentPlayer.getScore());
                    int stringWidth = g.getFontMetrics().stringWidth("Score: " + playerScore);
                    int stringHeight = g.getFontMetrics().getHeight();
                    int textX = rectX + (rectWidth - stringWidth) / 2;
                    int textY = y + stringHeight; // Ajuste de la posición vertical del texto
                    g.drawString("Score: " + playerScore, textX, textY); // Puntuación

                    try {
                        BufferedImage image = ImageIO.read(new File("src/main/resources/" + currentPlayer.getLocation() + ".png"));
                        Image scaledImage = image.getScaledInstance(rectWidth, rectHeight, Image.SCALE_SMOOTH);
                        g.drawImage(scaledImage, rectX, rectY, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    g.drawRect(x, y, boxWidth, boxHeight);
                }

                g.setColor(Color.BLACK);
                g.fillRect(0, 0, marginLeft, panelHeight); // Margen izquierdo
                g.fillRect(panelWidth - marginRight, 0, marginRight, panelHeight); // Margen derecho
            }
        };
        return panel;
    }

    public static JPanel podioPanelFinal(ArrayList<Player> lugares) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int panelWidth = getWidth();
                int panelHeight = getHeight();

                int marginLeft = 300; // Aumentado
                int marginRight = 300; // Aumentado
                int usableWidth = panelWidth - marginLeft - marginRight;

                Color marginColor = new Color(254, 196, 38); // Color #FEC426
                g.setColor(marginColor);

                g.fillRect(0, 0, marginLeft, panelHeight); // Margen izquierdo
                g.fillRect(panelWidth - marginRight, 0, marginRight, panelHeight); // Margen derecho

                try {
                    BufferedImage backgroundImage = ImageIO.read(new File("src/main/resources/metafinal.png"));
                    Image scaledImage = backgroundImage.getScaledInstance(usableWidth, panelHeight, Image.SCALE_SMOOTH);
                    g.drawImage(scaledImage, marginLeft, 0, null); // Ajuste de posición
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int rectCount = lugares.size();
                int rectHeight = 100;
                int rectWidth = usableWidth / rectCount;
                int rectY = panelHeight - rectHeight;

                for (int i = 0; i < rectCount; i++) {
                    Player currentPlayer = lugares.get(i);
                    int rectX = marginLeft + i * rectWidth;
                    g.setColor(Color.WHITE);
                    g.fillRect(rectX, rectY, rectWidth, rectHeight);
                    g.setColor(Color.BLACK);
                    g.drawRect(rectX, rectY, rectWidth, rectHeight);

                    int boxWidth = rectWidth; // Ancho del cuadro de texto
                    int boxHeight = rectHeight / 3; // Altura del cuadro de texto
                    int x = rectX;
                    int y = rectY - rectHeight / 3; // Mover el cuadro de texto arriba del rectángulo
                    g.setColor(Color.WHITE);
                    g.fillRect(x, y, boxWidth, boxHeight);

                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial", Font.BOLD, 12)); // Tamaño del texto ajustado
                    String playerScore = String.valueOf(currentPlayer.getScore());
                    int stringWidth = g.getFontMetrics().stringWidth("Score: " + playerScore);
                    int stringHeight = g.getFontMetrics().getHeight();
                    int textX = rectX + (rectWidth - stringWidth) / 2;
                    int textY = y + stringHeight; // Ajuste de la posición vertical del texto
                    g.drawString("Score: " + playerScore, textX, textY); // Puntuación

                    try {
                        BufferedImage image = ImageIO.read(new File("src/main/resources/" + currentPlayer.getLocation() + ".png"));
                        Image scaledImage = image.getScaledInstance(rectWidth, rectHeight, Image.SCALE_SMOOTH);
                        g.drawImage(scaledImage, rectX, rectY, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    g.drawRect(x, y, boxWidth, boxHeight);
                }
            }
        };
        return panel;
    }

    public JPanel fondo() {
        // Crear el panel principal
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Cargar la imagen de fondo
                ImageIcon backgroundImage = new ImageIcon("src/main/resources/fondo.jpg");
                // Dibujar la imagen de fondo
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };

        // Establecer el layout del panel principal
        panel.setLayout(new BorderLayout());

        // Agregar márgenes más grandes a los lados y arriba/abajo
        panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        return panel;
    }


    public void runner() {
        pointsformatch = 200;
        partidas=numberOfGames;
        principal.removeAll();
        principal.add(PanelBienvenida());
        principal.revalidate();
        principal.repaint();
        start.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
                  start.setEnabled(false);
                principal.removeAll();
                principal.add(fondo());
                principal.revalidate();
                principal.repaint();

                  Timer timer1 = new Timer(randomtime(), new ActionListener() {
                      @Override
                      public void actionPerformed(ActionEvent e) {
                          if (read.getPlayers().get(0).getPoints() >= pointsformatch) {
                              Timers.get(0).stop();
                              players.add(read.getPlayers().get(0));
                              partidas--;
                              con++;
                              if (con == 5) {
                                  asignarPuntuacion(players);
                                  read.creararchivoJson(read.getPlayers());
                                  if (partidas <= 0) {

                                      Reiniciarjuego();
                                  }else{
                                      principal.removeAll();
                                      principal.add(podioPanelPartida(players));
                                      principal.revalidate();
                                      principal.repaint();
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
                              partidas--;
                              con++;
                              if (con == 5) {
                                  asignarPuntuacion(read.getPlayers());
                                  read.creararchivoJson(read.getPlayers());
                                  if (partidas <= 0) {

                                      Reiniciarjuego();
                                  }else{
                                      principal.removeAll();
                                      principal.add(podioPanelPartida(players));
                                      principal.revalidate();
                                      principal.repaint();
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
                              partidas--;
                              con++;
                              if (con == 5) {
                                  asignarPuntuacion(players);
                                  read.creararchivoJson(read.getPlayers());
                                  if (partidas <= 0) {

                                      Reiniciarjuego();
                                  }else{
                                      principal.removeAll();
                                      principal.add(podioPanelPartida(players));
                                      principal.revalidate();
                                      principal.repaint();
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
                              partidas--;
                              con++;

                              if (con == 5) {
                                  asignarPuntuacion(players);
                                  read.creararchivoJson(read.getPlayers());
                                  if (partidas <= 0) {
                                      Reiniciarjuego();
                                  }else{
                                      principal.removeAll();
                                      principal.add(podioPanelPartida(players));
                                      principal.revalidate();
                                      principal.repaint();
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
                              partidas--;
                              con++;

                              if (con == 5) {
                                  asignarPuntuacion(players);
                                  read.creararchivoJson(read.getPlayers());
                                  if (partidas <= 0) {
                                      Reiniciarjuego();
                                  }else{
                                      principal.removeAll();
                                      principal.add(podioPanelPartida(players));
                                      principal.revalidate();
                                      principal.repaint();
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
