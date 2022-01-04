import java.util.Arrays;
import java.util.Random;

public class MinMaxHeap<Key extends Comparable<Key>> {

    // ---------------------------------------------------------------------------------------
    // Instance variables
    // ---------------------------------------------------------------------------------------

    // You are not allowed to add/delete variables, nor modifying their names or visibility.
    public Key[] pq;          // contains the elements starting at position 1
    public int N = 0;         // number of elements in the heap
    public int height = 0;    // should help you to know if you are at a level min or max

    // ---------------------------------------------------------------------------------------------------
    // Functions that you should implement. This is the only part of this class that you should modify ;-)
    // ---------------------------------------------------------------------------------------------------

    /**
     * @pre size() >= 1
     */
    public Key min() {
        return pq[1];
    }

    /**
     * @pre size() >= 1
     */
    public Key max() {
        if (size() == 1)return pq[1];
        else if (size() == 2) return pq[2];
        else if (less(3,2)) return pq[2];
        else return pq[3];
    }

    /**
     * @pre 1 <= k <= size()
     */
    private void swim(int k) {
        if (height%2 ==0) swimMax(k);
        else swimMin(k);
    }
    private void swimMin(int k){
        if (k == 1){
            return;
        }
        if (less(k/2,k)){
            exch(k/2,k);
            swimMax(k/2);
            return;
        }
        if (less(k,k/4)){
            exch(k/4,k);
            swimMin(k/4);
        }
    }

    private void swimMax(int k){
        if (less(k,k/2)){
            exch(k,k/2);
            swimMin(k/2);
            return;
        }
        if (pq[k/4] != null && less(k/4,k)){
            exch(k/4,k);
            swimMax(k/4);
        }

    }

    // ---------------------------------------------------------------------------------------
    // Constructor and helpers. You should not modify this. However, using them may be useful.
    // ---------------------------------------------------------------------------------------

    public MinMaxHeap(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    /**
     * @return pq[i] < pq[j]
     */
    public boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    /**
     * Exchanges positions i and j in pq
     */
    private void exch(int i, int j) {
        Key e = pq[i];
        pq[i] = pq[j];
        pq[j] = e;
    }

    /**
     * @return true if the heap is empty, false else
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * @return the size of the heap
     */
    public int size() {
        return N;
    }

    /**
     * @param v value to insert in the heap. v != null.
     */
    public void insert(Key v) {
        pq[++N] = v;
        if (N >= (1 << height)) height++;
        swim(N);
    }

    @Override
    public String toString() {
        return Arrays.toString(pq);
    }
}