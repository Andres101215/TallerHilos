package Presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Horario1 implements  Runnable {

    private JLabel label;

    public Horario1(JLabel label) {
        this.label = label;
    }

    @Override
    public void run() {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarHorario();
            }
        });
        timer.start();
    }

    private void actualizarHorario() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("HH:mm:ss");
        Date ahora = new Date();
        String horarioActual = "<html>Horario de Colombia:<br>"+formatoFecha.format(ahora)+"</html>";
        label.setText(horarioActual);
    }
}
