
public class Permutation {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Deque<Integer> intQ = new Deque<Integer>();
        for (int i = 0; i < 10; i ++) {
            intQ.addFirst(i);
        }
        
        for(int i = 0; i < 10; i ++) {
            intQ.removeFirst();
        }
            
    }

}
