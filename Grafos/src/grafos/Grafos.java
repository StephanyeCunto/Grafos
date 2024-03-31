package grafos;

import classes.matrizAdjacente;
import classes.matrizIncidente;
import java.io.IOException;

/**
 *
 * @author steph
 */
public class Grafos {

    public static void main(String[] args) throws IOException {
        matrizAdjacente matrizAdjacente= new matrizAdjacente();
        matrizIncidente matrizIncidente = new matrizIncidente();
        matrizIncidente.abrirCsv();
        matrizIncidente.matrizIncidenteToMatrizAdjacente();
       // matrizIncidente.preencheMatrizAdjacente();
        //matrizAdjacente.preencheMatrizAleatoria();
    }
    
}
