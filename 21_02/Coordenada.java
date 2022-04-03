public class Coordenada {

    private double x, y;

    public Coordenada(double x, double y) {

        this.x = x;

        this.y = y;

    }
    public Coordenada (){}

    //Metodo getter de x

    public double abcisa( ) { return x; }

 

    //Metodo getter de y

    public double ordenada( ) { return y; }
    public void setPoint(double x,double y){this.x =x;this.y=y;}
    

    //Sobreescritura del método de la superclase objeto para imprimir con System.out.println( )

    @Override

    public String toString( ) {

        return "[" + x + "," + y + "]";

    }

}
