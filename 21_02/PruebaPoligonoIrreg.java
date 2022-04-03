import java.util.Random;
public class PruebaPoligonoIrreg {
  public static void main(String[] args) {
   Random random = new Random();
   int vertices = 10;
   PoligonoIrreg poligono = new PoligonoIrreg(vertices);
   //con new 
   long inicio = System.nanoTime();
   for (int i = 0; i < vertices; i++) {
     poligono.anadeVertice(new Coordenada(random.nextDouble(),random.nextDouble()), i);
   }
   long fin = System.nanoTime(); 
   System.out.println("Tiempo transcurrido :"+(fin - inicio));
   PoligonoIrreg poligono2 = new PoligonoIrreg(vertices);
   //con setter
   inicio = System.nanoTime();
   Coordenada c1 = new Coordenada(0,0);
   for (int i = 0; i < vertices; i++) {
    c1.setPoint(random.nextDouble(),random.nextDouble());
    poligono2.anadeVertice(c1, i);
  }
  fin = System.nanoTime();
  System.out.println(poligono2);
  System.out.println("Tiempo transcurrido :"+(fin - inicio));

  }
}
