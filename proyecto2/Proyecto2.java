import javax.swing.*;import java. awt.*;
public class Proyecto2 extends JFrame{
  public static void main(String[] args){ 
    Proyecto2 gui = new Proyecto2(); 
    gui.setVisible(true);
  }
  public Proyecto2()
  {
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Panel p = new Panel();add(p);}
  private class Panel extends JPanel 
  {    
    @Override
    public void paintComponent(Graphics g){
      g.setColor(Color.blue);
      Polygon poligono=new Polygon();
      poligono.addPoint(0, 0);
      poligono.addPoint(100, 0);
      poligono.addPoint(50, 100);
      g.drawPolygon(poligono);
    }
  }
}
