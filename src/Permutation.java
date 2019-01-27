import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        for (int i = 0; i < k; i++) {
            String str = StdIn.readString();
            q.enqueue(str);
        }
        
        while (!q.isEmpty() ) {
            System.out.println(q.dequeue());
        }
    }

}
