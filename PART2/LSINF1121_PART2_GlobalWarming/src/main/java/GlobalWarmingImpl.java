

import java.util.*;

public class GlobalWarmingImpl extends GlobalWarming {

    private final int[] list;
    public GlobalWarmingImpl(int[][] altitude) {
        super(altitude);
        // expected pre-processing time in the constructror : O(n^2 log(n^2))
        list = new int[altitude.length*altitude.length];
        int i = 0;
        for (int[] line:altitude) {
            for (int element:line) {
                list[i] = element;
                i++;
            }
        }
        Arrays.sort(list);
    }


    public int nbSafePoints(int waterLevel) {
        if (waterLevel < list[0]){
            return list.length;
        }
        if (waterLevel >=list[list.length-1]){
            return 0;
        }
        boolean found = false;
        int lo = 0;
        int hi = list.length-1;
        int mid = lo + (hi-lo)/2;
        while (!found){
            if (lo ==hi){
                found = true;
                mid = lo;
            }
            if (lo == hi-1){
                found = true;
                mid = lo;
            }
            mid = lo + (hi-lo)/2;
            if (list[mid]==waterLevel){
                found = true;
            }
            else if (list[mid]>waterLevel){
                hi = mid;
            }
            else{
                lo = mid;
            }
        }

        while (list[mid] <= waterLevel){
            mid ++;
        }

        return list.length-mid;
        // expected time complexity O(log(n^2))
    }

}