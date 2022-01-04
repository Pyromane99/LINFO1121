import java.util.Arrays;

public class Vector {

    private int [] array;
    private int nOp = 0;

    Vector(int n) {
        array = new int[n];
    }

    public Vector(int[] array) {
        this.array = array;
    }

    public int size() {
        return array.length;
    }

    public void set(int i, int v) {
        nOp++;
        array[i] = v;
    }

    public int get(int i) {
        nOp++;
        return array[i];
    }

    public void swap(int i, int j) {
        nOp++;
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    public int nOp() {
        return nOp;
    }

}