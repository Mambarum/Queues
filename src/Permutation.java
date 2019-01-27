import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class Permutation {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        RandomizedQueue<Integer> intQ = new RandomizedQueue<Integer>();
        System.out.println();
        for (int i = 0; i < 10; i ++) {
            intQ.enqueue(i);
            System.out.print(i + " ");
        }
        System.out.println();
        
        Iterator<Integer> iter = intQ.iterator();
        while (iter.hasNext())
            System.out.print(iter.next() + " ");
        System.out.println();
        
        while (!intQ.isEmpty()) {
            System.out.print(intQ.dequeue() + " ");
        }
        System.out.println();
        
           
    }

}
