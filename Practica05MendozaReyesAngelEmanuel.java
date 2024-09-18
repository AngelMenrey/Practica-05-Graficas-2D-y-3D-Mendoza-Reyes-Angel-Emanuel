import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class Practica05MendozaReyesAngelEmanuel extends JFrame {
    public Practica05MendozaReyesAngelEmanuel() {
        setTitle("Práctica 05 - Mendoza Reyes Angel Emanuel");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelConImagen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imagen = new ImageIcon("sala-chimenea.jpg");
                g.drawImage(imagen.getImage(), 0, 0, getWidth(), getHeight(), this);

                g.setColor(Color.RED);
                int diametroRojo = 100;
                int xRojo = (getWidth() - diametroRojo) / 2 + 225;
                int yRojo = (getHeight() - diametroRojo) / 2 - 80;
                g.fillOval(xRojo, yRojo, diametroRojo, diametroRojo);

                Graphics2D g2d = (Graphics2D) g;
                int diametroBlanco = 90;
                int xBlanco = xRojo + (diametroRojo - diametroBlanco) / 2;
                int yBlanco = yRojo + (diametroRojo - diametroBlanco) / 2;
                GradientPaint degradado = new GradientPaint(
                        xBlanco, yBlanco, Color.WHITE,
                        xBlanco + diametroBlanco, yBlanco + diametroBlanco, new Color(135, 206, 235));
                g2d.setPaint(degradado);
                g2d.fillOval(xBlanco, yBlanco, diametroBlanco, diametroBlanco);

                dibujarReloj(g2d, xBlanco, yBlanco, diametroBlanco);
            }

            private void dibujarReloj(Graphics2D g2d, int x, int y, int diametro) {
                int centroX = x + diametro / 2;
                int centroY = y + diametro / 2;
                int radio = diametro / 2;

                g2d.setColor(Color.BLACK);
                for (int i = 1; i <= 12; i++) {
                    double angulo = Math.toRadians((i * 30) - 90);
                    int numeroX = centroX + (int) (radio * 0.8 * Math.cos(angulo));
                    int numeroY = centroY + (int) (radio * 0.8 * Math.sin(angulo));
                    g2d.drawString(String.valueOf(i), numeroX - 5, numeroY + 5);
                }

                Calendar calendario = Calendar.getInstance();
                int horas = calendario.get(Calendar.HOUR);
                int minutos = calendario.get(Calendar.MINUTE);
                int segundos = calendario.get(Calendar.SECOND);

                double anguloHora = Math.toRadians((horas * 30) - 90 + (minutos / 2));
                int horaX = centroX + (int) (radio * 0.5 * Math.cos(anguloHora));
                int horaY = centroY + (int) (radio * 0.5 * Math.sin(anguloHora));
                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(3));
                g2d.drawLine(centroX, centroY, horaX, horaY);

                double anguloMinuto = Math.toRadians((minutos * 6) - 90);
                int minutoX = centroX + (int) (radio * 0.7 * Math.cos(anguloMinuto));
                int minutoY = centroY + (int) (radio * 0.7 * Math.sin(anguloMinuto));
                g2d.setColor(Color.BLUE);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawLine(centroX, centroY, minutoX, minutoY);

                double anguloSegundo = Math.toRadians((segundos * 6) - 90);
                int segundoX = centroX + (int) (radio * 0.9 * Math.cos(anguloSegundo));
                int segundoY = centroY + (int) (radio * 0.9 * Math.sin(anguloSegundo));
                g2d.setColor(Color.RED);
                g2d.setStroke(new BasicStroke(1));
                g2d.drawLine(centroX, centroY, segundoX, segundoY);
            }
        };

        setContentPane(panelConImagen);

        Thread hiloReloj = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                panelConImagen.repaint();
            }
        });
        hiloReloj.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Practica05MendozaReyesAngelEmanuel ventana = new Practica05MendozaReyesAngelEmanuel();
            ventana.setVisible(true);
        });
    }
}