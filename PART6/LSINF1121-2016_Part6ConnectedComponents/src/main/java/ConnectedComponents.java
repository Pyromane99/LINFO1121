public class ConnectedComponents {
    private static boolean[] marked;
    private static int count;
    /**
     * @return the number of connected components in g
     */
    public static int numberOfConnectedComponents(Graph g) {
        count = 0;
        marked = new boolean[g.V()];
        for (int i = 0; i < g.V(); i++) {
            if (!marked[i]){
                dfs(g,i);
                count++;
            }
        }
        return count;
    }

    private static void dfs(Graph g,int s){
        if (!marked[s]){
            marked[s]=true;
            for (Integer adjToV:g.adj(s)) {
                dfs(g,adjToV);
            }
        }
    }
}