import java.util.Arrays;
import java.util.LinkedList;

public class Union {

    public static Interval [] union(Interval [] intervals) {
        if (intervals.length == 0){
            return intervals;
        }
        Arrays.sort(intervals);
        LinkedList<Interval> returned = new LinkedList<>();
        int min = intervals[0].min;
        int max = intervals[0].max;
        for (Interval interval:intervals) {
            if (max < interval.min){
                returned.add(new Interval(min,max));
                min = interval.min;
                max = interval.max;
            }
            else {
                if (interval.max > max){
                    max = interval.max;
                }
            }
        }
        returned.add(new Interval(min,max));
        return returned.toArray(new Interval[0]);
    }

    public static void main(String[] args) {
        Interval[] intervals = {new Interval(10,10),new Interval(2,4),new Interval(3,4),new Interval(5,6),new Interval(6,9),new Interval(6,8)};
        Interval[] union = union(intervals);
        System.out.println(Arrays.toString(union));
    }

}
