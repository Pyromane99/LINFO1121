

import java.util.*;

public  class WordTransformationSP {
    /**
     *
     * Rotate the substring between start and end of a given string s
     * eg. s = HAMBURGER, rotation(s,4,8) = HAMBEGRUR i.e. HAMB + EGRU + R
     * @param s
     * @param start
     * @param end
     * @return rotated string
     */
    public static String rotation(String s, int start, int end) {
        return s.substring(0,start)+new StringBuilder(s.substring(start,end)).reverse().toString()+s.substring(end);
    }

    /**
     * Compute the minimal cost from string "from" to string "to" representing the shortest path
     * @param from
     * @param to
     * @return
     */
    public static int minimalCost(String from, String to) {
        HashMap<String,Integer> distTo = new HashMap<>();
        PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(distTo.get(o1),distTo.get(o2));
            }
        });
        int wordLength = from.length();
        distTo.put(from,0);
        pq.add(from);
        while (!pq.isEmpty()){
            String current = pq.poll();
            for (int i = 0; i < wordLength; i++) {
                for (int j = i+2; j <= wordLength; j++) {
                    String newWord = rotation(current,i,j);
                    if (distTo.containsKey(newWord)){
                        if (distTo.get(newWord) > distTo.get(current)+(j-i)){
                            distTo.put(newWord,distTo.get(current)+(j-i));
                        }
                    }
                    else{
                        distTo.put(newWord,distTo.get(current)+(j-i));
                        pq.add(newWord);
                    }
                }
            }
        }

        return distTo.get(to);
    }
}