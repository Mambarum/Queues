import java.util.Iterator;

public class Deque<Item> implements Iterable<Item>{

    private Node first = new Node();
    private Node last = new Node();
    
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
        return newNode;
    }
    
    private void delete(Node node)
    {
        if (node == null)
            return;
        
        if (node.prev != null)
            node.prev.next = node.next;
        
        if (node.next != null)
            node.next.prev = node.prev;
        
        node = null;
    }
    
    public void addFirst(Item item)
    {
        addAfter(item, first);
    }
    
    public void addLast(Item item)
    {
        addBefore(item, last);
    }
    
    public Item removeFirst()
    {
        delete(first.next);
        if (first.next != null)
            return first.next.data;
        else 
            return null;
    }
    
    public Item removeLast()
    {
        delete(last.prev);
        if (last.prev != null)
            return last.prev.data;
        else
            return null;
    }
        
    @Override
    public Iterator<Item> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

}
