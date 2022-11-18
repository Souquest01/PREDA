 
package preda;


/**
 * Data structure to sort the objects to be added to the backpack according
 * to the ratio of the perceived value against its weight.
 * 
 * 
 * Estructura de datos basada en montículo,  almacena
 * los objetos segun el ratio valor/peso.
 * 
 * 
 */

public class HeapClass {
    
    
    NodeBackpack[] heapArray; // matriz de objetos, montículo
    int counter;              // contador
    boolean trace;            // muestra la traza del algoritmo
     
    
    public HeapClass(int capacity, boolean trace){
        
        heapArray = new NodeBackpack[capacity];
        this.trace = trace;
    }
    
    public void addNode(NodeBackpack newImport)
    {
        heapArray[counter] = newImport;
        if(trace){System.out.println("asigna nodo en posicion "+counter + "del array/monticulo");}
        counter++;
        if(trace){System.out.println("suma 1 al contador");}
        if(counter > 1){
            if(trace){System.out.println("Si el contador es > 1 llama a flotar()");}
            sift_up(); //restaura la propiedad de montículo, moviendo el elemento "hacia arriba"
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
        
        /*
        /* el contador (counter) esta apuntando al siguiente espacio vacío, la posición anterior
        /* a ésta es el último nodo añadido, encontramos al padre del nodo restando 1
        /* a su posición y dividiendo entre 2. 
        */
        selected = counter - 1;
        father = (selected-1)/2;
        if(trace){
            System.out.println("seleccionamos el contador("+counter+") y le restamos 1 para encontrar el ultimo elemento añadido, actualmente "+selected);
            System.out.println("Buscamos el padre restandole 1("+(selected-1)+") y dividiendo entre dos; padre = "+ father);
        }
        
        // we make sure that we keep in between the array's boundaries, we compare
        // the ratio of both objects and we swap their places if needed. We 
        // continue comparing until we are sure the heap property has been restored.
        
        
        /*
        /* father >= 0 nos asegura que nos mantenemos en el array, comparamos el ratio
        /* de ambos objetos y los intercambiamos si es necesario. Continuamos hasta
        /* asegurarnos de que la propiedad de montículo ha sido restaurada.
        */
        
        if(trace){
            System.out.println("nos aseguramos de que el index donde vamos a buscar al padre sea mayor que 0\n"
                    + "para no salir de la estructura.");
        }
        while ( father >= 0 && heapArray[selected].ratio() > heapArray[father].ratio()){
            if(trace){
                System.out.println("Si lo es, comparamos los ratios del padre y el hijo y cambiamos los lugares de ambos");
                System.out.println("hijo: "+ heapArray[selected].ratio() + " > padre :"+ heapArray[father].ratio());
                System.out.println("intercambiamos");
                System.out.println("seguimos buscando hacia arriba, seleccionando esta vez la posicion que correspondia al padre");
                System.out.println("y buscamos de nuevo el padre de este nodo");
            }
            aux = heapArray[father];
            heapArray[father] = heapArray[selected];
            heapArray[selected] = aux;
            
            selected = father;
            father = (selected - 1) / 2;
        }
        if(trace){System.out.println("hemos restaurado la propiedad");}
    }
    
    /* funcion hundir */
    private void sift_down(){
        // checks the head of the heap, if the head is smaller than one of his
        // children it moves it down the structure
        
        /*
        /* Comprobamos la cabeza del monticulo, si la cabeza es más pequeña
        /* que alguno de sus hijos la movemos hacia abajo en la estructura.
        */
        NodeBackpack aux;
        int select;
        int leftChild;
        int rightChild;
        int swap; // we will determine which child to swap with this variable.
        
        // we start selecting the head of the heap and its children.
        
        /*
        /* empezamos seleccionando la cabeza del montículo y sus hijos.
        */
        if(trace){System.out.println("Funcion hundir(), seleccionamos la cima del montículo y sus hijos");}
        select = 0;
        leftChild = (select*2)+1;
        rightChild = (select*2)+2;
        
       
        while ( heapArray[leftChild] != null){
             if(trace){System.out.println("Iteramos mientras haya hijo izquierdo");}
            swap = leftChild;  
            
            // if there is a right child we need to check which one is bigger.
            /*
            /* si hay un hijo en la derecha tenemos que comprobar cual es mayor.
            */
            
            if( heapArray[rightChild] != null){
                if(trace){System.out.println("En este caso hay hijo derecho, los comparamos");}
                
                 if(heapArray[rightChild].ratio() > heapArray[leftChild].ratio())
                 {
                 swap = rightChild;  // if right child is bigger, we select it.
         
                if(trace){System.out.println("hijo derecho ("+heapArray[rightChild].ratio()+") "
                + "> que hijo izquierdo (" +heapArray[leftChild].ratio()+"); hijo derecho es mayor");
                } 
                
                 } 
             
            // we have compared the childrens before, now we compare with the
            // selected node, if the child is bigger, we need to swap them.
            
            /*
            /* hemos comparado los hijos previamente, ahora comparamos el mayor de
            /* éstos con el nodo seleccionado, si el hijo es mayor, los intercambiamos.
            */
            if(heapArray[swap].ratio() > heapArray[select].ratio()){
                if(trace){System.out.println("Hemos comparados los hijos, uno de ellos es mayor que el padre y tenemos que corregir\n"
                        + "hijo ("+heapArray[swap].ratio()+") > padre ("+heapArray[select].ratio()+")");}
               
                aux = heapArray[swap];
                heapArray[swap] = heapArray[select];
                heapArray[select] = aux;
                
                
                // we continue checking for inconsistencies, therefore we need
                // to update our selections and check again in the loop.
                
                /*
                /* seguimos buscando por inconsistencias, actualizamos las 
                /* selecciones y comprobamos de nuevo en  el bucle.
                */
                
                if(trace){System.out.println("repetimos recorriendo la estructura");}
               
                select = swap;
                leftChild = (select*2) + 1;
                rightChild = (select*2) + 2;
                
                
                
            }else{  //if the child is not bigger, the heap has been restored.
                break; //scapes the while loop.
            }
        }
    }
 }

    
    
    
    public boolean is_empty(){
        return heapArray[0] == null;
    }
    
    //returns head of Heap and removes it
    /*
    /* devuelve la cima del monticulo y la elimina.
    */
    public NodeBackpack popFirst(){
        if(counter == 0) throw new IllegalStateException("Heap is Empty"); 
   
        
        NodeBackpack aux = heapArray[0];
        
        if(trace){System.out.println("Seleccionamos cima del monticulo");
            System.out.println("añadimos el nodo del final de la estructura a"
                    + "la cabeza de esta\n"
                    + "desreferenciamos dicha posicion, restamos uno al contador de"
                    + "elementos y finalmente retornamos la cabeza previamente seleccionada."
                    + "llamamos al a función hundir() ");
        }
        heapArray[0] = heapArray[counter - 1];
        heapArray[counter-1] = null;
        sift_down();
        counter--;
        return aux;
    
    }
    
       
}
