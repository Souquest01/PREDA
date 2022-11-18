/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package preda;

import static java.lang.Math.floor;
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
    
    
    public Backpack(int weight){
        this.maxWeight = weight;
        this.currentWeight = 0;
        pockets = new LinkedList<>();
        list_iter = pockets.listIterator();
    
    }
    
    public void addElement(NodeBackpack toPocket){
        double aux;
        double objectWeight;
        double objectValue;
        double percent;
        
        objectWeight = toPocket.get_weight();
        objectValue = toPocket.get_value();
        
        aux = objectWeight + currentWeight; 
        
        
        if(maxWeight == currentWeight){
            System.out.println("mochila llena");
        }else
        if(aux <= maxWeight){
            pockets.add(toPocket);
            currentWeight += objectWeight;
            currentValue += objectValue;
        }else{
            aux = aux - maxWeight;
            objectWeight = objectWeight - aux;
            this.currentWeight += objectWeight;
            
            percent = objectWeight/ (toPocket.get_weight()/100);
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

 
