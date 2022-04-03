import java.util.*;
public class PruebaPoligonoIrreg1 {
  public static void main(String[] args) {
    PoligonoIrreg pol = new PoligonoIrreg();
    int max =100;
    int min = -100;
    Random ran = new Random();
    for (int i = 0; i < 10; i++) {
      pol.anadeVertice(new Coordenada(
            min+(max-min)*ran.nextDouble()
            ,min+(max-min)*ran.nextDouble()));
    }
    System.out.println(pol.toString());
    pol.ordenaVertices();
    System.out.println("Ordenado");
    System.out.println(pol);
  }
}

