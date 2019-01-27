import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private final Node first = new Node();
    private final Node last = new Node();
    private int sizeQ = 0;
    private int modNum = 0;
    
    private class Node {
        Node next;
        Node prev;
        Item data;
    }
    
    public Deque()
    {
        first.next = last;
        first.prev = null;
        first.data = null;
        last.prev = first;
        last.next = null;
        last.data = null;
    }
    
    private Node addBefore(Item item, Node node)
    {
        Node newNode = new Node();
        
        newNode.data = item;
        newNode.prev = node.prev;
        newNode.next = node;
        
        if (node.prev != null)
            node.prev.next = newNode;
        
        node.prev = newNode;
        sizeQ++;
        return newNode;
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
    
    private void delete(Node node) {
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
    
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Cannot add null element to the beginning of the Deque");
        
        addAfter(item, first);
        modNum++;
    }
    
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Cannot add null element to the end of the Deque");

        addBefore(item, last);
        modNum++;
    }
    
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        
        Node tmp = first.next;
        delete(tmp);
        modNum++;
        return tmp.data;
    }
    
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        
        Node tmp = last.prev;
        delete(tmp);
        modNum++;
        return tmp.data;
    }
        
    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
    
    private class DequeIterator implements Iterator<Item> {

        private Node currentNode = first;
        private final int currModNum = modNum;
        
        @Override
        public boolean hasNext() {
            if (currentNode == null) {
                currentNode = first;
                return false;
            }
            
            if (currentNode.next == last)
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

}
