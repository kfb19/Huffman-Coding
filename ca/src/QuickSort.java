import java.util.ArrayList;

/**
* The QuickSort class contain the algorithm for implementing a QuickSort on the given ArrayList.  
* @author Kate Belson
*/
public class QuickSort {
    
    /**
    * Used to split and sort the ArrayList from smallest to largest values frequencies of the nodes. 
    * @author Kate Belson
    * @param tree is the ArrayList of nodes. 
    * @param start is the start of the ArrayList. 
    * @param end is the end of the ArrayList. 
    * @return the new start value of the ArrayList. 
	*/
    public static int partition(ArrayList<Node> tree, int start, int end){ 
        Node pivot = tree.get(end);
 
        for(int i=start; i<end; i++){
            if(tree.get(i).getFrequency()<pivot.getFrequency()){
                Node temp = tree.get(start);
                tree.set(start, tree.get(i));
                Node n1 = temp;
                tree.set(i, n1);
                start = start + 1;
            }
        }
 
        Node temp = tree.get(start);
        Node n2 = tree.get(start);
        Node n3 = tree.get(end);
        n2 = pivot;
        tree.set(start, n2);
        n3 = temp;
        tree.set(end, n3);
 
        return start;
    }

    /**
    * Implements a QuickSOrt algorithm. 
    * @author Kate Belson
    * @param tree is the ArrayList of nodes. 
    * @param start is the start of the ArrayList. 
    * @param end is the end of the ArrayList. 
    * @return the sorted ArrayList tree. 
	*/
    public static ArrayList<Node> quickSort(ArrayList<Node> tree, int start, int end) { 
        int partition = partition(tree, start, end);
 
        if(partition-1>start) {
            quickSort(tree, start, (partition - 1));
        }
        if(partition+1<end) {
            quickSort(tree, (partition + 1), end);
        }

        return tree;
    }
}
