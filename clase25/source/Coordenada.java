import java.lang.Math;
import java.io.*;
public class Coordenada implements java.io.Serializable{

    private double x, y;
    private double m;
    public Coordenada(double x, double y) {

        this.x = x;

        this.y = y;
        this.m = Math.sqrt(x*x+y*y);

    }
    public Coordenada (){}

    //Metodo getter de x

    public double abcisa( ) { return x; }

 

    //Metodo getter de y

    public double ordenada( ) { return y; }
    public void setPoint(double x,double y){this.x =x;this.y=y;}
    public double magnitud(){
      return m;
    }
    //Sobreescritura del método de la superclase objeto para imprimir con System.out.println( )

    @Override

    public String toString( ) {

        return "[" + x + "," + y + "]";

    }

}
