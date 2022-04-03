public class PruebaPoligonoIrreg1 {
  public static void main(String[] args) {
    PoligonoIrreg pol = new PoligonoIrreg(4);
    for (int i = 0; i < 4; i++) {
      pol.anadeVertice(i,i+2,i);
    }
    System.out.println(pol.toString());

  }
}

