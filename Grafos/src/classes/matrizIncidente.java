package classes;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author steph
 */
public class matrizIncidente extends matrizAdjacente{
    private int vertical; 
    private int diagonal;
    private int[][] matrizIncidente;
    private int[][] matrizAdjacente;
        
    
    public matrizIncidente() {
        this.vertical=0;
        this.diagonal=0;
        this.matrizIncidente= new int[vertical][diagonal];
        for (int i = 0; i < vertical; i++) {
            for (int j = 0; j < diagonal; j++) {
                this.matrizIncidente[i][j] = 0;
            }
        }
        for (int i = 0; i < vertical; i++) {
            for (int j = 0; j < diagonal; j++) {
                this.matrizAdjacente[i][j] = 0;
            }
        }
    }
    
    @Override
    public int getVertical() {
        return vertical;
    }

    @Override
    public void setVertical(int vertical) {
        this.vertical = vertical;
    }

    @Override
    public int getDiagonal() {
        return diagonal;
    }

    @Override
    public void setDiagonal(int diagonal) {
        this.diagonal = diagonal;
    }

    public int getMatrizIncidente(int vertical,int horizontal) {
        return matrizIncidente[vertical][horizontal];
    }

    public void setMatrizIncidente(int linha,int coluna, int valor) {
        this.matrizIncidente[linha][coluna] = valor;
    }
    
    public int getMatrizAdjacente(int vertical,int horizontal) {
        return matrizAdjacente[vertical][horizontal];
    }

    public void setMatrizAdjacente(int linha,int coluna, int valor) {
        this.matrizAdjacente[linha][coluna] = valor;
    }
    
    private int sortearNumero(){
        Random rand = new Random();
        return rand.nextInt(2);
    }
    
    @Override
    public void abrirCsv() throws IOException{
        FileReader f=null;
        String caminhoArquivo = "matrizIncidente.csv";
        f=new FileReader(caminhoArquivo);
        
        setVertical((int) Files.lines(Paths.get(caminhoArquivo)).count());
        String[] aux = new String[getVertical()+1];  
        int numeroAux=0;
        
        try (Scanner arquivo = new Scanner(f)) {
            arquivo.useDelimiter(",");
            
            while (arquivo.hasNext()) {
                String linha = arquivo.nextLine();

                setDiagonal(linha.replaceAll(",", "").length());
                aux[numeroAux]=linha;
                numeroAux++; 
            }
            f.close();
            
            matrizIncidente = new int[getVertical()][getDiagonal()];
                for(int i=0;i<getVertical();i++){
                    String[] valores = aux[i].split(",");
                    
                    for (int j = 0; j < getDiagonal(); j++) {
                        int valoresAux=Integer.parseInt(valores[j]);
                        setMatrizIncidente(i,j,valoresAux);
                    } 
                }
            printMatrizIncidente();
        }        
    }
    
    @Override
    public void preencheMatrizAleatoria(){
        tamanhoMatriz();
        matrizIncidente = new int[getVertical()][getDiagonal()];
    
        for(int i=0;i<getVertical();i++){
            for(int j=0;j<getDiagonal();j++){
                if(i>j){
                    setMatrizIncidente(i,j,getMatrizIncidente(j,i));
                }else{
                    setMatrizIncidente(i,j,sortearNumero());
                }
            }
        }
        
        printMatrizIncidente();
    }
      
    protected void printMatrizIncidente() {       
        for(int i=0;i<(getVertical()*2)+6;i++){
            System.out.print("_");
        }
        
        System.out.println();
        
        System.out.print("|i-j|");
                
        for(int i=0;i<getVertical();i++){
            int a=i+1;
            System.out.print("|"+ a);
        }
        
        System.out.print("|");
        
        System.out.println();
        
        for(int i=0;i<(getVertical()*2)+6;i++){
            System.out.print("-");
        }
        
        System.out.println();
                    
        for(int i=0;i<getVertical();i++){
            int a=i+1;
            System.out.print("|u"+a+"| ");
            for(int j=0;j<getDiagonal();j++){
                System.out.print("|"+getMatrizIncidente(i,j));
            }
            System.out.println("|");   
       }
        for(int i=0;i<(getVertical()*2)+6;i++){
            System.out.print("-");
        }
        System.out.println();
    }
    
    private void tamanhoMatriz(){
        Scanner scanner= new Scanner(System.in);
        System.out.println("Qual o tamanho da matriz?");
        setVertical(scanner.nextInt());
        setDiagonal(getVertical());
    }
    
    public void matrizIncidenteToMatrizAdjacente(){
        matrizAdjacente = new int[getVertical()][getDiagonal()];
        for(int j=0;j<getDiagonal();j++){
            int aux=0;
            for(int i=0;i<getVertical();i++){
                if(getMatrizIncidente(i,j)!=0){
                    for(int a=j;a<getDiagonal();a++){
                        if( getMatrizIncidente(i,a)!=0){
                            if(aux!=0){
                                if(aux-1!=a){
                                    setMatrizAdjacente(aux-1,a,1);
                                    setMatrizAdjacente(a,aux-1,1);
                                }
                            }
                            aux=a+1;
                        }
                    }
                }
            }   
        }
        preencheMatrizAdjacente();
    }
    
    private void printMatrizAdjacente() {   
        
        for(int i=0;i<(getVertical()*2)+6;i++){
            System.out.print("_");
        }
        
        System.out.println();
        
        System.out.print("|i-j|");
                
        for(int i=0;i<getVertical();i++){
            int a=i+1;
            System.out.print("|"+ a);
        }
        
        System.out.print("|");
        
        System.out.println();
        
        for(int i=0;i<(getVertical()*2)+6;i++){
            System.out.print("-");
        }
        
        System.out.println();
                    
        for(int i=0;i<getVertical();i++){
            int a=i+1;
            System.out.print("|"+a+"|  ");
            for(int j=0;j<getDiagonal();j++){
                System.out.print("|"+getMatrizAdjacente(i,j));
            }
            System.out.println("|");   
       }
        for(int i=0;i<(getVertical()*2)+6;i++){
            System.out.print("-");
        }
        System.out.println();
    }
    
    public void preencheMatrizAdjacente(){
        super.setVertical(getVertical());
        super.setDiagonal(getDiagonal());
        super.iniciarMatriz();
        for(int i=0;i<getDiagonal();i++){
            for(int j=0;j<getVertical();j++){
                super.setMatriz(i,j,getMatrizAdjacente(i,j));
            }
        }
        super.printMatriz();
        super.verificaMatriz();
    }
}

