import java.util.*;

public class Maze {
    public static class Graph {

        private List<Integer>[] edges;

        public Graph(int E)
        {
            this.edges = (ArrayList<Integer>[]) new ArrayList[E];
            for (int i = 0;i < edges.length;i++)
            {
                edges[i] = new ArrayList<>();
            }
        }

        /**
         * @return the number of vertices
         */
        public int V() {
            return edges.length;
        }

        /**
         * @return the number of edges
         */
        public int E() {
            int count = 0;
            for (List<Integer> bag : edges) {
                count += bag.size();
            }

            return count/2;
        }

        /**
         * Add edge v-w to this graph
         */
        public void addEdge(int v, int w) {
            edges[v].add(w);
            edges[w].add(v);
        }

        /**
         * @return the vertices adjacent to v
         */
        public Iterable<Integer> adj(int v) {
            return edges[v];
        }

        /**
         * @return a string representation
         */
        public String toString() {
            return "Ne devrait pas être utilisé";
        }
    }

    public static class BreadthFirstShortestPaths {
        public static final int INFINITY = Integer.MAX_VALUE;
        private boolean[] marked; // marked[v] = is there an s-v path
        private int[] edgeTo;
        private int s;

        /**
         * Computes the shortest path between any
         * one of the sources and very other vertex
         * @param G the graph
         * @param source the source vertices
         */
        public BreadthFirstShortestPaths(Graph G, int source) {
            marked = new boolean[G.V()];
            edgeTo = new int[G.V()];
            for (int v = 0;v < G.V();v++) {
                edgeTo[v]=v;
            }
            bfs(G, source);
        }

        // Breadth-first search from multiple sources
        private void bfs(Graph G, int source) {
            Queue<Integer> queue = new LinkedList<>();
            queue.add(source);
            marked[source]=true;
            while (!queue.isEmpty()){
                int v = queue.remove();
                for (Integer adjToV: G.adj(v)) {
                    if (!marked[adjToV]){
                        queue.add(adjToV);
                        marked[adjToV]=true;
                        edgeTo[adjToV]=v;
                    }
                }
            }
        }

        /**
         * Is there a path between (at least one of) the sources and vertex v?
         * @param v the vertex
         * @return true if there is a path, and false otherwise
         */
        public boolean hasPathTo(int v) {
            return marked[v];
        }

        public Iterable<Integer> pathTo(int v) {
            ArrayList<Integer> iterable = new ArrayList<>();
            iterable.add(v);
            while (edgeTo[v]!=s){
                v = edgeTo[v];
                iterable.add(v);
            }
            iterable.add(s);
            Collections.reverse(iterable);
            return iterable;
        }


    }

    public static Iterable<Integer> shortestPath(int [][] maze,  int x1, int y1, int x2, int y2) {
        if (maze[x1][y1]==1 || maze[x2][y2]==1){
            return new LinkedList<>();
        }
        int N = maze.length;
        int M = maze[0].length;
        if (x1 ==x2 && y1 == x2){
            LinkedList<Integer> linkedList = new LinkedList<>();
            linkedList.add(ind(x1, y1,M));
            return linkedList;
        }
        Graph graph = new Graph(N*M);
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (maze[x][y]!=1){
                    int currentIndex = ind(x,y,M);
                    if (x!=0 && maze[x-1][y] == 0){
                        graph.addEdge(currentIndex,ind(x-1,y,M));
                    }
                    if (x!=N-1 && maze[x+1][y] == 0){
                        graph.addEdge(currentIndex,ind(x+1,y,M));
                    }
                    if (y!=0 && maze[x][y-1] == 0){
                        graph.addEdge(currentIndex,ind(x,y-1,M));
                    }
                    if (y!=M-1 && maze[x][y+1] == 0){
                        graph.addEdge(currentIndex,ind(x,y+1,M));
                    }
                }
            }
        }

        BreadthFirstShortestPaths bfs = new BreadthFirstShortestPaths(graph,ind(x1,y1,M));
        if (!bfs.hasPathTo(ind(x2,y2,M))){
            return new LinkedList<>();
        }
        System.out.println(bfs.pathTo(ind(x2,y2,M)));
        return bfs.pathTo(ind(x2,y2,M));

    }

    public static int ind(int x,int y, int lg) {return x*lg + y;}

    public static int row(int pos, int mCols) { return pos / mCols; }

    public static int col(int pos, int mCols) { return pos % mCols; }
}