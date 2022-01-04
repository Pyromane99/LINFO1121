import java.util.*;

public class GlobalWarmingImpl extends GlobalWarming {
    public class Graph {

        private List<Integer>[] edges;

        public Graph(int E) {
            this.edges = (ArrayList<Integer>[]) new ArrayList[E];
            for (int i = 0; i < edges.length; i++) {
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

            return count / 2;
        }

        /**
         * Add edge v-w to this graph
         */
        public void addEdge(int v, int w) {
            edges[v].add(w);
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

    public class BreadthFirstShortestPaths {
        public static final int INFINITY = Integer.MAX_VALUE;
        private boolean[] marked; // marked[v] = is there an s-v path
        private int[] edgeTo;
        private int[] distTo;
        private final int i;
        int s;

        /**
         * Computes the shortest path between any
         * one of the sources and very other vertex
         * @param G the graph
         * @param source the source vertices
         */
        public BreadthFirstShortestPaths(Graph G, Integer source) {
            this.i = G.V();
            s = source;
            marked = new boolean[G.V()];
            edgeTo = new int[G.V()];
            distTo = new int[G.V()];
            for (int v = 0; v < G.V(); v++) {
                distTo[v] = INFINITY;
                edgeTo[v] = G.V();
            }
            bfs(G, source);
        }

        // Breadth-first search from multiple sources
        private void bfs(Graph G, Integer source) {
            Queue<Integer> queue = new LinkedList<>();
            queue.add(source);
            edgeTo[s]=s;
            marked[source]=true;
            while (!queue.isEmpty()){
                int v = queue.remove();
                for (Integer adjToV: G.adj(v)) {
                    if (!marked[adjToV]){
                        queue.add(adjToV);
                        marked[adjToV]=true;
                    }
                    if (distTo[adjToV] > distTo[v] + 1){
                        distTo[adjToV] = distTo[v]+1;
                        edgeTo[adjToV] = v;
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
            List<Integer> pathTo = new ArrayList<>();
            int current = v;
            while (current!=s){
                pathTo.add(current);
                current=edgeTo[current];
                if (current==i){
                    return null;
                }
            }
            pathTo.add(s);
            Collections.reverse(pathTo);
            return pathTo;
        }
    }

    Graph graph;
    int N;

    /**
     * In the following, we assume that the points are connected to
     * horizontal or vertical neighbors but not to the diagonal ones
     * @param altitude is a n x n matrix of int values representing altitudes (positive or negative)
     * @param waterLevel is the water level, every entry <= waterLevel is flooded
     */
    public GlobalWarmingImpl(int[][] altitude, int waterLevel) {
        super(altitude,waterLevel);
        N = altitude.length;
        graph = new Graph(N*N);

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (altitude[x][y]>waterLevel){
                    int currentIndex = convert(x,y);
                    if (x!=0 && altitude[x-1][y] > waterLevel){
                        graph.addEdge(currentIndex,convert(x-1,y));
                    }
                    if (x!=N-1 && altitude[x+1][y] > waterLevel){
                        graph.addEdge(currentIndex,convert(x+1,y));
                    }
                    if (y!=0 && altitude[x][y-1] > waterLevel){
                        graph.addEdge(currentIndex,convert(x,y-1));
                    }
                    if (y!=N-1 && altitude[x][y+1] > waterLevel){
                        graph.addEdge(currentIndex,convert(x,y+1));
                    }
                }
            }
        }

    }
    private int convert(int x,int y){
        return x*N + y;
    }

    private Point pointAt(int index){
        int x = index/N;
        int y = index%N;
        return new Point(x,y);
    }
    /**
     *
     * @param p1 a safe point with valid coordinates on altitude matrix
     * @param p2 a safe point (different from p1) with valid coordinates on altitude matrix
     * @return the shortest simple path (vertical/horizontal moves) if any between from p1 to p2 using only vertical/horizontal moves on safe points.
     *         an empty list if not path exists (i.e. p1 and p2 are not on the same island).
     */
    public List<Point> shortestPath(Point p1, Point p2) {
        BreadthFirstShortestPaths bfs = new BreadthFirstShortestPaths(graph,convert(p1.x,p1.y));
        List<Point> pointList = new ArrayList<>();
        if (altitude[p1.x][p1.y] <= waterLevel || altitude[p2.x][p2.y] <= waterLevel){
            return pointList;
        }
        if (p1.equals(p2)){
            pointList.add(p1);
            return pointList;
        }
        if (bfs.hasPathTo(convert(p2.x,p2.y))){
            for (Integer a: bfs.pathTo(convert(p2.x,p2.y))) {
                pointList.add(pointAt(a));
            }
        }

        return pointList;
    }

}