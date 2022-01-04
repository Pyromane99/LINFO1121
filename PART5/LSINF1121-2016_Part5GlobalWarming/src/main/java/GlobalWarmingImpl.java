import java.util.*;

   public class GlobalWarmingImpl extends GlobalWarming {

       public static class UF{

           public int[] id;
           private int count;

           public UF(int n) {
               id = new int[n];
               count = n;
               for (int i = 0; i < n; i++) {
                   id[i]=i;
               }
           }
           public void union(int p, int q){
               if (p==q){
                   return;
               }
               int a = find(p);
               int b = find(q);
               if (a!=b){
                   id[a]=b;
                   count--;
               }
           }

           public int find(int p){
               int identifier = id[p];
               if (identifier==p){
                   return p;
               }
               else{
                   return find(identifier);
               }
           }

           public boolean connected(int p, int q){
               return find(p)==find(q);
           }

           public int count(){
               return count;
           }
       }

       UF uf;
       int n;
       int water;

       public GlobalWarmingImpl(int[][] altitude, int waterLevel) {
           super(altitude,waterLevel);
           // expected pre-processing time in the constructror : O(n^2 log(n^2))
           n = altitude.length;
           water=-1;
           uf = new UF(n*n);
           for (int i = 0; i < n; i++) {
               for (int j = 0; j < n; j++) {
                   int center = i*n+j;
                   if (altitude[i][j]<=waterLevel){
                       if (water==-1) water = center;
                       else uf.union(center,water);
                   }
                   else{
                       if (i>0 && altitude[i-1][j]>waterLevel) {
                           uf.union(center, (i-1)*n+j);
                       }
                       if (i<n-1 && altitude[i+1][j]>waterLevel) {
                           uf.union(center, (i+1)*n+j);
                       }
                       if (j>0 && altitude[i][j-1]>waterLevel) {
                           uf.union(center, i*n+(j-1));
                       }
                       if (j<n-1 && altitude[i][j+1]>waterLevel) {
                           uf.union(center, i*n+(j+1));
                       }
                   }
               }
           }
       }

       public int nbIslands() {
           if (water == -1) return uf.count();
           else return uf.count() -1;
           // TODO
           // expected time complexity O(1)
       }


       public boolean onSameIsland(Point p1, Point p2) {
           int x1 = p1.x;
           int y1 = p1.y;
           int x2 = p2.x;
           int y2 = p2.y;
           if (altitude[x1][y1] <= waterLevel || altitude[x2][y2] <= waterLevel) return false;
           // TODO
           // expected time complexity O(1)
           return uf.connected(x1*n+y1,x2*n+y2);
       }

       public static void main(String[] args) {
           int [][] tab = new int[][] {{1,3,3,1,3},
                   {4,2,2,4,5},
                   {4,4,1,4,2},
                   {1,4,2,3,6},
                   {1,1,1,6,3}};
           GlobalWarming gw = new GlobalWarmingImpl(tab,3);
       }

   }