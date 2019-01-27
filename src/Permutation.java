import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> q = new RandomizedQueue<String>();

        if (k == 0) {
            while (!StdIn.isEmpty())
                StdIn.readString();
            return;
        }
        
        while (!StdIn.isEmpty())
            q.enqueue(StdIn.readString());
        
        Iterator<String> iter = q.iterator();
        for (int i = 0; i < k; i++) {
            System.out.println(iter.next());
        }
    }

}
