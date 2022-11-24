/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package preda;

import static java.lang.Math.floor;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.ListIterator;

 

/**
 *
 * @author FranksLP
 * 
 * Class Backpack is an abstraction of an object with the same name,
 * the constructor uses an int as parameter to use as maximum weight.
 * If the object being added is heavier than the weight it can hold it
 * takes a fraction of it, it changes the status of the object with the
 * appropriate method of the class NodeBackpack.
 * 
 * 
 * La clase Backpack (mochila) es una abtracción del objecto homónimo,
 * el constructor necesita un int como parámetro para determinar la capacidad
 * máxima. 
 * 
 * 4 atributos contenidos en la clase, el peso actual en formato double ya que 
 * los objetos pueden pesar en decimal, un valor para el tamaño máximo en formato
 * int ya que el tamaño será siempre entero, una lista de objetos la cual será
 * la mochila en sí y un iterador para poder recorrer la lista.
 * 
 * Los metodos y funciones de la clase se corresponden con los esenciales para
 * manejar la lista interna con especial mención del "añadir objeto" (addElement);
 * 
 * Si el peso del objeto es mayor que la capacidad el método fracciona
 * el objecto conceptualmente y actualiza la información con el método apropiado
 * de la clase NodeBackpack.
 * 
 */
public class Backpack {
    
    int maxWeight;
    double currentValue;
    double currentWeight;
    LinkedList<NodeBackpack> pockets; 
    ListIterator list_iter;
    boolean trace;
    
    public Backpack(int weight, boolean trace){
        this.maxWeight = weight;
        this.currentWeight = 0;
        pockets = new LinkedList<>();
        list_iter = pockets.listIterator();
        this.trace = trace;
    
    }
    
    public void addElement(NodeBackpack toPocket){
        double aux;
        double objectWeight;
        double objectValue;
        double percent;
        DecimalFormat df = new DecimalFormat("0.0");
        DecimalFormat ds = new DecimalFormat("0.00");
        
        if(trace){
            System.out.println("");
            System.out.println("Metodo añadir de clase Backpack");
            System.out.println("");
        }
        
        objectWeight = toPocket.get_weight();
        objectValue = toPocket.get_value();
        if(trace){System.out.println("peso Objeto: "+objectWeight+" "
                + "valor objeto: "+objectValue);}
        aux = objectWeight + currentWeight; 
        
        if(trace) System.out.println("Sumamos el peso del objeto al peso actual de la mochila");
        
        
        if(maxWeight == currentWeight){
          if(trace)  System.out.println("mochila llena");
        }else
        if(aux <= maxWeight){
            
            if(trace)System.out.println("si el peso combinado no sobrepasa el limite, añadimos");
            
            pockets.add(toPocket);
            currentWeight += objectWeight;
            currentValue += objectValue;
            
            if(trace) {System.out.println("Y actualizamos." );
            System.out.println("");
            System.out.println("peso mochila actual: "+currentWeight+" Valor actual: "+ currentValue);
            }}else{
            if(trace) System.out.println("En esta ocasion el peso combinado sobrepasaria el límite, debemos fraccionar el objeto");
            if(trace) System.out.println("Peso actual + objeto: "+aux);
            aux = aux - maxWeight;
            if(trace) System.out.println("Le restamos el maximo ("+maxWeight+") = "+objectWeight);
            
            objectWeight = objectWeight - aux;
            if(trace)System.out.println("el restante se lo restamos al objeto del total, fraccionandolo.");
            if(trace) System.out.println("Peso del objeto fraccionado: "+df.format(objectWeight));
            this.currentWeight += objectWeight;
            
            
            percent = objectWeight/ toPocket.get_weight();
            if(trace)System.out.println("Peso fraccion: "+ objectWeight +" / Peso Total: "+toPocket.get_weight());
            if(trace)System.out.println("sacamos el porcentaje, en este caso: "+ ds.format(percent));
            toPocket.change_percent(percent); 
            this.currentValue = (currentValue + toPocket.get_value());
            pockets.add(toPocket);
        }
        
    }
    
    public boolean has_Next(){
        return list_iter.hasNext();
        
    }
    
    public void reset(){
        list_iter = pockets.listIterator();
    }
    
    public NodeBackpack get_Next(){
        return (NodeBackpack)list_iter.next();
    }
    public void list_Objects(){
        System.out.println("list");
    }
    
    public double get_fullValue(){
        return this.currentValue;
    }
    
    public boolean isFull(){
        return maxWeight == currentWeight;
    }
}

 
