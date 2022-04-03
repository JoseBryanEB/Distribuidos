
/* 
** PROYECTO 2
** Escrito por: Estrada Bernal José Bryan
** Grupo: 4CM13 
*/
import java.util.ArrayList;
import java.util.Random;

public class PoligonoReg extends PoligonoIrreg {
    private int vertices;
    private double teta;
    private double area;

    PoligonoReg(int vertices) {
        super();
        this.vertices = vertices;
        teta = 2 * Math.PI / vertices;
        int aux = new Random().nextInt(200 - 50 + 1) + 50;
        int r = aux / 2;
        for (int i = 0; i < vertices; i++) {
            Coordenada c = new Coordenada(r + (int) (r * Math.cos(teta * i)), r + (int) (r * Math.sin(teta * i)));
            anadeVertice(c);
        }
        double b2 = 2 * (Math.pow(r, 2) - (Math.pow(r, 2) * Math.cos(teta)));
        area = vertices * ((Math.sqrt(b2) * (Math.sqrt(Math.pow(r, 2) - (b2 / 4)))) / 2);

    }

    public double obtieneArea() {
        return area;
    }

}
