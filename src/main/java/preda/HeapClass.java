 
package preda;


/**
 * Data structure to sort the objects to be added to the backpack according
 * to the ratio of the perceived value against its weight.
 * 
 * Constructor method accepts an integer to create the array, this doesn't
 * need to be changed at runtime therefore the class is simplified in comparison
 * with a standard heap data structure.
 * 
 * Estructura de datos basada en monticulo,  almacena
 * los objetos segun el ratio valor/peso.
 * 
 * 
 */

public class HeapClass {
    
    
    NodeBackpack[] heapArray;
    int counter;
 
     
    
    public HeapClass(int capacity){
        
        heapArray = new NodeBackpack[capacity];
        
    }
    
    public void addNode(NodeBackpack newImport)
    {
        heapArray[counter] = newImport;
        counter++;
        if(counter > 1){
        sift_up(); //restores the heap, moving last added element up the chain.
        }
    }
    
    /*funcion flotar */
    private void sift_up(){
        NodeBackpack aux;
        int father;
        int selected;
        
        // counter is pointing to the next empty slot, previous position in the array
        // corresponds with latest node added, we find the father's node by 
        // substracting 1 to the selected node's array index and dividing by 2.
        selected = counter - 1;
        father = (selected-1)/2;
        
        
        // we make sure that we keep in between the array's boundaries, we compare
        // the ratio of both objects and we swap their places if needed. We 
        // continue comparing until we are sure the heap property has been restored.
        
        while ( father >= 0 && heapArray[selected].ratio() > heapArray[father].ratio()){
            aux = heapArray[father];
            heapArray[father] = heapArray[selected];
            heapArray[selected] = aux;
            
            selected = father;
            father = (selected - 1) / 2;
        }
        
    }
    
    /* funcion hundir */
    private void sift_down(){
        // checks the head of the heap, if the head is smaller than one of his
        // children it moves it down the structure
        NodeBackpack aux;
        int select;
        int leftChild;
        int rightChild;
        int swap; // we will determine which child to swap with this variable.
        
        // we start selecting the head of the heap and its children.
        select = 0;
        leftChild = (select*2)+1;
        rightChild = (select*2)+2;
        
        
        while ( heapArray[leftChild] != null){
            swap = leftChild;  
            
            // if there is a right child we need to check which one is bigger.
            if( heapArray[rightChild] != null){
                if(heapArray[rightChild].ratio() > heapArray[leftChild].ratio())
                {
                    swap = rightChild;  // if right child is bigger, we select it.
                } 
                
            } 
            
            // we have compared the childrens before, now we compare with the
            // selected node, if the child is bigger, we need to swap them.
            if(heapArray[swap].ratio() > heapArray[select].ratio()){
                aux = heapArray[swap];
                heapArray[swap] = heapArray[select];
                heapArray[select] = aux;
                
                
                // we continue checking for inconsistencies, therefore we need
                // to update our selections and check again in the loop.
                select = swap;
                leftChild = (select*2) + 1;
                rightChild = (select*2) + 2;
                
                
                
            }else{  //if the child is not bigger, the heap has been restored.
                break; //scapes the while loop.
            }
        }
        
    }
    
    public boolean is_empty(){
        return heapArray[0] == null;
    }
    
    //returns head of Heap and removes it
    public NodeBackpack popFirst(){
        if(counter == 0) throw new IllegalStateException("Heap is Empty"); 
        NodeBackpack aux = heapArray[0];
        heapArray[0] = heapArray[counter - 1];
        sift_down();
        counter--;
        return aux;
    
    }
    
       
}
