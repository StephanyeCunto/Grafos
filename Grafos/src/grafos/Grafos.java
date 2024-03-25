package grafos;

import classes.matrizAdjacente;
import java.io.IOException;

/**
 *
 * @author steph
 */
public class Grafos {

    public static void main(String[] args) throws IOException {
        matrizAdjacente matrizAdjacente = new matrizAdjacente();
        matrizAdjacente.abrirCsv();
        //matrizAdjacente.preencheMatrizAleatoria();
    }
    
}
