
/* 
** PROYECTO 2
** Escrito por: Estrada Bernal José Bryan
** Grupo: 4CM13 
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;


public class Main extends JFrame {
    private static int numPoligonos;
    public Panel p;

    public static void main(String[] args) throws InterruptedException {
        // System.out.println(args[0]);
        if (args.length > 0)
            numPoligonos = Integer.parseInt(args[0]);
        else
            numPoligonos = 10;
        Main gui = new Main();
        gui.setVisible(true);
        /*
         * Thread.currentThread().join();;
         * for (int i = 0; i < numPoligonos; i++) {
         * Thread.sleep(3*1000);
         * gui.repaint();
         * }
         */

    }

    public Main() {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p = new Panel(0, 0);
        // getContentPane();
        add(p);

    }

    private class Panel extends JPanel {
        private int x;
        private int y;
        private int i;
        private boolean flag;
        private java.util.List<PoligonoReg> lstPoligonos;

        Panel(int x, int y) {
            this.x = x;
            this.y = y;
            i = 0;
            flag = true;
            lstPoligonos = new java.util.ArrayList();
            ActionListener animate = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    repaint();
                }
            };
            Timer timer = new Timer(3000, animate);
            timer.start();
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setLayout(null);
            g = g.create();
            g.setColor(Color.blue);
            if (flag) {
                setLocation(x, y);
                Random ran = new Random();
                for (int i = 0; i < numPoligonos; i++) {
                    int x = ran.nextInt(650 - 0 + 1) + 0;
                    int y = ran.nextInt(400 - 0 + 1) + 0;
                    PoligonoReg pol = new PoligonoReg(ran.nextInt(10 - 3 + 1) + 3);
                    lstPoligonos.add(pol);
                    // g.drawOval(x,y,150,150);
                    Polygon poligono = new Polygon();
                    for (var obj : pol.getVertices()) {
                        g.fillOval(x + (int) obj.abcisa() - 5, y + (int) obj.ordenada() - 5, 10, 10);
                        poligono.addPoint(x + (int) obj.abcisa(), y + (int) obj.ordenada());
                    }
                    g.drawPolygon(poligono);
                    Collections.sort(lstPoligonos, new Comparator<PoligonoReg>() {
                        @Override
                        public int compare(PoligonoReg poligonoReg, PoligonoReg t1) {
                            return Double.compare(poligonoReg.obtieneArea(), t1.obtieneArea());
                        }
                    });
                }
                flag = false;
            } else {

                // System.out.println(lstPoligonos);
                setLocation(300, 200);
                System.out.println(i);
                PoligonoReg pol;
                if (lstPoligonos.size() > i) {
                    pol = lstPoligonos.get(i++);
                } else
                    pol = lstPoligonos.get(lstPoligonos.size() - 1);

                Polygon poligono = new Polygon();
                for (var obj : pol.getVertices()) {
                    g.fillOval((int) obj.abcisa() - 5, (int) obj.ordenada() - 5, 10, 10);
                    poligono.addPoint((int) obj.abcisa(), (int) obj.ordenada());
                }
                g.drawPolygon(poligono);
            }
            g.dispose();
        }

    }

}
