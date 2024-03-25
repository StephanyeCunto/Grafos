package classes;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author steph
 */
public class matrizAdjacente {
    private int vertical; 
    private int diagonal;
    private int[][] matriz;
    private int[] grauVertice;
        
    
    public matrizAdjacente( ){
        this.vertical=vertical;
        this.diagonal=diagonal;
        this.matriz= new int[vertical][diagonal];
        for (int i = 0; i < vertical; i++) {
            for (int j = 0; j < diagonal; j++) {
                this.matriz[i][j] = matriz[i][j];
            }
        }
        this.grauVertice= new int[vertical];
        for(int i=0; i< vertical; i++){
            this.grauVertice[i]=grauVertice[i];
        }
    }
    
    public int getVertical() {
        return vertical;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
    }

    public int getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(int diagonal) {
        this.diagonal = diagonal;
    }

    public int getMatriz(int vertical,int horizontal) {
        return matriz[vertical][horizontal];
    }

    public void setMatriz(int linha,int coluna, int valor) {
        this.matriz[linha][coluna] = valor;
    }
    
    public int getGrauVertice(int coluna){
        return grauVertice[coluna];
    }
    
    public void setGrauVertice(int coluna,int valor){
        this.grauVertice[coluna]=valor;
    }
    
    private int sortearNumero(){
        Random rand = new Random();
        return rand.nextInt(2);
    }
    
    public void abrirCsv() throws IOException{
        FileReader f=null;
        String caminhoArquivo = "matrizAdjacente.csv";
        f=new FileReader(caminhoArquivo);
        
        setVertical((int) Files.lines(Paths.get("matrizAdjacente.csv")).count());
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
            
            matriz = new int[getVertical()][getDiagonal()];
                for(int i=0;i<getVertical();i++){
                    String[] valores = aux[i].split(",");
                    
                    for (int j = 0; j < getDiagonal(); j++) {
                        int valoresAux=Integer.parseInt(valores[j]);
                        setMatriz(i,j,valoresAux);
                    } 
                }
            printMatriz();
            verificaMatriz();
        }        
    }
    
    public void preencheMatrizAleatoria(){
        tamanhoMatriz();
        matriz = new int[vertical][diagonal];

        
        for(int i=0;i<vertical;i++){
            for(int j=0;j<diagonal;j++){
                if(i>j){
                    setMatriz(i,j,getMatriz(j,i));
                }else{
                    setMatriz(i,j,sortearNumero());
                }
            }
        }
        
        printMatriz();
        verificaMatriz();
    }
    
    private void simOuNao(boolean resposta){
      if(resposta==true){
            System.out.print("Sim. \n");
        }else{
            System.out.print("Nao. \n");
        }  
    }
    
    public void verificaMatriz(){
        System.out.println("Esse grafo possui ordem: "+ordemGrafo());
        System.out.println("Esse grafo possui tamanho: "+tamanhoGrafo());
        grauVertice();
        for(int i=0;i<vertical;i++){
            int a=i+1;
            System.out.println("O vertice "+a+" possui "+ getGrauVertice(i)+" grau.");
        }
        if(pseudoGrafo()){
            System.out.println("Grafo simples: Nao.");
            System.out.println("PseudoGrafo: Sim.");
            System.out.println("Grafo conexo: Nao");
            System.out.println("Grafo completo: Nao.");
            System.out.print("Grafo multiGrafo: ");
            simOuNao(multiGrafo());
        }else if(multiGrafo()){
            System.out.println("Grafo simples: Nao.");
            System.out.println("PseudoGrafo: Nao.");
            System.out.println("Grafo multiGrafo: Sim.");
            System.out.println("Grafo completo: Nao");
            System.out.print("Grafo conexo: ");
            simOuNao(grafoConexo());
        }else if(grafoConexo()){
            System.out.println("Grafo simples: Sim");
            System.out.println("PseudoGrafo: Nao.");
            System.out.println("MultiGrafo: Nao");
            System.out.println("Grafo conexo: Sim.");
            System.out.print("Grafo completo: ");
            simOuNao(grafoCompleto());
        }else{
            System.out.println("Grafo simples: Sim.");
            System.out.println("PseudoGrafo: Nao.");
            System.out.println("MultiGrafo: Nao.");
            System.out.println("Grafo conexo: Nao.");        
            System.out.println("Grafo completo: Nao.");
        }
        System.out.print("Grafo simetrico: ");
        simOuNao(grafoSimetrico());
        System.out.print("Grafo regular: ");
        simOuNao(grafoRegular());
    }
    
    private boolean grafoRegular(){
        for(int i=0;i<vertical;i++){
            for(int a=1;a<vertical;a++){
                if(grauVertice[i]!=grauVertice[a]){
                    return false;
                }
            }
        }  
        return true;
    }
    
    private boolean multiGrafo(){
     for(int i=0;i<vertical;i++){
            for(int j=0;j<diagonal;j++){
                if(getMatriz(i,j)>1){
                    return true;
                }
            }
         }
         return false;
    }
    
    private boolean pseudoGrafo(){
         for(int i=0;i<vertical;i++){
            for(int j=0;j<diagonal;j++){
                if(i==j && getMatriz(i,j)==1){
                    return true;
                }
            }
         }
         return false;
    }
    
    private boolean grafoSimetrico(){
        for(int i=0;i<vertical;i++){
            for(int j=0;j<diagonal;j++){
                if(getMatriz(i,j)!=getMatriz(j,i)){
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean grafoConexo(){
        for(int i=0;i<vertical;i++){
            int soma=0;
                for(int j=0;j<diagonal;j++){
                    if(i!=j){
                        soma=soma+getMatriz(i,j);
                    }
                }
                if(soma==0){
                    return false;
                }
        }
        return true;
    }
    
    private boolean grafoCompleto(){
        for(int i=0;i<vertical;i++){
            int soma=0;
                for(int j=0;j<diagonal;j++){
                    soma=soma+getMatriz(i,j);
                }
                if(soma!=diagonal-1){
                    return false;
                }else if(pseudoGrafo()){
                    return false;
                }
            }
        return true;
    }
    
    private int ordemGrafo(){
        return vertical;
    }
    
    private int tamanhoGrafo(){
        int soma=0;
        for(int i=0;i<vertical;i++){
            for(int j=0;j<diagonal;j++){
                if(i>j || i==j){
                    soma=soma+getMatriz(i,j);
                }
            }
        }
        return soma;
    }
    
    private void grauVertice(){
        grauVertice= new int[diagonal];
        for(int i=0;i<vertical;i++){
            int soma=0;
            for(int j=0;j<diagonal;j++){
                soma=soma+getMatriz(i,j);
                setGrauVertice(i,soma);
                
            }
        }
    }
    
    private void printMatriz() {   
        
        for(int i=0;i<(vertical*2)+6;i++){
            System.out.print("_");
        }
        
        System.out.println();
        
        System.out.print("|i-j|");
                
        for(int i=0;i<vertical;i++){
            int a=i+1;
            System.out.print("|"+ a);
        }
        
        System.out.print("|");
        
        System.out.println();
        
        for(int i=0;i<(vertical*2)+6;i++){
            System.out.print("-");
        }
        
        System.out.println();
                    
        for(int i=0;i<vertical;i++){
            int a=i+1;
            System.out.print("|"+a+"| ");
            for(int j=0;j<diagonal;j++){
                System.out.print("|"+matriz[i][j]);
            }
            System.out.println("|");   
       }
        for(int i=0;i<(vertical*2)+6;i++){
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
}
