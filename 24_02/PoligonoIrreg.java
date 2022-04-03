import java.util.*;
import java.lang.*;
import java.io.*;
public class PoligonoIrreg {
 private List<Coordenada> vertices ;
 public PoligonoIrreg(){ 
  vertices = new ArrayList<Coordenada>();
 }  
 public void anadeVertice(Coordenada coordenada){
    vertices.add(coordenada); 
 }
 public void ordenaVertices(){
    Collections.sort(vertices,new Comparator<Coordenada>(){
      @Override
      public int compare(Coordenada c1,Coordenada c2){
        return Double.compare(c1.magnitud(),c2.magnitud());
      }
    });
 }
 public List<Coordenada> getVertices(){return vertices;}
 @Override

 public String toString(){
   String resultado="";
   for (Coordenada var : vertices) {
     resultado+="cordenada: "+var.toString()+" Manitud: "+ var.magnitud()+"\n";
   }
   return resultado;
 }

}
