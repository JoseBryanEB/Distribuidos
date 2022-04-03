public class PoligonoIrreg {
 private int numVertices;
 private Coordenada []vertices ;
 public PoligonoIrreg(int numVertices){
   this.numVertices = numVertices; 
   vertices = new Coordenada[numVertices];
 }  
 public void anadeVertice(Coordenada coordenada, int posicion){
   vertices[posicion] = coordenada; 
 }
 
 public void anadeVertice(double x,double y,int posicion){
  vertices[posicion] = new Coordenada();
  vertices[posicion].setPoint(x,y);
 }

 @Override

 public String toString(){
   String resultado="";
   for (int i=0; i <numVertices; i++){
     resultado+=vertices[i].toString();
     resultado+=' ';
   }
   return resultado;
 }


}
