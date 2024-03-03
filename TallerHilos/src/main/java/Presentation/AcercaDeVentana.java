package Presentation;

import javax.swing.*;
import java.awt.*;

public class AcercaDeVentana extends JFrame {
    public AcercaDeVentana() {
        setTitle("Acerca De");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new GridLayout(1, 2));

        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.setBackground(Color.WHITE);

        JLabel mensaje = new JLabel("<html><div style='margin-left: 20px; text-align: left;'>" +
                "<span style='font-size:20px; font-weight:bold;'>Taller implementación de Hilos</span><br>" +
                "Versión 1.0<br>" +
                "<br><br><span style='font-size:20px; font-weight:bold;'>Desarrollado por:</span><br><br><br>" +
                "<br><span style='font-size:18px'>Andrés Felipe Puentes (202127995)<br>Luis Esteban Robelto (202128044)</span><br><br>" +
                "<br><br><br><span style='font-size:14px; font-style:italic;'>Ingeniería en Sistemas</span><br>" +
                "<span style='font-size:10px; font-style:italic;'>UPTC</span>" +
                "</div></html>", SwingConstants.LEFT);


        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setBackground(new Color(255, 220, 77));

        ImageIcon imagen = new ImageIcon("src/main/java/example/imagen.png");
        JLabel etiquetaImagen = new JLabel(imagen);
        panelDerecho.add(mensaje, BorderLayout.CENTER);
        panelIzquierdo.add(etiquetaImagen, BorderLayout.CENTER);

        panelPrincipal.add(panelIzquierdo);
        panelPrincipal.add(panelDerecho);

        add(panelPrincipal);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AcercaDeVentana().setVisible(true);
        });
    }
}
