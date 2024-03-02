package Presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Horario3 implements Runnable {

    private JLabel label;

    public Horario3(JLabel label) {
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
        TimeZone timeZoneJapon = TimeZone.getTimeZone("Europe/Paris");
        SimpleDateFormat formatoFecha = new SimpleDateFormat("HH:mm:ss");
        formatoFecha.setTimeZone(timeZoneJapon);
        Date ahora = new Date();
        String horarioActual = formatoFecha.format(ahora);
        label.setText("<html>Horario de Francia:<br>" + horarioActual+"</html>");
    }
}
