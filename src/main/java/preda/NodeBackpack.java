/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package preda;

import java.text.DecimalFormat;

/**
 * Node class, hold the information of the objects to be
 * stored in the backpack, weight as per the total value of the object,
 * value, as per the perceived value of the object and ratio, which is
 * the ratio weight/value to assign a priority while comparing objects.
 * 
 * 
 * Clase nodo, almacena la información del objeto a ser añadido en la
 * clase mochila, weight determina el peso total del objeto, value el
 * valor percibido del objeto y ratio el ratio entre peso y valor, será
 * la base para elegir la prioridad mientras se comparan objetos.
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
    
    public double get_value(){
        return this.value;
    }
    public void change_percent(double percent){
        if(percent > 100 || percent < 0 ){
            System.out.println("ERROR: INPUT CHANGE_PERCENT() ");
        }else{
            this.percent = percent;            
            this.value = ((this.value * percent)/100);
        }
        
    }
    
    @Override
    public String toString(){
        DecimalFormat df = new DecimalFormat("0.00");
        DecimalFormat ds = new DecimalFormat("0.0");
        String x ;
        
        if(percent<100){
        x  = (df.format(this.weight) + " " + ds.format(this.percent) + " " + ds.format(this.value)+"\n");    
        }else{
        x = ((int)this.weight + " " + (int)this.percent+ " " + ds.format(this.value));
        }
        return x ;
    }

}
 
