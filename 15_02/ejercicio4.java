public class ejercicio4 {
  public static void main(String[] args) {
    int tAnte[]={0,1,2};
   for (int v : tAnte) {
     System.out.println(v);
   }
    for (int i = 0; i < 17; i++) {
      int suma=tAnte[0]+tAnte[1]+tAnte[2];
      tAnte[0]=tAnte[1];
      tAnte[1]=tAnte[2];
      tAnte[2]=suma; 
      System.out.println(suma);
    }
  }
}
