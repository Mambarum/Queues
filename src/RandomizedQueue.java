import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private final Node first = new Node();
    private final Node last = new Node();
    private int sizeQ = 0;
    private int modNum = 0;
    
    private class Node {
        Node next;
        Node prev;
        Item data;
    }
    
    public RandomizedQueue() {
        first.next = last;
        first.prev = null;
        first.data = null;
        last.prev = first;
        last.next = null;
        last.data = null;
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
    
    private Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        
        Node tmp = first.next;
        delete(tmp);
        modNum++;
        return tmp.data;
    }
    
    private Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        
        Node tmp = last.prev;
        delete(tmp);
        modNum++;
        return tmp.data;
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
        
        modNum++;
        if (StdRandom.uniform(2) == 0)
           return removeFirst();
        else
            return removeLast();
    }
    
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        if (StdRandom.uniform(2) == 0)
            return last.prev.data;
         else
             return first.next.data;
    }
    
    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }
    
    private class RandomizedQueueIterator implements Iterator<Item> {
        private final int currModNum = modNum;
        private final Node iterFirst = new Node();
        private Node currNode = iterFirst;
        
        public RandomizedQueueIterator() {
            int[] order = new int[size()];
            for (int i = 0; i < order.length; i++)
                order[i] = i;
                    
            for (int i = 1; i < order.length; i++) {
                int swap = StdRandom.uniform(i + 1);
                int t = order[i];
                order[i] = order[swap];
                order[swap] = t;
            }

            Node iterTmp = iterFirst;
            for (int i = 0; i < order.length; i++) {
                Node tmp = first;
                for (int j = 0; j < order[i]; j++)
                    tmp = tmp.next;
                
                Node newNode = new Node();
                iterTmp.next = newNode;
                newNode.prev = iterTmp;
                newNode.data = tmp.next.data;
                iterTmp = newNode;
            }
        }

        @Override
        public boolean hasNext() {
            return !(currNode.next == null);
        }

        @Override
        public Item next() {
            if (currModNum != modNum)
                throw new ConcurrentModificationException("Dequeue was modified");

            if (hasNext()) {
                Item ret = currNode.next.data;
                currNode = currNode.next;
                return ret;
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
        
        for (int i = 0; i < 1000; i++) {
            intQ.enqueue(42);
            intQ.dequeue();
        }
        
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
