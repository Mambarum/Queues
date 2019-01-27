import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        for (int i = 0; i < k; i++)
            q.enqueue(StdIn.readString());
        
        while(!StdIn.isEmpty())
            StdIn.readString();
        
        while (!q.isEmpty() ) {
            System.out.println(q.dequeue());
        }
    }

}
