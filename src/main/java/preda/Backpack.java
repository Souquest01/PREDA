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
 */
public class Backpack {
    
    int maxWeight;
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
        double percent;
        
        objectWeight = toPocket.get_weight();
        
        aux = objectWeight + currentWeight; 
        
        if(aux <= maxWeight){
            pockets.add(toPocket);
            currentWeight += objectWeight;
        }else{
            aux = aux - maxWeight;
            objectWeight = objectWeight - aux;
            this.currentWeight += objectWeight;
            
            percent = objectWeight/ (toPocket.get_weight()/100);
            toPocket.change_percent((int) floor(percent)); // gets rid of the decimals
            pockets.add(toPocket);
        }
        
    }
    
    public boolean has_Next(){
        return list_iter.hasNext();
        
    }
    
    public void list_Objects(){
        System.out.println("list");
    }
    
}

 
