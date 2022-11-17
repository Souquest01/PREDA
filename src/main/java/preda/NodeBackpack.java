/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package preda;

/**
 * Node class, hold the information of the objects to be
 * stored in the backpack, weight as per the total value of the object,
 * value, as per the perceived value of the object and ratio, which is
 * the ratio weight/value to assign a priority while comparing objects.
 * 
 */
public class NodeBackpack {
    
    
    private double weight;
    private double value;
    private double ratio;
    private double percent;
    
public NodeBackpack(int weight, int value){
    this.weight = weight;
    this.value = value;
    ratio = value/weight;
    percent = 100;
}
    public double ratio(){
        return ratio;
    }
    
    public double get_percent(){
        return percent;
    }
    
    public double get_weight(){
        return this.weight;
    }
    
    public void change_percent(int percent){
        if(percent > 100 || percent < 0 ){
            System.out.println("ERROR: INPUT CHANGE_PERCENT() ");
        }else{
            this.percent = percent;
        }
        
    }
    
}

