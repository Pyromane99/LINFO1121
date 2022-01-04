import java.util.Arrays;

public class Median {
	
    public static int median(Vector a, int lo, int hi) {
    	int k = a.size()/2;
        int i = 0;
        while (true){
            i = partition(a,lo,hi);
            if (i < k){
                lo = i+1;
            }
            else if(i > k){
                hi = i-1;
            }
            else {
                return a.get(i);
            }
        }
    }
    private static int partition(Vector a, int lo, int hi){
        int i = lo+1;
        int j = hi;
        int p = lo;

        while (i < j){
            if (a.get(i) >= a.get(p) && a.get(j) <= a.get(p)){
                a.swap(i,j);
                i++;
                j--;
            }
            else if (a.get(i) < a.get(p)){
                i++;
            }
            else if(a.get(j) > a.get(p)){
                j--;
            }
        }
        a.swap(p,j);
        return j;
    }

    public static void main(String[] args) {
        int[] test = new int[]{13,6,1,11,14,18,4,3,20,8,8,16,17,20,11,75,96,24,1,5,68,7,3,5,7,32,47,35,6,7,1,9,6,5,4,8,99};
        Vector a = new Vector(test);
        System.out.println(median(a,0,a.size()-1));
        Arrays.sort(test);
        System.out.println(Arrays.toString(test));
        System.out.println(test[test.length/2]);

    }

}
