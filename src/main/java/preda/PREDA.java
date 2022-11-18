/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package preda;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FranksLP
 * Main class from the program.
 * 
 * Clase principal del programa.
 * 
 * Sintax:
 * java mochila-voraz [-t] [-h] [fichero entrada] [fichero salida]
 * O
 * java -jar mochila-voraz.jar [-t] [-h] [fichero entrada] [fichero salida]
 */
public class PREDA {


    public static void main(String[] args) {
       boolean trace, help, entrada, salida, malfunction;
       String entradaInfo = null, salidaInfo;
       Backpack mochila ;
       
       trace = false;
       help = false;
       entrada = false;
       salida = false;
       malfunction = false;
       
        
       
       
        if(args.length == 0){
            System.out.println("Faltan parametros, activado modo manual");
            mochila = modoManual();
        
        }else if(args.length <= 4){
            for(String x: args){
                if(x.equals("-h") && help == false){
                    help = true;
                    printHelp();
                
                }else if(x.equals("-t") && trace == false){
                    trace = true;
                    System.out.println("Activada traza");
                    
                }else if(entrada == false && !x.startsWith("-")){
                    entradaInfo = x;
                    entrada = true;
                    
                }else if(entrada == true && salida == false && !x.startsWith("-") ){
                    salidaInfo = x;
                    salida = true;
                    
                }else{
                    System.out.println("ERROR EN PARAMETRO, REINICIALICE");
                    malfunction = true;
                    throw new Error("Error en parametros, reinicialice");
                    
                }
            
            
            }
        
            if(!malfunction){
                
                try {
                    mochila = modoEntrada(entradaInfo);
                } catch (IOException ex) {
                    Logger.getLogger(PREDA.class.getName()).log(Level.SEVERE, "82 Line", ex);
                }
            }
            
        
        }else{
            System.out.println("Error en parametros");
        
        }
        
        if(!malfunction){
            if(salida == false){
                salidaInfo = "archivoSalida";                
            }
        }
        
   
    }
        
        
        
    public static void printResult(Backpack mochila, String salida) throws FileNotFoundException{
        
        DecimalFormat df = new DecimalFormat("0.0");
        PrintStream ps = new PrintStream(new File(salida +".txt"));
        NodeBackpack aux;
        mochila.reset();
        while(mochila.has_Next()){
            aux = mochila.get_Next();
            ps.print(aux.toString());
        }
        ps.print(df.format(mochila.get_fullValue()));
    }
        
        
    
    
    public static Backpack modoManual(){
       BufferedReader bf;
       boolean  malfunction;
       LinkedList<NodeBackpack> listaNodos;
       NodeBackpack nodo;
       int auxiliar = 0;
       int peso = 0;
       int valor = 0;
       Backpack mochila = null;
       
       do{
        listaNodos = new LinkedList<>();   
              try{
                
                bf = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Numero de objetos disponibles:\n");
                auxiliar = Integer.parseInt(bf.readLine());
                
                
                  for (int i = 0; i < auxiliar; i++) {
                      System.out.println("peso objeto " + (i+1) + ":   ");
                      peso = Integer.parseInt(bf.readLine());
                      System.out.println("valor objeto " + (i+1) + ":  ");
                      valor = Integer.parseInt(bf.readLine());
                      nodo = new NodeBackpack(peso, valor);
                      listaNodos.add(nodo);
                  }
               
                  System.out.println("Capacidad maxima de la mochila: ");
                  peso = Integer.parseInt(bf.readLine());
                  mochila = procesaMain(listaNodos, peso, auxiliar);
                malfunction = false;
              }catch(IOException ex){
                System.out.println("Error de tipo, repita");
                malfunction = true;
            }catch(NumberFormatException x){
                  System.out.println("error de tipo, repita");
                  malfunction = true;
            }
        }while(malfunction);
        return mochila;
}

    
    public static Backpack modoEntrada(String entrada) throws FileNotFoundException, IOException{
        
        BufferedReader br; 
        LinkedList<NodeBackpack> listaNodos = new LinkedList<>();
        NodeBackpack auxNodo;
        Backpack mochila;
        String lineaRaw;
        String[] lineaProcesada;
        boolean malfunction = false;
        int numObjects, peso, value, backpackSize;
        
        
        br = new BufferedReader(new FileReader(entrada)); 
        lineaRaw = br.readLine();
        lineaProcesada = lineaRaw.split("\\s+");
        if(lineaProcesada.length != 1){
            malfunction = true;
        }
        numObjects = Integer.parseInt(lineaProcesada[0]);
        
        for (int i = 0; i < numObjects; i++) {
            lineaRaw = br.readLine();
            lineaProcesada = lineaRaw.split("\\s+");
            if(lineaProcesada.length != 2){
                malfunction = true;
            }
            peso = Integer.parseInt(lineaProcesada[0]);
            value = Integer.parseInt(lineaProcesada[1]);
            auxNodo = new NodeBackpack(peso, value);
            listaNodos.add(auxNodo);
            
        }
        lineaRaw = br.readLine();
        lineaProcesada = lineaRaw.split("\\s+");
        if(lineaProcesada.length != 1){
            malfunction = true;
        }
        backpackSize = Integer.parseInt(lineaProcesada[0]);
        
        if(!malfunction){
        mochila = procesaMain(listaNodos, backpackSize, numObjects);
        }else{
            throw new IOException("Wrong format in input file");
                    }
        return mochila;
    }
    
    
public static void procesarLista(LinkedList<NodeBackpack> listaNodos, HeapClass modificar){
    for(NodeBackpack nodo: listaNodos){
        modificar.addNode(nodo);
    }
}

public static Backpack procesaMain(LinkedList<NodeBackpack> listaNodos, int backpackSize, int numObjects){
    Backpack mochila = new Backpack(backpackSize);
    HeapClass monticulo = new HeapClass(numObjects);
    
    
    for(NodeBackpack x: listaNodos){
        monticulo.addNode(x);
    }
    
    while(!mochila.isFull()){
        mochila.addElement(monticulo.popFirst());
    }
    return mochila;
}
public static void printHelp(){
System.out.println("SINTAXIS: mochila-voraz [-t] [-h] "
                            + "[fichero entrada] [fichero salida]");
                    System.out.println("-t                Traza el algoritmo");
                    System.out.println("-h                Muestra esta ayuda");
                    System.out.println("[fichero entrada] Nombre del fichero de entrada");
                    System.out.println("[fichero salida]  Nombre del fichero de salida");
}
}