//feel free to import anything here

import java.util.Comparator;
import java.util.PriorityQueue;

public class MineClimbing {
    /**
     * Returns the minimum distance between (startX, startY) and (endX, endY), knowing that
     * you can climb from one mine block (a,b) to the other (c,d) with a cost Math.abs(map[a][b] - map[c][d])
     *
     * Do not forget that the world is round: the position (map.length,i) is the same as (0, i), etc.
     *
     * map is a matrix of size n times m, with n,m > 0.
     *
     * 0 <= startX, endX < n
     * 0 <= startY, endY < m
     */
    public static int best_distance(int[][] map, int startX, int startY, int endX, int endY) {
        //Parameter
        int N = map.length;
        int M = map[0].length;

        //Data structure
        int[] distTo = new int[N*M];
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(distTo[o1],distTo[o2]);
            }
        });

        //Init
        for (int i = 0; i < N * M; i++) {
            distTo[i]=Integer.MAX_VALUE;
        }
        int start = ind(startX, startY, M);
        distTo[start]= 0;
        pq.add(start);

        //MainLoop
        while (!pq.isEmpty()){
            int current= pq.poll();
            int x = row(current,M);
            int y = col(current,M);

            //Explore

            //Up
            int xUp = (x+N-1)%N;
            int indexUp = ind(xUp,y,M);
            if (distTo[indexUp]>distTo[current]+Math.abs(map[x][y]-map[xUp][y])){
                distTo[indexUp]=distTo[current]+Math.abs(map[x][y]-map[xUp][y]);
                pq.add(indexUp);
            }

            //Down
            int xDown = (x+N+1)%N;
            int indexDown = ind(xDown,y,M);
            if (distTo[indexDown]>distTo[current]+Math.abs(map[x][y]-map[xDown][y])){
                distTo[indexDown]=distTo[current]+Math.abs(map[x][y]-map[xDown][y]);
                pq.add(indexDown);
            }

            //Left
            int yLeft = (y+M-1)%M;
            int indexLeft = ind(x,yLeft,M);
            if (distTo[indexLeft]>distTo[current]+Math.abs(map[x][y]-map[x][yLeft])){
                distTo[indexLeft]=distTo[current]+Math.abs(map[x][y]-map[x][yLeft]);
                pq.add(indexLeft);
            }

            //Right
            int yRight = (y+M+1)%M;
            int indexRight = ind(x,yRight,M);
            if (distTo[indexRight]>distTo[current]+Math.abs(map[x][y]-map[x][yRight])){
                distTo[indexRight]=distTo[current]+Math.abs(map[x][y]-map[x][yRight]);
                pq.add(indexRight);
            }

        }

        return distTo[ind(endX,endY,M)];
    }

    public static void main(String[] args) {
        int[][] map = {
                {7,2,9,6},
                {8,7,6,0},
                {4,6,5,8}
        };

        int best = best_distance(map,0,1,2,3);
        System.out.println(best);
    }


    public static int ind(int x,int y, int lg) {return x*lg + y;}

    public static int row(int pos, int mCols) { return pos / mCols; }

    public static int col(int pos, int mCols) { return pos % mCols; }


    // you may need to add additional things below.

}