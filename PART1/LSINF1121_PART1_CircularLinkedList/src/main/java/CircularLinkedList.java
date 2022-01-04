
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularLinkedList<Item> implements Iterable<Item> {
    private long nOp = 0; // count the number of operations
    private int n;          // size of the stack
    private Node  last;   // trailer of the list

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
    }

    public CircularLinkedList() {
        last = null;
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private long nOp() {
        return nOp;
    }



    /**
     * Append an item at the end of the list
     * @param item the item to append
     */
    public void enqueue(Item item) {
        Node toAdd = new Node();
        toAdd.item = item;
        if (size()==0){
            toAdd.next=toAdd;
            last = toAdd;
        }
        else {
            toAdd.next = last.next;
            last.next = toAdd;
            last = toAdd;
        }
        n++;
        nOp++;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * Returns the element that was removed from the list.
     */
    public Item remove(int index) {
        if (index<0 || index>size()-1){
            throw new IndexOutOfBoundsException();
        }
        Node temp = last;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        Item returned = temp.next.item;
        temp.next = temp.next.next;
        n--;
        nOp++;
        return returned;
    }


    /**
     * Returns an iterator that iterates through the items in FIFO order.
     * @return an iterator that iterates through the items in FIFO order.
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {

        long initialOperationNumber;
        Node iterate;
        final int size;
        int counter;

        public ListIterator() {
            if (isEmpty()){
                iterate = null;
            }
            else{
                iterate = last.next;
            }
            initialOperationNumber = nOp();
            size = size();
            counter = 0;
        }

        @Override
        public boolean hasNext() {
            if (initialOperationNumber != nOp()){
                throw new ConcurrentModificationException();
            }
            return counter < size;
        }

        @Override
        public Item next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            Item returned = iterate.item;
            iterate = iterate.next;
            counter++;
            return returned;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}