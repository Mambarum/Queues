import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private final Node first = new Node();
    private int sizeQ = 0;
    private int modNum = 0;
    
    private class Node {
        Node next;
        Node prev;
        Item data;
    }
    
    public RandomizedQueue() {
        first.next = null;
        first.prev = null;
        first.data = null;
    }
    
    private Node addAfter(Item item, Node node)
    {
        Node newNode = new Node();
        
        newNode.data = item;
        newNode.next = node.next;
        newNode.prev = node;
        
        if (node.next != null)
            node.next.prev = newNode;
        
        node.next = newNode;
        sizeQ++;
        return newNode;
    }
    
    private void delete(Node node)
    {
        if (node == null)
            throw new NoSuchElementException();
        
        if (node.prev != null)
            node.prev.next = node.next;
        
        if (node.next != null)
            node.next.prev = node.prev;
        
        sizeQ--;
    }
        
    public boolean isEmpty() {
        return (sizeQ == 0);
    }

    public int size() {
        return sizeQ;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Cannot add null element to the beginning of the Deque");
        
        addAfter(item, first);
        modNum++;
    }
    
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        
        int n = StdRandom.uniform(sizeQ);
        Node tmp = first;
        for (int i = 0; i <= n; i++) {
            tmp = tmp.next;
        }
        delete(tmp);
        modNum++;
        return tmp.data;
    }
    
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        
        int n = StdRandom.uniform(sizeQ);
        Node tmp = first;
        for (int i = 0; i <= n; i++)
            tmp = tmp.next;
        
        return tmp.data;
    }
    
    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }
    
    private class RandomizedQueueIterator implements Iterator<Item> {
        private final int currModNum = modNum;
        private Node currentNode = first;
        private Node[] nodes;
        
        @Override
        public boolean hasNext() {
            if (currentNode == null) {
                currentNode = first;
                return false;
            }
            
            if(currentNode.next == null)
                return false;
            
            return true;
        }

        @Override
        public Item next() {
            if (currModNum != modNum)
                throw new ConcurrentModificationException("Dequeue was modified");

            if (hasNext()) {
                currentNode = currentNode.next;
                return currentNode.data;
            }
            else
                throw new NoSuchElementException();
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }    
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> intQ = new RandomizedQueue<Integer>();
        System.out.println();
        for (int i = 0; i < 10; i++) {
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
